package com.hery.pintar;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hery.pintar.entity.Kuis;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class act_kuis_detail extends AppCompatActivity {
    private Button btnA, btnB, btnC, btnD;
    private TextView txtSoal;
    private ImageView imgKuis;
    private List<Kuis> listKuis = new ArrayList<>();
    private List<Kuis> tempKuis = new ArrayList<>();
    private int index = 0;
    private int cur_index = 0;

    private int TRUE  = 0;
    private int FALSE = 0;
    private double NILAI = 0;
    private List<Integer> RANDOM_INDEX = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_kuis_detail);

        btnA = (Button)findViewById(R.id.optA);
        btnB = (Button)findViewById(R.id.optB);
        btnC = (Button)findViewById(R.id.optC);
        btnD = (Button)findViewById(R.id.optD);
        txtSoal = (TextView)findViewById(R.id.txtSoal);
        imgKuis = (ImageView)findViewById(R.id.image_kuis_detail);

        readXml();

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionClickAction(btnA);
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionClickAction(btnB);
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionClickAction(btnC);
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionClickAction(btnD);
            }
        });
    }

    private void readXml(){
        try {
            InputStream in = getAssets().open("xml_kuis.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(in);

            Element element = doc.getDocumentElement();
            element.normalize();

            NodeList nodeList = doc.getElementsByTagName("kuis");
            for (int i = 0; i<nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element subElement = (Element) node;
                    Kuis obj = new Kuis();
                    obj.setIndex(Integer.parseInt(getValue("index",subElement)));
                    obj.setSoal(getValue("question",subElement));
                    obj.setJawaban(getValue("answer",subElement));
                    obj.setOptionA(getValue("option1",subElement));
                    obj.setOptionB(getValue("option2",subElement));
                    obj.setOptionC(getValue("option3",subElement));
                    obj.setOptionD(getValue("option4",subElement));
                    obj.setImgsource(getValue("image",subElement));

                    listKuis.add(obj);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        setKuis();
    }

    private void optionClickAction(Button button){
        btnA.setEnabled(false);
        btnB.setEnabled(false);
        btnC.setEnabled(false);
        btnD.setEnabled(false);

        answer(button);
        index++;

        Handler handler = new Handler();
        handler.postDelayed(mMyRunnable, 1000);


    }

    protected Runnable mMyRunnable = new Runnable() {
        @Override
        public void run() {
            resetButton();
            setKuis();
        }
    };

    private void answer(Button button){
        if (button.getText().equals(listKuis.get(cur_index).getJawaban())) {
            TRUE++;
            button.setBackgroundColor(Color.GREEN);
        }else{
            FALSE++;
            button.setBackgroundColor(Color.RED);
        }
    }

    private static String getValue(String tag, Element element){
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

    private void setKuis(){

        if (index < 10) {
            int i = (int) (Math.random()*listKuis.size());

            while (matchCheck(listKuis.get(i))) {
                i = (int) (Math.random()*listKuis.size());
            }

            txtSoal.setText(listKuis.get(i).getSoal());
            btnA.setText(listKuis.get(i).getOptionA());
            btnB.setText(listKuis.get(i).getOptionB());
            btnC.setText(listKuis.get(i).getOptionC());
            btnD.setText(listKuis.get(i).getOptionD());

            Resources res = getResources();
            String draw = listKuis.get(i).getImgsource();
            int resID = res.getIdentifier(draw, "drawable", getPackageName());
            imgKuis.setImageResource(resID);

            tempKuis.add(listKuis.get(i));
            cur_index = i;
        }
        else{
            calc();

            Intent in = new Intent(getApplicationContext(), act_kuis_review.class);
            Bundle bundle = new Bundle();

            bundle.putString("nilai",String.valueOf(NILAI));
            in.putExtras(bundle);
            startActivity(in);

            finish();
        }
    }

    private void resetButton(){
        btnA.setBackgroundColor(R.color.greydark);
        btnB.setBackgroundColor(R.color.greydark);
        btnC.setBackgroundColor(R.color.greydark);
        btnD.setBackgroundColor(R.color.greydark);

        btnA.setEnabled(true);
        btnB.setEnabled(true);
        btnC.setEnabled(true);
        btnD.setEnabled(true);
    }
    private void calc() {
        double benar = Double.parseDouble("" + TRUE);
        double salah = Double.parseDouble("" + FALSE);

        NILAI = Math.round(benar * 100 / (benar + salah));
    }

    private boolean matchCheck(Kuis obj){
        for (int i=0; i<tempKuis.size(); i++) {
            if (obj == tempKuis.get(i)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void onBackPressed() {
        Intent in = new Intent(getApplicationContext(), act_main.class);
        startActivity(in);
        finish();
    }
}
