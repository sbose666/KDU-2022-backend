package com.kduhomework.moviedb;

import com.kduhomework.moviedb.entities.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Locale;

@Configuration
@ComponentScan
public class MovieDbApplicationConfig {
    @Bean(name = "movie")
    @Scope("prototype")
    public Movie createMovie() {
        Movie movie = new Movie();
        movie.setTitle("John Wick");
        movie.setSynopsis("");
        movie.setLengthInHours(2);
        movie.setReleased(true);
        movie.setReleasedDate(LocalDate.parse("2022-12-21"));
        return movie;
    }

    @Bean(name = "actor")
    @Scope("prototype")
    public Actor createActor() {
        return new Actor();
    }

    @Bean
    public Validator createValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
}
