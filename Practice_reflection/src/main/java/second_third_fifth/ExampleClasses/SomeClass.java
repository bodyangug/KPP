package second_third_fifth.ExampleClasses;

public class SomeClass {

    private int number;
    private int anothernumber = 26;

    public SomeClass() {
    }

    public SomeClass(int somenumber) {
        this.number = somenumber;
    }

    public int getAnothernumber() {
        return anothernumber;
    }

    public void setAnothernumber(int anothernumber) {
        this.anothernumber = anothernumber;
    }

    public void thisIsMethod() {
        System.out.println("this method work");
    }

    public void something(String argument) {
        System.out.println("this method work with argument " + argument);
    }

}
