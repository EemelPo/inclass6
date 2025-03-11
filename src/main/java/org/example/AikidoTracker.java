package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TrainingSession {
    private LocalDate date;
    private int duration;

    public TrainingSession(LocalDate date, int duration) {
        this.date = date;
        this.duration = duration;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getDuration() {
        return duration;
    }
}

class Tracker {
    private List<TrainingSession> sessions = new ArrayList<>();

    public void addSession(LocalDate date, int duration) {
        sessions.add(new TrainingSession(date, duration));
    }

    public int getTotalPracticeTime() {
        return sessions.stream().mapToInt(TrainingSession::getDuration).sum();
    }

    public int getSessionCount() {
        return sessions.size();
    }

    public boolean isEligible(LocalDate currentDate) {
        if (sessions.size() >= 100) {
            return true;
        }
        if (sessions.isEmpty()) {
            return false;
        }
        LocalDate firstSessionDate = sessions.get(0).getDate();
        long monthsBetween = ChronoUnit.MONTHS.between(firstSessionDate, currentDate);
        return monthsBetween >= 6;
    }
}

public class AikidoTracker {
    private static Tracker tracker = new Tracker();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("===== Aikido Practice Tracker =====");
            System.out.println("1. Add Practice Session");
            System.out.println("2. View Total Practice Time");
            System.out.println("3. Check Graduation Eligibility");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addPracticeSession();
                    break;
                case 2:
                    viewTotalPracticeTime();
                    break;
                case 3:
                    checkGraduationEligibility();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addPracticeSession() {
        System.out.print("Enter date (YYYY-MM-DD): ");
        String dateInput = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateInput);
        System.out.print("Enter duration (minutes): ");
        int duration = scanner.nextInt();
        scanner.nextLine();
        tracker.addSession(date, duration);
        System.out.println("Practice session added.");
    }

    private static void viewTotalPracticeTime() {
        int totalMinutes = tracker.getTotalPracticeTime();
        System.out.println("Total practice time: " + totalMinutes + " minutes.");
    }

    private static void checkGraduationEligibility() {
        LocalDate currentDate = LocalDate.now();
        if (tracker.isEligible(currentDate)) {
            System.out.println("You are eligible for Kyu graduation.");
        } else {
            System.out.println("You are not eligible for Kyu graduation.");
        }
    }
}