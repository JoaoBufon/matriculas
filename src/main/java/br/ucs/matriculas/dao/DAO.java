package br.ucs.matriculas.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    T getById(Long id) throws SQLException;

    List<T> getAll() throws SQLException;

    Long save(T t) throws SQLException;

    Long update(T t) throws SQLException;

    Long delete(T t) throws SQLException;
}
