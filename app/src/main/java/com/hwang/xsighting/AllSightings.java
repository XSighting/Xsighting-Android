package com.hwang.xsighting;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hwang.xsighting.models.Sighting;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllSightings extends AppCompatActivity {

  private TextView mTextMessage;
  private RecyclerView recyclerView;
  private List<Sighting> sightingsList;
  private AllSightingsAdapter mAdapter;
  private final String TAG = "AllSightingsActivity";


  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
          = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.navigation_home:
          Intent navHome = new Intent(AllSightings.this, AllSightings.class);
          startActivity(navHome);
          break;
        case R.id.navigation_add_sighting:
          Intent navAddSighting = new Intent(AllSightings.this, CreateSighting.class);
          startActivity(navAddSighting);
          break;
        case R.id.navigation_user_profile:
          mTextMessage.setText(R.string.title_user_profile);
          return true;
      }
      return false;
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_all_sightings);
    TextView welcomeViewProject = findViewById(R.id.welcomeAddProject);
    welcomeViewProject.setText("hello");
    recyclerView = findViewById(R.id.recyclerview_allsightings);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    mTextMessage = (TextView) findViewById(R.id.message);
    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    getAllSightings();
  }

  private void getAllSightings(){
    class GetAllSightings extends AsyncTask<Void, Void, List<Sighting>> {

      @Override
      protected List<Sighting> doInBackground(Void... voids){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = db.collection("sighting");


        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
          @Override
          public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()) {
              for (DocumentSnapshot document : task.getResult()) {
                Sighting sighting = document.toObject(Sighting.class);
                sightingsList = new ArrayList<>();
                sightingsList.add(sighting);
              }
              Log.d(TAG, sightingsList.toString());
            } else {
              Log.d(TAG, "Error getting documents: ", task.getException());
            }
          }
        });


        return sightingsList;
      }

      @Override
      protected void onPostExecute(List<Sighting> sightings){
        super.onPostExecute(sightings);
        AllSightingsAdapter adapter = new AllSightingsAdapter(AllSightings.this, sightings);
        recyclerView.setAdapter(adapter);
      }
    }
    GetAllSightings gp = new GetAllSightings();
    gp.execute();
  }

}
