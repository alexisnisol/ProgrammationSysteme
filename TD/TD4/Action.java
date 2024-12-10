public class Action implements Runnable {
    Engine engine;

    public Action(Engine engine) {
        this.engine = engine;
    }

    public void run() {
        engine.swap();
        engine.display();
        if(engine.isFinished()) {
            engine.again = false;
        }
        engine.setChanged(false);
    }
}
