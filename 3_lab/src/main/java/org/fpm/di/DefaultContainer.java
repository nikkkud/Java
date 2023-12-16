package org.fpm.di;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class DefaultContainer implements Container {
    private final Map<Class<?>, Object> singletons = new HashMap<>();
    private final Map<Class<?>, Object> instances = new HashMap<>();
    private final Map<Class<?>, Class<?>> implementationClasses = new HashMap<>();

    public <T> void bind(Class<T> clazz) {
        implementationClasses.put(clazz, null);
    }

    public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {
        implementationClasses.put(clazz, implementation);
    }

    public <T> void bind(Class<T> clazz, T instance) {
        bind(clazz);
        instances.put(clazz, instance);
    }

    private <T> Class<? extends T> reciveImplClass(Class<T> clazz) {
        /*
        * recive Implementation class
        * */
        Class<? extends T> current = clazz;

        while (true) {
            if (!implementationClasses.containsKey(current)) {
                throw new RuntimeException("No binding for " + current);
            }

            Class<?> impl = implementationClasses.get(current);

            if (impl == null) {
                return current;
            }

            current = (Class<? extends T>)impl;
        }
    }

    @Override
    public <T> T getComponent(Class<T> clazz) {
        Class<? extends T> impl = reciveImplClass(clazz);

        if (instances.containsKey(impl)) {
            return (T) instances.get(impl);
        }

        if (impl.isAnnotationPresent(Singleton.class)) {
            synchronized (singletons) {
                if (!singletons.containsKey(impl)) {
                    T result = createComponent(impl);
                    singletons.put(impl, result);
                }
                return (T) singletons.get(impl);
            }
        }

        return createComponent(impl);
    }


    private <T> T createComponent(Class<T> clazz) {
        try {
            Constructor<T> cons = correctConstructor(clazz);
            Object[] params = new Object[cons.getParameterCount()];

            for (int i = 0; i < params.length; i++) {
                Class<?> paramType = cons.getParameterTypes()[i];
                params[i] = getComponent(paramType);
            }

            return cons.newInstance(params);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to create component", e);
        }
    }

    private static <T> Constructor<T> correctConstructor(Class<T> clazz) {
        /*
        * This class find constructor
        * */
        Constructor<T>[] constructors = (Constructor<T>[]) clazz.getConstructors();

        for (Constructor<T> constructor : constructors) {
            if (constructor.getParameterCount() == 0 || constructor.isAnnotationPresent(Inject.class)) {
                return constructor;
            }
        }

        throw new RuntimeException("Cannot find constructor for " + clazz);
    }

}
