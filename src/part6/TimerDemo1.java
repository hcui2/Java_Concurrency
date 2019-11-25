package part6;

import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo1 {

    public static void main (String[] args) throws Exception {
        Timer timer = new Timer();
        TimerTask badTask = new TimerTask() {
            @Override
            public void run() {
                while (true);
            }
        };

        TimerTask goodTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Hello I am a well-behaved task");
            }
        };

        timer.schedule(badTask, 100);
        timer.schedule(goodTask, 500);

        Thread.sleep(3000);



    }
}
