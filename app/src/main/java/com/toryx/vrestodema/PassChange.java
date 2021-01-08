package com.toryx.vrestodema;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PassChange extends AppCompatActivity {
    private FirebaseUser firebaseUser;

    private EditText passedited;
    private Button kata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_change);
        passedited =findViewById(R.id.passwordd);
        kata =findViewById(R.id.kataxwrisi);

        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();




        kata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userpass = passedited.getText().toString();
                firebaseUser.updatePassword(userpass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){Toast.makeText(PassChange.this, "Επιτυχής Αλλαγή Κωδικού", Toast.LENGTH_SHORT).show();
                        finish();
                        }
                        else{Toast.makeText(PassChange.this, "Μη Επιτυχής Αλλαγή Κωδικού", Toast.LENGTH_SHORT).show();}
                    }
                });
            }
        });



    }
}
