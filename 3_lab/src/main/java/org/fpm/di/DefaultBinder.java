package org.fpm.di;

public class DefaultBinder implements Binder {
    private final DefaultContainer container;

    public DefaultBinder(DefaultContainer container) {
        this.container = container;
    }

    @Override
    public <T> void bind(Class<T> clazz) {
        container.bind(clazz);
    }

    @Override
    public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {
        container.bind(clazz, implementation);
    }

    @Override
    public <T> void bind(Class<T> clazz, T instance) {
        container.bind(clazz, instance);
    }
}
