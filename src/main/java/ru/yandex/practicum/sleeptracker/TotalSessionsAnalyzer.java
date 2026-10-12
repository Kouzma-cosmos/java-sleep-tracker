package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class TotalSessionsAnalyzer implements Function<List<SleepSession>, SleepAnalysisResult<Long>> {

    @Override
    public SleepAnalysisResult<Long> apply(List<SleepSession> sessions) {
        long count = sessions.size();

        return new SleepAnalysisResult<>("Общее количество сессий сна за период", count);
    }
}
