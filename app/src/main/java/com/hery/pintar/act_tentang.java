package com.hery.pintar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class act_tentang extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        Intent in = new Intent(getApplicationContext(), act_main.class);
        startActivity(in);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tentang);
    }
}
