package ua.com.foxminded.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class University {
    private List<ClassItem> timetable = new ArrayList<>();
    private List<Faculty> faculties = new ArrayList<>();

    public List<ClassItem> getTimetable() {
        return timetable;
    }

    public void setTimetable(List<ClassItem> timetable) {
        this.timetable = timetable;
    }

    public void addTimetable(ClassItem classItem) {
        timetable.add(classItem);
    }

    public void removeTimetable(ClassItem classItem) {
        timetable.remove(classItem);
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public void addFaculty(Faculty faculty) {
        faculties.add(faculty);
    }

    public void removeFaculty(Faculty faculty) {
        faculties.remove(faculty);
    }

    public List<ClassItem> displayScheduleTeacher(LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd,
            Teacher teacher) {
        List<ClassItem> sheduleTeacher = new ArrayList<>();

        for (ClassItem lesson : timetable) {
            if ((teacher.equals(lesson.getTeacher())) && (isLessonInPeriod(dateTimeStart, dateTimeEnd, lesson))) {
                sheduleTeacher.add(lesson);
            }
        }
        return sheduleTeacher;
    }

    public List<ClassItem> displayScheduleStudent(LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd,
            Student student) {
        List<ClassItem> sheduleStudent = new ArrayList<>();

        for (ClassItem lesson : timetable) {
            if (((lesson.getGroup().getStudents().contains(student)))
                    && (isLessonInPeriod(dateTimeStart, dateTimeEnd, lesson))) {
                sheduleStudent.add(lesson);
            }
        }
        return sheduleStudent;
    }

    public boolean isLessonInPeriod(LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, ClassItem lesson) {
        if ((lesson.getDateTime().isAfter(dateTimeStart)) && (lesson.getDateTime().isBefore(dateTimeEnd))) {
            return true;
        } else {
            return false;
        }
    }

}
