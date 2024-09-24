package com.subria.fi.exceptions;

import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.Getter;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        // Log the exception for debugging purposes
        exception.printStackTrace();

        // Map different exceptions to specific HTTP status codes
        if (exception instanceof IMServiceException) {
            return buildResponse(Response.Status.fromStatusCode(((IMServiceException) exception).getHttpRequestCode()),
                    ((IMServiceException) exception).getError(), exception.getMessage());
        }
        else if (exception instanceof NoResultException) {
            return buildResponse(Response.Status.NOT_FOUND, "Resource Not Found", exception.getMessage());
        } else if (exception instanceof IllegalArgumentException) {
            return buildResponse(Response.Status.BAD_REQUEST, "Invalid Request", exception.getMessage());
        } else if (exception instanceof PersistenceException) {
            return buildResponse(Response.Status.INTERNAL_SERVER_ERROR, "Database Error", exception.getMessage());
        }
        else {
            // Default to 500 Internal Server Error for any unhandled exceptions
            return buildResponse(Response.Status.INTERNAL_SERVER_ERROR, "Internal Server Error", exception.getMessage());
        }
    }

    // Helper method to create a standardized error response
    private Response buildResponse(Response.Status status, String error, String message) {
        return Response.status(status)
                .entity(new ErrorResponse(error, message))
                .build();
    }

    // Inner class to represent a standard error response body
    @Getter
    public static class ErrorResponse {
        // Getters and setters for error and message
        private String error;
        private String message;

        public ErrorResponse(String error, String message) {
            this.error = error;
            this.message = message;
        }

        public void setError(String error) {
            this.error = error;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
