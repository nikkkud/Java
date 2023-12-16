package org.fpm.di.example;

import org.fpm.di.Configuration;
import org.fpm.di.Container;
import org.fpm.di.DefaultEnvironment;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.*;

public class DefaultContainerTests {
    public static class Class1 {
    }

    public static class Class2 extends Class1 {
    }

    public static class Class3 extends Class2 {
    }

    public static class Class4 {
    }

    public static class Dep {
        public Class1 class1;
        public Class4 class4;

        @Inject
        public Dep(Class1 class1, Class4 class4) {
            this.class1 = class1;
            this.class4 = class4;
        }
    }

    public Container createContainer(Configuration conf) {
        return new DefaultEnvironment().configure(conf);
    }

    @Test
    public void injectClass3ThroughClass1() {
        Container container = createContainer(binder -> {
            binder.bind(Class1.class, Class2.class);
            binder.bind(Class2.class, Class3.class);
            binder.bind(Class3.class);
        });

        Class1 result = container.getComponent(Class1.class);
        assertEquals(Class3.class, result.getClass());
    }

    @Test
    public void injectClass3ThroughClass2WithGivenInstance() {
        final Class3 class3Instance = new Class3();
        Container container = createContainer(binder -> {
            binder.bind(Class1.class, Class2.class);
            binder.bind(Class2.class, Class3.class);
            binder.bind(Class3.class, class3Instance);
        });

        Class2 result = container.getComponent(Class2.class);
        assertSame(result, class3Instance);
    }

    @Test
    public void noBindingTest() {
        var container = createContainer(binder -> {});
        assertThrows(RuntimeException.class, () -> container.getComponent(Object.class));
    }

    @Test
    public void createDepTest() {
        Container container = createContainer(binder -> {
            binder.bind(Class1.class, Class2.class);
            binder.bind(Class2.class, Class3.class);
            binder.bind(Class3.class);
            binder.bind(Class4.class);
            binder.bind(Dep.class);
        });

        Dep actual  = container.getComponent(Dep.class);
        assertEquals(Class3.class, actual.class1.getClass());
        assertEquals(Class4.class, actual.class4.getClass());
    }
}
