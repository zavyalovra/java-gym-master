package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private HashMap<DayOfWeek, TreeMap<TimeOfDay, ArrayList<TrainingSession>>> timetable;

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

    public Map<TimeOfDay, ArrayList<TrainingSession>> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {

        return null;
    }

    public ArrayList<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        return null;
    }
}
