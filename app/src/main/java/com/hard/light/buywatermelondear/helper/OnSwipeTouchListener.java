package com.hard.light.buywatermelondear.helper;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;


public class OnSwipeTouchListener implements OnTouchListener {

        private final GestureDetector gestureDetector;
        private ScaleGestureDetector mScaleDetector;
        private float mScaleFactor = 1.f;

        public OnSwipeTouchListener(Context context) {
            gestureDetector = new GestureDetector(context, new GestureListener());
            mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        }

        public void onSwipeLeft() {
        }

        public void onSwipeRight() {
        }


        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // TODO Auto-generated method stub

            ImageView view = (ImageView) v;
//            v.setScaleX(0.5f);
//            v.setScaleY(0.5f);
            v.setScaleX(mScaleFactor);
            v.setScaleY(mScaleFactor);

            // Handle touch events here...
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:


                    break;
                case MotionEvent.ACTION_POINTER_DOWN:


                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:

                    break;
                case MotionEvent.ACTION_MOVE:

                    break;
            }

            gestureDetector.onTouchEvent(event);
            mScaleDetector.onTouchEvent(event);
            return true;
        }

        private final class GestureListener extends SimpleOnGestureListener {

            private static final int SWIPE_DISTANCE_THRESHOLD = 40;
            private static final int SWIPE_VELOCITY_THRESHOLD = 70;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float distanceX = e2.getX() - e1.getX();
                float distanceY = e2.getY() - e1.getY();
                Log.d("TouchEvent", distanceX + ", " + distanceY);
                if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (distanceX > 0)
                        onSwipeRight();
                    else
                        onSwipeLeft();
                    return true;
                }
                return false;
            }
        }

        private class ScaleListener
                extends ScaleGestureDetector.SimpleOnScaleGestureListener {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                mScaleFactor *= detector.getScaleFactor();

                // Don't let the object get too small or too large.
                mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));


                return true;
            }
        }

}
