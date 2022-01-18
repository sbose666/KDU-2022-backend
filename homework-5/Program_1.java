import java.lang.annotation.*;
import java.util.ArrayList;

// Built-In Annotations
class Mobile {
    ArrayList contact = new ArrayList();

    public void call() {
        System.out.println("Calling");
    }

    @Deprecated // Denotes a deprecated method
    public void radio() {
        System.out.println("Radio");
    }

    @SuppressWarnings("unchecked") // Suppresses any unchecked warnings
    public void createPhonebook() {
        contact.add("Rohan");
        contact.add("Samson");
        contact.add("Venessa");
    }

    @Override // Denotes we are overriding a method
    public boolean equals(Object obj) {
        return obj == this;
    }

}

// Custom Annotation

@interface SmartPhone {
}; // Marker Annotation

@interface Linux {
    int version();
} // Single Value Annotation

@interface Human {
    String name();

    int age();

    String gender();
}   // Multi Value Annotation

public class Program_1 {
    public static void main(String[] args) {
        Mobile mb = new Mobile();
        mb.call();
    }
}
