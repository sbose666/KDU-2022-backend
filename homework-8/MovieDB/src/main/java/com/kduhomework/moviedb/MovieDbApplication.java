package com.kduhomework.moviedb;


import com.kduhomework.moviedb.entities.Actor;
import com.kduhomework.moviedb.entities.Movie;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class MovieDbApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MovieDbApplicationConfig.class);
        Movie movie = context.getBean(Movie.class);
        Actor actor = context.getBean(Actor.class);
        Validator validator = context.getBean(Validator.class);
        Set<ConstraintViolation<Movie>> movieViolationSet = validator.validate(movie);
        Set<ConstraintViolation<Actor>> actorViolationSet = validator.validate(actor);
        System.err.println("Violations for movie: ");
        for (ConstraintViolation<Movie> violation : movieViolationSet) {
            System.err.println(violation.getMessage());
        }
        System.err.println("Violations for actor: ");
        for (ConstraintViolation<Actor> violation : actorViolationSet) {
            System.err.println(violation.getMessage());
        }
    }

}
