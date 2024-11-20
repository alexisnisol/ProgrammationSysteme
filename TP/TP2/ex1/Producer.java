package TP2.ex1;

public class Producer extends Thread {

    private String name;
    private Data data;
    
    public Producer(String name, Data data) {
        this.name = name;
        this.data = data;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                this.data.put("Product " + i + " by " + this.name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
