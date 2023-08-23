package org.ryan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.ryan.constant.ResponseCode;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RpcResponse<T>(Boolean status, String message, T body) implements Serializable {

    public boolean isError() {
        return Boolean.FALSE.equals(status);
    }

    public static <T> RpcResponse<T> error(ResponseCode responseCode) {
        return new RpcResponse<>(false, responseCode.getMessage(), null);
    }

    public static <T> RpcResponse<T> error(String message) {
        return new RpcResponse<>(false, message, null);
    }

    public static <T> RpcResponse<T> ok(T body) {
        return new RpcResponse<>(true, null, body);
    }
}
