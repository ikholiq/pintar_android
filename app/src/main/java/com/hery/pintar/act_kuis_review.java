package com.hery.pintar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class act_kuis_review extends AppCompatActivity {

    private Button btnrepeat;
    private TextView txtNilai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_kuis_review);

        Bundle bundle = getIntent().getExtras();

        btnrepeat = (Button)findViewById(R.id.btnUlangKuis);
        txtNilai = (TextView)findViewById(R.id.txtNilai);
        txtNilai.setText(bundle.getString("nilai"));

        btnrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), act_kuis.class);
                startActivity(in);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent(getApplicationContext(), act_main.class);
        startActivity(in);
        finish();
    }
}
