package com.im.bin.threadPool;

import android.os.Process;
import android.util.Log;

import java.util.concurrent.ThreadFactory;

public class PriorityThreadFactory implements ThreadFactory {

    private final int mThreadPriority;

    public PriorityThreadFactory(int threadPriority) {
        mThreadPriority = threadPriority;
    }

    @Override
    public Thread newThread(final Runnable runnable) {
        Runnable wrapperRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Process.setThreadPriority(mThreadPriority);
                } catch (Throwable t) {
                    Log.d("Error ThreadPriority: ", t.getMessage());
                }
                runnable.run();
            }
        };
        return new Thread(wrapperRunnable);
    }
}