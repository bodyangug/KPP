package second_third_fifth.Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class Informator {

    public Class setClassName() throws ClassNotFoundException {
        out.println("Введите имя класса");
        Scanner sc = new Scanner(in);
        String enteredName;
        enteredName = sc.nextLine();
        return Class.forName(enteredName);
    }

    public void getClassInfo(Class clss) throws ClassNotFoundException {
        out.println(clss.getName() + " - class name ");
        out.println(clss.getSimpleName() + " - simple name ");
        out.println(clss.getPackage() + " - package name ");
        out.println("\n");
    }

    public void getConstructorsInfo(Class clss) {
        out.println("Constructors:");
        Constructor[] constructors = clss.getDeclaredConstructors();
        if (constructors.length != 0) {
            for (Constructor constructor : constructors) {
                out.println(constructor.getName() + " - constructor name ");
                Parameter[] parametrs = constructor.getParameters();
                if (parametrs.length != 0) {
                    out.println("Parameters:");
                    for (Parameter parameter : parametrs) {
                        out.println(parameter.getName() + " - parameter name ");
                        out.println(parameter.getType().getName() + " - parameter type ");
                        out.println("\n");
                    }
                } else {
                    out.println("No parameters!");
                    out.println("\n");
                }
            }
        } else {
            out.println("No constructors?");
            out.println("\n");
        }
    }

    public void getAllMethodsInfo(Class clss) {
        out.println("Methods:");
        Method[] methods = clss.getDeclaredMethods();
        if (methods.length != 0) {
            for (Method method : methods) {
                getMetodsParameters(method);

                out.println(Modifier.toString(method.getModifiers()) + " - modifier ");
                out.println(method.getReturnType().getName() + " - returt type ");
                out.println("\n");
            }
        } else {
            out.println("No methods!");
            out.println("\n");
        }
    }

    //second_third.ExampleClasses.SomeClass
    public void invokeMethodFromNameWithoutArgs(Object instance) {
        Method[] methods = instance.getClass().getMethods();

        for (Method method : methods) {
            method.setAccessible(true);
            if ("thisIsMethod".equals(method.getName())) {
                try {
                    getMetodsParameters(method);
                    out.println(method.invoke(instance));

                } catch (IllegalAccessException | InvocationTargetException e) {
                    out.println("Exeption!");
                }

            }
        }
        out.println();
    }

    public void invokeMethodFromNameWithtArgs(Object instance, String methodName, String arg) {
        Method method;

        try {
            method = instance.getClass().getMethod(methodName, String.class);
            method.invoke(instance, arg);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        out.println();
    }

    private void getMetodsParameters(Method method) {
        out.println(method.getName() + " - method name ");
        Parameter[] parametrs = method.getParameters();
        if (parametrs.length != 0) {
            out.println("Parameters:");
            for (Parameter parameter : parametrs) {
                out.println(parameter.getName() + " - has parameter ");
                out.println(parameter.getType().getName() + " - input type ");
            }
        } else {
            out.println("No parameters!");
            out.println("\n");
        }
    }

    public void getFieldsinfo(Class clss) {
        Field[] publicFields = clss.getFields();

        if (publicFields.length != 0) {
            for (Field field : publicFields) {
                Class fieldType = field.getType();
                out.println(field.getName() + " - Field name");
                out.println(fieldType.getName() + " - Field type");
                out.println("\n");
            }
        } else {
            out.println("No filds!");
            out.println("\n");
        }
    }

    public void getSuperclassInfo(Class clss) {
        Class superclass = null;
        try {
            superclass = clss.getSuperclass();
            out.println(superclass.getName() + " - superclass name");
        } catch (NullPointerException e) {
            out.println("No superclass!");
        }
        out.println("\n");
    }

    public void getInterfacesInfo(Class clss) {
        clss = LinkedList.class;
        Class[] interfaces = clss.getInterfaces();
        if (interfaces.length != 0) {
            for (Class cInterface : interfaces) {
                out.println(cInterface.getName() + " - interface name");
                out.println("\n");
            }
        } else {
            out.println("No interfaces!");
            out.println("\n");
        }
    }
}
