package ru.axamit.statistic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown to indicate that a method has been passed date in illegal or
 * inappropriate format.
 *
 * @author Madiyar Nurgazin (github.com/SnRise)
 */
@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Incorrect date format. Please use 'dd-MM-yyyy HH:mm:ss' format")
public class IncorrectDateFormatException extends Exception {
}
