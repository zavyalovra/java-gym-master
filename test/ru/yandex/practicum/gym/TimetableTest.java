package ru.yandex.practicum.gym;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TimetableTest {

    @Test
    void testGetTrainingSessionsForDaySingleSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        //Проверить, что за понедельник вернулось одно занятие
        assertEquals(1, timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY).size());
        //Проверить, что за вторник не вернулось занятий
        assertEquals(0, timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY).size());
    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessions() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        // Проверить, что за понедельник вернулось одно занятие
        assertEquals(1, timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY).size());
        // Проверить, что за четверг вернулось два занятия в правильном порядке: сначала в 13:00, потом в 20:00
        assertEquals(2, timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY).size());
        assertEquals(new TimeOfDay(13, 0),
                timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY).get(0).getTimeOfDay());
        assertEquals(new TimeOfDay(20, 0),
                timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY).get(1).getTimeOfDay());
        // Проверить, что за вторник не вернулось занятий
        assertEquals(0, timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY).size());
    }

    @Test
    void testGetTrainingSessionsForDayAndTime() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        //Проверить, что за понедельник в 13:00 вернулось одно занятие
        assertEquals(1,
                timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(13, 0)).size());
        //Проверить, что за понедельник в 14:00 не вернулось занятий
        assertEquals(0,
                timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(14, 0)).size());
    }

    @Test
    void testGetTrainingSessionsSameTime() {
        Timetable timetable = new Timetable();

        Group groupOne = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coachOne = new Coach("Васильев", "Николай", "Сергеевич");
        Group groupTwo = new Group("Акробатика для взрослых", Age.ADULT, 60);
        Coach coachTwo = new Coach("Сидоров", "Иван", "Петрович");

        timetable.addNewTrainingSession(new TrainingSession(groupOne, coachOne, DayOfWeek.MONDAY, new TimeOfDay(11, 0)));
        timetable.addNewTrainingSession(new TrainingSession(groupTwo, coachTwo, DayOfWeek.MONDAY, new TimeOfDay(11, 0)));

        //Проверить, что допустимо несколько тренировок в одно время
        assertEquals(2,
                timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(11, 0)).size());
    }

    @Test
    void testGetTrainingSessionsWrongTimeRequest() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        timetable.addNewTrainingSession(new TrainingSession(group, coach, DayOfWeek.MONDAY, new TimeOfDay(9, 0)));

        TimeOfDay timeRequest = new TimeOfDay(11, 0);

        //Проверить обработку запроса на несуществующее время
        assertNotEquals(timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY).getFirst().getTimeOfDay(), timeRequest);
    }

    @Test
    void testGetEmptyTimetable() {
        Timetable timetable = new Timetable();

        Group group = new Group("Занятие", Age.CHILD, 60);
        Coach coach = new Coach("Иванов", "Иван", "Иванович");

        //Проверить пустое ли расписание
        assertEquals(0, timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY).size());
    }
}
