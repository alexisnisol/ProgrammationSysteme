import java.util.concurrent.BrokenBarrierException;

public class Worker extends Thread {

    Engine engine;
    int beg;
    int end;

    public Worker(Engine engine, int beg, int end) {
        this.engine = engine;
        this.beg = beg;
        this.end = end;
    }

    @Override
    public void run() {
        System.out.println("Worker start ("+ beg + ", " + end + ")");
        while (engine.again) {
            for (int row = beg; row < end; row++) {
                for (int col = 0; col < engine.ncols; col++) {
                    engine.compute_next_state(row, col);
                }
            }
            System.out.println("Worker done");

            try {
                engine.barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }
    
}
