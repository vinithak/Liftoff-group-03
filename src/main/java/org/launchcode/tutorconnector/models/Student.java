package org.launchcode.tutorconnector.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student extends AbstractEntity{



    @ManyToMany(mappedBy = "students")
    private List<Tutor> tutors = new ArrayList<>();

    public Student() {}

    private GradeLevel gradeLevel;

    public Student(GradeLevel gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public List<Tutor> getTutors() {
        return tutors;
    }

    public void setTutors(List<Tutor> tutors) {
        this.tutors = tutors;
    }

    public GradeLevel getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(GradeLevel gradeLevel) {
        this.gradeLevel = gradeLevel;
    }
}
