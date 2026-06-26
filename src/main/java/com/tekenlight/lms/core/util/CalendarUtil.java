package com.tekenlight.lms.core.util;

import com.tekenlight.lms.module.holiday.entity.Holiday;
import com.tekenlight.lms.module.holiday.repository.HolidayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalendarUtilityService {

    private final HolidayRepository holidayRepository;
    public boolean isHoliday(LocalDate date, String location) {
        List<Holiday> holidays = holidayRepository.findByHolDateBetweenAndLocation(date, date, location);
        return !holidays.isEmpty();
    }
    public boolean isWeekend(LocalDate date) {
        return switch (date.getDayOfWeek()) {
            case SATURDAY, SUNDAY -> true;
            default -> false;
        };
    }
    public long getWorkingDaysBetween(LocalDate start, LocalDate end, String location) {
        List<LocalDate> holidays = holidayRepository
                .findByHolDateBetweenAndLocation(start, end, location)
                .stream()
                .map(Holiday::getHolDate)
                .collect(Collectors.toList());

        return start.datesUntil(end.plusDays(1))
                .filter(d ->!isWeekend(d)).filter(d ->!holidays.contains(d)).count();
    }
}