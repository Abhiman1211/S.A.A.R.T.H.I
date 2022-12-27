package com.example.saarthi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class laymenlogin extends AppCompatActivity {

Button login;
EditText layemail, laypass;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laymenlogin);
        login = findViewById(R.id.dashllogin);
        layemail = findViewById(R.id.layemail);
        laypass = findViewById(R.id.laypass);
        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
//                Intent i = new Intent(getApplicationContext(), dashboard.class);
//                startActivity(i);
            }
        });


    }
    private void loginUser(){
        String email = layemail.getText().toString();
        String password = laypass.getText().toString();

        if(TextUtils.isEmpty(email)){
            layemail.setError("Email cannot be empty.");
            layemail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            laypass.setError("Password cannot be empty.");
            laypass.requestFocus();
        }
        else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Logged in successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), laymenhomepg.class));
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Login error" + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }
}