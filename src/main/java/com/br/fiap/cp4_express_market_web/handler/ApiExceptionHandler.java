package com.br.fiap.cp4_express_market_web.handler;

import com.br.fiap.cp4_express_market_web.controller.ProdutoApiController;
import com.br.fiap.cp4_express_market_web.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Para o endpoint JSON, NotFoundException vira corpo JSON (como na Parte I),
 * não a página error.html do GlobalExceptionHandler. O assignableTypes limita
 * este advice ao controller REST e o @Order garante que ele seja consultado antes.
 */
@RestControllerAdvice(assignableTypes = ProdutoApiController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex, HttpServletRequest request) {
        ErrorResponse body = ErrorResponse.of(
                HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
