package org.launchcode.tutorconnector.models.data;

import org.launchcode.tutorconnector.models.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends CrudRepository<Subject,Integer> {
}
