package org.launchcode.tutorconnector.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class TutorReview extends AbstractEntity{

    @ManyToOne
    private Tutor tutor;
    private String subject;

    private String review;

    public TutorReview() {
    }

    public TutorReview(Tutor tutor, String subject, String review) {
        this.tutor = tutor;
        this.subject = subject;
        this.review = review;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
