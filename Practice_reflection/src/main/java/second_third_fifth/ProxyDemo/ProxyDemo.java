package second_third_fifth.ProxyDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProxyDemo {

    interface someInterface {
        void originalMethod(String s);
    }

    static class Original implements ProxyDemo.someInterface {
        public void originalMethod(String s) {
            System.out.println(s);
        }
    }

    static class Handler implements InvocationHandler {
        private final ProxyDemo.someInterface original;

        public Handler(ProxyDemo.someInterface original) {
            this.original = original;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException,
                IllegalArgumentException,
                InvocationTargetException {
            long after = System.currentTimeMillis();
            System.out.println("Should be text here");
            method.invoke(original, args);
            System.out.println("Text was here");
            System.out.println("Время выполнения " + (System.currentTimeMillis() - after));
            return null;
        }
    }

}
