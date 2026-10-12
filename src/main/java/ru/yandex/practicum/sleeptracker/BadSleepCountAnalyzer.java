package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class BadSleepCountAnalyzer implements Function<List<SleepSession>, SleepAnalysisResult<Long>> {

    @Override
    public SleepAnalysisResult<Long> apply(List<SleepSession> sessions) {
        long badSleepCount = sessions.stream()
                .filter(session -> session.getQuality() == SleepQuality.BAD)
                .count();

        return new SleepAnalysisResult<>("Количество сессий с плохим качеством сна", badSleepCount);
    }
}
