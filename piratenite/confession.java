package com.example.narender.piratenite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class confession extends AppCompatActivity {
    private FirebaseDatabase database;
    private int count=0;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confession);
        database=FirebaseDatabase.getInstance();
        final Button button=(Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText text=(EditText)findViewById(R.id.editText3);
                EditText confess=(EditText)findViewById(R.id.editText4);
                final String path=text.getText().toString().trim();
                final String mess=confess.getText().toString().trim();
                DatabaseReference mref= database.getReference(path);
                mref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            Toast t=Toast.makeText(confession.this,"already exists",Toast.LENGTH_SHORT);
                            if(count==0) {

                                t.show();
                            }
                        }
                        else{
                            button.setEnabled(false);
                            count=1;
                            Toast.makeText(confession.this,"successfully done",Toast.LENGTH_SHORT).show();
                            DatabaseReference mref= database.getReference(path);
                            DatabaseReference reference=mref.push();
                            reference.setValue(mess);
                            Intent intent=new Intent(confession.this,main2.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        }
        });

    }
}
