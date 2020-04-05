package fr.univtln.bruno.i211.dao;

import lombok.Data;

@Data
public class DAOException extends Exception {
    private final Exception sourceException;
}
