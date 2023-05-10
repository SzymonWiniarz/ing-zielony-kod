package pl.simcode.ing.exceptions;

public class ValidationException extends RuntimeException {

    public ValidationException(Class<?> propertyContainerClass, String property, Object value) {
        super("Validation failed for %s.%s. Value %s is invalid".formatted(propertyContainerClass.getSimpleName(), property, value));
    }

}
