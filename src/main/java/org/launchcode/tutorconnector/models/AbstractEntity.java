package org.launchcode.tutorconnector.models;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;


@MappedSuperclass
public abstract class AbstractEntity {

        @Id
        @GeneratedValue
        private int id;

        @NotNull(message = "first name is required")
        @Size(min = 3, max = 100, message = "first name must be between 3 and 100 characters long")
        private String fname;

        @NotNull(message = "last name is required")
        @Size(min = 3, max = 100, message = "last name must be between 3 and 100 characters long")
        private String lname;

        @NotNull(message = "name is required")
        @Size(min = 3, max = 100, message = "name must be between 3 and 100 characters long")
        private String name;

        @Email(message = "Invalid email. Try again.")
        private String email;

        private Timezone timezone;

        @NotNull(message = "image is required")
        @Size(min = 30, max = 150, message = "image path must be between 3 and 100 characters long")
        private String imagePath;

        public int getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getFname() {
                return fname;
        }

        public void setFname(String fname) {
                this.fname = fname;
        }

        public String getLname() {
                return lname;
        }

        public void setLname(String lname) {
                this.lname = lname;
        }

        @Override
        public String toString() {
                return name;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                AbstractEntity that = (AbstractEntity) o;
                return id == that.id;
        }

        @Override
        public int hashCode() {
                return Objects.hash(id);
        }

}
