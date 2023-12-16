package org.fpm.di;

public class Main {
    public static void main(String[] args) {

        var environment = new DefaultEnvironment();

        var cont = environment.configure(binder -> {
            binder.bind(A.class);
            binder.bind(B.class);
            binder.bind(Dep.class);
            binder.bind(TestSing.class);
        });

        Dep inst1 = cont.getComponent(Dep.class);
        //Dep inst2 = cont.getComponent(Dep.class);

        System.out.println(inst1);
        System.out.println(inst1.a);
        System.out.println(inst1.a);
        System.out.println(inst1.b);
        System.out.println(inst1.b);
        System.out.println(inst1.singleton);
        System.out.println(inst1.singleton);
        System.out.println();

        /*
        System.out.println(inst2);
        System.out.println(inst2.a);
        System.out.println(inst2.b);
        System.out.println(inst2.singleton);
        */
    }
}
