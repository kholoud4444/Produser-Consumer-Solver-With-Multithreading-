package final1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
public class Buffer {
    public int count = 1;
    public int size = 7;
    public Queue<Integer> aqueue;
    public Buffer() {

        this.aqueue = new LinkedList<>();

    }

    private final Semaphore Key = new Semaphore(1);
    private final Semaphore Produced = new Semaphore(0);
    private final Semaphore Consumed = new Semaphore(size);
    public void get() throws InterruptedException {

            try {
                if(aqueue.size()>=1){
                    Produced.acquire();
                    Key.acquire();
                    int remove = aqueue.remove();
                    System.out.println("consumer consumed item: " + remove);
                    System.out.println("Buffer size is " + aqueue.size());
                    Key.release();
                    Consumed.release();
                }

                else
                    System.out.println("Buffer is empty");

            }  catch (InterruptedException e){
                System.out.println("Process is interrupted");

            }}

    public void put() throws InterruptedException {
        try{
        if(aqueue.size()<=size){
            Consumed.acquire();
            Key.acquire();
            aqueue.add(count);
        System.out.println("producer produced item: " + count);
        System.out.println("Buffer size is " + aqueue.size());
            count++;
            Thread.sleep(500);
        Key.release();
        Produced.release();
        }
        else
            System.out.println("Buffer is completed");

    }catch (InterruptedException e){
            System.out.println("Process is interrupted");

        }}

}