package ru.yandex.practicum.sleeptracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SleepAnalyticsOrchestrator implements Function<List<SleepSession>, List<SleepAnalysisResult<?>>> {

    private final List<Function<List<SleepSession>, ?>> analyzers = List.of(
            new TotalSessionsAnalyzer(),
            new MinDurationAnalyzer(),
            new MaxDurationAnalyzer(),
            new AverageDurationAnalyzer(),
            new BadSleepCountAnalyzer(),
            new SleeplessNightsAnalyzer(),
            new ChronotypeSleeperAnalyzer()
    );

    @Override
    public List<SleepAnalysisResult<?>> apply(List<SleepSession> sessions) {
        return analyzers.stream()
                .map(analyzer -> (SleepAnalysisResult<?>) analyzer.apply(new ArrayList<>(sessions)))
                .collect(Collectors.toList());
    }
}
