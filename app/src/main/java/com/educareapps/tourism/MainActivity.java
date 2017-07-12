package com.educareapps.tourism;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;

import com.educareapps.fragment.MainFragment;
import com.educareapps.mylibrary.BaseActivity;
import com.educareapps.mylibrary.MarshMallowPermission;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MainActivity extends BaseActivity  {

    private ExpandableListView simpleExpandableListView;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private LinkedHashMap<String, RadioCategory> subjects = new LinkedHashMap<String, RadioCategory>();
    private ArrayList<RadioCategory> rdCategoryLst = new ArrayList<RadioCategory>();
    private RadioAdapter listAdapter;
    MainActivity activity;
    private static final String FRAGMENT_FLAG = "fragment_SIS_FLAG";

    private static final int RECORDER_SAMPLERATE = 8000;
    private static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;
    private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
    MarshMallowPermission marshMallowPermission;


    //Tourism
    MainFragment mainFragment;
    boolean isDrawerOpen = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        addDrawerItems();
        setupDrawer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mainFragment = new MainFragment();

        marshMallowPermission = new MarshMallowPermission(activity);

        if (!marshMallowPermission.checkPermissionForExternalStorage() || !marshMallowPermission.checkPermissionForRecord() || !marshMallowPermission.checkPermissionForCamera()) {
            marshMallowPermission.requestPermissionForExternalStorage();
            marshMallowPermission.requestPermissionForRecord();
            marshMallowPermission.requestPermissionForCamera();
        }
        int bufferSize = AudioRecord.getMinBufferSize(RECORDER_SAMPLERATE, RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING);
        System.out.println("BUFFER SIZE VALUE IS " + bufferSize);
        RadioStation radioStation = (RadioStation) listAdapter.getChild(0, 0);


        setFragment(mainFragment);

    }

    View previousSelectedItem;



    private void addDrawerItems() {

        loadData();

        //get reference of the ExpandableListView
        simpleExpandableListView = (ExpandableListView) findViewById(R.id.simpleExpandableListView);
        // create the adapter by passing your ArrayList data
        listAdapter = new RadioAdapter(MainActivity.this, rdCategoryLst);
        // attach the adapter to the expandable list view
        simpleExpandableListView.setAdapter(listAdapter);

        //expand all the Groups
        expandAll();

        // setOnChildClickListener listener for child row click
        simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //get the group header
                //RadioCategory headerInfo = rdCategoryLst.get(groupPosition);
                //get the child info

                if (previousSelectedItem != null) {
                    previousSelectedItem.setBackgroundColor(Color.parseColor("#ffeeeeee"));
                    mainFragment.loadCountryWiseTrip(childPosition);
                }

                previousSelectedItem = v;
                v.setBackgroundColor(getResources().getColor(R.color.appColor));
                if (isDrawerOpen) {
                    isDrawerOpen = true;
                    mDrawerLayout.closeDrawers();


                } else {
                    isDrawerOpen = true;
                    mDrawerLayout.closeDrawers();

                }

                return false;
            }
        });
        // setOnGroupClickListener listener for group heading click
        simpleExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                return false;
            }
        });


    }













    /*private void addDrawerItems() {

        loadData();

        //get reference of the ExpandableListView
        simpleExpandableListView = (ExpandableListView) findViewById(R.id.simpleExpandableListView);
        // create the adapter by passing your ArrayList data
        listAdapter = new RadioAdapter(MainActivity.this, rdCategoryLst);
        // attach the adapter to the expandable list view
        simpleExpandableListView.setAdapter(listAdapter);

        //expand all the Groups
        expandAll();

        // setOnChildClickListener listener for child row click
        simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //get the group header
                RadioCategory headerInfo = rdCategoryLst.get(groupPosition);
                //get the child info

                if (previousSelectedItem != null) {
                    previousSelectedItem.setBackgroundColor(Color.parseColor("#ffeeeeee"));
                }
                previousSelectedItem = v;
                v.setBackgroundColor(getResources().getColor(R.color.appColor));

                RadioStation detailInfo = headerInfo.getRadioStationList().get(childPosition);

                return false;
            }
        });
        // setOnGroupClickListener listener for group heading click
        simpleExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //get the group header
                RadioCategory headerInfo = rdCategoryLst.get(groupPosition);

                return false;
            }
        });


    }*/





    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
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
        /*if (id == R.id.action_settings) {

            return true;
        }*/

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            simpleExpandableListView.expandGroup(i);
        }
    }

    //method to collapse all groups
    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            simpleExpandableListView.collapseGroup(i);
        }
    }

    //load some initial data into out list
    private void loadData() {

        addRadioStation(getString(R.string.international_radio), "Bangladesh", "");
        addRadioStation(getString(R.string.international_radio), "India", "");
        addRadioStation(getString(R.string.international_radio), "Pakistan", "");
        addRadioStation(getString(R.string.international_radio), "USA", "");


        addRadioStation(getString(R.string.international_radio), "UK", "");
        addRadioStation(getString(R.string.international_radio), "Spain", "");
        addRadioStation(getString(R.string.international_radio), "Japan", "");
        addRadioStation(getString(R.string.international_radio), "Malayshia", "");

        addRadioStation(getString(R.string.international_radio), "Egypt", "");
        addRadioStation(getString(R.string.international_radio), "Indoneshia", "");
        addRadioStation(getString(R.string.international_radio), "Arab", "");
        addRadioStation(getString(R.string.international_radio), "Singapur", "");


       /* addRadioStation(getString(R.string.local_radio), "Radio Plus", "http://s4.voscast.com:8432");
        addRadioStation(getString(R.string.local_radio), "Radio One", "http://173.208.157.101:8020 ");
        addRadioStation(getString(R.string.local_radio), "Top Fm", "http://webcast.orange.mu:1935");
        addRadioStation(getString(R.string.local_radio), "MBC radio", "http://www.mbcradio.tv/sites/all/themes/mbcradiotv/templates/radio-mauritius.html");

        addRadioStation(getString(R.string.local_radio), "Taal FM ", "http://www.mbcradio.tv/sites/all/themes/mbcradiotv/templates/taalfm.html");
        addRadioStation(getString(R.string.local_radio), "Kool Fm", "http://www.mbcradio.tv/sites/all/themes/mbcradiotv/templates/koolfm.html");
        addRadioStation(getString(R.string.local_radio), "Music FM", "http://www.mbcradio.tv/sites/all/themes/mbcradiotv/templates/musicfm.html");
        addRadioStation(getString(R.string.local_radio), "Best fm ", "http://www.mbcradio.tv/sites/all/themes/mbcradiotv/templates/bestfm.html");
    */

    }


    //here we maintain our products in various departments
    private int addRadioStation(String rdCategory, String title, String link) {

        int groupPosition = 0;

        //check the hash map if the group already exists
        RadioCategory headerInfo = subjects.get(rdCategory);
        //add the group if doesn't exists
        if (headerInfo == null) {
            headerInfo = new RadioCategory();
            headerInfo.setName(rdCategory);
            subjects.put(rdCategory, headerInfo);
            rdCategoryLst.add(headerInfo);
        }

        //get the children for the group
        ArrayList<RadioStation> productList = headerInfo.getRadioStationList();
      /*  //size of the children list
        int listSize = productList.size();
        //add to the counter
        listSize++;*/

        //create a new child and add that to the group
        RadioStation aRadio = new RadioStation();
        aRadio.setLink(link);
        aRadio.setName(title);
        productList.add(aRadio);
        headerInfo.setRadioStationList(productList);

        //find the group position inside the list
        groupPosition = rdCategoryLst.indexOf(headerInfo);
        return groupPosition;
    }




    @Override
    protected void onPause() {
        super.onPause();


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            finish();
        }
        return super.onKeyDown(keyCode, event);
    }


    protected void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transactionMonth = fragmentManager.beginTransaction();
        transactionMonth.addToBackStack(null);
        transactionMonth.replace(R.id.flSisMenu, fragment, FRAGMENT_FLAG).commit();

    }

}
