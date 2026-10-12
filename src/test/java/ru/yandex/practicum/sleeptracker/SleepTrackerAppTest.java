package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class SleepTrackerAppTest {
    @Test
    void shouldRunAllAnalyzersAndReturnCorrectOutputSize() {
        SleepAnalyticsOrchestrator orchestrator = new SleepAnalyticsOrchestrator();
        List<SleepSession> sessions = new ArrayList<>();

        sessions.add(new SleepSession(
                LocalDateTime.of(2026, 10, 1, 23, 0),
                LocalDateTime.of(2026, 10, 2, 7, 0),
                SleepQuality.GOOD
        ));

        List<SleepAnalysisResult<?>> results = orchestrator.apply(sessions);

        assertEquals(7, results.size(), "Дирижёр должен вернуть результаты ровно от 7 анализаторов");

        assertNotNull(results.get(0).getDescription());
        assertNotNull(results.get(0).getValue());
    }














}