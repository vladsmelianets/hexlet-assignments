package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
public final class Validator {

    public static List<String> validate(Address address) {
        Field[] fields = address.getClass().getDeclaredFields();
        return Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(NotNull.class))
                .filter(field -> violatesNotNull(address, field))
                .map(Field::getName)
                .toList();
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Field[] fields = address.getClass().getDeclaredFields();
        return Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(NotNull.class) || field.isAnnotationPresent(MinLength.class))
                .map(field -> Map.entry(field.getName(), getViolations(address, field)))
                .filter(entry -> !entry.getValue()
                        .isEmpty())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static boolean violatesNotNull(Address address, Field field) {
        Object value;
        try {
            field.setAccessible(true);
            value = field.get(address);
            field.setAccessible(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return value == null;
    }

    private static List<String> getViolations(Object instance, Field field) {
        List<String> violations = new ArrayList<>();
        String value;
        try {
            field.setAccessible(true);
            value = (String) field.get(instance);
            field.setAccessible(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (field.isAnnotationPresent(NotNull.class) && value == null) {
            violations.add("can not be null");
        }
        if (field.isAnnotationPresent(MinLength.class)) {
            int minLength = field.getAnnotation(MinLength.class).minLength();
            if (value.length() < minLength) {
                violations.add("length less than " + minLength);
            }
        }
        return violations;
    }
}
// END
