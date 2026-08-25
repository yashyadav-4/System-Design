package TUF;
import java.util.*;

public class classes {
    public static void main(String[] args){

        Employee e1 = new Employee();
        e1.setName("Yash");
        e1.setSalary(100000);

        System.out.println("Salary of " + e1.name + " is " + e1.getSalary());

    }
}

class Employee{
    private int salary;
    public String name;

    public void setName(String s){
        name=s;
    }

    public void setSalary(int val) {
        salary=val;
    }

    public int getSalary(){
        return salary;
    }
}

// in java objects are always created on the heap using new keyword and variables stores reference to them.
// to delete the object in java just set it to null
// e1= null
// now garbage collector will automatically deletes it

