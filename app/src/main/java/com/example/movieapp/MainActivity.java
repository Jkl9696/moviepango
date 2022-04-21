package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //  things i will need for a recycler view
    // videos
    // create a link to the view
    // make a manager


    RecyclerView recyclerView;
    ArrayList<DataSetList> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        arrayList = new ArrayList<>();

        DataSetList dataSetList = new DataSetList("https://www.youtube.com/embed/_MS69oJm0iA");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/PjQCkcRzFmY");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/_MS69oJm0iA");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/-prfRzMi0g4");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/_MS69oJm0iA");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/_MS69oJm0iA");
        arrayList.add(dataSetList);

        YoutubeAdapter youtubeAdapter = new YoutubeAdapter(arrayList, getApplicationContext());
        recyclerView.setAdapter(youtubeAdapter);
    }
}