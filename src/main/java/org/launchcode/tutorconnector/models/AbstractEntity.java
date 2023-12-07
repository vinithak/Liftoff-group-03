package org.launchcode.tutorconnector.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;


public abstract class AbstractEntity {


        @NotNull
        @Size(min = 3, max = 100)
        private String name;




}
