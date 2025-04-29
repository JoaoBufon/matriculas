import { useEffect, useState } from "react";
import { buscarTotalAlunosPorAno } from "../services/consultasService";
import { TotalAlunosPorAno } from "../types/consultas/TotalAlunosPorAno";

const TotalAlunosPorAnoPage = () => {
  const [dados, setDados] = useState<TotalAlunosPorAno[]>([]);
  const [ano, setAno] = useState<number>(2022);
  const [modalidade, setModalidade] = useState<string | undefined>();
  const [estado, setEstado] = useState<string | undefined>();

  useEffect(() => {
    const carregarDados = async () => {
      const resultado = await buscarTotalAlunosPorAno(ano, modalidade, estado);
      setDados(resultado);
    };
    carregarDados();
  }, [ano, modalidade, estado]);

  return (
    <div className="p-4">
      <h1 className="text-2xl font-bold mb-4">Total de Alunos por Ano</h1>

      <div className="mb-4 flex gap-4">
        <select
          value={ano}
          onChange={(e) => setAno(Number(e.target.value))}
          className="border p-2 rounded"
        >
          {[2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022].map(
            (year) => (
              <option key={year} value={year}>
                {year}
              </option>
            )
          )}
        </select>

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
            <th className="py-2 px-4 border-b">Ano</th>
            <th className="py-2 px-4 border-b">Total de Alunos</th>
          </tr>
        </thead>
        <tbody>
          {dados.map((item) => (
            <tr key={item.numAno}>
              <td className="py-2 px-4 border-b">{item.numAno}</td>
              <td className="py-2 px-4 border-b">{item.nmrTotalAlunos}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default TotalAlunosPorAnoPage;
