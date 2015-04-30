package nerdlab.main;

import android.app.ListFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.TreeMap;

public class MyPagerAdapter extends FragmentPagerAdapter {

        TreeMap<Integer, Fragment> fragments=new TreeMap<>();

        public TreeMap<Integer, Fragment> getFragments() {
            return fragments;
        }

        private void init(){
            fragments.put(0, new AtyListFragment());
            fragments.put(1, new Fragment1());
            fragments.put(2, new Fragment2());
            fragments.put(3, new Fragment1());
            fragments.put(4, new Fragment2());
            fragments.put(5, new Fragment1());
            fragments.put(6, new Fragment2());
        }

        private final String[] TITLES = { "One" ,"Two","Three","Four","Five","Six"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            init();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return fragments.get(1);
                case 1:
                    return fragments.get(2);
                case 2:
                    return fragments.get(3);
                case 3:
                    return fragments.get(4);
                case 4:
                    return fragments.get(5);
                case 5:
                    return fragments.get(6);
                default:
                    return null;
            }
        }
    }