package com.creator.dataparsing.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creator.dataparsing.Ba;
import com.creator.dataparsing.R;
import com.creator.dataparsing.adapter.ViewPagerAdapter;

import java.util.ArrayList;

/**
 * Created by user1 on 7/4/2016.
 */
public class Hfragment extends Fragment implements ViewPager.OnPageChangeListener {
    private View view;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter adapter;
    private final String TABS[] = {"Daily Menu", "Main Course", "Dessert's Menu"};


    DailyMenu df = new DailyMenu();
    DesertsMenu dmf = new DesertsMenu();
    MainMenu mmf = new MainMenu();


    private ArrayList<Fragment> list;

    public Hfragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hfag, container, false);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPager();
    }



    private void initPager() {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(this);

        list = new ArrayList<>();
        list.add(df);
        list.add(mmf);
        list.add(dmf);

        adapter = new ViewPagerAdapter(getChildFragmentManager(), list, TABS);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }


    public Fragment getFragment(int pos) {
        return adapter.getItem(pos);
    }

    public void setCurrentFragment(int position) {
        viewPager.setCurrentItem(position);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        try {
            Fragment fragment = list.get(position);
            if (fragment instanceof Ba) {
                Ba baseFragment = (Ba) fragment;
                baseFragment.onFragmentVisible();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
