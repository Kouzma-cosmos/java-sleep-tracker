package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDateTime;


public class SleepSession {
    final LocalDateTime startTime;
    final LocalDateTime endTime;
    final SleepQuality quality;


    public SleepSession(LocalDateTime startTime, LocalDateTime endTime, SleepQuality quality) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.quality = quality;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public SleepQuality getQuality() {
        return quality;
    }


    public long getDurationInMinutes() {
        Duration duration = Duration.between(startTime, endTime);
        return duration.toMinutes();
    }

}
