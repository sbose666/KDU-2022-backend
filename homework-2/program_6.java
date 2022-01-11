import java.util.Scanner;

class ParallelLinesException extends Exception {
    public ParallelLinesException(String str) {
        super(str);
    }
}

class checker {
    public static void checkParallel(Line l1, Line l2) throws ParallelLinesException {
        if (l1.getSlope() == l2.getSlope()) {
            // lines are parallel
            if (l1.getIntercept() != l2.getIntercept()) throw new ParallelLinesException("Lines are Parallel");
            else throw new ParallelLinesException("Lines are coincident");
        }
    }

}

class Line {
    private double slope, intercept;

    Line(double slope, double intercept) {
        this.slope = slope;
        this.intercept = intercept;
    }

    // setter
    public void setSlope(double slope) {
        this.slope = slope;
    }

    public void setIntercept(double intercept) {
        this.intercept = intercept;
    }

    // getter
    public double getSlope() {
        return slope;
    }

    public double getIntercept() {
        return intercept;
    }
}

public class program_6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double m1, m2, C1, C2;

        System.out.println("Enter the slope of Line 1");
        m1 = sc.nextDouble();
        System.out.println("Enter the y-intercept of Line 1");
        C1 = sc.nextDouble();
        System.out.println("Enter the slope of Line 2");
        m2 = sc.nextDouble();
        System.out.println("Enter the y-intercept of Line 1");
        C2 = sc.nextDouble();

        Line l1 = new Line(m1, C1), l2 = new Line(m2, C2);

        try {
            // Check if parallel
            checker.checkParallel(l1, l2);

            // Find Intersection
            /*
            Let the lines be,
                a1x + b1y + c1 = 0
                a2X + b2y + c2 = 0

            Let (x, y) be the point of intersection, then,

                x = (b1c2 - b2c1) / (a1b2 - a2b1)
                y = (c1a2 - c2a1) / (a1b2 - a2b1)

            Let m1 be the slope of Line 1 and C1 be the intercept of Line 1
            Similarly for Line 2

            Thus, a1 = -m1, b1 = 1, c1 = -C1
            and,  a2 = -m2, b2 = 1, c2 - -C2

             */

            double a1 = -l1.getSlope(), b1 = 1, c1 = -l1.getIntercept();
            double a2 = -l2.getSlope(), b2 = 1, c2 = -l2.getIntercept();
            double num_x = (b1 * c2 - b2 * c1), den_x = (a1 * b2 - a2 * b1);
            double num_y = (c1 * a2 - c2 * a1), den_y = (a1 * b2 - a2 * b1);
            double x, y;

            try {
                x = num_x / den_x;
                y = num_y / den_y;
                System.out.println("The intersection point is: ( " + x + ", " + y + ")");
            } catch (ArithmeticException ex) {
                ex.printStackTrace();
            }

        } catch (ParallelLinesException ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("Exiting");
        }
    }
}
