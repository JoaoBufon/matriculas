import React, { useEffect, useState } from "react";

const LastQueriesPage: React.FC = () => {
  const [lastTotalAlunosQueries, setLastTotalAlunosQueries] = useState<any[]>([]);
  const [lastRankingCursosQueries, setLastRankingCursosQueries] = useState<any[]>([]);

  useEffect(() => {
    const totalAlunosQueries = JSON.parse(localStorage.getItem("lastTotalAlunosQueries") || "[]");
    const rankingCursosQueries = JSON.parse(localStorage.getItem("lastRankingCursosQueries") || "[]");

    setLastTotalAlunosQueries(totalAlunosQueries);
    setLastRankingCursosQueries(rankingCursosQueries);
  }, []);

  return (
    <div className="p-6 bg-gray-100 min-h-screen">
      <h1 className="text-3xl font-bold mb-6 text-center text-gray-800">Últimas Consultas</h1>

      {/* Total de Alunos por Ano */}
      <div className="mb-8">
        <h2 className="text-2xl font-semibold mb-4">Últimas Consultas de Total de Alunos por Ano</h2>
        {lastTotalAlunosQueries.length > 0 ? (
          <div className="overflow-x-auto">
            <table className="min-w-full bg-white rounded shadow-md">
              <thead className="bg-gray-200 text-gray-700">
                <tr>
                  <th className="py-3 px-6 text-left">Ano</th>
                  <th className="py-3 px-6 text-left">Modalidade</th>
                  <th className="py-3 px-6 text-left">Estado</th>
                  <th className="py-3 px-6 text-left">Curso</th>
                  <th className="py-3 px-6 text-left">Resultado</th>
                </tr>
              </thead>
              <tbody>
                {lastTotalAlunosQueries.map((query, index) => (
                  <tr key={index} className="border-b hover:bg-gray-100 transition">
                    <td className="py-3 px-6">{query.ano}</td>
                    <td className="py-3 px-6">{query.modalidade || "Não informado"}</td>
                    <td className="py-3 px-6">{query.estado || "Não informado"}</td>
                    <td className="py-3 px-6">{query.curso || "Não informado"}</td>
                    <td className="py-3 px-6">{JSON.stringify(query.data)}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        ) : (
          <p className="text-gray-500">Nenhuma consulta encontrada.</p>
        )}
      </div>

      {/* Ranking de Cursos */}
      <div>
        <h2 className="text-2xl font-semibold mb-4">Últimas Consultas de Ranking de Cursos</h2>
        {lastRankingCursosQueries.length > 0 ? (
          <div className="overflow-x-auto">
            <table className="min-w-full bg-white rounded shadow-md">
              <thead className="bg-gray-200 text-gray-700">
                <tr>
                  <th className="py-3 px-6 text-left">Ano</th>
                  <th className="py-3 px-6 text-left">Modalidade</th>
                  <th className="py-3 px-6 text-left">Estado</th>
                  <th className="py-3 px-6 text-left">Resultado</th>
                </tr>
              </thead>
              <tbody>
                {lastRankingCursosQueries.map((query, index) => (
                  <tr key={index} className="border-b hover:bg-gray-100 transition">
                    <td className="py-3 px-6">{query.ano}</td>
                    <td className="py-3 px-6">{query.modalidade || "Não informado"}</td>
                    <td className="py-3 px-6">{query.estado || "Não informado"}</td>
                    <td className="py-3 px-6">
                      {Array.isArray(query.data) ? (
                        query.data.map((curso: any) => (
                          <div key={curso.idCurso}>
                            {curso.desCurso}: {curso.nmrMatriculados} matrículas
                          </div>
                        ))
                      ) : (
                        <span className="text-gray-500">Dados indisponíveis</span>
                      )}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        ) : (
          <p className="text-gray-500">Nenhuma consulta encontrada.</p>
        )}
      </div>
    </div>
  );
};

export default LastQueriesPage;