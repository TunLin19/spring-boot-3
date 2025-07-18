package com.example.tunlin.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class DobValidator implements ConstraintValidator<DobConstraint, LocalDate> {

    private int min;

    @Override // get gia tri
    public void initialize(DobConstraint constraintAnnotation) {
        min =constraintAnnotation.min();
    }

    @Override // xu ly data
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(localDate))
        return true;

        long years = ChronoUnit.YEARS.between(localDate,LocalDate.now());
        return years >= min;
    }
}
