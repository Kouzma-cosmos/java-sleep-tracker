package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AverageDurationAnalyzerTest {

    @Test
    void shouldReturnZeroForEmptyListInAverage() {
        assertEquals(0, new AverageDurationAnalyzer().apply(new ArrayList<>()).getValue());
    }

    @Test
    void shouldCalculateFractionalAverageForFiveSessions() {
        List<SleepSession> sessions = new ArrayList<>();

        sessions.add(createSessionForDayWithMinutes(1, 37));
        sessions.add(createSessionForDayWithMinutes(2, 120));
        sessions.add(createSessionForDayWithMinutes(3, 201));
        sessions.add(createSessionForDayWithMinutes(4, 91));
        sessions.add(createSessionForDayWithMinutes(5, 69));

        long avg = new AverageDurationAnalyzer().apply(sessions).getValue();

        assertEquals(104L, avg, "Среднее должно округляться по математическим правилам");
    }

    private SleepSession createSessionForDayWithMinutes(int day, int minutes) {
        LocalDateTime start = LocalDateTime.of(2026, 10, day, 22, 0);
        return new SleepSession(start, start.plusMinutes(minutes), SleepQuality.NORMAL);
    }
}
