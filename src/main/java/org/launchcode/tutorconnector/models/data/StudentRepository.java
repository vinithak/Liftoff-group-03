package org.launchcode.tutorconnector.models.data;

import org.launchcode.tutorconnector.models.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {
}
