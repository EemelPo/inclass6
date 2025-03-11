package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AikidoTrackerTest {
    private Tracker tracker;

    @BeforeEach
    void setUp() {
        tracker = new Tracker();
    }

    @Test
    void testAddSession() {
        tracker.addSession(LocalDate.of(2025, 2, 11), 30);
        assertEquals(1, tracker.getSessionCount());
    }

    @Test
    void testGetTotalPracticeTime() {
        tracker.addSession(LocalDate.of(2024, 9, 4), 75);
        tracker.addSession(LocalDate.of(2025, 5, 6), 95);
        assertEquals(170, tracker.getTotalPracticeTime());
    }

    @Test
    void testIsEligibleForKyuGraduation() {
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        for (int i = 0; i < 100; i++) {
            tracker.addSession(startDate.plusDays(i), 60);
        }
        assertTrue(tracker.isEligible(LocalDate.of(2023, 4, 10))); // Adjust the date as needed

        tracker = new Tracker();
        tracker.addSession(LocalDate.of(2023, 1, 1), 60);
        assertFalse(tracker.isEligible(LocalDate.of(2023, 4, 10))); // Adjust the date as needed

        tracker.addSession(LocalDate.of(2023, 7, 1), 60);
        assertTrue(tracker.isEligible(LocalDate.of(2023, 7, 1).plusMonths(6))); // Adjust the date as needed
    }
}