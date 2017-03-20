package com.restoflife.itemdecoration;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.restoflife.itemdecoration.lib.UniversalItemDecoration;

import java.util.ArrayList;

/**
 * Created by 余俊魁 on 2017/3/20.
 * Version 1
 * Function
 * 万能分割线  翱翔
 */

public class UniversalDecorationActivity extends AppCompatActivity {

    private ArrayList<String> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (int i = 0; i < 100; i++) {
            listData.add("item " + i);
        }

        RecyclerView recyclerView = new RecyclerView(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 6);

        //总的6列  控制每个item占据的列数
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                //设定position2-6为  两个一行的
                if (1 < position && position < 7) {
                    return 3;//3代表 当前(position)item占据3列  总6列  所以一行可以放2个
                } else if (9 < position && position < 19) {//设定position10-18为  三个一行的
                    return 2;
                } else {
                    return 6;
                }
            }
        });

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new UniversalItemDecoration() {
            @Override
            public Decoration getItemOffsets(int position) {

                Decoration decoration = new Decoration();

                //这里应该是你的判断逻辑  判断当前position 需要上下左右的分割线到底是多少 以及颜色

                //这是一个示例代码  这里要根据你自己的逻辑判断当前item需要上下左右的分割线到底是多少 以及颜色
                if (1 < position && position < 7) {

                    //本组占据不同的列数的 中的position
                    int index = position - 2;

                    if (isLeft(2, index)) {//是左边
                        decoration.left = 10;
                        decoration.right = 10;
                    } else if (isRight(2, index)) { //是右边
                        decoration.right = 10;
                    } else {
                        //中间
                        decoration.right = 10;
                    }

                    //前两个是第一行额外加上上边据
                    if (index < 2) {
                        decoration.top = 10;
                        decoration.bottom = 10;
                    } else {
                        decoration.bottom = 10;
                    }

                    decoration.decorationColor = Color.BLUE;

                } else if (9 < position && position < 19) {

                    int index = position - 10;

                    if (isLeft(3, index)) {
                        decoration.left = 10;
                        decoration.right = 10;
                    } else if (isRight(3, index)) {
                        decoration.right = 10;
                    } else {
                        decoration.right = 10;
                    }

                    if (index < 3) {
                        decoration.top = 10;
                        decoration.bottom = 10;
                    } else {
                        decoration.bottom = 10;
                    }

                } else {

                    //普通一个item占据6列  一行一个
                    decoration.bottom = 2;
                    decoration.decorationColor = Color.GREEN;
                }

                return decoration;
            }
        });

        recyclerView.setAdapter(new MyAdapter(listData));
        setContentView(recyclerView);

    }

//    /**
//     * 是否是边上
//     *
//     * @param index  当前index
//     * @param rowNum 当前一行几列
//     * @return
//     */
//    public static boolean isEdge(int rowNum, int index) {
//
//        return isLeft(rowNum, index) || isRight(rowNum, index);
//    }

    public static boolean isLeft(int rowNum, int index) {

        return index % rowNum == 0;
    }

    public static boolean isRight(int rowNum, int index) {

        return index % rowNum == rowNum - 1;
    }

}
