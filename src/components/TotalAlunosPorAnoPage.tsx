import { useEffect, useState } from "react";
import { buscarTotalAlunosPorAno } from "../services/consultasService";
import { TotalAlunosPorAno } from "../types/consultas/TotalAlunosPorAno";
import { estadosBrasil } from "../constants/estadosBrasil";

const TotalAlunosPorAnoPage = () => {
  const [dados, setDados] = useState<TotalAlunosPorAno[]>([]);
  const [modalidade, setModalidade] = useState<string | undefined>();
  const [estado, setEstado] = useState<string | undefined>();
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    const carregarDados = async () => {
      setLoading(true);
      const anos = [2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022];
      const resultados = await Promise.all(
        anos.map((ano) => buscarTotalAlunosPorAno(ano, modalidade, estado))
      );

      const dadosFormatados = resultados.map((resultado, index) => ({
        numAno: anos[index],
        nmrTotalAlunos: Number(resultado),
      }));

      setDados(dadosFormatados);
      setLoading(false);
    };
    carregarDados();
  }, [modalidade, estado]);

  return (
    <div className="p-6 bg-gray-100 min-h-screen">
      <h1 className="text-3xl font-bold mb-6 text-center text-gray-800">
        Total de Alunos por Ano
      </h1>

      <div className="mb-6 flex flex-col md:flex-row gap-4 justify-center">
        <select
          value={modalidade}
          onChange={(e) => setModalidade(e.target.value)}
          className="border p-3 rounded w-full md:w-1/3 bg-white shadow"
        >
          <option value="">Todas Modalidades</option>
          <option value="EAD">EAD</option>
          <option value="Presencial">Presencial</option>
        </select>

        <select
          value={estado}
          onChange={(e) => setEstado(e.target.value)}
          className="border p-3 rounded w-full md:w-1/3 bg-white shadow"
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
                <th className="py-3 px-6 text-left w-1/2">Ano</th>
                <th className="py-3 px-6 text-left w-1/2">Total de Alunos</th>
              </tr>
            </thead>
            <tbody>
              {dados.length > 0 ? (
                dados.map((item) => (
                  <tr
                    key={item.numAno}
                    className="border-b hover:bg-gray-100 transition"
                  >
                    <td className="py-3 px-6">{item.numAno}</td>
                    <td className="py-3 px-6">{item.nmrTotalAlunos}</td>
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

export default TotalAlunosPorAnoPage;