package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxAndMinDurationAnalyzerTest {
    @Test
    void shouldReturnZeroForEmptyListInMinAndMax() {
        List<SleepSession> emptyList = new ArrayList<>();

        assertEquals(0L, new MinDurationAnalyzer().apply(emptyList).getValue());
        assertEquals(0L, new MaxDurationAnalyzer().apply(emptyList).getValue());
    }

    @Test
    void shouldCorrectlyCompareSessionsWithSameHoursButDifferentMinutes() {
        List<SleepSession> sessions = new ArrayList<>();

        sessions.add(new SleepSession(
                LocalDateTime.of(2026, 10, 1, 22, 0),
                LocalDateTime.of(2026, 10, 2, 5, 0),
                SleepQuality.GOOD
        ));

        sessions.add(new SleepSession(
                LocalDateTime.of(2026, 10, 2, 22, 0),
                LocalDateTime.of(2026, 10, 3, 5, 5),
                SleepQuality.GOOD
        ));

        assertEquals(420L, new MinDurationAnalyzer().apply(sessions).getValue(), "Минимум должен быть " +
                "420");
        assertEquals(425L, new MaxDurationAnalyzer().apply(sessions).getValue(), "Максимум должен быть" +
                " 425");
    }
}
