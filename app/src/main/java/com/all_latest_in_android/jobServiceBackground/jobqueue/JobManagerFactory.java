package com.all_latest_in_android.jobServiceBackground.jobqueue;

import android.content.Context;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;
import com.birbit.android.jobqueue.log.CustomLogger;
import com.birbit.android.jobqueue.scheduling.FrameworkJobSchedulerService;

import timber.log.Timber;

public class JobManagerFactory {

    private static JobManager jobManager;
    private static CustomLogger customLogger = new CustomLogger() {

        @Override
        public boolean isDebugEnabled() {
            return true;
        }

        @Override
        public void d(String text, Object... args) {
            Timber.d(String.format(text, args));
        }

        @Override
        public void e(Throwable t, String text, Object... args) {
            Timber.e(t, String.format(text, args));
        }

        @Override
        public void e(String text, Object... args) {
            Timber.e(String.format(text, args));
        }

        @Override
        public void v(String text, Object... args) {
            // no-op
        }
    };

    static synchronized JobManager getJobManager() {
        return jobManager;
    }

    public static synchronized JobManager getJobManager(Context context) {
        if (jobManager == null) {
            jobManager = configureJobManager(context);
        }
        return jobManager;
    }

    private static JobManager configureJobManager(Context context) {
        Configuration.Builder builder = new Configuration.Builder(context)
                .minConsumerCount(1)//always keep at least one consumer alive
                .maxConsumerCount(3)//up to 3 consumers at a time
                .loadFactor(3)//3 jobs per consumer
                .consumerKeepAlive(120)//wait 2 minutes
                .customLogger(customLogger);
        // we are setting batch param below to false so that sync results are observed and acted upon in the background.
        builder.scheduler(FrameworkJobSchedulerService.createSchedulerFor(context,
                SchedulerJobService.class), false);
        return new JobManager(builder.build());
    }
}
