package org.launchcode.tutorconnector.models.data;

import org.launchcode.tutorconnector.models.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Integer> {
}
