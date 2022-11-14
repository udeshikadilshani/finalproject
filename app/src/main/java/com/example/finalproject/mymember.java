package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class mymember extends AppCompatActivity {

    Button up;
    Button login;

    ImageButton googleLoginBtn;
    GoogleSignInClient client;
    private EditText editTextEmail,editTextPassword;
    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymember);

        googleLoginBtn=  findViewById(R.id.glogo);
        up = (Button) findViewById(R.id.sinup);
        login=findViewById(R.id.login_login);
        editTextEmail=(EditText) findViewById(R.id.login_email);
        editTextPassword=(EditText) findViewById(R.id.login_password);
        mAuth=FirebaseAuth.getInstance();

        GoogleSignInOptions options= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        client= GoogleSignIn.getClient(this,options);

        googleLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent=client.getSignInIntent();
                startActivityForResult(signInIntent,1000);

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    login();
            }

            private void login() {
                String email= editTextEmail.getText().toString().trim();
                String password= editTextPassword.getText().toString().trim();
                if(email.isEmpty()){
                    editTextEmail.setError("Email is required!");
                    editTextEmail.requestFocus();
                    return;

                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    editTextEmail.setError("Please enter a valid email");
                    editTextEmail.requestFocus();
                    return;

                }
                if(password.isEmpty()){
                    editTextPassword.setError("Password is required!");
                    editTextPassword.requestFocus();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if(user.isEmailVerified()){
                                startActivity((new Intent(mymember.this,memberdashboad.class)));
                            }else{
                                user.sendEmailVerification();
                                Toast.makeText(mymember.this, "Check your email to verify your account!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(mymember.this,"Faild to login! Please check your credentials",Toast.LENGTH_LONG).show();
                        }



                    }
                });



            }

        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4 = new Intent(mymember.this, SignUpActivity.class);
                startActivity(i4);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(mymember.this, memberdashboad.class);
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(mymember.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            } catch (ApiException e) {
                e.printStackTrace();

            }
        }
        }
        @Override
        protected void onStart() {
            super.onStart();
            FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
            if(user!= null){
                Intent intent = new Intent(mymember.this, memberdashboad.class);
                startActivity(intent);

            }
        }



}
