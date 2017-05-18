package com.example.navigationdrawer;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_container, new Fragment1());
        fragmentTransaction.commit();

        mDrawerList = (ListView)findViewById(R.id.navList);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        addDrawerItems();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "position " + position, Toast.LENGTH_SHORT).show();
                fragmentTransaction = fragmentManager.beginTransaction();
                switch (position){
                    case 0:
                        fragmentTransaction.replace(R.id.main_container, new Fragment1());
                        break;
                    case 1:
                        fragmentTransaction.replace(R.id.main_container, new Fragment2());
                        break;
                    case 2:
                        fragmentTransaction.replace(R.id.main_container, new Fragment3());
                        break;
                }
                fragmentTransaction.commit();
//                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
//                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });
    }

    private void addDrawerItems() {
        String[] osArray = { "Android", "iOS", "Windows"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
    }
}
