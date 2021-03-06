package com.restoflife.itemdecoration;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.restoflife.itemdecoration.lib.UniversalItemDecoration;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by 余俊魁 on 2017/3/20.
 * Version 1
 * Function
 * 纵向
 */

public class VerticalDecorationActivity extends AppCompatActivity {

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
            public Decoration getItemOffsets(int position) {

                //直接不理position  设置下面分割线 2个px  颜色BLACK
                ColorDecoration decoration = new ColorDecoration();
                decoration.bottom = 2;
                decoration.decorationColor = position % 2 == 0 ? Color.GREEN : Color.BLUE;
                return decoration;
            }
        });

        recyclerView.setAdapter(new MyAdapter(listData));
        setContentView(recyclerView);

    }

}
