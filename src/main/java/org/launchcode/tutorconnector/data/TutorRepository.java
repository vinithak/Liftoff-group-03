package org.launchcode.tutorconnector.data;

import org.launchcode.tutorconnector.models.Tutor;
import org.springframework.data.repository.CrudRepository;

public interface TutorRepository extends CrudRepository<Tutor, Integer> {

    Tutor findByEmail(String email);

}
