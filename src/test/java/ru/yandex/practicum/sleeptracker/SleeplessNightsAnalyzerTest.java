package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleeplessNightsAnalyzerTest {
    @Test
    void shouldReturnZeroForEmptyListForThis() {
        SleeplessNightsAnalyzer analyzer = new SleeplessNightsAnalyzer();
        assertEquals(0, analyzer.apply(new ArrayList<>()).getValue());
    }

    @Test
    void shouldCorrectlyCalculateWhenMonthChanges() {
        SleeplessNightsAnalyzer analyzer = new SleeplessNightsAnalyzer();
        List<SleepSession> sessions = new ArrayList<>();

        sessions.add(new SleepSession(
                LocalDateTime.of(2026, 10, 31, 23, 0),
                LocalDateTime.of(2026, 11, 1, 7, 0),
                SleepQuality.GOOD
        ));

        assertEquals(1, analyzer.apply(sessions).getValue(), "Должна быть 1 бессонная ночь при " +
                "переходе месяцев");
    }

    @Test
    void shouldShiftStartDateIfSleepStartedBeforeNoon() {
        SleeplessNightsAnalyzer analyzer = new SleeplessNightsAnalyzer();
        List<SleepSession> sessions = new ArrayList<>();

        sessions.add(new SleepSession(
                LocalDateTime.of(2026, 10, 2, 4, 0),
                LocalDateTime.of(2026, 10, 2, 8, 0),
                SleepQuality.NORMAL
        ));

        assertEquals(1, analyzer.apply(sessions).getValue(), "Правило 12 дня должно сдвинуть дату " +
                "старта");
    }

    @Test
    void shouldNotDuplicateSameNightIfUserWokeUp() {
        SleeplessNightsAnalyzer analyzer = new SleeplessNightsAnalyzer();
        List<SleepSession> sessions = new ArrayList<>();

        sessions.add(new SleepSession(
                LocalDateTime.of(2026, 10, 1, 23, 0),
                LocalDateTime.of(2026, 10, 2, 2, 0),
                SleepQuality.NORMAL
        ));

        sessions.add(new SleepSession(
                LocalDateTime.of(2026, 10, 2, 3, 0),
                LocalDateTime.of(2026, 10, 2, 6, 0),
                SleepQuality.NORMAL
        ));

        sessions.clear();

        sessions.add(new SleepSession(
                LocalDateTime.of(2026, 10, 2, 1, 0),
                LocalDateTime.of(2026, 10, 2, 3, 0),
                SleepQuality.NORMAL
        ));
        sessions.add(new SleepSession(
                LocalDateTime.of(2026, 10, 2, 4, 0),
                LocalDateTime.of(2026, 10, 2, 5, 30),
                SleepQuality.NORMAL
        ));
        sessions.add(new SleepSession(
                LocalDateTime.of(2026, 10, 2, 23, 0),
                LocalDateTime.of(2026, 10, 3, 6, 0),
                SleepQuality.GOOD
        ));

        assertDoesNotThrow(() -> analyzer.apply(sessions));
    }

    @Test
    void shouldReturnDataMissingForEmptyList() {
        ChronotypeSleeperAnalyzer analyzer = new ChronotypeSleeperAnalyzer();
        List<SleepSession> emptyList = new ArrayList<>();
        assertEquals(" не определен ввиду отсутствия данных для метрики",
                analyzer.apply(emptyList).getValue());
    }
}
