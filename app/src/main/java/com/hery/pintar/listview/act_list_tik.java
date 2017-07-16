package com.hery.pintar.listview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hery.pintar.R;
import com.hery.pintar.act_detail;
import com.hery.pintar.entity.Materi;

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


public class act_list_tik extends Fragment {
    private static List<Materi> LIST_MATERI;
    private Adapter adapter;
    private Activity activity;
    private String FILE_NAME;

    public act_list_tik() {
    }

    @SuppressLint("ValidFragment")
    public act_list_tik(String filename) {
        this.FILE_NAME = filename;
        this.LIST_MATERI = new ArrayList<Materi>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_materi, container, false);

        LIST_MATERI = new ArrayList<Materi>();
        readXml();
        adapter = new Adapter(getContext(), R.layout.row_materi, LIST_MATERI);
        final ListView listview = (ListView) view.findViewById(R.id.listMateri);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                Intent in = new Intent(getActivity(), act_detail.class);

                bundle.putString("judul", LIST_MATERI.get(i).getJudul());
                bundle.putString("isi", LIST_MATERI.get(i).getCaption());
                bundle.putString("image", LIST_MATERI.get(i).getGambar());

                in.putExtras(bundle);
                getActivity().startActivity(in);
                getActivity().finish();
            }
        });

        return  view;

    }

    public class Adapter extends ArrayAdapter<Materi> {

        public Adapter(Context context, int resource, List<Materi> objects) {
            super(context, resource, objects);
        }

        private class viewHolder {
            TextView txtJudul, txtCaption;
            ImageView imageView;
        }

        public View getView (int position, View convertView, ViewGroup parent){
            View view = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.row_materi, null);

            ImageView imageView = (ImageView) view.findViewById(R.id.image_item);
            TextView txtJudul = (TextView) view.findViewById(R.id.txtJudul);
            TextView txtCaption = (TextView) view.findViewById(R.id.txtcaption);

            Materi materi = LIST_MATERI.get(position);
            txtJudul.setText(materi.getJudul());
            txtCaption.setText(materi.getCaption());

            Resources res = getResources();
            String draw = LIST_MATERI.get(position).getGambar();
            int resID = res.getIdentifier(draw, "drawable",getContext().getPackageName());
            int defaultresID = res.getIdentifier("image_broken", "drawable",getContext().getPackageName());

            try{
                imageView.setImageResource(resID);
            }catch (Throwable e){
                imageView.setImageResource(defaultresID);
            }


            return view;
        }
    }

    private void readXml(){
        try {
            InputStream in = getActivity().getAssets().open(FILE_NAME+".xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(in);

            Element element = doc.getDocumentElement();
            element.normalize();

            NodeList nodeList = doc.getElementsByTagName("materi");
            for (int i = 0; i<nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element subElement = (Element) node;
                    Materi obj = new Materi();
                    obj.setIndex(Integer.parseInt(getValue("index",subElement)));
                    obj.setJudul(getValue("judul",subElement));
                    obj.setCaption(getValue("isi",subElement));
                    obj.setGambar(getValue("image",subElement));

                    /*
                    Tambah kondisi disini untuk memisahkan kategorinya nanti
                    sementara untuk testing dulu
                     */
                    LIST_MATERI.add(obj);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    private static String getValue(String tag, Element element){
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
