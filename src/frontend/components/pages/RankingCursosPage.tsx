import { useEffect, useState } from "react";
import { buscarRankingCursos } from "../../services/consultasService";
import { RankingCurso } from "../../types/consultas/RankingCurso";
import { estadosBrasil } from "../../constants/estadosBrasil";
import { formatNumber } from "../../utils/numberUtils"; // Import the utility function

const RankingCursosPage = () => {
  const [ranking, setRanking] = useState<RankingCurso[]>([]);
  const [ano, setAno] = useState<number>(2022); // Default year
  const [modalidade, setModalidade] = useState<string | undefined>();
  const [estado, setEstado] = useState<string | undefined>();
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    const carregarRanking = async () => {
      setLoading(true);
      const resultado = await buscarRankingCursos(ano, modalidade, estado);
      setRanking(resultado);
      setLoading(false);
    };
    carregarRanking();
  }, [ano, modalidade, estado]);

  return (
    <div className="p-6 bg-gray-100 min-h-screen">
      <h1 className="text-3xl font-bold mb-6 text-center text-gray-800">
        Ranking de Cursos
      </h1>

      <div className="mb-6 flex flex-col md:flex-row gap-4 justify-center">
        {/* Select for Year */}
        <select
          value={ano}
          onChange={(e) => setAno(Number(e.target.value))}
          className="border p-3 rounded w-full md:w-1/4 bg-white shadow"
        >
          {Array.from({ length: 2022 - 2014 + 1 }, (_, i) => 2014 + i).map((year) => (
            <option key={year} value={year}>
              {year}
            </option>
          ))}
        </select>

        {/* Select for Modalidade */}
        <select
          value={modalidade}
          onChange={(e) => setModalidade(e.target.value)}
          className="border p-3 rounded w-full md:w-1/4 bg-white shadow"
        >
          <option value="">Todas Modalidades</option>
          <option value="EAD">EAD</option>
          <option value="Presencial">Presencial</option>
        </select>

        {/* Select for Estado */}
        <select
          value={estado}
          onChange={(e) => setEstado(e.target.value)}
          className="border p-3 rounded w-full md:w-1/4 bg-white shadow"
        >
          {estadosBrasil.map((estado) => (
            <option key={estado.value} value={estado.value}>
              {estado.label}
            </option>
          ))}
        </select>
      </div>

      <div className="overflow-x-auto">
        {loading ? (
          <div className="text-center py-6">
            <div className="loader border-t-4 border-blue-500 rounded-full w-12 h-12 mx-auto animate-spin"></div>
            <p className="text-gray-500 mt-2">Carregando dados...</p>
          </div>
        ) : (
          <table className="min-w-full bg-white rounded shadow-md">
            <thead>
              <tr className="bg-gray-200 text-gray-700">
                <th className="py-3 px-6 text-left w-2/3">Curso</th>
                <th className="py-3 px-6 text-left w-1/3">Número de Matrículas</th>
              </tr>
            </thead>
            <tbody>
              {ranking.length > 0 ? (
                ranking.map((curso) => (
                  <tr
                    key={curso.idCurso}
                    className="border-b hover:bg-gray-100 transition"
                  >
                    <td className="py-3 px-6">{curso.desCurso}</td>
                    <td className="py-3 px-6">{formatNumber(curso.nmrMatriculados)}</td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan={2} className="py-3 px-6 text-center text-gray-500">
                    Nenhum dado encontrado.
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
};

export default RankingCursosPage;