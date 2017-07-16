package com.hery.pintar;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import android.widget.ListView;

import com.hery.pintar.listview.act_list_hardware;
import com.hery.pintar.listview.act_list_jaringan;
import com.hery.pintar.listview.act_list_software;
import com.hery.pintar.listview.act_list_tik;

public class act_materi extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private static ListView listmateri;

    public void act_materi(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_materi);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        //mViewPager.setCurrentItem(0);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //mViewPager.removeAllViews();

            switch (position){
                case 0:
                    act_list_tik tik = new act_list_tik("xml_materi_tik");
                    return  tik;
                case 1:
                    act_list_hardware hardware = new act_list_hardware("xml_materi_hardware");
                    return  hardware;
                case 2:
                    act_list_software software = new act_list_software("xml_materi_software");
                    return  software;
                case 3:
                    act_list_jaringan jaringan = new act_list_jaringan("xml_materi_jaringan");
                    return  jaringan;
            }
            return  null;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "TIK";
                case 1:
                    return "HARDWARE";
                case 2:
                    return "SOFTWARE";
                case 3:
                    return "JARINGAN";
            }
            return null;
        }
    }

}
