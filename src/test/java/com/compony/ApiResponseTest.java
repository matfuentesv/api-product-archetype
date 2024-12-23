package com.compony;

import cl.company.exception.ApiResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiResponseTest {

    @Test
    void shouldCreateApiResponseWithSuccess() {

        String message = "Operation successful";
        boolean success = true;


        ApiResponse apiResponse = new ApiResponse(message, success);


        assertNotNull(apiResponse);
        assertEquals(message, apiResponse.getMessage());
        assertTrue(apiResponse.isSuccess());
    }

    @Test
    void shouldCreateApiResponseWithFailure() {
        // Given
        String message = "Operation failed";
        boolean success = false;

        // When
        ApiResponse apiResponse = new ApiResponse(message, success);

        // Then
        assertNotNull(apiResponse);
        assertEquals(message, apiResponse.getMessage());
        assertFalse(apiResponse.isSuccess());
    }


    @Test
    void testSetMessage() {
        ApiResponse apiResponse = new ApiResponse("Initial message", true);

        // Cambiar el mensaje
        String newMessage = "Updated message";
        apiResponse.setMessage(newMessage);

        // Verificar que el mensaje fue actualizado
        assertEquals(newMessage, apiResponse.getMessage());
    }

    @Test
    void testSetSuccess() {
        ApiResponse apiResponse = new ApiResponse("Some message", true);

        // Cambiar el estado de éxito
        apiResponse.setSuccess(false);

        // Verificar que el estado fue actualizado
        assertFalse(apiResponse.isSuccess());
    }

}