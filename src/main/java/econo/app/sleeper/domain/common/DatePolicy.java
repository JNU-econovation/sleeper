package econo.app.sleeper.domain.common;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public interface DatePolicy {

    public abstract LocalDate decideDate(ZonedDateTime savedDiaryTime);
}
