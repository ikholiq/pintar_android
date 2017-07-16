package com.hery.pintar;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class act_detail extends AppCompatActivity {

    private TextView txtJudul, txtIsi;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_materi_detail);

        Bundle bundle = getIntent().getExtras();

        txtJudul = (TextView)findViewById(R.id.detail_judul);
        txtIsi = (TextView)findViewById(R.id.detail_isi);
        image = (ImageView)findViewById(R.id.detail_image);

        txtJudul.setText(bundle.getString("judul"));
        txtIsi.setText(bundle.getString("isi"));

        Resources res = getResources();
        int resID = res.getIdentifier(bundle.getString("image"), "drawable",getApplicationContext().getPackageName());
        int defaultresID = res.getIdentifier("image_broken", "drawable",getApplicationContext().getPackageName());
        try{
            image.setImageResource(resID);
        }catch (Throwable e){
            image.setImageResource(defaultresID);
        }
    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent(getApplicationContext(), act_materi.class);
        startActivity(in);
        finish();
    }
}
