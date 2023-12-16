package org.fpm.di;

public class DefaultEnvironment implements Environment {
    @Override
    public Container configure(Configuration configuration) {
        var container = new DefaultContainer();
        var binder = new DefaultBinder(container);
        configuration.configure(binder);

        return container;
    }
}
