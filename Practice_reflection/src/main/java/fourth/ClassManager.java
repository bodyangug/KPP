package fourth;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClassManager {
    public static Scanner in = new Scanner(System.in);
    private Class cls;
    private Object classObject;

    public ClassManager(String cls) throws ClassNotFoundException {
        this.cls = Class.forName(cls);
    }

    public static void main(String[] args) {
        ClassManager classManager;
        Scanner in = new Scanner(System.in);
        try {
            System.out.print("Введите полное название класса, объект котоого хотите создать: ");
            String className = in.next();
            classManager = new ClassManager(className);
            classManager.newInstance();

            while (true)
                classManager.chooseMethods();

        } catch (ClassNotFoundException e) {
            System.out.println("Клас не найден");
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            System.out.println("Ошибка при вызове метода или конструктора. Перезапустите программу.");
        } catch (NoSuchElementException e) {
            System.out.println("\nОшибка в сканере. Нет элементтов для считывания.");
        }
    }

    private boolean isSimpleType(Class classObject) {
        switch (classObject.getName()) {
            case "int":
            case "double":
            case "float":
            case "boolean":
            case "java.lang.String":
            case "char":
            case "short":
            case "long":
                return true;
        }

        return false;
    }

    private Object getObjectByType(Class classObject) {
        if (isSimpleType(classObject)) {
            System.out.print("Введите " + classObject + " параметр: ");
            String stringParameter = in.next();

            switch (classObject.getName()) {
                case "int":
                    return Integer.parseInt(stringParameter);
                case "double":
                    return Double.parseDouble(stringParameter);
                case "float":
                    return Float.parseFloat(stringParameter);
                case "boolean":
                    return Boolean.parseBoolean(stringParameter);
                case "java.lang.String":
                    return stringParameter;
                case "char":
                    return stringParameter.charAt(0);
                case "short":
                    return Short.parseShort(stringParameter);
                case "long":
                    return Long.parseLong(stringParameter);
            }
        } else
            try {
                System.out.println("\nСоздание объекта параметра" + classObject + ": ");
                return createObject(classObject);
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                System.out.println("Ошибка при создании объекта " + classObject);
            }

        return null;
    }

    private Object[] getParametersArray(Class[] typeArray) {
        Object[] parameterArray = new Object[typeArray.length];

        for (int i = 0; i < parameterArray.length; i++)
            parameterArray[i] = getObjectByType(typeArray[i]);

        return parameterArray;
    }

    private Executable getConstructor(Executable[] constructorArray) {
        for (int i = 0; i < constructorArray.length; i++)
            System.out.println("    " + (i + 1) + ") " + constructorArray[i]);

        System.out.print("Выберите пункт [1 - " + constructorArray.length + "]: ");
        int n = in.nextInt() - 1;

        return constructorArray[n];
    }

    private Object createObject(Class cls) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        System.out.println("Выберите конструктор: ");
        Constructor constructor = (Constructor) getConstructor(cls.getConstructors());
        Object[] parameters = getParametersArray(constructor.getParameterTypes());
        return constructor.newInstance(parameters);
    }

    private void chooseMethods() throws InvocationTargetException, IllegalAccessException {
        System.out.println("\nВыберите метод: ");
        Method method = (Method) getConstructor(classObject.getClass().getMethods());

        Object[] args = getParametersArray(method.getParameterTypes());
        method.invoke(classObject, args);
        System.out.println("Метод успешно вызван!");
    }

    public void newInstance() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        classObject = createObject(cls);
        System.out.println("Объект успешно создан!");
    }
    public Object getObject() {
        return classObject;
    }
}