package br.ucs.matriculas.dao;

import br.ucs.matriculas.entity.Estado;

import java.sql.SQLException;
import java.util.List;

public class EstadoDAO implements IEstadoDAO {
    @Override
    public Estado getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<Estado> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public Long save(Estado estado) throws SQLException {
        return 0L;
    }

    @Override
    public Long update(Estado estado) throws SQLException {
        return 0L;
    }

    @Override
    public Long delete(Estado estado) throws SQLException {
        return 0L;
    }
}
