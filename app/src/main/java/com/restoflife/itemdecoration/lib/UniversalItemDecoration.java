package com.restoflife.itemdecoration.lib;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yu on 2017/3/19.
 * 万能分割
 * 使用此类  item的tag会被占用  如果外部使用会造成乱
 * 外部可以使用 item.setTag(key,obj)
 */

public abstract class UniversalItemDecoration extends RecyclerView.ItemDecoration {

    private Map<Integer, Decoration> decorations = new HashMap<>();

    private static final String TAG = "UniversalItemDecoration";

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {

            final View child = parent.getChildAt(i);
            //获取在getItemOffsets存起来的position
            int position = string2Int(child.getTag().toString(), 0);
            Decoration decoration = decorations.get(position);

            if (decoration == null) continue;
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();

            //view的上下左右包括 Margin
            int bottom = child.getBottom() + layoutParams.bottomMargin;
            int left = child.getLeft() - layoutParams.leftMargin;
            int right = child.getRight() + layoutParams.rightMargin;
            int top = child.getTop() - layoutParams.topMargin;

            //下面的
            if (decoration.bottom != 0)
                decoration.drawItemOffsets(c, left - decoration.left, bottom, right + decoration.right, bottom + decoration.bottom);
            //上面的
            if (decoration.top != 0)
                decoration.drawItemOffsets(c, left - decoration.left, top - decoration.top, right + decoration.right, top);
            //左边的
            if (decoration.left != 0)
                decoration.drawItemOffsets(c, left - decoration.left, top, left, bottom);
            //右边的
            if (decoration.right != 0)
                decoration.drawItemOffsets(c, right, top, right + decoration.right, bottom);

        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        //获取position
        int position = parent.getChildAdapterPosition(view);
        view.setTag(position);

        //获取调用者返回的Decoration
        Decoration decoration = getItemOffsets(position);

        if (decoration != null) {
            //偏移量设置给item
            outRect.set(decoration.left, decoration.top, decoration.right, decoration.bottom);

        } else {
            //不要线
            decoration = null;
        }
        //存起来在onDraw用
        decorations.put(position, decoration);

    }


    /***
     * 需调用者返回分割线对象  上下左右 和颜色值
     * @param position
     * @return
     */
    public abstract Decoration getItemOffsets(int position);

    /**
     * 分割线
     */
    public abstract static class Decoration {

        public int left, right, top, bottom;

        /**
         * 根据偏移量设定的 当前的线在界面中的坐标
         *
         * @param leftZ
         * @param topZ
         * @param rightZ
         * @param bottomZ
         */
        public abstract void drawItemOffsets(Canvas c, int leftZ, int topZ, int rightZ, int bottomZ);

    }

    public static class ColorDecoration extends Decoration {

        private Paint mPaint;
        public int decorationColor = Color.BLACK;

        public ColorDecoration() {
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setStyle(Paint.Style.FILL);
        }

        @Override
        public void drawItemOffsets(Canvas c, int leftZ, int topZ, int rightZ, int bottomZ) {

            mPaint.setColor(decorationColor);
            c.drawRect(leftZ, topZ, rightZ, bottomZ, mPaint);
        }

    }


    public static int string2Int(String s, int defValue) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return defValue;
        }


    }



}
