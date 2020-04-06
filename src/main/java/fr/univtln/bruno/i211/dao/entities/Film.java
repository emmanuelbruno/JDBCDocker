package fr.univtln.bruno.i211.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * The type Film.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Film {

    @Id
    @SequenceGenerator(name = "FilmSeqGen", sequenceName = "film_film_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FilmSeqGen")
    @Column(name = "film_id")
    private long id;

    private String title;

    private String description;

    private int release_year;

    private short language_id;

    private short rental_duration;

    private float rental_rate;

    private short length;

    private float replacement_cost;

    private String rating;

    private Timestamp last_update;

    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id"))
    private List<Actor> actors;
}
