package ua.kpi.model.dao.jdbc;

import ua.kpi.model.entity.Reader;

import java.util.List;
import java.util.Optional;

public interface ReaderDao extends GenericDao<Reader> {
    List<Reader> findByLastName(String lastName);
}