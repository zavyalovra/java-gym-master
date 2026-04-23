package ru.yandex.practicum.gym;

public class CounterOfTrainings implements Comparable<CounterOfTrainings> {
    private final Coach coach;
    private final int countOfTrainings;

    public CounterOfTrainings(Coach coach, int countOfTrainings) {
        this.coach = coach;
        this.countOfTrainings = countOfTrainings;
    }

    public Coach getCoach() {
        return coach;
    }

    public int getCountOfTrainings() {
        return countOfTrainings;
    }

    @Override
    public int compareTo(CounterOfTrainings coach) {
        return Integer.compare(coach.countOfTrainings, countOfTrainings);
    }

    @Override
    public String toString() {
        return "\n" + coach + ", " + countOfTrainings + " занятий";
    }
}
