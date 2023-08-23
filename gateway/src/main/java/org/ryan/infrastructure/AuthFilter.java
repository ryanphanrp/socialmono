package org.ryan.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ryan.constant.GlobalConstant;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Slf4j
@Component
@RequiredArgsConstructor
public class AuthFilter extends AbstractGatewayFilterFactory<Object> {
    private final JwtUtil jwtUtil;

    @Override
    public GatewayFilter apply(Object object) {
        return (exchange, chain) -> {
            log.info("AuthenticationFilter - GatewayFilter");
            ServerHttpRequest request = exchange.getRequest();
            if (!hasAuthorization(request)) {
                log.error("Unauthorized - No Token");
                return onError(exchange, HttpStatus.BAD_REQUEST);
            }
            var token = getAuthHeader(request);
            if (!jwtUtil.isValid(token)) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }
            exchange.getRequest().mutate()
                    .header(GlobalConstant.USER_HEADER, jwtUtil.getUsernameFromToken(token))
                    .build();
            return chain.filter(exchange);
        };
    }

    private boolean hasAuthorization(ServerHttpRequest request) {
        return request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION);
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION).get(0);
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }
}
