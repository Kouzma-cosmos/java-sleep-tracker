package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChronotypeSleeperAnalyzer implements Function<List<SleepSession>, SleepAnalysisResult<String>> {

    @Override
    public SleepAnalysisResult<String> apply(List<SleepSession> sessions) {

        List<SleepSession> nightSessions = sessions.stream()
                .filter(s -> s.getStartTime().toLocalTime().isBefore(LocalTime.of(6, 0))
                        || s.getEndTime().toLocalTime().isAfter(LocalTime.of(0, 0))
                        && s.getStartTime().toLocalTime().isAfter(s.getEndTime().toLocalTime()))
                .collect(Collectors.toList());

        if (nightSessions.isEmpty()) {
            return new SleepAnalysisResult<>("Хронотип пользователя: ", " не определен ввиду отсутствия " +
                    "данных для метрики");
        }

        Map<SleeperType, Long> counts = nightSessions.stream()
                .collect(Collectors.groupingBy(session -> {
                    LocalTime bedtime = session.getStartTime().toLocalTime();
                    LocalTime wakeup = session.getEndTime().toLocalTime();

                    if (bedtime.isBefore(LocalTime.of(22, 0)) && wakeup.isBefore(LocalTime.of(7,
                            0))) {
                        return SleeperType.ЖАВОРОНОК;
                    } else if (bedtime.isAfter(LocalTime.of(23, 0)) && wakeup.isAfter(LocalTime.of(9,
                            0))) {
                        return SleeperType.СОВА;
                    } else {
                        return SleeperType.ГОЛУБЬ;
                    }
                }, Collectors.counting()));

        SleeperType finalType = counts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

        return new SleepAnalysisResult<>("Хронотип пользователя: ", finalType.name());
    }
}
