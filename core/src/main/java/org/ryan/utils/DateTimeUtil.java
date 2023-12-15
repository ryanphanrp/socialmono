package org.ryan.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DateTimeUtil {

  private static final String ZONE_ID = "UTC";

  public static Long unixTimeOf(LocalDateTime time) {
    if (Objects.isNull(time)) {
      return 0L;
    }
    return time.atZone(ZoneId.of(ZONE_ID)).toEpochSecond();
  }

  public static Date fromNow(ChronoUnit chronoUnit, int amount) {
    return Date.from(LocalDateTime.now()
                                  .plus(amount, chronoUnit)
                                  .atZone(ZoneId.of(ZONE_ID))
                                  .toInstant()
    );
  }

  public static Date fromNow(ChronoUnit chronoUnit) {
    return fromNow(chronoUnit, 1);
  }
}
