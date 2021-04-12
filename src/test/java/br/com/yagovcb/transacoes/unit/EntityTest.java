package br.com.yagovcb.transacoes.unit;

import javax.persistence.Id;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.springframework.test.util.ReflectionTestUtils;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

@DisplayName("Entidades, DTOs e Mappers")
public class EntityTest {

    private final List<String> METHODS_TO_IGNORE = Arrays.asList();
    private final List<String> CLASSES_TO_IGNORE = Arrays.asList();
    private final Set<Class<? extends Object>> classAllMethods;

    public EntityTest(Set<Class<? extends Object>> classAllMethods) {
        this.classAllMethods = new HashSet<>();
        classAllMethods.addAll(new Reflections("br.com.yagovcb.transacoes.services.dto", new SubTypesScanner(false)).getSubTypesOf(Object.class));
    }

    @Test
    @DisplayName("Contrutores e Métodos das Entidades")
    public void testarEntidades() throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException {
        for (Class classe : classAllMethods) {
            if (ignoreClass(classe)) {
                continue;
            }
            testarTodosConstrutores(classe);
            testarTodosMetodos(classe, getInstance(classe));
        }
    }

    private Object getInstance(Class classe) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor = classe.getDeclaredConstructors()[0];
        constructor.setAccessible(Boolean.TRUE);
        Object instancia;
        if (constructor.getParameters().length > 0) {
            Object[] lObject = new Object[constructor.getParameters().length];
            instancia = constructor.newInstance(lObject);
        } else {
            instancia = constructor.newInstance();
        }
        return instancia;
    }

    private boolean ignoreClass(Class classe) {
        if (classe.getDeclaredConstructors().length == 0) {
            return true;
        }
        if (classe.getName().contains("Test")) {
            return true;
        }
        if (classe.getName().endsWith("_")) {
            return true;
        }
        for (String classToIgnore : CLASSES_TO_IGNORE) {
            if (classe.getName().contains(classToIgnore)) {
                return true;
            }
        }
        return false;
    }

    private void testarTodosConstrutores(Class classe) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        for (Constructor constructor : classe.getDeclaredConstructors()) {
            constructor.setAccessible(Boolean.TRUE);
            List parametros = new ArrayList<>();
            for (int i = 0; i < constructor.getParameterCount(); i++) {
                parametros.add(null);
            }
            constructor.newInstance(parametros.toArray());
        }
    }

    private void testarTodosMetodos(Class classe, Object instancia) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        for (Method method : obterTodosMetodosTestaveis(classe)) {
            List parameters = new ArrayList<>();
            for (Class type : method.getParameterTypes()) {
                if (type.equals(classe)) {
                    setIdField(classe, instancia);
                    parameters.add(instancia);
                } else {
                    parameters.add(null);
                }
            }
            if (!Modifier.isStatic(method.getModifiers())) {
                method.setAccessible(true);
                if (isMethodInIgnoreList(method)) {
                    continue;
                }
                method.invoke(instancia, parameters.toArray());
                if ("equals".equals(method.getName())) {
                    method.invoke(instancia, "");
                    method.invoke(instancia, instancia);
                    method.invoke(instancia, getInstance(classe));

                    equalsWithId(method, instancia, classe, 1L, 1L);
                    equalsWithId(method, instancia, classe, 1L, 2L);
                    equalsWithId(method, instancia, classe, 1L, null);
                    equalsWithId(method, instancia, classe, null, 2L);
                }
            }
        }
    }

    private void equalsWithId(Method method, Object instancia, Class classe, Long p1, Long p2) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object instancia2 = getInstance(classe);
        ReflectionTestUtils.setField(instancia, "id", p1);
        ReflectionTestUtils.setField(instancia2, "id", p2);
        method.invoke(instancia, instancia2);
    }

    private boolean isMethodInIgnoreList(Method method) {
        for (String ignoredMethod : METHODS_TO_IGNORE) {
            if (method.getName().contains(ignoredMethod)) {
                return true;
            }
        }
        return false;
    }

    private void setIdField(Class classe, Object instancia) throws IllegalAccessException {
        for (Field field : classe.getDeclaredFields()) {
            if (field.getDeclaredAnnotation(Id.class) != null) {
                field.setAccessible(Boolean.TRUE);
                field.set(instancia, 1L);
            }
        }
    }

    private List<Method> obterTodosMetodosTestaveis(Class pClasse) {
        List<Method> lMetodos = new ArrayList<>();
        lMetodos.addAll(Arrays.asList(pClasse.getDeclaredMethods()));
        if (!Arrays.asList(Object.class, Exception.class, RuntimeException.class).contains(pClasse.getSuperclass())) {
            lMetodos.addAll(obterTodosMetodosTestaveis(pClasse.getSuperclass()));
        }
        return lMetodos.stream().filter(method -> !method.isSynthetic()).collect(Collectors.toList());
    }
}
