package br.com.microsservice.mercadoms.domain;

import java.lang.reflect.Field;
import java.util.Arrays;

public interface Entidade {

    /**
     * Realiza o merge dos campos não nulos do objeto como deve ser
     * com esta entidade, ou seja, atualiza os campos desta entidade
     * com os valores não nulos do parâmetro de referência.
     *
     * @param toBe O objeto que será utilizado para realizar a
     *             atualização desta entidade
     */
    default void modificar(Entidade toBe) {
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
