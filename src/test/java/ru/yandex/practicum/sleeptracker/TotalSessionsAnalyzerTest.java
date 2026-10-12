package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TotalSessionsAnalyzerTest {

    @Test
    void shouldReturnZeroForEmptyList() {
        TotalSessionsAnalyzer analyzer = new TotalSessionsAnalyzer();
        List<SleepSession> emptyList = new ArrayList<>();

        SleepAnalysisResult<Long> result = analyzer.apply(emptyList);

        assertEquals(0L, result.getValue(), "Для пустого списка количество сессий должно быть 0");
        assertNotNull(result.getDescription(), "Описание не должно быть null");
    }

    @Test
    void shouldReturnCorrectCountForNonEmptyList() {
        TotalSessionsAnalyzer analyzer = new TotalSessionsAnalyzer();
        List<SleepSession> sessions = new ArrayList<>();

        sessions.add(new SleepSession(
                LocalDateTime.of(2026, 10, 1, 22, 0),
                LocalDateTime.of(2026, 10, 2, 6, 0),
                SleepQuality.GOOD
        ));

        SleepAnalysisResult<Long> result = analyzer.apply(sessions);

        assertEquals(1L, result.getValue(), "Количество сессий должно быть равно 1");
    }
}
