package com.kduhomework.moviedb.entities;

import com.kduhomework.moviedb.CustomDateValidAnnotation;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class Movie {
    @NotNull(message = "Movie Title cannot be null")
    private String title;

    @AssertTrue(message = "Movie must have already been released")
    private boolean isReleased;

    @Size(min = 10, max = 100, message = "Movie Synopsis must be between 10 and 100 characters")
    private String synopsis;

    @Min(value = 1, message = "Movie must be at least an hour long")
    @Max(value = 4, message = "Movie must be at max four hours long")
    private int lengthInHours;

    @CustomDateValidAnnotation(message = "Released Year must be before today")
    private LocalDate releasedDate;
}
