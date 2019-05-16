package com.autchariya.kiklik.makeuptutorial4;

import android.content.Context;
import android.graphics.PointF;
import android.widget.Button;
import android.widget.ImageView;

import com.autchariya.kiklik.makeuptutorial4.ar.camera.CameraSourcePreview;
import com.autchariya.kiklik.makeuptutorial4.ar.camera.GraphicOverlay;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.Landmark;

import java.util.HashMap;
import java.util.Map;

import io.github.yavski.fabspeeddial.FabSpeedDial;


class FaceTracker extends Tracker<Face> {

    private static final String TAG = "FaceTracker";

    private GraphicOverlay mOverlay;
    private Context mContext;
    private boolean mIsFrontFacing;
    private FaceGraphic mFaceGraphic;
    private FaceData mFaceData;
    private ImageView mImageView;
    private boolean mPreviousIsLeftEyeOpen = true;
    private boolean mPreviousIsRightEyeOpen = true;
    private FabSpeedDial mbtn_leftCheek;
    private CameraSourcePreview mPreview;

    private Map<Integer,PointF> mPreviousLandmarkPosition = new HashMap<>();

    FaceTracker(GraphicOverlay overlay, Context context, boolean isFrontFacing, ImageView imageView, FabSpeedDial btn_leftCheek){
        mOverlay = overlay;
        mContext = context;
        mIsFrontFacing = isFrontFacing;
        mFaceData = new FaceData();
        mImageView = imageView;
        mbtn_leftCheek = btn_leftCheek;



    }

    // เมธอดต่างๆ ของ Facial Landmark

    private PointF getLandmarkPosition(Face face, int landmarkID){
        for(Landmark landmark : face.getLandmarks()) {
            if (landmark.getType() == landmarkID) {
                return landmark.getPosition();
            }
        }

        PointF landmarkPosition = mPreviousLandmarkPosition.get(landmarkID);
        if(landmarkPosition == null){
            return null;
        }
        float x = face.getPosition().x + (landmarkPosition.x * face.getWidth());
        float y = face.getPosition().y + (landmarkPosition.y * face.getHeight());
        return  new PointF(x,y);
    }

    private  void updatePreviousLandmarkPositions(Face face){
        for (Landmark landmark : face.getLandmarks()){
            PointF position = landmark.getPosition();
            float xProp = (position.x - face.getPosition().x) / face.getWidth();
            float yProp = (position.y - face.getPosition().y) / face.getHeight();
            mPreviousLandmarkPosition.put(landmark.getType(),new PointF(xProp,yProp));
        }
    }

    @Override
    public void onNewItem(int id, Face face) {
        mFaceGraphic = new FaceGraphic(mOverlay,mContext,mIsFrontFacing,mImageView,mbtn_leftCheek);
    }

    @Override
    public void onUpdate(Detector.Detections<Face> detections, Face face) {
        mOverlay.add(mFaceGraphic);
        updatePreviousLandmarkPositions(face);

        //ดึงค่ามุมของศรีษะ
        mFaceData.setEulerY(face.getEulerY());
        mFaceData.setEulerZ(face.getEulerZ());

        // ดึงมิติขิงหหน้า
        mFaceData.setPosition((face.getPosition()));
        mFaceData.setWidth(face.getWidth());
        mFaceData.setHeight(face.getHeight());

        //ดึงตำแหน่งของอวัยวะบนใบหน้า
        mFaceData.setLeftEyePosition(getLandmarkPosition(face, Landmark.LEFT_EYE));
        mFaceData.setRightEyePosition(getLandmarkPosition(face, Landmark.RIGHT_EYE));
        mFaceData.setNoseBasePosition(getLandmarkPosition(face, Landmark.NOSE_BASE));
        mFaceData.setLeftEarPosition(getLandmarkPosition(face, Landmark.LEFT_EAR));
        mFaceData.setLeftEarTipPosition(getLandmarkPosition(face, Landmark.LEFT_EAR_TIP));
        mFaceData.setRightEarPosition(getLandmarkPosition(face, Landmark.RIGHT_EAR));
        mFaceData.setRightEarTipPosition(getLandmarkPosition(face, Landmark.RIGHT_EAR_TIP));
        mFaceData.setMouthLeftPosition(getLandmarkPosition(face, Landmark.LEFT_MOUTH));
        mFaceData.setMouthBottomPosition(getLandmarkPosition(face, Landmark.BOTTOM_MOUTH));
        mFaceData.setMouthRightPosition(getLandmarkPosition(face, Landmark.RIGHT_MOUTH));
        mFaceData.setRightCheekPosition(getLandmarkPosition(face,Landmark.RIGHT_CHEEK));
        mFaceData.setLeftCheekPosition(getLandmarkPosition(face,Landmark.LEFT_CHEEK));

        final float EYE_CLOSED_THRESHOLD = 0.4f;
        float leftOpenScore = face.getIsLeftEyeOpenProbability();
        if (leftOpenScore == Face.UNCOMPUTED_PROBABILITY) {
            mFaceData.setLeftEyeOpen(mPreviousIsLeftEyeOpen);
        } else {
            mFaceData.setLeftEyeOpen(leftOpenScore > EYE_CLOSED_THRESHOLD);
            mPreviousIsLeftEyeOpen = mFaceData.isLeftEyeOpen();
        }
        float rightOpenScore = face.getIsRightEyeOpenProbability();
        if (rightOpenScore == Face.UNCOMPUTED_PROBABILITY) {
            mFaceData.setRightEyeOpen(mPreviousIsRightEyeOpen);
        } else {
            mFaceData.setRightEyeOpen(rightOpenScore > EYE_CLOSED_THRESHOLD);
            mPreviousIsRightEyeOpen = mFaceData.isRightEyeOpen();
        }

        // 2
        // See if there's a smile!
        // Determine if person is smiling.
        final float SMILING_THRESHOLD = 0.8f;
        mFaceData.setSmiling(face.getIsSmilingProbability() > SMILING_THRESHOLD);

        mFaceGraphic.update(mFaceData);
    }

    @Override
    public void onMissing(Detector.Detections<Face> detections) {
        mOverlay.remove((mFaceGraphic));
    }

    @Override
    public void onDone() {
        mOverlay.remove(mFaceGraphic);
    }
}
