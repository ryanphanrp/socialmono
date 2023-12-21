package org.ryan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.ryan.constant.ResponseCode;
import org.springframework.http.ResponseEntity;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseDto<T>(
    Integer code,
    String message,
    Object reason,
    T body
) {

  public static <T> ResponseDto<T> of(ResponseCode responseCode, T body) {
    return ResponseDto.<T>builder()
        .code(responseCode.getCode())
        .message(responseCode.getMessage())
        .body(body)
        .build();
  }

  public static <T> ResponseDto<T> ok(T body) {
    return ResponseDto.of(ResponseCode.OK, body);
  }

  public static <T> ResponseDto<T> ok() {
    return ResponseDto.ok(null);
  }

  public static <T> ResponseEntity<ResponseDto<T>> error(ResponseCode responseCode) {
    return ResponseEntity.status(responseCode.getHttpStatus())
        .body(ResponseDto.of(responseCode, null));
  }

  public static <T> ResponseEntity<ResponseDto<T>> error(
      ResponseCode responseCode, String reason
  ) {
    return ResponseEntity.status(responseCode.getHttpStatus())
        .body(ResponseDto.<T>builder()
                  .code(responseCode.getCode())
                  .message(responseCode.getMessage())
                  .reason(reason)
                  .build());
  }
}