package org.launchcode.tutorconnector.models.data;

import org.launchcode.tutorconnector.models.Login;
import org.springframework.data.repository.CrudRepository;

public interface LoginRepository extends CrudRepository<Login, String> {
}
