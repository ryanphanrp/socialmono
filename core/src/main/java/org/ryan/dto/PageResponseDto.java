package org.ryan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Builder;
import org.springframework.data.domain.Page;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record PageResponseDto<T>(
    Integer code, String message, Object reason, List<T> body, MetaDto meta
) {

  public static <T> PageResponseDto<T> of(Integer code, String message, List<T> body, MetaDto meta) {
    return PageResponseDto.<T>builder()
                          .code(code)
                          .message(message)
                          .body(body)
                          .meta(meta)
                          .build();
  }

  public static <T> PageResponseDto<T> of(Page<T> page) {
    return PageResponseDto.of(
        200,
        "OK",
        page.getContent(),
        MetaDto.fromPage(page)
    );
  }
}
