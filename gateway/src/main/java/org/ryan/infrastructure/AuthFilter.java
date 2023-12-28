package org.ryan.infrastructure;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ryan.constant.GlobalConstant;
import org.ryan.constant.CoreResponseCode;
import org.ryan.dto.ResponseDto;
import org.ryan.utils.JSONUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthFilter extends AbstractGatewayFilterFactory<Object> {

  private static final String AUTH_KEY = "Bearer";
  private final JwtUtil jwtUtil;

  @Override
  public GatewayFilter apply(Object object) {
    return (exchange, chain) -> {
      log.info("AuthenticationFilter - GatewayFilter");
      ServerHttpRequest request = exchange.getRequest();
      if (!hasAuthorization(request)) {
        log.error("[Unauthorized]: No Token");
        return onError(exchange, CoreResponseCode.UNAUTHORIZED);
      }
      var token = getAuthToken(request);
      if (!jwtUtil.isValid(token)) {
        log.error("[Unauthorized]: Invalid Token");
        return onError(exchange, CoreResponseCode.FORBIDDEN);
      }
      exchange.getRequest()
          .mutate()
          .header(GlobalConstant.USER_ID_HEADER, jwtUtil.getUserIdFromToken(token).toString())
          .header(GlobalConstant.USER_HEADER, jwtUtil.getUsernameFromToken(token))
          .build();
      return chain.filter(exchange);
    };
  }

  private Mono<Void> onError(
      ServerWebExchange exchange, CoreResponseCode responseCode
  ) {
    ServerHttpResponse response = exchange.getResponse();
    response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
    response.getHeaders()
        .setContentType(MediaType.APPLICATION_JSON);
    String content = Optional.ofNullable(JSONUtils.stringify(ResponseDto.error(responseCode, HttpStatus.INTERNAL_SERVER_ERROR)))
            .orElse("");
    return response.writeWith(Mono.just(content).map(b -> response.bufferFactory()
        .wrap(b.getBytes(StandardCharsets.UTF_8))));
  }

  private boolean hasAuthorization(ServerHttpRequest request) {
    return request.getHeaders()
        .containsKey(HttpHeaders.AUTHORIZATION);
  }

  private String getAuthToken(ServerHttpRequest request) {
    var header = request.getHeaders()
        .getOrEmpty(HttpHeaders.AUTHORIZATION)
        .get(0);
    if (StringUtils.hasText(header)) {
      return header.startsWith(AUTH_KEY) ? header.substring(7) : header;
    }
    return null;
  }
}
