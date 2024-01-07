package org.launchcode.tutorconnector.models;


import java.util.ArrayList;

public class Search {


    public static ArrayList<Tutor> findTutorsByColumnAndValue(String column, String value, Iterable<Tutor> allTutors) {

        ArrayList<Tutor> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")){
            return (ArrayList<Tutor>) allTutors;
        }

        for (Tutor tutor : allTutors) {

            String aValue = getTutorFieldValue(tutor, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(tutor);
            }
        }

        return results;
    }

    public static String getTutorFieldValue(Tutor tutor, String fieldName){
        String theValue;
        if (fieldName.equals("name")){
            theValue = tutor.getFirstName();
        } else if (fieldName.equals("subject")){
            theValue = tutor.getSubjects().toString();
        } else {
            theValue = tutor.getFirstName();
        }

        return theValue;
    }


    public static ArrayList<Student> findStudentsByColumnAndValue(String column, String value, Iterable<Student> allStudents) {

        ArrayList<Student> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")){
            return (ArrayList<Student>) allStudents;
        }

        for (Student student : allStudents) {

            String aValue = getStudentFieldValue(student, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(student);
            }
        }

        return results;
    }

    public static String getStudentFieldValue(Student student, String fieldName){
        String theValue;
        if (fieldName.equals("name")){
            theValue = student.getFirstName();
        } else {
            theValue = student.getFirstName();
        }

        return theValue;
    }

}
