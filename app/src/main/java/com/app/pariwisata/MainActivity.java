package com.app.pariwisata;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentAdapter fm = new FragmentAdapter( getSupportFragmentManager() );
        fm.addData("Detail",new DetailFragment());
     //   fm.addData("Timeline",new TimelineFragment());
        fm.addData("Map",new MapFragment());

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(fm);

        tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class FragmentAdapter extends FragmentStatePagerAdapter{

        ArrayList<FragmentComponent> listOfComponent;

        public FragmentAdapter(FragmentManager fm){
            super(fm);
            listOfComponent = new ArrayList<>();
        }

        public void addData(String title,Fragment fragment){
            listOfComponent.add(new FragmentComponent(title,fragment));
        }

        @Override
        public Fragment getItem(int position) {
            return listOfComponent.get(position).getFragment();
        }

        @Override
        public int getCount() {
            return listOfComponent.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return listOfComponent.get(position).getTitle();
        }
    }

    private class FragmentComponent{
        private String title;
        private Fragment fragment;

        public FragmentComponent(String title,Fragment fragment){
            this.title = title;
            this.fragment = fragment;
        }

        public Fragment getFragment() {
            return fragment;
        }

        public String getTitle() {
            return title;
        }
    }
}
