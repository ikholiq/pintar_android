package com.hery.pintar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class act_main extends AppCompatActivity {
    private Button btnMateri, btnKuis, btnTentang, btnKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_main);

        initComponent();
    }


    private void initComponent(){
        btnMateri = (Button)findViewById(R.id.btnMateri);
        btnKuis = (Button)findViewById(R.id.btnKuis);
        btnTentang = (Button)findViewById(R.id.btnTentang);
        btnKeluar = (Button)findViewById(R.id.btnKeluar);

        btnMateri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnMateri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), act_materi.class);
                startActivity(in);
                //finish();
            }
        });

        btnKuis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), act_kuis.class);
                startActivity(in);
                finish();
            }
        });

        btnTentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), act_tentang.class);
                startActivity(in);
                finish();
            }
        });
        btnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitApplication();
            }
        });
    }

    @Override
    public void onBackPressed() {
        exitApplication();
    }

    private void exitApplication(){
        AlertDialog.Builder builder = new AlertDialog.Builder(act_main.this);
        builder.setMessage("Yakin Ingin Keluar Dari Aplikasi ?")
                .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(1);
                    }
                }).setNegativeButton("TIDAK", null);
        AlertDialog alert = builder.create();
        alert.show();
    }
}
