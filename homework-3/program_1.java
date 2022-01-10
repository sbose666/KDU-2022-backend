import java.util.*;

class Employee implements Comparable<Employee> {
    int id, salary, equityShare, age;
    String fName, lName;

    Employee(String fName, String lName, int id, int salary, int equityShare, int age) {
        this.fName = fName;
        this.lName = lName;
        this.id = id;
        this.salary = salary;
        this.equityShare = equityShare;
        this.age = age;
    }

    public int compareTo(Employee emp) {
        // Let the ordering be done according to the age
        return Integer.compare(age, emp.age);
    }

    // overriding equals method
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            // if the same object is referenced, return true
            return true;
        }
        // check if obj is an instance of Employee class
        if (!(obj instanceof Employee)) {
            return false;
        }

        // Typecast obj to Employee, so that we can compare the data members
        Employee emp = (Employee) obj;
        return Integer.compare(age, emp.age) == 0;
    }

}

public class program_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Employee> list = new ArrayList<>();
        System.out.println("Enter the number of employees");
        int numberOfEmployees = sc.nextInt();
        for (int i = 0; i < numberOfEmployees; i++) {
            System.out.println("Enter details of " + (i + 1) + (" employee: "));
            String fName, lName;
            int id, salary, equityShare, age;
            System.out.println("Enter first name: ");
            fName = sc.next();
            System.out.println("Enter last name: ");
            lName = sc.next();
            System.out.print("Enter id: ");
            id = sc.nextInt();
            System.out.print("Enter salary: ");
            salary = sc.nextInt();
            System.out.print("Enter equity share: ");
            equityShare = sc.nextInt();
            System.out.print("Enter age: ");
            age = sc.nextInt();
            Employee emp = new Employee(fName, lName, id, salary, equityShare, age);
            list.add(emp);
        }
        System.out.println("The Employees ordered according to their age: ");
        Collections.sort(list);
        for (Employee emp : list) {
            System.out.println("Name: " + emp.fName + " " + emp.lName + ", Age: " + emp.age + ", Id: " + emp.id);
        }
        System.out.println(list.get(0).equals(list.get(1)));
    }
}