package org.ryan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import org.ryan.constant.ResponseCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RpcResponse<T>(Boolean status, String message, T body) implements
    Serializable {

  public static <T> RpcResponse<T> of(Boolean status, String message, T body) {
    return new RpcResponse<>(status, message, body);
  }

  public static <T> RpcResponse<T> error(ResponseCode responseCode) {
    return RpcResponse.of(false, responseCode.getMessage(), null);
  }

  public static <T> RpcResponse<T> error(String message) {
    return RpcResponse.of(false, message, null);
  }

  public static <T> RpcResponse<T> ok(T body) {
    return RpcResponse.of(true, null, body);
  }

  public boolean isError() {
    return Boolean.FALSE.equals(status);
  }
}
