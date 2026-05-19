package ru.yandex.practicum.gym;


import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

import java.util.List;

public class CoachListTest {
    Timetable timetable = new Timetable();
    TrainingSession trainingSession;

    Coach coach1 = new Coach("Васильев", "Николай", "Сергеевич");
    Coach coach2 = new Coach("Сидоров", "Иван", "Петрович");
    Coach coach3 = new Coach("Иванов", "Иван", "Иванович");

    Group group1 = new Group("Акробатика для детей", Age.CHILD, 60);
    Group group2 = new Group("Акробатика для взрослых", Age.ADULT, 60);

    @Test
    void shouldReturnEmptyListWhenNoTrainings() {
        assertTrue(timetable.getCountByCoaches().isEmpty(), "Список должен быть пустым, если тренировок нет");
    }

    @Test
    void getCountOfTrainings() {
        trainingSession = new TrainingSession(group1, coach1, DayOfWeek.TUESDAY, new TimeOfDay(10, 0));
        timetable.addNewTrainingSession(trainingSession);

        //Проверить количество тренировок
        assertEquals(1, timetable.getCountByCoaches().get(0).getCountOfTrainings());
    }

    @Test
    void shouldReturnSortedCountsListOfTrainings() {
        for (DayOfWeek day : DayOfWeek.values()) {
            for (int i = 9; i < 12; i++) {
                trainingSession = new TrainingSession(group1, coach1, day, new TimeOfDay(i, 0));
                timetable.addNewTrainingSession(trainingSession);
            }
            for (int i = 10; i < 19; i++) {
                trainingSession = new TrainingSession(group2, coach2, day, new TimeOfDay(++i, 0));
                timetable.addNewTrainingSession(trainingSession);
            }
            for (int i = 8; i < 22; i++) {
                trainingSession = new TrainingSession(group2, coach3, day, new TimeOfDay(i, 0));
                timetable.addNewTrainingSession(trainingSession);
                i = i + 6;
            }
        }
        System.out.println("Список занятости тренеров: " + timetable.getCountByCoaches());

        //Проверить, проверить сортировку по убыванию
        assertEquals(35, timetable.getCountByCoaches().get(0).getCountOfTrainings());
        assertEquals(21, timetable.getCountByCoaches().get(1).getCountOfTrainings());
        assertEquals(14, timetable.getCountByCoaches().get(2).getCountOfTrainings());
    }
}
