package net.debreczeni.validation;

import net.debreczeni.exception.ValidationException;

public interface Validator<T> {
    void validate(T object) throws ValidationException;
}
