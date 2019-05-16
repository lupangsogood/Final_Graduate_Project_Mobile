package com.autchariya.kiklik.makeuptutorial4.ar.camera;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.images.Size;
import com.google.android.gms.vision.CameraSource;
import com.google.android.youtube.player.internal.e;

import java.io.IOException;

public class  CameraSourcePreview extends ViewGroup {

    private static final String TAG = "CameraSourvePreview";

    private Context mContext;
    public SurfaceView mSurfaceView;
    private boolean mStartRequested;
    private boolean mSurfaceAvailable;
    private CameraSource mCameraSource;
    private static final int REQUEST_CAMERA_PERMISSION = 200;

    private com.autchariya.kiklik.makeuptutorial4.ar.camera.GraphicOverlay mOverlay;

    public CameraSourcePreview(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mStartRequested = false;
        mSurfaceAvailable = false;

        mSurfaceView = new SurfaceView(context);
        mSurfaceView.getHolder().addCallback(new SurfaceCallback());
        addView(mSurfaceView);



    }

    public void start(CameraSource cameraSource) throws IOException {
        if (cameraSource == null) {
            stop();
        }

        mCameraSource = cameraSource;

        if (mCameraSource != null) {
            mStartRequested = true;
            startIFReady();
        }
    }

    public void start(CameraSource cameraSource, com.autchariya.kiklik.makeuptutorial4.ar.camera.GraphicOverlay overlay) throws IOException {
        mOverlay = overlay;
        start(cameraSource);
    }

    public void stop() {
        if (mCameraSource != null) {
            mCameraSource.stop();
        }
    }

    public void release() {
        if (mCameraSource != null) {
            mCameraSource.release();
            mCameraSource = null;
        }
    }


    private void startIFReady() throws IOException {
        if (mStartRequested && mSurfaceAvailable) {
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mCameraSource.start(mSurfaceView.getHolder());
            if (mOverlay != null){
                Size size = mCameraSource.getPreviewSize();
                int min = Math.min(size.getWidth(),size.getHeight());
                int max = Math.max(size.getWidth(),size.getWidth());

                if (isPortraitMode()){
                    mOverlay.setCameraInfo(min,max,mCameraSource.getCameraFacing());
                }else {
                    mOverlay.setCameraInfo(max,min,mCameraSource.getCameraFacing());
                }
                mOverlay.clear();
            }
            mStartRequested = false;


        }
    }

    private class   SurfaceCallback  implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            mSurfaceAvailable   =   true;
            try {
                startIFReady();
            }catch (IOException e){
                Log.e(TAG,"Could not start camera source.",e);
            }

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            mSurfaceAvailable = false;
        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        int previewWidth    =   1024;
        int previewHeight   =   768;
        if (mCameraSource   != null ){
            Size size   =   mCameraSource.getPreviewSize();
            if (size    !=  null){
                previewWidth    =   size.getWidth();
                previewHeight   =   size.getHeight();
            }
        }

        if (isPortraitMode()){
            int tmp =   previewWidth;
            previewWidth    =   previewHeight;
            previewHeight   =   tmp;
        }

        final int   viewWidth   =   right   -   left;
        final int   viewHeight  =   bottom  -   top;

        int childWidth;
        int childHeight;
        int childXOffset = 0;
        int childYOffset = 0;
        float widthRatio = (float) viewHeight / (float) previewHeight;
        float heightRatio = (float) viewWidth / (float) previewWidth;

        if (widthRatio  >   heightRatio) {
            childWidth = viewWidth;
            childHeight = (int) ((float) previewHeight * widthRatio);
            childYOffset = (childHeight - viewHeight) / 2;
        }else{
            childWidth  = (int) ((float) previewWidth * heightRatio);
            childHeight = viewHeight;
            childXOffset = (childWidth - viewHeight) / 2;
        }

        for (int i = 0; i < getChildCount(); i++){

            getChildAt(i).layout(
                    -1 * childXOffset,-1*childYOffset,childWidth-childXOffset,childHeight-childYOffset);
        }
        try {
            startIFReady();

        }catch (IOException e){

            Log.e(TAG,"Could not start camera source.",e);
        }
    }

    private boolean isPortraitMode(){

        int orientation =   mContext.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            return false;
        }
        if (orientation ==  Configuration.ORIENTATION_PORTRAIT){
            return true;
        }
        Log.e(TAG,"isPortationMode returning false by default");
        return false;
    }

    @Override
    public boolean performClick() {

        super.performClick();

        return true;
    }

}

