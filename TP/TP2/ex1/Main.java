package TP2.ex1;

public class Main {
    
    public static void main(String[] args) {
        
        Data data = new Data(3);

        Producer producer1 = new Producer("A", data);
        Producer producer2 = new Producer("B", data);
        producer1.start();
        producer2.start();

        Consumer consumer1 = new Consumer(data);
        consumer1.start();

        try {
            producer1.join();
            producer2.join();
            consumer1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
