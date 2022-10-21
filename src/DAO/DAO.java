package DAO;

import java.sql.SQLException;
import java.util.List;

/**Interface for CRUD. */
public interface DAO<T> {

    /**CRUD Retrieve.
     * Retrieval of one object.
     * @param id indexing or PK/FK id*/
    T get(int id) throws SQLException;

    /**CRUD Retrieve.
     * Retrieval of all objects of one object type. */
    List<T> getAll() throws SQLException;

    /**CRUD Create.
     * @param t object to be created. */
    int save(T t) throws SQLException;

    /**CRUD Create and Update.
     * @param t object to be inserted. */
    int insert(T t) throws SQLException;

    /**CRUD Update.
     * @param t object to be updated. */
    int update(T t) throws SQLException;

    /**CRUD Delete.
     * @param t object to be deleted. */
    int delete(T t) throws SQLException;
}
