package ca.tahnok.poke_stopclock;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;
import android.util.Log;

public class MyJobService extends JobService {
    public static String KEY_TICK = "tick";
    public static final int TIME_1_MINUTE = 100 * 5;

    private static final String TAG = MyJobService.class.getSimpleName();

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        int tick = jobParameters.getExtras().getInt(KEY_TICK);
        Log.e(TAG, "job started for tick: " + tick);
        if (tick >= 5) {
            handleFinalTick(getApplicationContext());
        } else {
            handleTick(getApplicationContext(), tick);
        }
        return false;
    }

    private static void handleFinalTick(Context applicationContext) {
        NotificationUtility.showFinalNotification(applicationContext);
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }


    public static void handleTick(Context context, int currentTick) {
        NotificationUtility.showNotification(context, currentTick);
        PersistableBundle extras = new PersistableBundle();
        extras.putInt(MyJobService.KEY_TICK, currentTick + 1);
        ComponentName name = new ComponentName(context, MyJobService.class);
        JobInfo job = new JobInfo.Builder(10, name)
                          .setOverrideDeadline(TIME_1_MINUTE)
                          .setExtras(extras)
                          .build();
        JobScheduler scheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        scheduler.schedule(job);
    }
}
