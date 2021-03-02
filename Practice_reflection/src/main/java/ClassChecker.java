import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class ClassChecker {
    public static String checkModifiers(Class myClass){
        String result = "";
        int mods = myClass.getModifiers();
        if (Modifier.isPublic(mods))
            result += "public ";

        if (Modifier.isAbstract(mods))
            result += " abstract ";

        if (Modifier.isFinal(mods))
            result += "final ";

        result += "class " + myClass.getSimpleName();
        return result;
    }

    public static String checkSuperclass(Class myClass){
        String superClass = myClass.getSuperclass().getSimpleName();
        return " extends " + superClass;
    }

    public static String checkInterfaces(Class myClass){
        String result = "";
        Class [] interfaces = myClass.getInterfaces();

        if (interfaces.length > 0) {
            result = " implements ";
            for (int i = 0; i < interfaces.length - 1; i++)
                result += interfaces[i].getSimpleName() + ", ";

            result += interfaces[interfaces.length - 1];
        }

            return result;
    }

    public static void checkConstructors(Class myClass){
        System.out.println("\nКонструкторы: ");
        for (Constructor constructor : myClass.getDeclaredConstructors())
            System.out.println("   * " + constructor);
    }

    public static void checkMethods(Class myClass){
        System.out.println("\nМетоды: ");
        for (Method methods : myClass.getDeclaredMethods())
            System.out.println("   * " + methods);
    }

    public static void checkFields(Class myClass){
        System.out.println("\nПоля: ");
        for(Field field : myClass.getDeclaredFields())
            System.out.println("   * " + field);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите полное имя класса (наппример java.util.Date): ");
        Class myClass;

        try {
            myClass = Class.forName(in.nextLine());
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не найден.");
            return;
        }

        System.out.print(checkModifiers(myClass));
        System.out.print(checkSuperclass(myClass));
        System.out.println(checkInterfaces(myClass));
        checkFields(myClass);
        checkConstructors(myClass);
        checkMethods(myClass);
    }
}