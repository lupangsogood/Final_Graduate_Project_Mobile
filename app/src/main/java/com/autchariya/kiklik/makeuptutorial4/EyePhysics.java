package com.autchariya.kiklik.makeuptutorial4;

import android.Manifest;
import android.graphics.PointF;
import android.os.SystemClock;

public class EyePhysics {

    private static final String TAG = "EyePhysics";


    private final long TIME_PERIOD_MS = 1000;

    private final float FRICTION = 2.2f;
    private final float GRAVITY  = 40.0f;

    private final float BOUNCE_MULTIPLIER = 20.f;

    private final float ZERO_TOLERANCE = 0.001f;

    private long mLastUpdateTimeMs = SystemClock.elapsedRealtime();

    private PointF mEyePosition;
    private float mEyeRadius;

    private PointF mIrisPosition;
    private float mIrisRadius;

    private float vx = 0.0f;
    private float vy = 0.0f;

    private int mConsecutiveBounces = 0;


    PointF  nextIrisPosition(PointF eyePosition , float eyeRadius , float irisRadius){

        mEyePosition = eyePosition;
        mEyeRadius   = eyeRadius;
        if (mIrisPosition == null){
            mIrisPosition = eyePosition;
        }
        mIrisRadius = irisRadius;

        long nowMS = SystemClock.elapsedRealtime();
        long elapsedTimeMs = nowMS - mLastUpdateTimeMs;
        float simulationRate = (float) elapsedTimeMs / TIME_PERIOD_MS;
        mLastUpdateTimeMs = nowMS;

        if (!isStopped()){
            vy += GRAVITY * simulationRate;
        }

        vx =applyFriction(vx,simulationRate);
        vy =applyFriction(vy,simulationRate);

        float x = mIrisPosition.x +(vx * mIrisRadius * simulationRate);
        float y = mIrisPosition.y +(vy * mIrisRadius * simulationRate);
        mIrisPosition =new PointF(x,y);

        makeIrisBounds(simulationRate);

        return mIrisPosition;
    }

    private float applyFriction(float velocity , float simulationTate) {
        if (isZero(velocity)){
            velocity = 0.0f;

        }else if(velocity > 0){
            velocity = Math.max(0.0f,velocity - (FRICTION * simulationTate));

        }else {
            velocity = Math.min(0.0f, velocity +(FRICTION * simulationTate));
        }

        return velocity;
    }

    private void makeIrisBounds(float simulationRate){
        float irisOffsetX = mIrisPosition.x - mEyePosition.x;
        float irisOffsetY = mIrisPosition.y - mEyePosition.y;

        float maxDistance = mEyeRadius - mIrisRadius;
        float distance =(float) Math.sqrt(Math.pow(irisOffsetX,2) + Math.pow(irisOffsetY,2));
        if (distance <= maxDistance){

            mConsecutiveBounces = 0;
            return;
        }

        mConsecutiveBounces++;

        float ratio = maxDistance /distance;
        float x = mEyePosition.x + (ratio * irisOffsetX);
        float y = mEyePosition.y + (ratio * irisOffsetY);

        float dx = x - mIrisPosition.x;
        vx = applyBounce(vx,dx,simulationRate) / mConsecutiveBounces;

        float dy = y - mIrisPosition.y;
        vy = applyBounce(vy,dy,simulationRate) / mConsecutiveBounces;

        mIrisPosition = new PointF(x,y);
    }

    private float applyBounce(float velocity , float disOutOfBounds, float simulationRate){
        if  (isZero(disOutOfBounds)){
            return velocity;
        }

        velocity *= -1;

        float bounce = BOUNCE_MULTIPLIER * Math.abs(disOutOfBounds /mIrisRadius);
        if (velocity > 0 ){
            velocity += bounce * simulationRate;
        }else {
            velocity -= bounce * simulationRate;

        }

        return velocity;
    }

    private boolean isStopped(){
        if(mEyePosition.y >= mIrisPosition.y){
            return false;
        }

        float irisOffsetY = mIrisPosition.y - mEyePosition.y;
        float maxdistance = mEyeRadius - mIrisRadius;
        if (irisOffsetY < maxdistance){
            return false;
        }
        return (isZero(vx) && isZero(vy));
    }

    private boolean isZero(float num){
        return ((num < ZERO_TOLERANCE) && (num > -1 * ZERO_TOLERANCE));
    }

}
