package org.launchcode.tutorconnector.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Entity
public class Tutor extends AbstractEntity {


    @OneToMany
    @JoinColumn(name = "tutor_id")
    private List<TutorReview> tutorReviews = new ArrayList<>();

    @ManyToMany
    private List<Subject> subjects = new ArrayList<>();

    private String zoomLink;

    private ArrayList<String> qualifications;

    private String availability;

    @ManyToMany
    private List<Student> students = new ArrayList<>();


    public Tutor() {
    }


    public Tutor(String firstName, String lastName, String email, String password, TimeZone timeZone, ArrayList<String> qualifications, List<Subject> subjects, String availability) {
        super(firstName, lastName, email, password, timeZone);
        this.qualifications = qualifications;
        this.subjects = subjects;
        this.availability = availability;
    }

    public Tutor(String email, String password) {
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

    public ArrayList<String> getQualifications() {
        return qualifications;
    }

    public void setQualifications(ArrayList<String> qualifications) {
        this.qualifications = qualifications;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

}
