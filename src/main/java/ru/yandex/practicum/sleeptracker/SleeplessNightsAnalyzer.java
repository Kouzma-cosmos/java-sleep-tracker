package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SleeplessNightsAnalyzer implements Function<List<SleepSession>, SleepAnalysisResult<Integer>> {

    @Override
    public SleepAnalysisResult<Integer> apply(List<SleepSession> sessions) {

        if (sessions.isEmpty()) {
            return new SleepAnalysisResult<>("Количество бессонных ночей", 0);
        }

        sessions.sort(Comparator.comparing(SleepSession::getStartTime));

        LocalDateTime firstSessionStart = sessions.get(0).getStartTime();
        LocalDate firstDate;

        if (firstSessionStart.toLocalTime().isBefore(LocalTime.of(12, 0))) {
            firstDate = firstSessionStart.toLocalDate().minusDays(1);
        } else {
            firstDate = firstSessionStart.toLocalDate();
        }
        LocalDate lastDate = sessions.get(sessions.size() - 1).getEndTime().toLocalDate();

        int totalDays = Period.between(firstDate, lastDate).getDays() + 1;

        Set<LocalDate> nightsWithSleep = sessions.stream()
                .filter(s -> s.getStartTime().toLocalTime().isBefore(LocalTime.of(6, 0))
                        || s.getEndTime().toLocalTime().isAfter(LocalTime.of(0, 0))
                        && s.getStartTime().toLocalTime().isAfter(s.getEndTime().toLocalTime()))
                .map(session -> session.getStartTime().toLocalDate())
                .collect(Collectors.toSet());

        int sleeplessNights = totalDays - nightsWithSleep.size();

        return new SleepAnalysisResult<>("Количество бессонных ночей", sleeplessNights);
    }
}

