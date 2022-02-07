package helpers;

import java.util.Timer;
import java.util.TimerTask;

public class TimerCleanupTask {

    private static TimerCleanupTask timerCleanupTask;

    public TimerCleanupTask() {
    }

    public static TimerCleanupTask getInstance() {
        if (timerCleanupTask==null) {
            timerCleanupTask = new TimerCleanupTask();
        }
        return timerCleanupTask;
    }

    public void cleanPurchasedGoods() {
        Timer timer = new Timer();
        timer.schedule(new OrderCleaner(), 100,120000);
    }

    public class OrderCleaner extends TimerTask {

        @Override
        public void run() {
            OrderHelper.getInstance().purchasedGoods.clear();
        }

    }

}
