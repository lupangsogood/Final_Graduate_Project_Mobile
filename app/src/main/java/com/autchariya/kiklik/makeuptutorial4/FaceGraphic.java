package com.autchariya.kiklik.makeuptutorial4;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.design.internal.NavigationMenu;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.autchariya.kiklik.makeuptutorial4.ar.camera.GraphicOverlay;

import io.github.yavski.fabspeeddial.FabSpeedDial;

import static android.content.Context.MODE_PRIVATE;


public class FaceGraphic extends GraphicOverlay.Graphic {
    private static final String TAG = "FaceGraphic";

    private static final float DOT_RADIUS = 3.0f;
    private static final float TEXT_OFFSET_Y = -30.0f;
    private  String Mode_Tutorial;
    private  String Style_Tutorial;

    private boolean mIsFrontFacing;
    private ImageView mImageView;
    private Context mContext;


    // This variable may be written to by one of many threads. By declaring it as volatile,
    // we guarantee that when we read its contents, we're reading the most recent "write"
    // by any thread.
    private volatile FaceData mFaceData;
    private volatile FaceActivity mFaceActivity;
    public static int[] arrayLeftCheek;
    public static int[] arrayRightCheek;
    public static int[] arrayRightEyeBrown;
    public static int[] arrayLeftEyeBrown;
    public static int[] arrayNose;
    public static int[] arrayMouth;

    public FabSpeedDial mbtn_menu ;
    public Integer checkposition = 0;




    private Paint mHintTextPaint;
    private Paint mHintOutlinePaint;
    private Paint mEyeWhitePaint;
    private Paint mIrisPaint;
    private Paint mEyeOutlinePaint;
    private Paint mEyelidPaint;

    private Drawable mPigNoseGraphic;
    private Drawable mMustacheGraphic;
    private Drawable mHappyStarGraphic;
    private Drawable mHatGraphic;
    private static Drawable mRed_dot;
    private Drawable mLeftCheek1;
    private Drawable mLeftCheek2;
    private Drawable mLeftCheek3;
    private Drawable mLeftCheek4;
    
    private Drawable mRightCheek1;
    private Drawable mRightCheek2;
    private Drawable mRightCheek3;
    private Drawable mRightCheek4;

    private Drawable mLeftEyeBrown1;
    private Drawable mLeftEyeBrown2;
    private Drawable mLeftEyeBrown3;

    private Drawable mRightEyeBrown1;
    private Drawable mRightEyeBrown2;


    private Drawable mNose1;
    private Drawable mNose2;

    private Drawable mMouth1;
    private Drawable mMouth2;
    private Drawable mMouth3;

    // We want each iris to move independently, so each one gets its own physics engine.
    private EyePhysics mLeftPhysics = new EyePhysics();
    private EyePhysics mRightPhysics = new EyePhysics();


    private boolean checkDelay1 = false;
    private boolean checkDelay2 = false;
    private boolean checkDelay3 = false;
    private boolean checkDelay4 = false;
    private boolean checkDelay5 = false;
    private boolean checkDelay6 = false;
    private boolean checkDelay7 = false;
    private boolean checkDelay8 = false;
    private boolean checkDelay9 = false;

    private CountDownTimer controlLeftCheek;
    public CountDownTimer delayleftCheek1;
    public CountDownTimer delayleftCheek2 ;
    public CountDownTimer delayleftCheek3 ;
    public CountDownTimer delayleftCheek4 ;


    private CountDownTimer controlRightCheek;
    private CountDownTimer delayrightCheek1;
    public CountDownTimer delayrightCheek2;
    public CountDownTimer delayrightCheek3;
    public CountDownTimer delayrightCheek4;


    private CountDownTimer controlLeftEyeBrwon;
    private CountDownTimer delayLefteyeBrown1;
    private CountDownTimer delayLefteyeBrown2;
    private CountDownTimer delayLefteyeBrown3;

    private CountDownTimer controlRightEyeBrown;
    private CountDownTimer delayRightEyeBrown1;
    private CountDownTimer delayRightEyeBrown2;
    private CountDownTimer delayRightEyeBrown3;
    private CountDownTimer delayRightEyeBrown4;
    private CountDownTimer delayRightEyeBrown5;
    private CountDownTimer delayRightEyeBrown6;
    private CountDownTimer delayRightEyeBrown7;
    private CountDownTimer delayRightEyeBrown8;
    private CountDownTimer delayRightEyeBrown9;


    private CountDownTimer controlNose;
    private CountDownTimer delayNose1;
    private CountDownTimer delayNose2;

    private CountDownTimer controlMouth;
    private CountDownTimer delayMouth1;
    private CountDownTimer delayMouth2;
    private CountDownTimer delayMouth3;



    FaceGraphic(GraphicOverlay overlay, final Context context, boolean isFrontFacing, ImageView imageView, FabSpeedDial btn_leftCheek) {
        super(overlay);

        SharedPreferences prefs = context.getSharedPreferences("makeup_tutorial", MODE_PRIVATE);
        Mode_Tutorial = prefs.getString("MODE_TUTORIAL"," ");
        Style_Tutorial = prefs.getString("STYLE_TUTORIAL"," ");

        Log.d("TAG_TUTORIAL","RES = "+Mode_Tutorial +" " + Style_Tutorial);
        mIsFrontFacing = isFrontFacing;
        Resources resources = context.getResources();
        initializePaints(resources);
        initializeGraphics(resources);
        mImageView = imageView;
        mContext = context;
        mbtn_menu= btn_leftCheek;


    }

    private void initializeGraphics(Resources resources) {


        if (Mode_Tutorial.equals("1")) {
            switch (Style_Tutorial) {
                case "1":
                    mRightEyeBrown1 = resources.getDrawable(R.drawable.eyebrowr1_1);
                    mRightEyeBrown2 = resources.getDrawable(R.drawable.eyebrowr1_2);
                    mLeftEyeBrown1 = resources.getDrawable(R.drawable.eyebrowl1_1);
                    mLeftEyeBrown2 = resources.getDrawable(R.drawable.eyebrowl1_2);
                    mNose1 = resources.getDrawable(R.drawable.nose1);
                    mNose2 = resources.getDrawable(R.drawable.nose2);
                    mLeftCheek1 = resources.getDrawable(R.drawable.cheekl1_1);
                    mLeftCheek2 = resources.getDrawable(R.drawable.cheekl1_2);
                    mLeftCheek3 = resources.getDrawable(R.drawable.cheekl1_3);

                    mRightCheek1 = resources.getDrawable(R.drawable.cheekr1_1);
                    mRightCheek2 = resources.getDrawable(R.drawable.cheekr1_2);
                    mRightCheek3 = resources.getDrawable(R.drawable.cheekr1_3);

                    mMouth1 = resources.getDrawable(R.drawable.mouth1_1);
                    mMouth2 = resources.getDrawable(R.drawable.mouth1_2);
                    mMouth3 = resources.getDrawable(R.drawable.mouth1_3);

                    //mLeftEyeBrown3 = resources.getDrawable(R.drawable.lefteyebrown3);

                    break;
                case "2":
                    mRightEyeBrown1 = resources.getDrawable(R.drawable.eyebrowr2_1);
                    mRightEyeBrown2 = resources.getDrawable(R.drawable.eyebrowr2_2);
                    mLeftEyeBrown1 = resources.getDrawable(R.drawable.eyebrowl2_1);
                    mLeftEyeBrown2 = resources.getDrawable(R.drawable.eyebrowl2_2);
                    mNose1 = resources.getDrawable(R.drawable.nose1);
                    mNose2 = resources.getDrawable(R.drawable.nose2);

                    mLeftCheek1 = resources.getDrawable(R.drawable.cheekl2_1);
                    mLeftCheek2 = resources.getDrawable(R.drawable.cheekl2_2);
                    mLeftCheek3 = resources.getDrawable(R.drawable.cheekl2_3);

                    mRightCheek1 = resources.getDrawable(R.drawable.cheekr2_1);
                    mRightCheek2 = resources.getDrawable(R.drawable.cheekr2_2);
                    mRightCheek3 = resources.getDrawable(R.drawable.cheekr2_3);

                    mMouth1 = resources.getDrawable(R.drawable.mouth1_1);
                    mMouth2 = resources.getDrawable(R.drawable.mouth1_2);
                    mMouth3 = resources.getDrawable(R.drawable.mouth1_3);
                    break;
                case "3":
                    mRightEyeBrown1 = resources.getDrawable(R.drawable.eyebrowr3_1);
                    mRightEyeBrown2 = resources.getDrawable(R.drawable.eyebrowr3_2);
                    mLeftEyeBrown1 = resources.getDrawable(R.drawable.eyebrowl3_1);
                    mLeftEyeBrown2 = resources.getDrawable(R.drawable.eyebrowl3_2);
                    mNose1 = resources.getDrawable(R.drawable.nose1);
                    mNose2 = resources.getDrawable(R.drawable.nose2);

                    mLeftCheek1 = resources.getDrawable(R.drawable.cheekl3_1);
                    mLeftCheek2 = resources.getDrawable(R.drawable.cheekl3_2);
                    mLeftCheek3 = resources.getDrawable(R.drawable.cheekl3_3);

                    mRightCheek1 = resources.getDrawable(R.drawable.cheekr3_1);
                    mRightCheek2 = resources.getDrawable(R.drawable.cheekr3_2);
                    mRightCheek3 = resources.getDrawable(R.drawable.cheekr3_3);

                    mMouth1 = resources.getDrawable(R.drawable.mouth4_1);
                    mMouth2 = resources.getDrawable(R.drawable.mouth4_2);
                    mMouth3 = resources.getDrawable(R.drawable.mouth4_3);
                    break;
            }
        }

        if (Mode_Tutorial.equals("2")) {
            switch (Style_Tutorial) {
                case "1":
                    mRightEyeBrown1 = resources.getDrawable(R.drawable.eyebrowr4_1);
                    mRightEyeBrown2 = resources.getDrawable(R.drawable.eyebrowr4_2);
                    mLeftEyeBrown1 = resources.getDrawable(R.drawable.eyebrowl4_1);
                    mLeftEyeBrown2 = resources.getDrawable(R.drawable.eyebrowl4_2);
                    mNose1 = resources.getDrawable(R.drawable.nose1);
                    mNose2 = resources.getDrawable(R.drawable.nose2);

                    mLeftCheek1 = resources.getDrawable(R.drawable.cheekl4_1);
                    mLeftCheek2 = resources.getDrawable(R.drawable.cheekl4_2);
                    mLeftCheek3 = resources.getDrawable(R.drawable.cheekl4_3);

                    mRightCheek1 = resources.getDrawable(R.drawable.cheekr4_1);
                    mRightCheek2 = resources.getDrawable(R.drawable.cheekr4_2);
                    mRightCheek3 = resources.getDrawable(R.drawable.cheekr4_3);

                    mMouth1 = resources.getDrawable(R.drawable.mouth4_1);
                    mMouth2 = resources.getDrawable(R.drawable.mouth4_2);
                    mMouth3 = resources.getDrawable(R.drawable.mouth4_3);
                    break;
                case "2":
                    mRightEyeBrown1 = resources.getDrawable(R.drawable.eyebrowr5_1);
                    mRightEyeBrown2 = resources.getDrawable(R.drawable.eyebrowr5_2);
                    mLeftEyeBrown1 = resources.getDrawable(R.drawable.eyebrowl5_1);
                    mLeftEyeBrown2 = resources.getDrawable(R.drawable.eyebrowl5_2);
                    mNose1 = resources.getDrawable(R.drawable.nose1);
                    mNose2 = resources.getDrawable(R.drawable.nose2);

                    mLeftCheek1 = resources.getDrawable(R.drawable.cheekl5_1);
                    mLeftCheek2 = resources.getDrawable(R.drawable.cheekl5_2);
                    mLeftCheek3 = resources.getDrawable(R.drawable.cheekl5_3);

                    mRightCheek1 = resources.getDrawable(R.drawable.cheekr5_1);
                    mRightCheek2 = resources.getDrawable(R.drawable.cheekr5_2);
                    mRightCheek3 = resources.getDrawable(R.drawable.cheekr5_3);

                    mMouth1 = resources.getDrawable(R.drawable.mouth5_1);
                    mMouth2 = resources.getDrawable(R.drawable.mouth5_2);
                    mMouth3 = resources.getDrawable(R.drawable.mouth5_3);
                    break;
                case "3":
                    mRightEyeBrown1 = resources.getDrawable(R.drawable.eyebrowr6_1);
                    mRightEyeBrown2 = resources.getDrawable(R.drawable.eyebrowr6_2);
                    mLeftEyeBrown1 = resources.getDrawable(R.drawable.eyebrowl6_1);
                    mLeftEyeBrown2 = resources.getDrawable(R.drawable.eyebrowl6_2);
                    mNose1 = resources.getDrawable(R.drawable.nose1);
                    mNose2 = resources.getDrawable(R.drawable.nose2);

                    mLeftCheek1 = resources.getDrawable(R.drawable.cheekl6_1);
                    mLeftCheek2 = resources.getDrawable(R.drawable.cheekl6_2);
                    mLeftCheek3 = resources.getDrawable(R.drawable.cheekl6_3);

                    mRightCheek1 = resources.getDrawable(R.drawable.cheekr6_1);
                    mRightCheek2 = resources.getDrawable(R.drawable.cheekr6_2);
                    mRightCheek3 = resources.getDrawable(R.drawable.cheekr6_3);

                    mMouth1 = resources.getDrawable(R.drawable.mouth6_1);
                    mMouth2 = resources.getDrawable(R.drawable.mouth6_2);
                    mMouth3 = resources.getDrawable(R.drawable.mouth6_3);
                    break;
            }
        }

        if (Mode_Tutorial.equals("3")) {
            switch (Style_Tutorial) {
                case "1":
                    mRightEyeBrown1 = resources.getDrawable(R.drawable.eyebrowr7_1);
                    mRightEyeBrown2 = resources.getDrawable(R.drawable.eyebrowr7_2);
                    mLeftEyeBrown1 = resources.getDrawable(R.drawable.eyebrowl7_1);
                    mLeftEyeBrown2 = resources.getDrawable(R.drawable.eyebrowl7_2);
                    mNose1 = resources.getDrawable(R.drawable.nose1);
                    mNose2 = resources.getDrawable(R.drawable.nose2);

                    mLeftCheek1 = resources.getDrawable(R.drawable.cheekl7_1);
                    mLeftCheek2 = resources.getDrawable(R.drawable.cheekl7_2);
                    mLeftCheek3 = resources.getDrawable(R.drawable.cheekl7_3);

                    mRightCheek1 = resources.getDrawable(R.drawable.cheekr7_1);
                    mRightCheek2 = resources.getDrawable(R.drawable.cheekr7_2);
                    mRightCheek3 = resources.getDrawable(R.drawable.cheekr7_3);

                    mMouth1 = resources.getDrawable(R.drawable.mouth7_1);
                    mMouth2 = resources.getDrawable(R.drawable.mouth7_2);
                    mMouth3 = resources.getDrawable(R.drawable.mouth7_3);

                    break;
                case "2":
                    mRightEyeBrown1 = resources.getDrawable(R.drawable.eyebrowr8_1);
                    mRightEyeBrown2 = resources.getDrawable(R.drawable.eyebrowr8_2);
                    mLeftEyeBrown1 = resources.getDrawable(R.drawable.eyebrowl8_1);
                    mLeftEyeBrown2 = resources.getDrawable(R.drawable.eyebrowl8_2);
                    mNose1 = resources.getDrawable(R.drawable.nose1);
                    mNose2 = resources.getDrawable(R.drawable.nose2);

                    mLeftCheek1 = resources.getDrawable(R.drawable.cheekl8_1);
                    mLeftCheek2 = resources.getDrawable(R.drawable.cheekl8_2);
                    mLeftCheek3 = resources.getDrawable(R.drawable.cheekl8_3);

                    mRightCheek1 = resources.getDrawable(R.drawable.cheekr8_1);
                    mRightCheek2 = resources.getDrawable(R.drawable.cheekr8_2);
                    mRightCheek3 = resources.getDrawable(R.drawable.cheekr8_3);

                    mMouth1 = resources.getDrawable(R.drawable.mouth8_1);
                    mMouth2 = resources.getDrawable(R.drawable.mouth8_2);
                    mMouth3 = resources.getDrawable(R.drawable.mouth8_3);

                    break;
                case "3":
                    mRightEyeBrown1 = resources.getDrawable(R.drawable.eyebrowr9_1);
                    mRightEyeBrown2 = resources.getDrawable(R.drawable.eyebrowr9_2);
                    mLeftEyeBrown1 = resources.getDrawable(R.drawable.eyebrowl9_1);
                    mLeftEyeBrown2 = resources.getDrawable(R.drawable.eyebrowl9_2);
                    mNose1 = resources.getDrawable(R.drawable.nose1);
                    mNose2 = resources.getDrawable(R.drawable.nose2);

                    mLeftCheek1 = resources.getDrawable(R.drawable.cheekl9_1);
                    mLeftCheek2 = resources.getDrawable(R.drawable.cheekl9_2);
                    mLeftCheek3 = resources.getDrawable(R.drawable.cheekl9_3);

                    mRightCheek1 = resources.getDrawable(R.drawable.cheekr9_1);
                    mRightCheek2 = resources.getDrawable(R.drawable.cheekr9_2);
                    mRightCheek3 = resources.getDrawable(R.drawable.cheekr9_3);

                    mMouth1 = resources.getDrawable(R.drawable.mouth9_1);
                    mMouth2 = resources.getDrawable(R.drawable.mouth9_2);
                    mMouth3 = resources.getDrawable(R.drawable.mouth9_3);
                    break;
            }
        }

        if (Mode_Tutorial.equals("4")) {
            switch (Style_Tutorial) {
                case "1":
                    mRightEyeBrown1 = resources.getDrawable(R.drawable.eyebrowr10_1);
                    mRightEyeBrown2 = resources.getDrawable(R.drawable.eyebrowr10_2);
                    mLeftEyeBrown1 = resources.getDrawable(R.drawable.eyebrowl10_1);
                    mLeftEyeBrown2 = resources.getDrawable(R.drawable.eyebrowl10_2);
                    mNose1 = resources.getDrawable(R.drawable.nose1);
                    mNose2 = resources.getDrawable(R.drawable.nose2);

                    mLeftCheek1 = resources.getDrawable(R.drawable.cheekl10_1);
                    mLeftCheek2 = resources.getDrawable(R.drawable.cheekl10_2);
                    mLeftCheek3 = resources.getDrawable(R.drawable.cheekl10_3);

                    mRightCheek1 = resources.getDrawable(R.drawable.cheekr10_1);
                    mRightCheek2 = resources.getDrawable(R.drawable.cheekr10_2);
                    mRightCheek3 = resources.getDrawable(R.drawable.cheekr10_3);

                    mMouth1 = resources.getDrawable(R.drawable.mouth10_1);
                    mMouth2 = resources.getDrawable(R.drawable.mouth10_2);
                    mMouth3 = resources.getDrawable(R.drawable.mouth10_3);

                    break;
                case "2":
                    mRightEyeBrown1 = resources.getDrawable(R.drawable.eyebrowr11_1);
                    mRightEyeBrown2 = resources.getDrawable(R.drawable.eyebrowr11_2);
                    mLeftEyeBrown1 = resources.getDrawable(R.drawable.eyebrowl11_1);
                    mLeftEyeBrown2 = resources.getDrawable(R.drawable.eyebrowl11_2);
                    mNose1 = resources.getDrawable(R.drawable.nose1);
                    mNose2 = resources.getDrawable(R.drawable.nose2);

                    mLeftCheek1 = resources.getDrawable(R.drawable.cheekl11_1);
                    mLeftCheek2 = resources.getDrawable(R.drawable.cheekl11_2);
                    mLeftCheek3 = resources.getDrawable(R.drawable.cheekl11_3);

                    mRightCheek1 = resources.getDrawable(R.drawable.cheekr11_1);
                    mRightCheek2 = resources.getDrawable(R.drawable.cheekr11_2);
                    mRightCheek3 = resources.getDrawable(R.drawable.cheekr11_3);

                    mMouth1 = resources.getDrawable(R.drawable.mouth11_1);
                    mMouth2 = resources.getDrawable(R.drawable.mouth11_2);
                    mMouth3 = resources.getDrawable(R.drawable.mouth11_3);
                    break;
                case "3":
                    mRightEyeBrown1 = resources.getDrawable(R.drawable.eyebrowr12_1);
                    mRightEyeBrown2 = resources.getDrawable(R.drawable.eyebrowr12_2);
                    mLeftEyeBrown1 = resources.getDrawable(R.drawable.eyebrowl12_1);
                    mLeftEyeBrown2 = resources.getDrawable(R.drawable.eyebrowl12_2);
                    mNose1 = resources.getDrawable(R.drawable.nose1);
                    mNose2 = resources.getDrawable(R.drawable.nose2);

                    mLeftCheek1 = resources.getDrawable(R.drawable.cheekl12_1);
                    mLeftCheek2 = resources.getDrawable(R.drawable.cheekl12_2);
                    mLeftCheek3 = resources.getDrawable(R.drawable.cheekl12_3);

                    mRightCheek1 = resources.getDrawable(R.drawable.cheekr12_1);
                    mRightCheek2 = resources.getDrawable(R.drawable.cheekr12_2);
                    mRightCheek3 = resources.getDrawable(R.drawable.cheekr12_3);

                    mMouth1 = resources.getDrawable(R.drawable.mouth12_1);
                    mMouth2 = resources.getDrawable(R.drawable.mouth12_2);
                    mMouth3 = resources.getDrawable(R.drawable.mouth12_3);
                    break;
            }
        }
    }



    private void initializePaints(Resources resources) {
        mHintTextPaint = new Paint();
        mHintTextPaint.setColor(resources.getColor(R.color.overlayHint));
        mHintTextPaint.setTextSize(resources.getDimension(R.dimen.textSize));

        mHintOutlinePaint = new Paint();
        mHintOutlinePaint.setColor(resources.getColor(R.color.overlayHint));
        mHintOutlinePaint.setStyle(Paint.Style.STROKE);
        mHintOutlinePaint.setStrokeWidth(resources.getDimension(R.dimen.hintStroke));

        mEyeWhitePaint = new Paint();
        mEyeWhitePaint.setColor(resources.getColor(R.color.eyeWhite));
        mEyeWhitePaint.setStyle(Paint.Style.FILL);

        mIrisPaint = new Paint();
        mIrisPaint.setColor(resources.getColor(R.color.iris));
        mIrisPaint.setStyle(Paint.Style.FILL);

        mEyeOutlinePaint = new Paint();
        mEyeOutlinePaint.setColor(resources.getColor(R.color.eyeOutline));
        mEyeOutlinePaint.setStyle(Paint.Style.STROKE);
        mEyeOutlinePaint.setStrokeWidth(resources.getDimension(R.dimen.eyeOutlineStroke));

        mEyelidPaint = new Paint();
        mEyelidPaint.setColor(resources.getColor(R.color.eyelid));
        mEyelidPaint.setStyle(Paint.Style.FILL);
    }

    // 1
    void update(FaceData faceData) {
        mFaceData = faceData;

        postInvalidate(); // Trigger a redraw of the graphic (i.e. cause draw() to be called).
    }


    public  void resetDelay(){

        checkDelay1 = false;
        checkDelay2 = false;
        checkDelay3 = false;
        checkDelay4 = false;
        checkDelay5 = false;
        checkDelay6 = false;
        checkDelay7 = false;
        checkDelay8 = false;
        checkDelay9 = false;
    }

    public  void delayLeftCheek(){


        controlLeftCheek = new CountDownTimer(6000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                resetDelay();

                delayLeftCheek();
            }
        }.start();

        delayleftCheek1 = new CountDownTimer(500,100) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
               checkDelay1 = true;
            }
        }.start();
            delayleftCheek2 = new CountDownTimer(1000,1000) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    checkDelay2 = true;
                }
            }.start();
                delayleftCheek3 = new CountDownTimer(1500,1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        checkDelay3 = true;
                    }
                }.start();
                    delayleftCheek4 = new CountDownTimer(2000,1000) {
                        @Override
                        public void onTick(long l) {

                        }

                        @Override
                        public void onFinish() {
                            checkDelay4 = true;
                        }
                    }.start();



    }
    public  void delayRightCheek(){

        controlRightCheek = new CountDownTimer(6000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                resetDelay();

                delayRightCheek();
            }
        }.start();

        delayrightCheek1 = new CountDownTimer(500,100) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                checkDelay1 = true;
            }
        }.start();
        delayrightCheek2 = new CountDownTimer(1000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                checkDelay2 = true;
            }
        }.start();
        delayrightCheek3 = new CountDownTimer(1500,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                checkDelay3 = true;
            }
        }.start();
        delayrightCheek4 = new CountDownTimer(2000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                checkDelay4 = true;
            }
        }.start();
        
    }
    public  void delayLeftEyeBrown(){

        controlLeftEyeBrwon = new CountDownTimer(15000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

                resetDelay();
                delayLeftEyeBrown();

            }
        }.start();

        delayLefteyeBrown1 = new CountDownTimer(500,100) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                checkDelay1 = true;
            }
        }.start();
        delayLefteyeBrown2 = new CountDownTimer(1000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                checkDelay2 = true;
            }
        }.start();
        delayLefteyeBrown3 = new CountDownTimer(1500,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                checkDelay3 = true;
            }
        }.start();




    }
    public  void delayRightEyeBrown(){

        controlRightEyeBrown = new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

                resetDelay();
                delayRightEyeBrown();

            }
        }.start();

        delayRightEyeBrown1 = new CountDownTimer(500,100) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                checkDelay1 = true;
            }
        }.start();
        delayRightEyeBrown2 = new CountDownTimer(1000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                checkDelay2 = true;
            }
        }.start();
        delayRightEyeBrown3 = new CountDownTimer(1500,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                checkDelay3 = true;
            }
        }.start();
        delayRightEyeBrown4 = new CountDownTimer(1800,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                checkDelay4 = true;
            }
        }.start();
        delayRightEyeBrown5 = new CountDownTimer(2000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() { checkDelay5 = true;
            }
        }.start();
        delayRightEyeBrown6 = new CountDownTimer(2300,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                checkDelay6 = true;
            }
        }.start();
        delayRightEyeBrown7 = new CountDownTimer(2500,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                checkDelay7 = true;
            }
        }.start();
        delayRightEyeBrown8 = new CountDownTimer(2800,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                checkDelay8 = true;
            }
        }.start();
        delayRightEyeBrown9 = new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                checkDelay9 = true;
            }
        }.start();


    }
    public  void delayNose(){

        controlNose = new CountDownTimer(1500, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                resetDelay();
                delayNose();

            }
        }.start();

        delayNose1 = new CountDownTimer(500,100) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                checkDelay1 = true;
            }
        }.start();

        delayNose2 = new CountDownTimer(1000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                checkDelay2 = true;
            }
        }.start();




    }
    public  void delayMouth(){

        controlMouth = new CountDownTimer(1500,100) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                resetDelay();
                delayMouth();
            }
        }.start();

        delayMouth1 = new CountDownTimer(300,100) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                checkDelay1 = true;
            }
        }.start();

        new CountDownTimer(500, 100) {
            @Override
            public void onTick(long l) {
                checkDelay2 = true;
            }

            @Override
            public void onFinish() {
                checkDelay1 = false;
            }
        }.start();


        delayMouth2 = new CountDownTimer(600,100) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                checkDelay2 = true;
            }
        }.start();

        new CountDownTimer(800, 100) {
            @Override
            public void onTick(long l) {
                checkDelay3 = true;
            }

            @Override
            public void onFinish() {
                checkDelay2 = false;
            }
        }.start();

        delayMouth3 = new CountDownTimer(900,100) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

            }
        }.start();



    }

    @Override
    public void draw(final Canvas canvas) {
        final float DOT_RADIUS = 3.0f;
        final float TEXT_OFFSET_Y = -30.0f;



        // Confirm that the face and its features are still visible
        // before drawing any graphics over it.
        if (mFaceData == null) {

            return;
        }

        PointF detectPosition = mFaceData.getPosition();
        PointF detectLeftEyePosition = mFaceData.getLeftEyePosition();
        PointF detectRightEyePosition = mFaceData.getRightEyePosition();
        PointF detectNoseBasePosition = mFaceData.getNoseBasePosition();
        PointF detectMouthLeftPosition = mFaceData.getMouthLeftPosition();
        PointF detectMouthBottomPosition = mFaceData.getMouthBottomPosition();
        PointF detectMouthRightPosition = mFaceData.getMouthRightPosition();
        PointF detectCheekRightPosition = mFaceData.getRightCheekPosition();
        PointF detectCheekLeftPosition = mFaceData.getLeftCheekPosition();

        if ((detectPosition == null) ||
                (detectLeftEyePosition == null) ||
                (detectRightEyePosition == null) ||
                (detectNoseBasePosition == null) ||
                (detectMouthLeftPosition == null) ||
                (detectMouthBottomPosition == null) ||
                (detectMouthRightPosition == null) ||
                (detectCheekRightPosition == null) ||
                (detectCheekLeftPosition == null)) {


            return;
        }



        // Face position and dimensions
        PointF position = new PointF(translateX(detectPosition.x),
                translateY(detectPosition.y));
        final float width = scaleX(mFaceData.getWidth());
        final float height = scaleY(mFaceData.getHeight());

        // Eye coordinates
        final PointF leftEyePosition = new PointF(translateX(detectLeftEyePosition.x),
                translateY(detectLeftEyePosition.y));
        PointF rightEyePosition = new PointF(translateX(detectRightEyePosition.x),
                translateY(detectRightEyePosition.y));

        // Eye state
        boolean leftEyeOpen = mFaceData.isLeftEyeOpen();
        boolean rightEyeOpen = mFaceData.isRightEyeOpen();

        // Nose coordinates
        final PointF noseBasePosition = new PointF(translateX(detectNoseBasePosition.x),
                translateY(detectNoseBasePosition.y));

        // Mouth coordinates
        final PointF mouthLeftPosition = new PointF(translateX(detectMouthLeftPosition.x),
                translateY(detectMouthLeftPosition.y));
        PointF mouthRightPosition = new PointF(translateX(detectMouthRightPosition.x),
                translateY(detectMouthRightPosition.y));
        PointF mouthBottomPosition = new PointF(translateX(detectMouthBottomPosition.x),
                translateY(detectMouthBottomPosition.y));

        //Cheek Coordinates

        PointF cheekRightPosition = new PointF(translateX(detectCheekRightPosition.x), translateY(detectCheekRightPosition.y));


        final PointF cheekLeftPosition = new PointF(translateX(detectCheekLeftPosition.x), translateY(detectCheekLeftPosition.y));


        // Smile state
        boolean smiling = mFaceData.isSmiling();

        // Calculate the distance between the eyes using Pythagoras' formula,
        // and we'll use that distance to set the size of the eyes and irises.
        float EYE_RADIUS_PROPORTION = 0.45f;
        float IRIS_RADIUS_PROPORTION = EYE_RADIUS_PROPORTION / 2.0f;
        float distance = (float) Math.sqrt(
                (rightEyePosition.x - leftEyePosition.x) * (rightEyePosition.x - leftEyePosition.x) +
                        (rightEyePosition.y - leftEyePosition.y) * (rightEyePosition.y - leftEyePosition.y));
        float eyeRadius = EYE_RADIUS_PROPORTION * distance;
        float irisRadius = IRIS_RADIUS_PROPORTION * distance;


        // Draw the eyes.
        PointF leftIrisPosition = mLeftPhysics.nextIrisPosition(leftEyePosition, eyeRadius, irisRadius);
        // drawEye(canvas, leftEyePosition, eyeRadius, leftIrisPosition, irisRadius, leftEyeOpen, smiling);
        PointF rightIrisPosition = mRightPhysics.nextIrisPosition(rightEyePosition, eyeRadius, irisRadius);
        // drawEye(canvas, rightEyePosition, eyeRadius, rightIrisPosition, irisRadius, rightEyeOpen, smiling);

        //ทดสอบ เปลี่ยนค่า checkDelay หลังจากวาด Gif ทั้งหทดขึ้นมา
        if (checkposition == 1) {



            if (checkDelay1) {
                drawGifLeftCheek1(canvas, width, height, mouthLeftPosition, cheekLeftPosition, leftEyePosition, noseBasePosition);
            }

            if(checkDelay2){
                drawGifLeftCheek2(canvas, width, height, mouthLeftPosition, cheekLeftPosition, leftEyePosition, noseBasePosition);
            }

            if(checkDelay3){
                drawGifLeftCheek3(canvas, width, height, mouthLeftPosition, cheekLeftPosition, leftEyePosition, noseBasePosition);
            }
            /*
            if(checkDelay4){
                drawGifLeftCheek4(canvas, width, height, mouthLeftPosition, cheekLeftPosition, leftEyePosition, cheekRightPosition);

            }
*/

        }

        if (checkposition == 2 ) {

            if (checkDelay1) {
                drawGifRightCheek1(canvas, width, height, mouthRightPosition, cheekRightPosition, rightEyePosition);
            }
            if (checkDelay2){
                drawGifRightCheek2(canvas, width, height, mouthRightPosition, cheekRightPosition, rightEyePosition);
            }

            if (checkDelay3){
                drawGifRightCheek3(canvas, width, height, mouthRightPosition, cheekRightPosition, rightEyePosition);
            }
/*
            if (checkDelay4){
                drawGifRightCheek4(canvas, width, height, mouthRightPosition, cheekRightPosition, rightEyePosition);
            }
*/


        }

        if (checkposition == 3) {

            if (checkDelay1){

                drawMouthGif1(canvas,noseBasePosition,mouthLeftPosition,mouthRightPosition,mouthBottomPosition);
            }

            if (checkDelay2){

                drawMouthGif2(canvas,noseBasePosition,mouthLeftPosition,mouthRightPosition,mouthBottomPosition);
            }

            if (checkDelay3){

                drawMouthGif3(canvas,noseBasePosition,mouthLeftPosition,mouthRightPosition,mouthBottomPosition);
            }

        }

        if (checkposition == 4 ) {

            if (checkDelay1) {
                drawGifNose1(canvas, noseBasePosition, leftEyePosition, rightEyePosition, width);
            }

            if (checkDelay2){
                drawGifNose2(canvas, noseBasePosition, leftEyePosition, rightEyePosition, width);

            }
        }


        if (checkposition == 5) {

            if(checkDelay1) {

                drawGifLeftEyeBrown1(canvas, width, height, leftEyePosition, cheekRightPosition, leftIrisPosition, irisRadius);
            }

            if(checkDelay2) {

                drawGifLeftEyeBrown2(canvas, width, height, leftEyePosition, eyeRadius, leftIrisPosition, irisRadius);
            }
            /*
            if(checkDelay3) {

                drawGifLeftEyeBrown3(canvas, width, height, leftEyePosition, eyeRadius, leftIrisPosition, irisRadius);
            }
            */
            
            }


        if (checkposition == 6) {

            if (checkDelay1) {

                drawGifRightEyeBrown1(canvas, width, height, rightEyePosition, eyeRadius, rightIrisPosition, irisRadius);            }
            if (checkDelay2) {

                drawGifRightEyeBrown2(canvas, width, height, rightEyePosition, eyeRadius, rightIrisPosition, irisRadius);            }
            /*
            if (checkDelay3) {

                drawGifRightEyeBrown3(canvas, width, height, rightEyePosition, eyeRadius, rightIrisPosition, irisRadius);
            }

            */
        }


        // Head tilt
        float eulerY = mFaceData.getEulerY();
        float eulerZ = mFaceData.getEulerZ();

        // Draw the hat only if the subject's head is titled at a sufficiently jaunty angle.
        final float HEAD_TILT_HAT_THRESHOLD = 20.0f;
        if (Math.abs(eulerZ) > HEAD_TILT_HAT_THRESHOLD) {
            // drawHat(canvas, position, width, height, noseBasePosition);
        }


        mbtn_menu.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {

                if (checkposition == 1 ){

                    controlLeftCheek.cancel();
                    delayleftCheek2.cancel();
                    delayleftCheek3.cancel();
                    delayleftCheek4.cancel();

                    resetDelay();

                }
                if (checkposition == 2 ){

                    controlRightCheek.cancel();
                    delayrightCheek1.cancel();
                    delayrightCheek2.cancel();
                    delayrightCheek3.cancel();

                    resetDelay();

                }

                if (checkposition == 3 ){

                    controlMouth.cancel();
                    delayMouth1.cancel();
                    delayMouth2.cancel();

                    resetDelay();
                }

                if (checkposition == 4){

                    controlNose.cancel();
                    delayNose1.cancel();
                    delayNose2.cancel();

                    resetDelay();
                }

                if (checkposition == 5 ){

                    controlLeftEyeBrwon.cancel();
                    delayLefteyeBrown1.cancel();
                    delayLefteyeBrown2.cancel();
                    delayLefteyeBrown3.cancel();

                    resetDelay();

                }
                if (checkposition == 6 ){

                    controlRightEyeBrown.cancel();
                    delayRightEyeBrown1.cancel();
                    delayRightEyeBrown2.cancel();
                    delayRightEyeBrown3.cancel();
                    delayRightEyeBrown4.cancel();
                    delayRightEyeBrown5.cancel();
                    delayRightEyeBrown6.cancel();
                    delayRightEyeBrown7.cancel();
                    delayRightEyeBrown8.cancel();
                    delayRightEyeBrown9.cancel();

                    resetDelay();

                }

                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {

                checkposition = 0;

                // เปลี่ยนค่า Delay เพื่อเครียมวาดของตำแหน่งอื่น

                if (menuItem.getTitle().equals("LeftCheek")) {

                    checkposition = 1;
                    delayLeftCheek();
                }
                if (menuItem.getTitle().equals("RightCheek")){

                    checkposition = 2;
                    delayRightCheek();
                }
                if (menuItem.getTitle().equals("Mouth")){

                    checkposition = 3;
                    delayMouth();
                }
                if (menuItem.getTitle().equals("Nose")){

                    checkposition = 4;

                    delayNose();
                }
                if (menuItem.getTitle().equals("LeftEyeBrown")){

                    checkposition = 5;
                    delayLeftEyeBrown();
                }
                if (menuItem.getTitle().equals("RightEyeBrown")){

                    checkposition = 6;
                    delayRightEyeBrown();

                }
                return true;
            }

            @Override
            public void onMenuClosed() {
                resetDelay();
            }
        });


    }

    private void drawEye(Canvas canvas,
                         PointF eyePosition, float eyeRadius,
                         PointF irisPosition, float irisRadius,
                         boolean eyeOpen, boolean smiling) {
        if (eyeOpen) {
            canvas.drawCircle(eyePosition.x, eyePosition.y, eyeRadius, mEyeWhitePaint);
            if (smiling) {
                mHappyStarGraphic.setBounds(
                        (int) (irisPosition.x - irisRadius),
                        (int) (irisPosition.y - irisRadius),
                        (int) (irisPosition.x + irisRadius),
                        (int) (irisPosition.y + irisRadius));
                mHappyStarGraphic.draw(canvas);
            } else {
                canvas.drawCircle(irisPosition.x, irisPosition.y, irisRadius, mIrisPaint);
            }
        } else {
            canvas.drawCircle(eyePosition.x, eyePosition.y, eyeRadius, mEyelidPaint);
            float y = eyePosition.y;
            float start = eyePosition.x - eyeRadius;
            float end = eyePosition.x + eyeRadius;
            canvas.drawLine(start, y, end, y, mEyeOutlinePaint);
        }
        canvas.drawCircle(eyePosition.x, eyePosition.y, eyeRadius, mEyeOutlinePaint);

    }



//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


   

    private void drawGifLeftCheek1 (final Canvas canvas, float faceWidth , float faceHeight, PointF mouthLeftPosition, PointF cheekLeftPosition, PointF leftEyePosition, PointF noseBasePosition ){

        final float HALF_FACE_RATIO = (float) (1 / 4.0);
        float halfFace = faceWidth * HALF_FACE_RATIO;
        final float FACE_HEIGHT_RATIO = (float)(1.0 / 6.0);
        //ตั้งตัวแปรมารับค่าแล้วคำนวณหาตำแหน่งที่ต้องการ
        float heightFace = faceHeight * FACE_HEIGHT_RATIO;

        final int left = (int) (cheekLeftPosition.x -(halfFace));
        final int top  = (int) (cheekLeftPosition.y-(cheekLeftPosition.y-leftEyePosition.y));
        final int right = (int) (cheekLeftPosition.x +(halfFace/1.25));
        final int bottom = (int) (mouthLeftPosition.y-5);

        mLeftCheek1.setBounds(left,top,right,bottom);
        mLeftCheek1.draw(canvas);
    }

    private void drawGifLeftCheek2 (final Canvas canvas, float faceWidth , float faceHeight, PointF mouthLeftPosition, PointF cheekLeftPosition, PointF leftEyePosition, PointF noseBasePosition ){

        final float HALF_FACE_RATIO = (float) (1 / 4.0);
        float halfFace = faceWidth * HALF_FACE_RATIO;
        final float FACE_HEIGHT_RATIO = (float)(1.0 / 6.0);
        //ตั้งตัวแปรมารับค่าแล้วคำนวณหาตำแหน่งที่ต้องการ
        float heightFace = faceHeight * FACE_HEIGHT_RATIO;

        final int left = (int) (cheekLeftPosition.x -(halfFace));
        final int top  = (int) (cheekLeftPosition.y-(cheekLeftPosition.y-leftEyePosition.y));
        final int right = (int) (cheekLeftPosition.x +(halfFace/1.25));
        final int bottom = (int) (mouthLeftPosition.y-5);

        mLeftCheek2.setBounds(left,top,right,bottom);
        mLeftCheek2.draw(canvas);
    }

    private void drawGifLeftCheek3 (final Canvas canvas, float faceWidth , float faceHeight, PointF mouthLeftPosition, PointF cheekLeftPosition, PointF leftEyePosition, PointF noseBasePosition ){

        final float HALF_FACE_RATIO = (float) (1 / 4.0);
        float halfFace = faceWidth * HALF_FACE_RATIO;
        final float FACE_HEIGHT_RATIO = (float)(1.0 / 6.0);
        //ตั้งตัวแปรมารับค่าแล้วคำนวณหาตำแหน่งที่ต้องการ
        float heightFace = faceHeight * FACE_HEIGHT_RATIO;

        final int left = (int) (cheekLeftPosition.x -(halfFace));
        final int top  = (int) (cheekLeftPosition.y-(cheekLeftPosition.y-leftEyePosition.y));
        final int right = (int) (cheekLeftPosition.x +(halfFace/1.25));
        final int bottom = (int) (mouthLeftPosition.y-5);

        mLeftCheek3.setBounds(left,top,right,bottom);
        mLeftCheek3.draw(canvas);
    }

    private void drawGifLeftCheek4 (final Canvas canvas, float faceWidth , float faceHeight, PointF mouthLeftPosition, PointF cheekLeftPosition, PointF leftEyePosition, PointF cheekRightPosition ){

        final float HALF_FACE_RATIO = (float) (1 / 4.0);
        float halfFace = faceWidth * HALF_FACE_RATIO;
        final float FACE_HEIGHT_RATIO = (float)(1.0 / 6.0);
        //ตั้งตัวแปรมารับค่าแล้วคำนวณหาตำแหน่งที่ต้องการ
        float heightFace = faceHeight * FACE_HEIGHT_RATIO;

        final int left = (int) (cheekLeftPosition.x -(halfFace));
        final int top  = (int) (cheekLeftPosition.y-(cheekLeftPosition.y-leftEyePosition.y));
        final int right = (int) (cheekLeftPosition.x +(halfFace/1.25));
        final int bottom = (int) (mouthLeftPosition.y-5);

       // Log.d("CHECK Left CHEEK","Left "+check);
        /*
            mLeftCheek4.setBounds(left, top, right, bottom);
            mLeftCheek4.draw(canvas);
        */
    }

    // -----------------------------------------------------------------------------------------------------------------------------

    public  void drawGifRightCheek1 (Canvas canvas,float faceWidth,float faceHeight,PointF mouthRightPosition, PointF cheekRightPosition,PointF rightEyePosition ) {

        final float HALF_FACE_RATIO = (float) (1 / 4.0);
        float halfFace = faceWidth * HALF_FACE_RATIO;
        final float FACE_HEIGHT_RATIO = (float) (1.0 / 6.0);
        //ตั้งตัวแปรมารับค่าแล้วคำนวณหาตำแหน่งที่ต้องการ
        float heightFace = faceHeight * FACE_HEIGHT_RATIO;
        int left = (int) (cheekRightPosition.x - (halfFace / 1.25));
        int top = (int) (cheekRightPosition.y - (cheekRightPosition.y - rightEyePosition.y));
        int right = (int) (cheekRightPosition.x + (halfFace));
        int bottom = (int) (mouthRightPosition.y - 5);


        mRightCheek1.setBounds(left, top, right, bottom);
        mRightCheek1.draw(canvas);



    }

    public  void drawGifRightCheek2 (Canvas canvas,float faceWidth,float faceHeight,PointF mouthRightPosition, PointF cheekRightPosition,PointF rightEyePosition ) {

        final float HALF_FACE_RATIO = (float) (1 / 4.0);
        float halfFace = faceWidth * HALF_FACE_RATIO;
        final float FACE_HEIGHT_RATIO = (float) (1.0 / 6.0);
        //ตั้งตัวแปรมารับค่าแล้วคำนวณหาตำแหน่งที่ต้องการ
        float heightFace = faceHeight * FACE_HEIGHT_RATIO;
        int left = (int) (cheekRightPosition.x - (halfFace / 1.25));
        int top = (int) (cheekRightPosition.y - (cheekRightPosition.y - rightEyePosition.y));
        int right = (int) (cheekRightPosition.x + (halfFace));
        int bottom = (int) (mouthRightPosition.y - 5);


        mRightCheek2.setBounds(left, top, right, bottom);
        mRightCheek2.draw(canvas);


    }

    public  void drawGifRightCheek3 (Canvas canvas,float faceWidth,float faceHeight,PointF mouthRightPosition, PointF cheekRightPosition,PointF rightEyePosition ) {

        final float HALF_FACE_RATIO = (float) (1 / 4.0);
        float halfFace = faceWidth * HALF_FACE_RATIO;
        final float FACE_HEIGHT_RATIO = (float) (1.0 / 6.0);
        //ตั้งตัวแปรมารับค่าแล้วคำนวณหาตำแหน่งที่ต้องการ
        float heightFace = faceHeight * FACE_HEIGHT_RATIO;
        int left = (int) (cheekRightPosition.x - (halfFace / 1.25));
        int top = (int) (cheekRightPosition.y - (cheekRightPosition.y - rightEyePosition.y));
        int right = (int) (cheekRightPosition.x + (halfFace));
        int bottom = (int) (mouthRightPosition.y - 5);


        mRightCheek3.setBounds(left, top, right, bottom);
        mRightCheek3.draw(canvas);


    }

    public  void drawGifRightCheek4 (Canvas canvas,float faceWidth,float faceHeight,PointF mouthRightPosition, PointF cheekRightPosition,PointF rightEyePosition ) {

        final float HALF_FACE_RATIO = (float) (1 / 4.0);
        float halfFace = faceWidth * HALF_FACE_RATIO;
        final float FACE_HEIGHT_RATIO = (float) (1.0 / 6.0);
        //ตั้งตัวแปรมารับค่าแล้วคำนวณหาตำแหน่งที่ต้องการ
        float heightFace = faceHeight * FACE_HEIGHT_RATIO;
        int left = (int) (cheekRightPosition.x - (halfFace / 1.25));
        int top = (int) (cheekRightPosition.y - (cheekRightPosition.y - rightEyePosition.y));
        int right = (int) (cheekRightPosition.x + (halfFace));
        int bottom = (int) (mouthRightPosition.y - 5);


        mRightCheek4.setBounds(left, top, right, bottom);
        mRightCheek4.draw(canvas);


    }

    // -----------------------------------------------------------------------------------------------------------------------------

    private void drawMouthGif1(Canvas canvas,
                              PointF noseBasePosition,
                              PointF mouthLeftPosition, PointF mouthRightPosition,PointF mouthBottomPosition) {
        int left = (int) (mouthLeftPosition.x);
        int top = (int) (noseBasePosition.y);
        int right = (int)( mouthRightPosition.x);
        int bottom = (int) (Math.min(mouthLeftPosition.y, mouthRightPosition.y)*1.15);

        if (mIsFrontFacing) {
            mMouth1.setBounds(left, top, right, bottom);
        } else {
            mMouth1.setBounds(right, top, left, bottom);
        }
        mMouth1.draw(canvas);
    }

    private void drawMouthGif2(Canvas canvas,
                              PointF noseBasePosition,
                              PointF mouthLeftPosition, PointF mouthRightPosition,PointF mouthBottomPosition) {
        int left = (int) (mouthLeftPosition.x);
        int top = (int) (noseBasePosition.y);
        int right = (int)( mouthRightPosition.x);
        int bottom = (int) (Math.min(mouthLeftPosition.y, mouthRightPosition.y)*1.15);

        if (mIsFrontFacing) {
            mMouth2.setBounds(left, top, right, bottom);
        } else {
            mMouth2.setBounds(right, top, left, bottom);
        }
        mMouth2.draw(canvas);
    }

    private void drawMouthGif3(Canvas canvas,
                               PointF noseBasePosition,
                               PointF mouthLeftPosition, PointF mouthRightPosition,PointF mouthBottomPosition) {
        int left = (int) (mouthLeftPosition.x);
        int top = (int) (noseBasePosition.y);
        int right = (int)( mouthRightPosition.x);
        int bottom = (int) (Math.min(mouthLeftPosition.y, mouthRightPosition.y)*1.15);

        if (mIsFrontFacing) {
            mMouth3.setBounds(left, top, right, bottom);
        } else {
            mMouth3.setBounds(right, top, left, bottom);
        }
        mMouth3.draw(canvas);
    }

    //-------------------------------------------------------------------------------------------------------------------

    private  void drawGifLeftEyeBrown1 (Canvas canvas,float faceWidth,float faceHeight ,PointF leftEyePosition
            ,PointF rightCheekPosition ,PointF irisPosition ,float irisRadius )
    {
        final float HALF_FACE_RATIO = (float) (1 / 4.0);
        float halfFace = faceWidth * HALF_FACE_RATIO;
        final float FACE_HEIGHT_RATIO = (float)(1.0 / 6.0);
        //ตั้งตัวแปรมารับค่าแล้วคำนวณหาตำแหน่งที่ต้องการ
        float heightFace = faceHeight * FACE_HEIGHT_RATIO;


        int left = (int) ((leftEyePosition.x - (irisRadius*2)));
        int top  = (int) (leftEyePosition.y - (heightFace*1.3));
        int right= (int) (leftEyePosition.x + irisRadius*1.25);
        int bottom=(int) (leftEyePosition.y + (heightFace*0.35));


        mLeftEyeBrown1.setBounds(left,top,right,bottom);
        mLeftEyeBrown1.draw(canvas);

    }


    private  void drawGifLeftEyeBrown2 (Canvas canvas,float faceWidth,float faceHeight ,PointF leftEyePosition
            ,float eyeRadius ,PointF irisPosition ,float irisRadius )
    {
        final float HALF_FACE_RATIO = (float) (1 / 4.0);
        float halfFace = faceWidth * HALF_FACE_RATIO;
        final float FACE_HEIGHT_RATIO = (float)(1.0 / 6.0);
        //ตั้งตัวแปรมารับค่าแล้วคำนวณหาตำแหน่งที่ต้องการ
        float heightFace = faceHeight * FACE_HEIGHT_RATIO;

        int left = (int) ((leftEyePosition.x - (irisRadius*2)));
        int top  = (int) (leftEyePosition.y - (heightFace*1.3));
        int right= (int) (leftEyePosition.x + irisRadius*1.25);
        int bottom=(int) (leftEyePosition.y + (heightFace*0.35));


        mLeftEyeBrown2.setBounds(left,top,right,bottom);
        mLeftEyeBrown2.draw(canvas);

    }


    private  void drawGifLeftEyeBrown3 (Canvas canvas,float faceWidth,float faceHeight ,PointF leftEyePosition
            ,float eyeRadius ,PointF irisPosition ,float irisRadius )
    {
        final float HALF_FACE_RATIO = (float) (1 / 4.0);
        float halfFace = faceWidth * HALF_FACE_RATIO;
        final float FACE_HEIGHT_RATIO = (float)(1.0 / 6.0);
        //ตั้งตัวแปรมารับค่าแล้วคำนวณหาตำแหน่งที่ต้องการ
        float heightFace = faceHeight * FACE_HEIGHT_RATIO;

        int left = (int) (leftEyePosition.x - irisRadius);
        int top  = (int) (leftEyePosition.y - (heightFace*1.3));
        int right= (int) (leftEyePosition.x + irisRadius*2);
        int bottom=(int) (leftEyePosition.y + (heightFace*0.35));


        mLeftEyeBrown3.setBounds(left,top,right,bottom);
        mLeftEyeBrown3.draw(canvas);

    }

    //--------------------------------------------------------------------------------------------------------------------

    private  void drawGifRightEyeBrown1 (Canvas canvas,float faceWidth,float faceHeight ,PointF rightEyePosition
            ,float eyeRadius ,PointF irisPosition ,float irisRadius )
    {
        final float HALF_FACE_RATIO = (float) (1 / 4.0);
        float halfFace = faceWidth * HALF_FACE_RATIO;
        final float FACE_HEIGHT_RATIO = (float)(1.0 / 6.0);
        //ตั้งตัวแปรมารับค่าแล้วคำนวณหาตำแหน่งที่ต้องการ
        float heightFace = faceHeight * FACE_HEIGHT_RATIO;


        int left = (int) (rightEyePosition.x - irisRadius);
        int top  = (int) (rightEyePosition.y - (heightFace*1.3));
        int right= (int) (rightEyePosition.x + irisRadius*2);
        int bottom=(int) (rightEyePosition.y + (heightFace*0.35));

        Log.d("CHECK EYE","   "+top);

        mRightEyeBrown1.setBounds(left,top,right,bottom);
        mRightEyeBrown1.draw(canvas);
    }

    private  void drawGifRightEyeBrown2 (Canvas canvas,float faceWidth,float faceHeight ,PointF rightEyePosition
            ,float eyeRadius ,PointF irisPosition ,float irisRadius )
    {
        final float HALF_FACE_RATIO = (float) (1 / 4.0);
        float halfFace = faceWidth * HALF_FACE_RATIO;
        final float FACE_HEIGHT_RATIO = (float)(1.0 / 6.0);
        //ตั้งตัวแปรมารับค่าแล้วคำนวณหาตำแหน่งที่ต้องการ
        float heightFace = faceHeight * FACE_HEIGHT_RATIO;

        int left = (int) (rightEyePosition.x - irisRadius);
        int top  = (int) (rightEyePosition.y - (heightFace*1.3));
        int right= (int) (rightEyePosition.x + irisRadius*2);
        int bottom=(int) (rightEyePosition.y + (heightFace*0.35));

        mRightEyeBrown2.setBounds(left,top,right,bottom);
        mRightEyeBrown2.draw(canvas);
    }

    private  void drawGifRightEyeBrown3 (Canvas canvas,float faceWidth,float faceHeight ,PointF rightEyePosition
            ,float eyeRadius ,PointF irisPosition ,float irisRadius )
    {
        final float HALF_FACE_RATIO = (float) (1 / 4.0);
        float halfFace = faceWidth * HALF_FACE_RATIO;
        final float FACE_HEIGHT_RATIO = (float)(1.0 / 6.0);
        //ตั้งตัวแปรมารับค่าแล้วคำนวณหาตำแหน่งที่ต้องการ
        float heightFace = faceHeight * FACE_HEIGHT_RATIO;

        int left = (int) (rightEyePosition.x - irisRadius);
        int top  = (int) (rightEyePosition.y - (heightFace*1.3));
        int right= (int) (rightEyePosition.x + irisRadius*2);
        int bottom=(int) (rightEyePosition.y + (heightFace*0.35));

        mRightEyeBrown2.setBounds(left,top,right,bottom);
        mRightEyeBrown2.draw(canvas);
    }


    //--------------------------------------------------------------------------------------------------------------------------

    private void drawGifNose1 (Canvas canvas,
                          PointF noseBasePosition,
                          PointF leftEyePosition, PointF rightEyePosition,
                          float faceWidth) {
        final float NOSE_FACE_WIDTH_RATIO = (float) (1 / 5.0);
        float noseWidth = faceWidth * NOSE_FACE_WIDTH_RATIO;
        int left = (int) (noseBasePosition.x - (noseWidth / 2));
        int right = (int) (noseBasePosition.x + (noseWidth / 2));
        int top = (int) ((leftEyePosition.y + rightEyePosition.y) /2.15);
        int bottom = (int) (noseBasePosition.y);

        mNose1.setBounds(left, top, right, bottom);
        mNose1.draw(canvas);
    }

    private void drawGifNose2 (Canvas canvas,
                               PointF noseBasePosition,
                               PointF leftEyePosition, PointF rightEyePosition,
                               float faceWidth) {
        final float NOSE_FACE_WIDTH_RATIO = (float) (1 / 5.0);
        float noseWidth = faceWidth * NOSE_FACE_WIDTH_RATIO;
        int left = (int) (noseBasePosition.x - (noseWidth / 2));
        int right = (int) (noseBasePosition.x + (noseWidth / 2));
        int top = (int) ((leftEyePosition.y + rightEyePosition.y) /2.15);
        int bottom = (int) noseBasePosition.y;

        mNose2.setBounds(left, top, right, bottom);
        mNose2.draw(canvas);
    }
//---------------------------------------------------------------------------------------------------------------------------------




}