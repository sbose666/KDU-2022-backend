package com.kduhomework.moviedb;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class CustomDateValidClass implements ConstraintValidator<CustomDateValidAnnotation, LocalDate> {
    @Override
    public boolean isValid(LocalDate dateField, ConstraintValidatorContext constraintValidatorContext) {
        return !dateField.isAfter(LocalDate.now());
    }
}
