package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class MaxDurationAnalyzer implements Function<List<SleepSession>, SleepAnalysisResult<Long>> {

    @Override
    public SleepAnalysisResult<Long> apply(List<SleepSession> sessions) {

        long maxMinutes = sessions.stream()
                .mapToLong(SleepSession::getDurationInMinutes)
                .max()
                .orElse(0L);

        return new SleepAnalysisResult<>("Максимальная продолжительность сессии (в минутах)", maxMinutes);
    }

}

