package com.example.administrator.its_gs_mvp.ui.listener;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * 照片详情的手势处理
 *
 * @Created by xww on 2018/4/23 0023.
 */

public class OnTouchListener implements View.OnTouchListener {
    private ImageView imageView;
    /**
     * 标记手势模式
     **/
    private int Mode = 0;//初始状态
    private final int MODE_DRAG = 1; //拖拽图片模式
    private final int MODE_ZOOM = 2; //缩放图片模式

    private PointF startPoint = new PointF();
    private Matrix matrix = new Matrix();
    private Matrix currentMatrix = new Matrix();

    private float startDis;
    private PointF midPoint;

    public OnTouchListener(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            /** 手指压下屏幕 **/
            case MotionEvent.ACTION_DOWN:
                Mode = MODE_DRAG;
                /** 记录imageview当前的移动位置 **/
                currentMatrix.set(imageView.getMatrix());
                startPoint.set(event.getX(), event.getY());
                break;
            /** 手指在屏幕上移动，此事件会不断触发 **/
            case MotionEvent.ACTION_MOVE:
                if (Mode == MODE_DRAG) {    /** 拖拽图片 **/
                    float dx = event.getX() - startPoint.x;//获得X抽的移动距离
                    float dy = event.getY() - startPoint.y;//获得Y周的移动距离
                    matrix.set(currentMatrix);
                    matrix.postTranslate(dx, dy);
                } else if (Mode == MODE_ZOOM) { /** 缩放图片 **/
                    float dx = event.getX(1) - event.getX(0);
                    float dy = event.getY(1) - event.getY(0);
                    float endDis = (float) Math.sqrt(dx * dx + dy * dy);
                    if (endDis > 10f) {
                        float scale = endDis / startDis;
                        matrix.set(currentMatrix);
                        matrix.postScale(scale, scale, midPoint.x, midPoint.y);
                    }
                }
                break;
            /** 手指离开屏幕 **/
            case MotionEvent.ACTION_UP:
                break;
            /** 手指压下屏幕 **/
            case MotionEvent.ACTION_POINTER_UP:
                Mode = 0;
                break;
            /** 手指压下屏幕 **/
            case MotionEvent.ACTION_POINTER_DOWN:
                Mode = MODE_ZOOM;
                float dx = event.getX(1) - event.getX(0);
                float dy = event.getY(1) - event.getY(0);
                startDis = (float) Math.sqrt(dx * dx + dy * dy);
                if (startDis > 10f) {
                    float midX = (event.getX(1) + event.getX(0)) / 2;
                    float midY = (event.getY(1) + event.getY(0)) / 2;
                    midPoint = new PointF(midX,midY);
                    currentMatrix.set(imageView.getMatrix());/** 记录当前ImageView的缩放倍数 **/
                }
                break;
        }
        imageView.setImageMatrix(matrix);
        return true;
    }
}
