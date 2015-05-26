package com.example.my.parceler;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.my.parceler.data.Data;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Data data;
    private List<Data> dataList;
    private Map<String, Data> dataMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            data = Parcels.unwrap(savedInstanceState.getParcelable("data"));
            dataList = Parcels.unwrap(savedInstanceState.getParcelable("list"));
            dataMap = Parcels.unwrap(savedInstanceState.getParcelable("map"));

            Toast.makeText(this, dataMap.get("data").data, Toast.LENGTH_LONG).show();
        } else {
            data = new Data();
            data.data = new Date().toString();

            dataList = new ArrayList<>();
            dataList.add(data);

            dataMap = new HashMap<>();
            dataMap.put("data", data);
        }
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        Parcelable wrap = Parcels.wrap(data);
        outState.putParcelable("data", wrap);

        Parcelable list = Parcels.wrap(dataList);
        outState.putParcelable("list", list);

        Parcelable map = Parcels.wrap(dataMap);
        outState.putParcelable("map", map);
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
}
