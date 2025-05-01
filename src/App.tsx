import React, { useState, useEffect, useRef } from "react";
import { Bar, Line } from "react-chartjs-2";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  LineElement, // Register LineElement
  PointElement, // Register PointElement
  Title,
  Tooltip,
  Legend,
} from "chart.js";
import RankingCursosPage from "./components/RankingCursosPage";
import TotalAlunosPorAnoPage from "./components/TotalAlunosPorAnoPage";
import ImportCsvPage from "./components/ImportCsvPage";
import { buscarRankingCursos, buscarTotalAlunosPorAno } from "./services/consultasService";

// Register Chart.js components
ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  LineElement, // Register LineElement
  PointElement, // Register PointElement
  Title,
  Tooltip,
  Legend
);

const App: React.FC = () => {
  const [currentPage, setCurrentPage] = useState<"menu" | "ranking" | "totalAlunos" | "importCsv">("menu");
  const [isSidebarOpen, setIsSidebarOpen] = useState<boolean>(false);
  const sidebarRef = useRef<HTMLDivElement>(null);

  const [rankingData, setRankingData] = useState<any>(null);
  const [totalAlunosData, setTotalAlunosData] = useState<any>(null);

  // Fetch data for the charts
  useEffect(() => {
    const fetchData = async () => {
      try {
        // Fetch Ranking de Cursos data
        const ranking = await buscarRankingCursos();
        const rankingLabels = ranking.map((curso: any) => curso.desCurso);
        const rankingValues = ranking.map((curso: any) => curso.nmrMatriculados);

        setRankingData({
          labels: rankingLabels,
          datasets: [
            {
              label: "Número de Matrículas",
              data: rankingValues,
              backgroundColor: "rgba(54, 162, 235, 0.6)",
              borderColor: "rgba(54, 162, 235, 1)",
              borderWidth: 1,
            },
          ],
        });

        // Fetch Total de Alunos por Ano data
        const anos = [2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022];
        const resultados = await Promise.all(
          anos.map((ano) => buscarTotalAlunosPorAno(ano))
        );

        const dadosFormatados = resultados.map((resultado, index) => ({
          numAno: anos[index],
          nmrTotalAlunos: Number(resultado),
        }));

        const totalAlunosLabels = dadosFormatados.map((item) => item.numAno);
        const totalAlunosValues = dadosFormatados.map((item) => item.nmrTotalAlunos);

        setTotalAlunosData({
          labels: totalAlunosLabels,
          datasets: [
            {
              label: "Total de Alunos",
              data: totalAlunosValues,
              backgroundColor: "rgba(255, 99, 132, 0.6)",
              borderColor: "rgba(255, 99, 132, 1)",
              borderWidth: 1,
            },
          ],
        });
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, []);

  // Close the sidebar when clicking outside of it
  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (sidebarRef.current && !sidebarRef.current.contains(event.target as Node)) {
        setIsSidebarOpen(false);
      }
    };

    if (isSidebarOpen) {
      document.addEventListener("mousedown", handleClickOutside);
    } else {
      document.removeEventListener("mousedown", handleClickOutside);
    }

    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, [isSidebarOpen]);

  return (
    <div className="relative min-h-screen bg-gray-100">
      {/* Header */}
      <header className="bg-blue-500 text-white p-4 flex items-center justify-between sticky top-0 z-50">
  <button
    onClick={() => setIsSidebarOpen(!isSidebarOpen)}
    className="text-white focus:outline-none"
  >
    {/* 3 stripes button */}
    <div className="space-y-1">
      <div className="w-6 h-1 bg-white"></div>
      <div className="w-6 h-1 bg-white"></div>
      <div className="w-6 h-1 bg-white"></div>
    </div>
  </button>
  <div className="flex-grow text-center">
    <button
      onClick={() => setCurrentPage("menu")}
      className="text-xl font-bold focus:outline-none"
    >
      Projeto Matrículas
    </button>
  </div>
</header>
      {/* Sidebar */}
      <div
        ref={sidebarRef}
        className={`fixed top-0 left-0 h-full bg-white shadow-md z-60 transform ${
          isSidebarOpen ? "translate-x-0" : "-translate-x-full"
        } transition-transform duration-300`}
        style={{ width: "250px" }}
      >
        <div className="p-4 pt-16">
          <div className="flex justify-between items-center mb-4">
            <h2 className="text-xl font-bold text-gray-800">Menu</h2>
          </div>
          <ul className="space-y-4">
            <li>
              <button
                onClick={() => {
                  setCurrentPage("ranking");
                  setIsSidebarOpen(false);
                }}
                className="text-blue-600 hover:text-blue-800 font-medium text-lg transition-colors duration-200"
              >
                Ranking de Cursos
              </button>
            </li>
            <li>
              <button
                onClick={() => {
                  setCurrentPage("totalAlunos");
                  setIsSidebarOpen(false);
                }}
                className="text-blue-600 hover:text-blue-800 font-medium text-lg transition-colors duration-200"
              >
                Total de Alunos por Ano
              </button>
            </li>
            <li>
              <button
                onClick={() => {
                  setCurrentPage("importCsv");
                  setIsSidebarOpen(false);
                }}
                className="text-blue-600 hover:text-blue-800 font-medium text-lg transition-colors duration-200"
              >
                Importar CSV
              </button>
            </li>
          </ul>
        </div>
      </div>

      {/* Main Content */}
      <div className="p-6">
        {currentPage === "menu" && (
          <div className="flex flex-col items-center justify-center min-h-screen">
            <h1 className="text-4xl font-bold mb-6 text-blue-500">Bem-vindo ao Projeto Matrículas</h1>
            <div className="w-full max-w-8xl grid grid-cols-1 md:grid-cols-2 gap-8">
              {rankingData && (
                <div className="bg-white p-4 rounded shadow" style={{ height: "400px" }}>
                  <h2 className="text-lg font-bold mb-4 text-center break-words">Ranking de Cursos 2022</h2>
                  <Bar
                    data={rankingData}
                    options={{
                      responsive: true,
                      maintainAspectRatio: false,
                      layout: {
                        padding: {
                          bottom: 20,
                        },
                      },
                      plugins: {
                        legend: {
                          display: true,
                          position: "top",
                        },
                      },
                      scales: {
                        x: {
                          ticks: {
                            font: {
                              size: 10,
                            },
                            callback: function (value, index, values) {
                              const label = this.getLabelForValue(value as number);
                              return label.split(" ").join("\n");
                            },
                          },
                        },
                      },
                    }}
                  />
                </div>
              )}
              {totalAlunosData && (
                <div className="bg-white p-4 rounded shadow" style={{ height: "400px" }}>
                  <h2 className="text-lg font-bold mb-4 text-center break-words">Total de Alunos por Ano</h2>
                  <Line
                    data={totalAlunosData}
                    options={{
                      responsive: true,
                      maintainAspectRatio: false,
                      plugins: {
                        legend: {
                          display: true,
                          position: "top",
                        },
                      },
                      scales: {
                        x: {
                          title: {
                            display: true,
                            text: "Ano",
                          },
                        },
                        y: {
                          title: {
                            display: true,
                            text: "Total de Alunos",
                          },
                        },
                      },
                      elements: {
                        line: {
                          tension: 0.3, // Add smooth curves to the line
                        },
                        point: {
                          radius: 5, // Size of the points
                          backgroundColor: "rgba(255, 99, 132, 1)", // Color of the points
                        },
                      },
                    }}
                  />
                </div>
              )}
            </div>
          </div>
        )}
        {currentPage === "ranking" && <RankingCursosPage />}
        {currentPage === "totalAlunos" && <TotalAlunosPorAnoPage />}
        {currentPage === "importCsv" && <ImportCsvPage />}
x      </div>
    </div>
  );
};

export default App;