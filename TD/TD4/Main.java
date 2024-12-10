public class Main {

    public static void main(String[] args) {
        Engine engine = new Engine(20, 20, 10);
        while (!engine.isFinished()) {
            engine.step();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
