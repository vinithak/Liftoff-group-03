package org.launchcode.tutorconnector.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Student extends AbstractEntity{

    @ManyToMany(mappedBy = "students")
    private List<Tutor> tutors = new ArrayList<>();
  
    private GradeLevel gradeLevel;

    public Student() {}
  
    public Student(String firstName, String lastName, String email, String password, TimeZone timeZone, GradeLevel gradeLevel) {
      super(firstName, lastName, email, password, timeZone);
      this.gradeLevel = gradeLevel;
     }

    public Student(String email, String password) {
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
