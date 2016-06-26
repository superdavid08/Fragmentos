package elsuper.david.com.fragmentos.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import elsuper.david.com.fragmentos.util.Key;

/**
 * Created by Andrés David García Gómez
 */
public class ServiceTimer extends Service{

    public static final String ACTION_SEND_TIMER = "com.david.elsuper_.SEND_TIMER";
    int counter;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            counter++;
            handler.postDelayed(runnable,1000);

            Intent i = new Intent(ACTION_SEND_TIMER);
            i.putExtra(Key.KEY_SERVICE_TIMER, counter);
            sendBroadcast(i);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler.post(runnable);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}


