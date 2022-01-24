package com.kduhomework.moviedb.entities;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class Actor {
    @NotNull(message = "Actor Name cannot be null")
    private String name;

    @Size(min = 10, max = 100, message = "Actor bio must be between 10 and 100 characters")
    private String bio;

    @Min(value = 5, message = "Actor must be at least five years old")
    @Max(value = 80, message = "Actor must be at most eighty years old")
    private int age;
}
