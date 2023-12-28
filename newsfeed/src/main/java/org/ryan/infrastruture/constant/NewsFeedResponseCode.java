package org.ryan.infrastruture.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.ryan.constant.ResponseCode;

@Getter
@AllArgsConstructor
public enum NewsFeedResponseCode implements ResponseCode {
  POST_NOT_FOUND(-404, "Cant find this post.");

  private final Integer code;
  private final String message;
}
