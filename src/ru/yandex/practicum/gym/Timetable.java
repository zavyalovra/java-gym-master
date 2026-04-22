package ru.yandex.practicum.gym;

import java.util.*;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.ArrayList;

public class Timetable {

    private HashMap<DayOfWeek, TreeMap<TimeOfDay, ArrayList<TrainingSession>>> timetable;

    public Timetable() {
        this.timetable = new HashMap<>();
    }

    public void addNewTrainingSession(TrainingSession trainingSession) {
        //сохраняем занятие в расписании
        DayOfWeek dayOfWeek = trainingSession.getDayOfWeek();
        TimeOfDay timeOfDay = trainingSession.getTimeOfDay();

        // Проверяем существует ли мапа для указанного дня, если нет, то создаем ее
        if (!timetable.containsKey(dayOfWeek)) {
            timetable.put(dayOfWeek, new TreeMap<>());
        }

        TreeMap<TimeOfDay, ArrayList<TrainingSession>> dayMap = timetable.get(dayOfWeek);

        // Проверяем существует ли список тренировок для указанного дня, если нет, то создаем его
        if (!dayMap.containsKey(timeOfDay)) {
            dayMap.put(timeOfDay, new ArrayList<>());
        }

        dayMap.get(timeOfDay).add(trainingSession);
    }

    public int getCountByCoaches(Coach coach) {

        return 0;
    }

    public void clearTimetable() {
        timetable.clear();
    }

    public ArrayList<TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        ArrayList<TrainingSession> trainingSessionsForDay = new ArrayList<>();
        TreeMap<TimeOfDay, ArrayList<TrainingSession>> dayMap = timetable.get(dayOfWeek);

        if (dayMap == null) {
            return trainingSessionsForDay;
        }

        for (TimeOfDay timeOfDay : dayMap.navigableKeySet()) {
            trainingSessionsForDay.addAll(dayMap.get(timeOfDay));
        }

        return trainingSessionsForDay;
    }

    public ArrayList<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        TreeMap<TimeOfDay, ArrayList<TrainingSession>> dayMap = timetable.get(dayOfWeek);

        if (dayMap == null) {
            return new ArrayList<>();
        }

        return dayMap.getOrDefault(timeOfDay, new ArrayList<>());
    }
}
