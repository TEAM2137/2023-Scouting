package com.team2137.chargedupscouting;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.Chronometer;

public class ClimbingChronometer extends Chronometer {
    private long lastTime = 0;
    private boolean started = false;

    public ClimbingChronometer(Context context) {
        super(context);
        stop();
        reset();
    }

    public ClimbingChronometer(Context context, AttributeSet attrs) {
        super(context, attrs);
        stop();
        reset();
    }

    public ClimbingChronometer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        stop();
        reset();
    }

    public boolean hasStarted() {
        return started;
    }

    public long getCurrentTimeMilliseconds() {
        if(started)
            return SystemClock.elapsedRealtime() - getBase();
        else
            if (lastTime > 0)
                return lastTime - getBase();
            else
                return 0;
    }

    @Override
    public void start() {
        super.start();
        started = true;
    }

    @Override
    public void stop() {
        super.stop();
        started = false;
    }

    public void resume() {
        if ( lastTime == 0 )
            setBase( SystemClock.elapsedRealtime() );
        else {
            long intervalOnPause = (SystemClock.elapsedRealtime() - lastTime);
            setBase( getBase() + intervalOnPause );
        }
        start();
    }

    public void pause()  {
        stop();
        lastTime = SystemClock.elapsedRealtime();
    }

    public void reset()  {
        stop();
        setText("00:00");
        lastTime = 0;
    }
}
