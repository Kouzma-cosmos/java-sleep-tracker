package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChronotypeAnalyzerTest {
    @Test
    void shouldIdentifyLark() {
        ChronotypeSleeperAnalyzer analyzer = new ChronotypeSleeperAnalyzer();
        List<SleepSession> sessions = new ArrayList<>();

        sessions.add(new SleepSession(
                LocalDateTime.of(2026, 10, 1, 21, 30),
                LocalDateTime.of(2026, 10, 2, 6, 0),
                SleepQuality.GOOD
        ));
        assertEquals("ЖАВОРОНОК", analyzer.apply(sessions).getValue());
    }

    @Test
    void shouldIdentifyOwl() {
        ChronotypeSleeperAnalyzer analyzer = new ChronotypeSleeperAnalyzer();
        List<SleepSession> sessions = new ArrayList<>();

        sessions.add(new SleepSession(
                LocalDateTime.of(2026, 10, 1, 23, 30),
                LocalDateTime.of(2026, 10, 2, 9, 30),
                SleepQuality.GOOD
        ));
        assertEquals("СОВА", analyzer.apply(sessions).getValue());
    }

    @Test
    void shouldIdentifyPigeonAsMajority() {
        ChronotypeSleeperAnalyzer analyzer = new ChronotypeSleeperAnalyzer();
        List<SleepSession> sessions = new ArrayList<>();

        sessions.add(new SleepSession(LocalDateTime.of(2026,10,1,22,30),
                LocalDateTime.of(2026,10,2,7,30), SleepQuality.GOOD));
        sessions.add(new SleepSession(LocalDateTime.of(2026,10,2,22,30),
                LocalDateTime.of(2026,10,3,7,30), SleepQuality.GOOD));
        sessions.add(new SleepSession(LocalDateTime.of(2026,10,3,23,30),
                LocalDateTime.of(2026,10,4,9,30), SleepQuality.GOOD));

        assertEquals("ГОЛУБЬ", analyzer.apply(sessions).getValue());
    }
}
