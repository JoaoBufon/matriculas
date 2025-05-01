import React, { useState } from "react";
import axios from "axios";

const ImportCsvPage: React.FC = () => {
  const [selectedFile, setSelectedFile] = useState<File | null>(null);
  const [loading, setLoading] = useState<boolean>(false);

  const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event.target.files && event.target.files.length > 0) {
      const file = event.target.files[0];
      setSelectedFile(file);
    }
  };

  const handleImportCsv = async () => {
    if (!selectedFile) {
      alert("Por favor, selecione um arquivo CSV.");
      return;
    }

    const formData = new FormData();
    formData.append("file", selectedFile);

    setLoading(true);
    try {
      console.log("Enviando arquivo:", selectedFile.name);
      await axios.post("http://localhost:8080/api/csv/upload", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });
      alert("CSV importado com sucesso!");
    } catch (error: any) {
      console.error("Erro ao importar o CSV:", error);
      if (error.response) {
        alert(`Erro do servidor: ${error.response.data}`);
      } else if (error.request) {
        alert("Erro de rede: Não foi possível conectar ao servidor.");
      } else {
        alert("Erro desconhecido ao importar o CSV.");
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="relative p-6 bg-gray-100 min-h-screen flex flex-col items-center">
      <h1 className="text-3xl font-bold mb-6 text-blue-500">Importar CSV</h1>
      <div className="bg-white p-6 rounded shadow w-full max-w-md">
        <label className="block text-gray-700 font-medium mb-2">
          Selecione um arquivo CSV:
        </label>
        <input
          type="file"
          accept=".csv"
          onChange={handleFileChange}
          className="block w-full text-gray-700 border rounded p-2 mb-4"
        />
        <button
          onClick={handleImportCsv}
          className={`bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition ${
            loading ? "opacity-50 cursor-not-allowed" : ""
          }`}
          disabled={loading}
        >
          {loading ? "Processando..." : "Importar CSV"}
        </button>
      </div>

      {/* Full-screen loading overlay */}
      {loading && (
        <div className="fixed inset-0 bg-gray-800 bg-opacity-50 flex items-center justify-center z-50">
          <div className="animate-spin rounded-full h-16 w-16 border-t-4 border-blue-500 border-solid"></div>
        </div>
      )}
    </div>
  );
};

export default ImportCsvPage;