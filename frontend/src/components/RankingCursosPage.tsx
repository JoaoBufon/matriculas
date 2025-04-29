import { useEffect, useState } from "react";
import { buscarRankingCursos } from "../services/consultasService";
import { RankingCurso } from "../types/consultas/RankingCurso";

const RankingCursosPage = () => {
  const [ranking, setRanking] = useState<RankingCurso[]>([]);
  const [modalidade, setModalidade] = useState<string | undefined>();
  const [estado, setEstado] = useState<string | undefined>();

  useEffect(() => {
    const carregarRanking = async () => {
      const resultado = await buscarRankingCursos(modalidade, estado);
      setRanking(resultado);
    };
    carregarRanking();
  }, [modalidade, estado]);

  return (
    <div className="p-4">
      <h1 className="text-2xl font-bold mb-4">Ranking de Cursos em 2022</h1>

      <div className="mb-4 flex gap-4">
        <select
          value={modalidade}
          onChange={(e) => setModalidade(e.target.value)}
          className="border p-2 rounded"
        >
          <option value="">Todas Modalidades</option>
          <option value="EAD">EAD</option>
          <option value="Presencial">Presencial</option>
        </select>

        <input
          type="text"
          placeholder="Filtrar por Estado"
          value={estado || ""}
          onChange={(e) => setEstado(e.target.value)}
          className="border p-2 rounded"
        />
      </div>

      <table className="min-w-full bg-white rounded">
        <thead>
          <tr>
            <th className="py-2 px-4 border-b">Curso</th>
            <th className="py-2 px-4 border-b">Número de Matrículas</th>
          </tr>
        </thead>
        <tbody>
          {ranking.map((curso) => (
            <tr key={curso.idCurso}>
              <td className="py-2 px-4 border-b">{curso.desCurso}</td>
              <td className="py-2 px-4 border-b">{curso.nmrMatriculados}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default RankingCursosPage;
