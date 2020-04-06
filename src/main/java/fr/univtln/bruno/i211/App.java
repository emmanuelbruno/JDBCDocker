package fr.univtln.bruno.i211;

import fr.univtln.bruno.i211.dao.entities.Actor;
import lombok.extern.java.Log;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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

            //Create a new Actor and persist it
            Actor newActor = Actor.builder().first_name("John").last_name("DOE").build();
            long newActorID = -1;

            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("testpostgresqllocal");
            EntityManager em = emf.createEntityManager();

            EntityTransaction transac = em.getTransaction();

            transac.begin();
            em.persist(newActor);
            em.flush();
            newActorID = newActor.getId();
            log.info("L'acteur ajouté :" + newActor);

            transac.commit();

            //Find the news actor by ID
            newActor = em.find(Actor.class, newActorID);
            log.info("The new actor (by Id) : " + newActor.toString());

            //List all actors (limit to 5)
            List<Actor> actors = em.createNamedQuery("Actor.findAll")
                    .setMaxResults(5)
                    .getResultList();
            log.info("5 actors: " + actors);


            //Update this actor
            transac.begin();
            newActor.setLast_name("SMITH");
            em.merge(newActor);
            log.info("The new actor updated: " + em.find(Actor.class, newActorID).toString());
            transac.commit();

            //and delete It.
            transac.begin();
            em.remove(newActor);
            transac.commit();

            //We try to find and raise an error.
            newActor = em.find(Actor.class, newActorID);
            log.info("The deleted newActor ???"+newActor.toString());


            em.close();

        } catch (InterruptedException e) {
            log.severe("Erreur de pause.");
        }
    }


}
