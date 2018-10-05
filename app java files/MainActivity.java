package com.example.narender.piratenite;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth=FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(mAuth.getCurrentUser()!=null){
            Toast.makeText(this,"already login",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, main2.class);
            startActivity(intent);
        }
       else {


            Button btn = findViewById(R.id.button);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   mAuth.signInAnonymously().addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"login successful",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, main2.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(MainActivity.this,"login unsuccessful",Toast.LENGTH_SHORT).show();
                            }
                       }
                   });

                }
            });
        }
    }
}
