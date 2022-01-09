class Credentials {
    private String name;
    private int age;
    static Credentials instance = null;

    private Credentials() {
        name = "Sova";
        age = 26;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    static public Credentials getInstance() {
        if (instance == null)
            instance = new Credentials();
        return instance;
    }
}

public class program_1 {
    public static void main(String[] args) {
        Credentials person = Credentials.getInstance();
        System.out.println("Name: " + person.getName() + " " + " Age: " + person.getAge());
    }
} 
