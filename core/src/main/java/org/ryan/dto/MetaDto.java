package org.ryan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record MetaDto(
    Integer currentPage,
    Integer pageSize,
    Integer totalPages,
    Long totalElements
) {

  public static MetaDto of(Integer currentPage, Integer pageSize) {
    return new MetaDto(currentPage, pageSize, 1, null);
  }

  public static MetaDto of(Pageable pageRequest) {
    return new MetaDto(
        pageRequest.getPageNumber(),
        pageRequest.getPageSize(),
        null,
        null
    );
  }

  public static <T> MetaDto fromPage(Page<T> page) {
    return new MetaDto(
        page.getNumber(),
        page.getSize(),
        page.getTotalPages(),
        page.getTotalElements()
    );
  }
}
