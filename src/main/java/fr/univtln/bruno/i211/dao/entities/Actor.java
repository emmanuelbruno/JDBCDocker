package fr.univtln.bruno.i211.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * The type Actor.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity

@NamedQueries({
        @NamedQuery(name = "Actor.findAll", query = "select actor from Actor actor")
})
@Table(name = "actor")
public class Actor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "AuthorSeqGen", sequenceName = "actor_actor_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AuthorSeqGen")
    @Column(name = "actor_id")
    private long id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @ManyToMany(mappedBy = "actors")
    private List<Film> films;
}

