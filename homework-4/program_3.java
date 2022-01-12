class ThreadingDemo_extend extends Thread {
    public void run() {
        System.out.println("Running inside a thread with id: " + Thread.currentThread().getId());
    }
}

class ThreadingDemo_implement implements Runnable {
    public void run() {
        System.out.println("Running inside a thread with id: " + Thread.currentThread().getId());
    }
}

class ThreadJoin extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
                System.out.println("Hi from Thread: " + Thread.currentThread().getId());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

public class Program_3 {
    public static void main(String[] args) throws Exception {

//        System.out.println("Extending Thread: ");
//        for (int i = 0; i <= 10; i++) {
//            ThreadingDemo_extend instance = new ThreadingDemo_extend();
//            instance.start();
//        }
//        System.out.println("Implementing Runnable: ");
//        for (int i = 0; i <= 10; i++) {
//            Thread instance = new Thread(new ThreadingDemo_implement());
//            instance.start();
//        }

        ThreadJoin t1 = new ThreadJoin();
        ThreadJoin t2 = new ThreadJoin();
        t1.start();
        t2.start();
        System.out.println("t1 is alive: " + t1.isAlive());
        System.out.println("t2 is alive: " + t2.isAlive());
        t1.join();
        t2.join();
        System.out.println("t1 is alive: " + t1.isAlive());
        System.out.println("t2 is alive: " + t2.isAlive());
        System.out.println("Bye");
    }
}
