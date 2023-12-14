package org.launchcode.tutorconnector.models;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Tutor extends AbstractEntity {


    @OneToMany
    @JoinColumn(name = "tutor_id")
    private List<TutorReview> tutorReviews = new ArrayList<>();

    @ManyToMany
    private List<Subject> subjects = new ArrayList<>();

    private String zoomLink;

    @ManyToMany
    private List<Student> students = new ArrayList<>();


    public Tutor() {}

    public Tutor(List<Subject> subjects, String zoomLink) {
        this.subjects = subjects;
        this.zoomLink = zoomLink;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<TutorReview> getTutorReviews() {
        return tutorReviews;
    }

    public void setTutorReviews(List<TutorReview> tutorReviews) {
        this.tutorReviews = tutorReviews;
    }

    public String getZoomLink() {
        return zoomLink;
    }

    public void setZoomLink(String zoomLink) {
        this.zoomLink = zoomLink;
    }
}
