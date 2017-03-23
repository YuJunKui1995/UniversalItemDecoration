package com.restoflife.itemdecoration;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.restoflife.itemdecoration.lib.UniversalItemDecoration;

import java.util.ArrayList;

/**
 * Created by 余俊魁 on 2017/3/23.
 * Version 1
 * Function
 * Tips  自定义示例
 */

public class CustomDecorationActivity extends AppCompatActivity {


    private ArrayList<String> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (int i = 0; i < 100; i++) {
            listData.add("item " + i);
        }

        RecyclerView recyclerView = new RecyclerView(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new UniversalItemDecoration() {
            @Override
            public Decoration getItemOffsets(final int position) {


                Decoration decoration = new Decoration() {
                    @Override
                    public void drawItemOffsets(Canvas c, int leftZ, int topZ, int rightZ, int bottomZ) {

                        //拿着Canvas 随便玩啦(不要在这搞复杂的东西，因为不会缓存)  leftZ是线在屏幕中的实际位置  其他同上
                        if (position == 3) {
                            Paint paint = new Paint();
                            paint.setAntiAlias(true);
                            paint.setTextSize(50);
                            paint.setColor(Color.parseColor("#000000"));
                            c.drawText("啦啦啦啦 我是自定义的分割线标题", 20, bottomZ, paint);
                        }
                    }
                };

                decoration.top = 100;

                return decoration;
            }
        });

        recyclerView.setAdapter(new MyAdapter(listData, R.layout.item_horizontal));
        setContentView(recyclerView);

    }


}
