import React, { useState } from "react";
import RankingCursosPage from "./components/RankingCursosPage";
import TotalAlunosPorAnoPage from "./components/TotalAlunosPorAnoPage";

const App: React.FC = () => {
  const [currentPage, setCurrentPage] = useState<"ranking" | "totalAlunos">("ranking");

  return (
    <div className="p-6 bg-gray-100 min-h-screen">
      <div className="flex justify-center gap-4 mb-6">
        <button
          onClick={() => setCurrentPage("ranking")}
          className={`px-4 py-2 rounded ${
            currentPage === "ranking" ? "bg-blue-500 text-white" : "bg-gray-200 text-gray-700"
          }`}
        >
          Ranking de Cursos
        </button>
        <button
          onClick={() => setCurrentPage("totalAlunos")}
          className={`px-4 py-2 rounded ${
            currentPage === "totalAlunos" ? "bg-blue-500 text-white" : "bg-gray-200 text-gray-700"
          }`}
        >
          Total de Alunos por Ano
        </button>
      </div>

      {currentPage === "ranking" && <RankingCursosPage />}
      {currentPage === "totalAlunos" && <TotalAlunosPorAnoPage />}
    </div>
  );
};

export default App;