package co.com.restaurant.model.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    NOT_FOUND(404, "No se encontraron registros"),
    BAD_REQUEST(400, "El cuerpo de la solicitud es inavlido"),
    ERROR_INTERNAL_SERVER(500, "Error interno del servidor");

    private Integer code;
    private String message;
}
