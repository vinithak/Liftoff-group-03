package org.launchcode.tutorconnector.models;

import jakarta.persistence.Entity;

@Entity
public class Student extends AbstractEntity{

    private String gradeLevel;

    public Student() {}

    public Student(String firstName, String lastName, String email, String password, TimeZone timeZone, String gradeLevel) {
        super(firstName, lastName, email, password, timeZone);
       this.gradeLevel = gradeLevel;
    }


    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

}
