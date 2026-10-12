package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class MinDurationAnalyzer implements Function<List<SleepSession>, SleepAnalysisResult<Long>> {

    @Override
    public SleepAnalysisResult<Long> apply(List<SleepSession> sessions) {

        long minMinutes = sessions.stream()
                .mapToLong(SleepSession::getDurationInMinutes)
                .min()
                .orElse(0L);

        return new SleepAnalysisResult<>("Минимальная продолжительность сессии (в минутах)", minMinutes);
    }

}
