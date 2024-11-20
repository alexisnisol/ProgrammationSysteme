package TP2.ex1;

public class Consumer extends Thread {
    
    private Data data;

    public Consumer(Data data){
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while(true){         
                System.out.println("Consumer:");
                System.out.println(this.data.get());
                this.sleep(100 + (int)(Math.random() * 3000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
