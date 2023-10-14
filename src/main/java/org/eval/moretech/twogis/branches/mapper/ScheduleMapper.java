package org.eval.moretech.twogis.branches.mapper;

import lombok.extern.slf4j.Slf4j;
import org.eval.moretech.twogis.branches.entity.Place;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class ScheduleMapper {

    private final Pattern MON_SUN = Pattern.compile("пн-вс:\s*(\\d{2})[:.](\\d{2})-(\\d{2})[:.](\\d{2})");
    private final Pattern MON_FRI = Pattern.compile("пн-пт:\s*(\\d{2})[:.](\\d{2})-(\\d{2})[:.](\\d{2})\s*сб,\s*вс:\s*выходной");
    private final Pattern MON_FRI_SAT = Pattern.compile("пн-пт:\s*(\\d{2})[:.](\\d{2})-(\\d{2})[:.](\\d{2})\s*сб:\s*(\\d{2})[:.](\\d{2})-(\\d{2})[:.](\\d{2})\s*вс:\s*выходной");
    private final Pattern MON_THU_FRI = Pattern.compile("пн-чт:\s*(\\d{2})[:.](\\d{2})-(\\d{2})[:.](\\d{2})\s*пт:\s*(\\d{2})[:.](\\d{2})-(\\d{2})[:.](\\d{2})\s*сб,\s*вс:\s*выходной");

    public Place.ScheduleWeek map(String source) {

        Matcher monSunMatcher = MON_SUN.matcher(source);
        if (monSunMatcher.matches()) {
            return monSunSchedule(monSunMatcher);
        }

        Matcher monFriMatcher = MON_FRI.matcher(source);
        if (monFriMatcher.matches()) {
            return monFriSchedule(monFriMatcher);
        }

        Matcher monFriSatMatcher = MON_FRI_SAT.matcher(source);
        if (monFriSatMatcher.matches()) {
            return monFriSatSchedule(monFriSatMatcher);
        }

        Matcher monThuFriMatcher = MON_THU_FRI.matcher(source);
        if (monThuFriMatcher.matches()) {
            return monThuFriSchedule(monThuFriMatcher);
        }

        log.warn("Unparsed schedule: \"{}\"", source);
        return null;
    }

    private Place.ScheduleWeek monSunSchedule(Matcher matcher) {
        LocalTime weekDayTimeFrom = LocalTime.of(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        LocalTime weekDayTimeTill = LocalTime.of(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));

        Place.ScheduleDay weekDay = Place.ScheduleDay.builder()
            .from(weekDayTimeFrom)
            .till(weekDayTimeTill)
            .build();

        return Place.ScheduleWeek.builder()
            .monday(weekDay)
            .tuesday(weekDay)
            .wednesday(weekDay)
            .thursday(weekDay)
            .friday(weekDay)
            .saturday(weekDay)
            .sunday(weekDay)
            .build();
    }

    private Place.ScheduleWeek monFriSchedule(Matcher matcher) {
        LocalTime weekDayTimeFrom = LocalTime.of(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        LocalTime weekDayTimeTill = LocalTime.of(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));

        Place.ScheduleDay weekDay = Place.ScheduleDay.builder()
            .from(weekDayTimeFrom)
            .till(weekDayTimeTill)
            .build();

        return Place.ScheduleWeek.builder()
            .monday(weekDay)
            .tuesday(weekDay)
            .wednesday(weekDay)
            .thursday(weekDay)
            .friday(weekDay)
            .build();
    }

    private Place.ScheduleWeek monThuFriSchedule(Matcher matcher) {
        LocalTime weekDayTimeFrom = LocalTime.of(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        LocalTime weekDayTimeTill = LocalTime.of(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
        LocalTime friTimeFrom = LocalTime.of(Integer.parseInt(matcher.group(5)), Integer.parseInt(matcher.group(6)));
        LocalTime friTimeTill = LocalTime.of(Integer.parseInt(matcher.group(7)), Integer.parseInt(matcher.group(8)));

        Place.ScheduleDay weekDay = Place.ScheduleDay.builder()
            .from(weekDayTimeFrom)
            .till(weekDayTimeTill)
            .build();

        Place.ScheduleDay fri = Place.ScheduleDay.builder()
            .from(friTimeFrom)
            .till(friTimeTill)
            .build();

        return Place.ScheduleWeek.builder()
            .monday(weekDay)
            .tuesday(weekDay)
            .wednesday(weekDay)
            .thursday(weekDay)
            .friday(fri)
            .build();
    }

    private Place.ScheduleWeek monFriSatSchedule(Matcher matcher) {
        LocalTime weekDayTimeFrom = LocalTime.of(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        LocalTime weekDayTimeTill = LocalTime.of(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
        LocalTime satTimeFrom = LocalTime.of(Integer.parseInt(matcher.group(5)), Integer.parseInt(matcher.group(6)));
        LocalTime satTimeTill = LocalTime.of(Integer.parseInt(matcher.group(7)), Integer.parseInt(matcher.group(8)));

        Place.ScheduleDay weekDay = Place.ScheduleDay.builder()
            .from(weekDayTimeFrom)
            .till(weekDayTimeTill)
            .build();

        Place.ScheduleDay sat = Place.ScheduleDay.builder()
            .from(satTimeFrom)
            .till(satTimeTill)
            .build();

        return Place.ScheduleWeek.builder()
            .monday(weekDay)
            .tuesday(weekDay)
            .wednesday(weekDay)
            .thursday(weekDay)
            .friday(weekDay)
            .saturday(sat)
            .build();
    }
}
