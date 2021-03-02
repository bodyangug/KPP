import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class ArrayReflect {
    public static void menu() {
        System.out.println("|1|. Изменить размер массива |");
        System.out.println("|2|. Распечатать массив      |");
        System.out.println("|3|. Выход                   |");
        System.out.println("--|--------------------------|");
        System.out.print("    Ваш выбор:   ");
    }

    public static int getDimension(Object array) {
        String classString = array.getClass().toString();
        int dimension = 0;

        for (int i = 0; i < classString.length(); i++) {
            if (classString.charAt(i) == '[')
                dimension++;
        }

        return dimension;
    }

    public static Class getSimpleClass(Character letter) {
        switch (letter) {
            case 'I':
                return Integer.class;
            case 'D':
                return Double.class;
            case 'J':
                return Long.class;
            case 'F':
                return Float.class;
            case 'S':
                return Short.class;
            case 'C':
                return Character.class;
            case 'Z':
                return Boolean.class;
        }

        return null;
    }

    public static Class getClass(Object array) throws ClassNotFoundException {
        String s = array.getClass().toString();
        if (s.indexOf(';') != -1) {
            s = s.substring(s.indexOf('L') + 1, s.indexOf(';'));
            return Class.forName(s);
        } else
            return getSimpleClass(s.charAt(s.length() - 1));
    }

    public static void init1DArray(Object array) throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        String classString = getClass(array).getName();

        for (int i = 0; i < Array.getLength(array); i++) {
            System.out.println("Инициализация параметра [" + i + "]: ");
            ClassManager classManager = new ClassManager(classString);
            classManager.newInstance();
            Array.set(array, i, classManager.getObject());
            System.out.println();
        }
    }

    public static void init2DArray(Object array) throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        Object[][] arrayCopy = (Object[][]) array;
        String classString = getClass(array).getName();

        for (int i = 0; i < arrayCopy.length; i++) {
            for (int j = 0; j < arrayCopy[0].length; j++) {
                System.out.println("Инициализация параметра [" + i + "][" + j + "]: ");
                ClassManager classManager = new ClassManager(classString);
                classManager.newInstance();
                ((Object[][]) array)[i][j] = classManager.getObject();
                System.out.println();
            }
        }
    }

    public static void handInit(Object array) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (getDimension(array) == 1)
            init1DArray(array);
        else
            init2DArray(array);
    }

    public static Object getArray(Class cls, int[] dimension) {
        return Array.newInstance(cls, dimension);
    }

    public static Object getArray(String cls, int[] dimension) throws ClassNotFoundException {
        return getArray(Class.forName(cls), dimension);
    }

    public static Object setSize(Object array, int[] newDimension) throws ClassNotFoundException {
        String classString = getClass(array).getName();
        Object newArray = getArray(Class.forName(classString), newDimension);

        int height;

        if (newDimension[0] > Array.getLength(array))
            height = Array.getLength(array);
        else
            height = newDimension[0];

        if (getDimension(array) == 2) {
            int width;

            for (int i = 0; i < height; i++) {
                if (newDimension[1] > Array.getLength(Array.get(array, i)))
                    width = Array.getLength(Array.get(array, i));
                else
                    width = newDimension[1];

                System.arraycopy(Array.get(array, i), 0, Array.get(newArray, i), 0, width);
            }
        } else {
            System.arraycopy(array, 0, newArray, 0, height);
        }

        return newArray;
    }

    public static void printArray(Object array) {
        if (getDimension(array) == 1) {
            for (int i = 0; i < Array.getLength(array); i++)
                System.out.print(Array.get(array, i) + " ");
            System.out.println();
        } else
            for (int i = 0; i < Array.getLength(array); i++) {
                for (int j = 0; j < Array.getLength(Array.get(array, i)); j++)
                    System.out.print(Array.get(Array.get(array, i), j) + " ");

                System.out.println();
            }
    }

    public static int[] initDimension(int modifier) {
        int[] dimension = null;
        Scanner in = new Scanner(System.in);
        if (modifier == 0) {
            System.out.print("Введите колличество измерений массива: (1, 2): ");
            dimension = new int[in.nextInt()];
        } else if (modifier == 1)
            dimension = new int[1];
        else
            dimension = new int[2];

        if (dimension.length > 2) {
            System.out.println("Неправильний размер массива. Перезапустите программу.");
            System.exit(0);
        }

        for (int i = 0; i < dimension.length; i++) {
            System.out.print("  * Введите размер " + (i + 1) + " - го измерения: ");
            dimension[i] = in.nextInt();
        }

        return dimension;
    }

    public static void main(String... args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Scanner in = new Scanner(System.in);
        int dimension[] = initDimension(0);

        System.out.println("Введите полное название класса, объект которого хотите создать:");
        System.out.print("=> ");
        String classString = in.next();
        Object array;
        try {
            array = getArray(classString, dimension);
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не найден. Перезапустите программу.");
            return;
        }

        System.out.println("Инициализировать массив вручную?(Y/N): ");
        String answer = in.next();
        if (answer.toLowerCase().equals("y"))
            handInit(array);

        int choice = 0;
        while (choice != 3) {
            menu();
            choice = in.nextInt();

            switch (choice) {
                case 1:
                    dimension = initDimension(dimension.length);
                    array = setSize(array, dimension);
                    System.out.println("Размер изменен\n");
                    break;
                case 2:
                    System.out.println("________________________________________");
                    printArray(array);
                    System.out.println("\nРаспечатано\n");
                    break;
            }
        }

        in.close();
    }
}