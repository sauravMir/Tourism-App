package com.educareapps.tourism;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.educareapps.adapter.CountriesAdapter;
import com.educareapps.fragment.MainFragment;
import com.educareapps.parser.ParserMode;
import com.educareapps.utilities.RootUrl;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MainActivity extends BaseActivity {

    private ExpandableListView simpleExpandableListView;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private LinkedHashMap<String, CountryCategory> subjects = new LinkedHashMap<String, CountryCategory>();
    private ArrayList<CountryCategory> rdCategoryLst = new ArrayList<CountryCategory>();
    private CountriesAdapter listAdapter;
    MainActivity activity;
    private static final String FRAGMENT_FLAG = "fragment_SIS_FLAG";

    private static final int RECORDER_SAMPLERATE = 8000;
    private static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;
    private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
    ProgressDialog progressDialog;
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
        progressDialog = new ProgressDialog(activity);
        mainFragment = new MainFragment();

        addDrawerItems();
        setupDrawer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


     /*   marshMallowPermission = new MarshMallowPermission(activity);

        if (!marshMallowPermission.checkPermissionForExternalStorage() || !marshMallowPermission.checkPermissionForRecord() || !marshMallowPermission.checkPermissionForCamera()) {
            marshMallowPermission.requestPermissionForExternalStorage();
            marshMallowPermission.requestPermissionForRecord();
            marshMallowPermission.requestPermissionForCamera();
        }*/
        int bufferSize = AudioRecord.getMinBufferSize(RECORDER_SAMPLERATE, RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING);
        System.out.println("BUFFER SIZE VALUE IS " + bufferSize);
//        Countries countries = (Countries) listAdapter.getChild(0, 0);


    }

    View previousSelectedItem;


    private void addDrawerItems() {
        //get reference of the ExpandableListView
        simpleExpandableListView = (ExpandableListView) findViewById(R.id.simpleExpandableListView);
//        http://192.52.243.6/TourismApp/Packages/getCountryList/
        showProgress();
        makeRequest(RootUrl.RootUrl + "getCountryList");


    }


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
/*    private void loadData() {

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


    }*/


    //here we maintain our products in various departments
    public int addRadioStation(String rdCategory, String title, String link) {

        int groupPosition = 0;

        //check the hash map if the group already exists
        CountryCategory headerInfo = subjects.get(rdCategory);
        //add the group if doesn't exists
        if (headerInfo == null) {
            headerInfo = new CountryCategory();
            headerInfo.setName(rdCategory);
            subjects.put(rdCategory, headerInfo);
            rdCategoryLst.add(headerInfo);
        }

        //get the children for the group
        ArrayList<Countries> productList = headerInfo.getRadioStationList();
      /*  //size of the children list
        int listSize = productList.size();
        //add to the counter
        listSize++;*/


        //create a new child and add that to the group
        if (!isCountry(productList, title)) {
            Countries aRadio = new Countries();
            aRadio.setLink(link);
            aRadio.setName(title);
            productList.add(aRadio);
        }


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

    /// making json request
    private void makeRequest(String url) {
        JsonArrayRequest jreq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("Json", response.toString());
                        parserJson(response);
                        hideProgress();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Json", error.toString());
                hideProgress();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(jreq);

    }

    private void parserJson(JSONArray response) {
        ParserMode parserMode = new ParserMode(activity, response);
        parserMode.parser();

        // create the adapter by passing your ArrayList data
        if (rdCategoryLst.size() > 0)
            listAdapter = new CountriesAdapter(MainActivity.this, rdCategoryLst);
        // attach the adapter to the expandable list view
        simpleExpandableListView.setAdapter(listAdapter);

        //expand all the Groups
        expandAll();

        // setOnChildClickListener listener for child row click
        simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //get the group header
                //CountryCategory headerInfo = rdCategoryLst.get(groupPosition);
                //get the child info

                if (previousSelectedItem != null) {
                    previousSelectedItem.setBackgroundColor(Color.parseColor("#ffeeeeee"));
                    mainFragment.loadCountryWiseTrip(rdCategoryLst.get(groupPosition).getRadioStationList().get(childPosition).getLink());
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
        setFragment(mainFragment);
        mainFragment.loadCountryWiseTrip(rdCategoryLst.get(0).getRadioStationList().get(0).getLink());

    }


    private void showProgress() {
        if (progressDialog != null) {
            progressDialog.setMessage("please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

    }

    private void hideProgress() {

        if (progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }

    public boolean isCountry(ArrayList<Countries> productList, String name) {
        boolean isCountry = false;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getName().equals(name)) {
                isCountry = true;
                break;
            } else {
                isCountry = false;
            }
        }
        return isCountry;
    }
}
