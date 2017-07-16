package com.hery.pintar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class act_kuis extends AppCompatActivity {

    private TextView btnMulai;

    @Override
    public void onBackPressed() {
        Intent in = new Intent(getApplicationContext(), act_main.class);
        startActivity(in);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_kuis);

        btnMulai = (TextView)findViewById(R.id.btnKuisStart);

        btnMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), act_kuis_detail.class);
                startActivity(in);
                finish();
            }
        });
    }
}
