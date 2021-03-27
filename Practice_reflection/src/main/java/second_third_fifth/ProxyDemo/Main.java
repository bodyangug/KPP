package second_third_fifth.ProxyDemo;

import second_third_fifth.Reflection.Informator;

import java.lang.reflect.Proxy;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Informator informator = new Informator();
        Class clss = informator.setClassName();
        Object instance = clss.newInstance();

        out.println("Вы запросили класс -> " + clss.getName() + "\n");

        while (true) {
            out.println("1) Получить информацию о классе");
            out.println("2) Вызвать метод класса без аргументов");
            out.println("3) Вызвать метод класса и передать аргумент");
            out.println("4) Proxy");
            out.println("default -> exit");

            Scanner scan = new Scanner(in);
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    out.println("********* COMMON INFORMATION");
                    informator.getClassInfo(clss);
                    out.println("********* CONSTRUCTORS INFORMATION");
                    informator.getConstructorsInfo(clss);
                    out.println("********* METHODS INFORMATION");
                    informator.getAllMethodsInfo(clss);
                    out.println("********* SUPERCLASS INFORMATION");
                    informator.getSuperclassInfo(clss);
                    out.println("********* FIELDS INFORMATION");
                    informator.getFieldsinfo(clss);
                    out.println("********* INTERFACES INFORMATION");
                    informator.getInterfacesInfo(clss);
                    return;
                case 2:
                    informator.invokeMethodFromNameWithoutArgs(instance);
                    return;
                case 3:
                    informator.invokeMethodFromNameWithtArgs(instance, "something", "some arg");
                    return;
                case 4:
                    ProxyDemo.Original original = new ProxyDemo.Original();
                    ProxyDemo.Handler handler = new ProxyDemo.Handler(original);
                    ProxyDemo.someInterface f =
                            (ProxyDemo.someInterface) Proxy.newProxyInstance(ProxyDemo.someInterface.class.getClassLoader(),
                                    new Class[]{ProxyDemo.someInterface.class},
                                    handler);
                    f.originalMethod("Put some text");
                    return;
                default:
                    return;
            }
        }


    }

}
