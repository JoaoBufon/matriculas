import React, { useState, useEffect } from "react";
import axios from "axios";

interface Curso {
  idCurso: number;
  desCurso: string;
  desDetalhadaCurso?: string;
  grau?: string;
}

interface AutocompleteCursoProps {
  onSelect: (curso: Curso | null) => void;
}

const AutocompleteCurso: React.FC<AutocompleteCursoProps> = ({ onSelect }) => {
  const [cursos, setCursos] = useState<Curso[]>([]);
  const [filteredCursos, setFilteredCursos] = useState<Curso[]>([]);
  const [inputValue, setInputValue] = useState<string>("");
  const [showDropdown, setShowDropdown] = useState<boolean>(false);

  useEffect(() => {
    const fetchCursos = async () => {
      try {
        const response = await axios.get<Curso[]>("http://localhost:8080/api/curso");
        const distinctCursos = Array.from(
          new Map(response.data.map((curso: Curso) => [curso.desCurso, curso])).values()
        );
        setCursos(distinctCursos);
      } catch (error) {
        console.error("Erro ao buscar cursos:", error);
      }
    };

    fetchCursos();
  }, []);

  const normalizeString = (str: string) => {
    return str.normalize("NFD").replace(/[\u0300-\u036f]/g, "").toLowerCase();
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;
    setInputValue(value);

    if (value.trim() === "") {
      setFilteredCursos([]);
      setShowDropdown(false);
      onSelect(null); // Notify parent that the value is cleared
    } else {
      const normalizedValue = normalizeString(value);
      const filtered = cursos.filter((curso) =>
        normalizeString(curso.desCurso).includes(normalizedValue)
      );
      setFilteredCursos(filtered);
      setShowDropdown(true);
    }
  };

  const handleSelect = (curso: Curso) => {
    setInputValue(curso.desCurso);
    setShowDropdown(false);
    onSelect(curso);
  };

  return (
    <div className="relative w-full md:w-1/3">
      <input
        type="text"
        value={inputValue}
        onChange={handleInputChange}
        onFocus={() => setShowDropdown(true)}
        className="border p-3 rounded w-full bg-white shadow"
        placeholder="Digite o nome do curso"
      />
      {showDropdown && filteredCursos.length > 0 && (
        <ul className="absolute z-10 bg-white border border-gray-300 rounded shadow w-full max-h-60 overflow-y-auto">
          {filteredCursos.map((curso) => (
            <li
              key={curso.idCurso}
              onClick={() => handleSelect(curso)}
              className="p-3 hover:bg-gray-100 cursor-pointer"
            >
              {curso.desCurso}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default AutocompleteCurso;