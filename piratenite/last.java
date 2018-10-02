package com.example.narender.piratenite;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class last extends AppCompatActivity {
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        Intent intent=getIntent();
        final String subject =intent.getStringExtra("name");
        TextView textView=findViewById(R.id.textView5);
        database=FirebaseDatabase.getInstance();
        DatabaseReference mref=database.getReference(subject);
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> list =new ArrayList<String>();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    list.add((String) snapshot.getValue());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.list_view,list);
                ListView listView = (ListView) findViewById(R.id.list_voew);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        textView.setText(subject);
        final EditText editText=findViewById(R.id.posttext);
        Button button=findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String string=editText.getText().toString();
                DatabaseReference mref= database.getReference(subject);
                DatabaseReference reference=mref.push();
                reference.setValue(string);
            }
        });
    }
}
