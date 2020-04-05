package fr.univtln.bruno.i211.dao.entities;

import lombok.Builder;
import lombok.Data;

/**
 * The type Actor.
 */
@Data
@Builder
public class Actor {
    private int id;
    private String first_name;
    private String last_name;
}

