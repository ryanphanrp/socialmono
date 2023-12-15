package org.ryan.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class JSONUtils {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static <R> R parse(String json, Class<R> clazz) {
    try {
      return objectMapper.readValue(json, clazz);
    } catch (Exception ex) {
      log.error(
          "Parse object of class {} failed, json: {}, reason: {}",
          clazz.getName(),
          json,
          ex.getMessage()
      );
      return null;
    }
  }

  public static <R> R parse(String json, TypeReference<R> typeReference) {
    try {
      return objectMapper.readValue(json, typeReference);
    } catch (Exception ex) {
      log.error("Parse object of class {} failed, json: {}, reason: {}",
          typeReference.getClass().getName(), json, ex.getMessage()
      );
      return null;
    }
  }

  public static String stringify(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (Exception ex) {
      log.error(
          "Stringify object of class {} failed, reason: {}",
          object.getClass()
                .getName(),
          ex.getMessage()
      );
      return null;
    }
  }
}
