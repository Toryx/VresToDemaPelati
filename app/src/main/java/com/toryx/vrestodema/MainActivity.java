package com.toryx.vrestodema;

import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button eisodos;
    private Button register;
    private TextView username;
    private TextView password;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    RelativeLayout rellay1, rellay2;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eisodos = (Button) findViewById(R.id.buttoneisodoy);
        username = (TextView) findViewById(R.id.UsernameLogin);
        password = (TextView) findViewById(R.id.PasswordID);
        register = (Button) findViewById(R.id.register);
        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);

        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            finish();
            startActivity(new Intent(MainActivity.this, Main2Activity.class));
        }
        eisodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(username.getText().toString().matches("") || password.getText().toString().matches("") ){
                    Toast.makeText(MainActivity.this, "Παρακαλώ συμπληρώστε Ονομα Χρήστη και Κωδικο", Toast.LENGTH_LONG).show();
                }
                else{validate(username.getText().toString(), password.getText().toString());
                    password.setText("");
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });


    }
    public void openRegister() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);


    }

    private void validate(String Usern, String Userp) {
        progressDialog.setMessage("Αυθεντικοποιηση...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(Usern, Userp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override

            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    checkE();
                }
                else {
                    Toast.makeText(MainActivity.this, "Εισοδος Απετυχε", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void checkE() {


        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();
        if(emailflag) {
            startActivity(new Intent(MainActivity.this, Main2Activity.class));
            Toast.makeText(MainActivity.this, "Εισοδος Πετυχε,Καλως Ηρθες", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(MainActivity.this, "Εισοδος Aπέτυχε.Δεν εχεις κανει αυθεντικοποιηση του E-mail", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }

    }

}
