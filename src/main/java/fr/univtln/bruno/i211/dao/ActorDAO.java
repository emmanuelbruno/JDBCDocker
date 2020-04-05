package fr.univtln.bruno.i211.dao;

import fr.univtln.bruno.i211.dao.entities.Actor;
import fr.univtln.bruno.i211.dao.exceptions.DAOException;
import fr.univtln.bruno.i211.dao.utils.DBCPDataSource;
import lombok.extern.java.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Actor dao.
 */
@Log
public class ActorDAO implements DAO<Actor> {

    private PreparedStatement persist;
    private PreparedStatement findByIdStmt;
    private PreparedStatement findAllStmt;
    private PreparedStatement update;
    private PreparedStatement remove;

    /**
     * Instantiates a new Actor dao.
     *
     * @throws DAOException the dao exception
     */
    public ActorDAO() throws DAOException {
        try {
            //CREATE
            this.persist = DBCPDataSource.getConnection().prepareStatement("INSERT INTO actor (first_name, last_name) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            //READ
            this.findByIdStmt = DBCPDataSource.getConnection().prepareStatement("SELECT * FROM actor WHERE actor_id = ?");
            this.findAllStmt = DBCPDataSource.getConnection().prepareStatement("SELECT * FROM actor");
            //UPDATE
            this.update = DBCPDataSource.getConnection().prepareStatement("UPDATE actor SET first_name=?, last_name=? WHERE actor_id = ?");
            //DELETE
            this.remove = DBCPDataSource.getConnection().prepareStatement("DELETE FROM actor where actor_id = ?");
            log.info("Prepared statements created.");
        } catch (SQLException e) {
            log.info(e.getMessage());
            throw new DAOException(e);
        } finally {
        }
    }

    private Actor resulsetItemToFilm(ResultSet resultSet) throws SQLException {
        return Actor.builder()
                .id(resultSet.getInt("actor_id"))
                .first_name(resultSet.getString("first_name"))
                .last_name(resultSet.getString("last_name")).build();
    }

    @Override
    public Actor findById(long id) throws DAOException {
        Actor film = null;
        try {
            findByIdStmt.setLong(1, id);
            ResultSet resultSet = findByIdStmt.executeQuery();
            if (resultSet.next()) film = resulsetItemToFilm(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        if (film == null) throw new DAOException(new SQLException("Actor not  found."));
        else
            return film;
    }

    public List<Actor> findAll() {
        List<Actor> films = new ArrayList<>();
        try (ResultSet resultSet = findAllStmt.executeQuery()) {
            while (resultSet.next()) films.add(resulsetItemToFilm(resultSet));
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            return films;
        }
    }

    @Override
    public void update(Actor entity) throws DAOException {
        try {
            update.setString(1, entity.getFirst_name());
            update.setString(2, entity.getLast_name());
            update.setInt(3, entity.getId());
            update.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void remove(Actor entity) throws DAOException {
        try {
            remove.setInt(1, entity.getId());
            remove.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public long persist(Actor entity) throws DAOException {
        long id = -1;
        try {
            persist.setString(1, entity.getFirst_name());
            persist.setString(2, entity.getLast_name());

            int nbChanges = persist.executeUpdate();
            if (nbChanges == 0)
                throw new DAOException(new SQLException("No actor created."));
            else
                try (ResultSet getGeneratedKeys = persist.getGeneratedKeys()) {
                    if (getGeneratedKeys.next())
                        id = persist.getGeneratedKeys().getLong(1);
                    log.info("Inserted new Actor: " + id);
                }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            return id;
        }
    }
}
