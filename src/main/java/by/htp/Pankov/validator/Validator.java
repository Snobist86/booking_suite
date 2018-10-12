package by.htp.Pankov.validator;

import java.util.List;

public interface Validator<T> {

    List<String> validate(T object);
}
