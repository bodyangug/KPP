package second_third_fifth.ExampleClasses;

public class ClassForReflection extends ClassForExtend implements ClassInterface {
    private int somenumber;
    private int anothernumber = 26;

    public ClassForReflection() {
        somenumber = 5;
    }

    public ClassForReflection(int somenumber) {
        this.somenumber = somenumber;
    }

    public int getAnothernumber() {
        return anothernumber;
    }

    public void setAnothernumber(int anothernumber) {
        this.anothernumber = anothernumber;
    }


}
