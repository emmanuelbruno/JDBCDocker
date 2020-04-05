package fr.univtln.bruno.i211.dao.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.sql.SQLException;

@Data
@AllArgsConstructor
@ToString
public class DAOException extends BasicException {
    private SQLException sqlException;
}
