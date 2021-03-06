package com.example.jobhunt;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText email,password;
    FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.username);
        password = findViewById(R.id.password);

        if (auth.getCurrentUser()!=null){
            startActivity(new Intent(Login.this,HomeActivity.class));
            finish();
        }
    }

    // Login the firebase code
    public void Login(View view) {
        String emailid = email.getText().toString().trim();
        String passw = password.getText().toString().trim();

        if (TextUtils.isEmpty(emailid)){
            email.setError("email id is required");
            return;
        }
        if (TextUtils.isEmpty(passw)){
            password.setError("password is empty");
            return;
        }
        if (passw.length()<8){
            password.setError("password length must be 8 char long");
            return;
        }
        auth.signInWithEmailAndPassword(emailid,passw).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
        // Redirect to Register Activity
    public void Direct(View view) {
        startActivity(new Intent(getApplicationContext(),Register.class));
        finish();

    }

        // Redirect to ForgetPassword
    public void ForgetPassword(View view) {
        startActivity(new Intent(getApplicationContext(),ForgetPassword.class));
        finish();
    }

    public void RedirectToAdminLogin(View view) {

    }

    public void RedirectToCompanyLogin(View view) {
        startActivity(new Intent(getApplicationContext(),Clogin.class));
        finish();
    }
}