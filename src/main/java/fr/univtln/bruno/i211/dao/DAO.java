package fr.univtln.bruno.i211.dao;

import fr.univtln.bruno.i211.dao.exceptions.DAOException;

import java.util.List;

/**
 * The interface Dao.
 *
 * @param <E> the type parameter
 */
public interface DAO<E> {
    /**
     * Persist long.
     *
     * @param entity the entity
     * @return the long
     * @throws DAOException the dao exception
     */
    long persist(E entity) throws DAOException;

    /**
     * Find by id e.
     *
     * @param id the id
     * @return the e
     * @throws DAOException the dao exception
     */
    E findById(long id) throws DAOException;

    /**
     * Find all entities.
     *
     * @return the list
     */
    List<E> findAll();

    /**
     * Updates an entity.
     *
     * @param entity the entity
     * @throws DAOException the dao exception
     */
    void update(E entity) throws DAOException;

    /**
     * Remove an entity.
     *
     * @param entity the entity
     * @throws DAOException the dao exception
     */
    void remove(E entity) throws DAOException;
}
