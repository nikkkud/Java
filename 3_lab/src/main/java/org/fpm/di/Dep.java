package org.fpm.di;

import javax.inject.Inject;

public class Dep {

    public A a;

    public B b;

    public TestSing singleton;

    @Inject
    public Dep(A a, B b, TestSing singleton) {
        this.a = a;
        this.b = b;
        this.singleton = singleton;
    }
}
