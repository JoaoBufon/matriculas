import api from "./api";
import { TotalAlunosPorAno } from "../types/consultas/TotalAlunosPorAno";
import { RankingCurso } from "../types/consultas/RankingCurso";

export const buscarTotalAlunosPorAno = async (
  ano: number,
  modalidade?: string,
  estado?: string
): Promise<TotalAlunosPorAno[]> => {
  const modalidadeStr = modalidade ? `/${modalidade}` : "";
  const estadoStr = estado ? `/${estado}` : "";
  const response = await api.get(
    `/pesquisas/totalAlunos/${ano}${modalidadeStr}${estadoStr}`
  );
  return response.data;
};

export const buscarRankingCursos = async (
  modalidade?: string,
  estado?: string
): Promise<RankingCurso[]> => {
  const modalidadeStr = modalidade ? `/${modalidade}` : "";
  const estadoStr = estado ? `/${estado}` : "";
  const response = await api.get(
    `/pesquisas/rankingCursos${modalidadeStr}${estadoStr}`
  );
  return response.data;
};
