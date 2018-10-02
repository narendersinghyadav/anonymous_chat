package com.example.narender.piratenite;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class people_confessions extends AppCompatActivity {
    private FirebaseDatabase database;
    private static final String TAG = "MyApp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_confessions);
        database=FirebaseDatabase.getInstance();
        DatabaseReference mref=database.getReference();
        ListView lv=findViewById(R.id.dynamic);
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    int k = -1;
                ArrayList<String> mylist=new ArrayList<String >();
                   for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                        mylist.add(postsnapshot.getKey());
                   }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,mylist);
                    ListView listView = (ListView) findViewById(R.id.dynamic);
                    listView.setAdapter(adapter);
                }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"unsuccessfully done",Toast.LENGTH_SHORT).show();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),last.class);
                intent.putExtra("name",((TextView)view).getText());
                startActivity(intent);
            }
        });
    }
}
