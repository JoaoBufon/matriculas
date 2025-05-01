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
    <div className="relative p-6 min-h-screen flex flex-col items-center">
      <h1 className="text-4xl font-extrabold mb-8 text-blue-600">Importar CSV</h1>
      <div className="bg-white p-8 rounded-lg shadow-lg w-full max-w-lg">
        <label className="block text-gray-700 font-semibold mb-4 text-lg">
          Selecione um arquivo CSV:
        </label>
        <div className="flex items-center justify-center w-full mb-6">
          <label
            htmlFor="file-upload"
            className="cursor-pointer bg-blue-500 hover:bg-blue-600 text-white font-medium py-2 px-4 rounded-lg shadow-md transition duration-300"
          >
            Escolher Arquivo
          </label>
          <input
            id="file-upload"
            type="file"
            accept=".csv"
            onChange={handleFileChange}
            className="hidden"
          />
          {selectedFile && (
            <span className="ml-4 text-gray-600 font-medium">
              {selectedFile.name}
            </span>
          )}
        </div>
        <button
          onClick={handleImportCsv}
          className={`w-full bg-blue-500 text-white py-3 rounded-lg font-semibold hover:bg-blue-600 transition duration-300 ${
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
          <p className="absolute mt-24 text-white font-semibold text-lg">
            Processando, por favor aguarde...
          </p>
        </div>
      )}
    </div>
  );
};

export default ImportCsvPage;