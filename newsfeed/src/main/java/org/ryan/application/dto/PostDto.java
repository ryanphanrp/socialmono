package org.ryan.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import org.ryan.domain.Post;

import static org.ryan.utils.DateTimeUtil.unixTimeOf;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PostDto(
    Long postId,
    String content,
    Long userId,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

  public static PostDto of(Post post) {
    return new PostDto(
        post.getPostId(),
        post.getContent(),
        post.getUserId(),
        post.getCreatedAt(),
        post.getUpdatedAt()
    );
  }

  @JsonInclude(value = JsonInclude.Include.NON_DEFAULT)
  public Long getCreatedAt() {
    return unixTimeOf(createdAt);
  }

  @JsonInclude(value = JsonInclude.Include.NON_DEFAULT)
  public Long getUpdatedAt() {
    return unixTimeOf(updatedAt);
  }
}
