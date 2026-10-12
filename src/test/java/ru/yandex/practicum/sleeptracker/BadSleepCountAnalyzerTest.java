package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BadSleepCountAnalyzerTest {
    @Test
    void shouldReturnZeroWhenNoBadSleepSessionsExist() {
        List<SleepSession> sessions = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();
        sessions.add(new SleepSession(now, now.plusHours(8), SleepQuality.GOOD));
        sessions.add(new SleepSession(now, now.plusHours(7), SleepQuality.NORMAL));

        assertEquals(0L, new BadSleepCountAnalyzer().apply(sessions).getValue());
    }

    @Test
    void shouldCountOnlyBadSleepSessions() {
        List<SleepSession> sessions = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        sessions.add(new SleepSession(now, now.plusHours(5), SleepQuality.BAD)); // +1
        sessions.add(new SleepSession(now, now.plusHours(8), SleepQuality.GOOD));
        sessions.add(new SleepSession(now, now.plusHours(4), SleepQuality.BAD)); // +2

        assertEquals(2L, new BadSleepCountAnalyzer().apply(sessions).getValue(), "Должно найти ровно 2 плохие сессии");
    }
}
