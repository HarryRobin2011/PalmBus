package com.palm.palmbus.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.palm.palmbus.R;

/**
 * Created by Robin on 2016/11/23.
 */

public class WelComeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wel_come_layout);
    }
}
