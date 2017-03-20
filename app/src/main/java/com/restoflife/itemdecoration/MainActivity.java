package com.restoflife.itemdecoration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt_horizontal).setOnClickListener(this);
        findViewById(R.id.bt_vertical).setOnClickListener(this);
        findViewById(R.id.bt_universal).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bt_horizontal:
                startActivity(new Intent(this, HorizontalItemDecorationActivity.class));
                break;

            case R.id.bt_vertical:
                startActivity(new Intent(this, VerticalDecorationActivity.class));
                break;

            case R.id.bt_universal:
                startActivity(new Intent(this, UniversalDecorationActivity.class));
                break;
        }

    }

}
