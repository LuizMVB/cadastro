package br.com.microsservice.mercado.domain;

import java.lang.reflect.Field;
import java.util.Arrays;

public abstract class Entidade<T> {

    public void mergeNonNullProperties(T toBe) {
        Arrays.stream(toBe.getClass().getDeclaredFields()).forEach(toBeField -> {
            try {
                Field asIsField = this.getClass().getDeclaredField(toBeField.getName());

                toBeField.setAccessible(true);
                asIsField.setAccessible(true);

                Object toBeValue = toBeField.get(toBe);

                if(toBeValue == null) {
                    return;
                }

                asIsField.set(this, toBeValue);
            } catch (NoSuchFieldException | IllegalAccessException ignore) {}
        });
    }

}
