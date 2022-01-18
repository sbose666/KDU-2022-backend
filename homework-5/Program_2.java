import java.lang.annotation.*;

@Target(ElementType.TYPE) // Defines on what the annotation would be applied
@Retention(RetentionPolicy.RUNTIME) // Defines the scope of the Annotation
@interface Android {
    float version();
} // Single-Value Annotation

@Android(version = 5.67f)
class Samsung {
    private String model;

    Samsung(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }
}

public class Program_2 {
    public static void main(String[] args) {
        Samsung S21 = new Samsung("Galaxy S21");
        System.out.println("Model: " + S21.getModel());

        // Trying to print the version
        Class S21_class = S21.getClass(); // getting the class
        Annotation annotation = S21_class.getAnnotation(Android.class);
        Android android = (Android) annotation; // typecasting to Android
        System.out.println("Android Version: " + android.version());
    }
}
