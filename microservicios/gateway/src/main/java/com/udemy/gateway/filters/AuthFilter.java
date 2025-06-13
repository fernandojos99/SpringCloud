package com.udemy.gateway.filters;


//import com.debuggeandoideas.gateway.dtos.TokenDto;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import com.udemy.gateway.dtos.TokenDto;

import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GatewayFilter {

	//Esto para poder hacer la request
    private final WebClient webClient;

    //Para validar el filtro
    private static final String AUTH_VALIDATE_URI = "http://localhost:3000/auth-server/auth/jwt";
    //private static final String AUTH_VALIDATE_URI = "http://localhost:8081/product/api/auth/valid"; 
    private static final String ACCESS_TOKEN_HEADER_NAME = "accessToken";

    public AuthFilter() {
        this.webClient = WebClient.builder().build();
    }

    //El serverWebExchange es para traer la request
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            return this.onError(exchange);
        }

        final var tokenHeader = exchange
                .getRequest()
                .getHeaders()
                //Le tenemos que poner el get(0) porque es una lista .
                .get(HttpHeaders.AUTHORIZATION).get(0);

        // Vamos a cortar el token para analizarlo
        final var chunks = tokenHeader.split(" ");
        if (chunks.length != 2|| !chunks[0].equals("Bearer")) {
            return this.onError(exchange);
        }
        final var token = chunks[1];

        return this.webClient
                .post()
                .uri(AUTH_VALIDATE_URI)
                .header(ACCESS_TOKEN_HEADER_NAME, token)
                .retrieve()
                .bodyToMono(TokenDto.class)
 //               .toBodilessEntity() // <--- no espera cuerpo
                .map(response -> exchange)
                .flatMap(chain::filter); //???
    }

    
    /** Es un metodo que regresa un reponde con header "Bad request"
     * Mono es para cosas reactivas*/
    private Mono<Void> onError(ServerWebExchange exchange) {
        final var response = exchange.getResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        return response.setComplete();
    }
}