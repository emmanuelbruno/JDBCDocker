package fr.univtln.bruno.i211;

import fr.univtln.bruno.i211.dao.ActorDAO;
import fr.univtln.bruno.i211.dao.entities.Actor;
import fr.univtln.bruno.i211.dao.exceptions.DAOException;
import fr.univtln.bruno.i211.dao.utils.DBCPDataSource;
import lombok.extern.java.Log;

/**
 * A simple JDBC App!
 */
@Log
public class App {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        try {
            Thread.sleep(5000);
            //Create a DAO for actors
            ActorDAO actorDAO = new ActorDAO();
            log.info(DBCPDataSource.getStatus());

            //Create a new Actor and persist it
            Actor newActor = Actor.builder().first_name("John").last_name("DOE").build();
            long newActorID = actorDAO.persist(newActor);


            //List all actors
            //System.out.println(actorDAO.findAll());

            //Find the news actor by ID
            newActor = actorDAO.findById(newActorID);
            log.info("The new actor: "+newActor.toString());

            //Update this actor
            newActor.setLast_name("SMITH");
            actorDAO.update(newActor);
            log.info("The new actor updated: "+newActor.toString());

            //and delete It.
            actorDAO.remove(newActor);

            //We try to find and raise an error.
            newActor = actorDAO.findById(newActorID);
            log.info("The deleted newActor ???"+newActor.toString());

        } catch (DAOException e) {
            log.severe("Erreur d'accès à la DAO. "+e.getSqlException());
        } catch (InterruptedException e) {
            log.severe("Erreur de pause.");
        }
    }


}
