package TP2.ex1;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private int size;
    private List<String> data;
    //private String data;

    public Data(int size) {
        this.size = size;
        this.data = new ArrayList<String>();
    }

    public synchronized void put(String value) throws InterruptedException {
        while (this.data.size() >= this.size) {
            wait();
        }
        this.data.add(value);
        notifyAll();
    }

    public synchronized String get() throws InterruptedException {
        while (this.data.isEmpty()) {
            wait();
        }
        String value = this.data.remove(0);
        notifyAll();
        return value;
    }
    
}
