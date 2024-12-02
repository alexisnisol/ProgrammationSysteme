import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class oldStock {

    private final Lock motorLock = new ReentrantLock();
    private final Lock bodyLock = new ReentrantLock();
    private final Lock wheelLock = new ReentrantLock();

    private final Condition motorEmpty = motorLock.newCondition();
    private final Condition motorFull = motorLock.newCondition();

    private final Condition bodyEmpty = bodyLock.newCondition();
    private final Condition bodyFull = bodyLock.newCondition();

    private final Condition wheelEmpty = wheelLock.newCondition();
    private final Condition wheelFull = wheelLock.newCondition();

    private final int MAX_MOTOR = 5;
    private final int MAX_BODY = 3;
    private final int MAX_WHEEL = 20;

    private int motorCount = 0;
    private int bodyCount = 0;
    private int wheelCount = 0;

    public oldStock() {
    }

    public void putMotor() throws InterruptedException {
        motorLock.lock();
        try {

            while (this.motorCount >= MAX_MOTOR) {
                motorFull.await();
            }

            this.motorCount++;
            motorEmpty.signal();

        } finally {
            motorLock.unlock();
        }
    }

    public void takeMotor() throws InterruptedException {
        motorLock.lock();
        try {

            while (this.motorCount <= 0) {
                motorEmpty.await();
            }

            this.motorCount--;
            motorFull.signal();
        } finally {
            motorLock.unlock();
        }
    }

    public void putBody() throws InterruptedException {
        bodyLock.lock();
        try {

            while (this.bodyCount >= MAX_BODY) {
                bodyFull.await();
            }

            this.bodyCount++;
            bodyEmpty.signal();

        } finally {
            bodyLock.unlock();
        }
    }

    public void takeBody() throws InterruptedException {
        bodyLock.lock();
        try {

            while (this.bodyCount <= 0) {
                bodyEmpty.await();
            }

            this.bodyCount--;
            bodyFull.signal();
        } finally {
            bodyLock.unlock();
        }
    }

    public void putWheel() throws InterruptedException {
        wheelLock.lock();
        try {

            while (this.wheelCount >= MAX_WHEEL) {
                wheelFull.await();
            }

            this.wheelCount++;
            wheelEmpty.signal();

        } finally {
            wheelLock.unlock();
        }
    }

    public void takeWheel() throws InterruptedException {
        wheelLock.lock();
        try {

            while (this.wheelCount <= 4) {
                wheelEmpty.await();
            }

            for(int i = 0; i < 4; i++) {
                this.wheelCount--;
                wheelFull.signal();
            }
        } finally {
            wheelLock.unlock();
        }
    }

}