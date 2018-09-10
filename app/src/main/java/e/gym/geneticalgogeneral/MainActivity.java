package e.gym.geneticalgogeneral;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private android.widget.EditText userName;
    private android.widget.EditText pwd;
    private android.widget.Button loginB;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.loginB = (Button) findViewById(R.id.loginB);
        this.pwd = (EditText) findViewById(R.id.pwd);
        this.userName = (EditText) findViewById(R.id.userName);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            startActivity(new Intent(MainActivity.this,ChooseAlgo.class));
        } else {
            // No user is signed in
        }

        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String un=userName.getText().toString();
                String pass=pwd.getText().toString();

                if(un!=null && un!=""&& pass!=null && pass.length()>5){
                    mAuth.signInWithEmailAndPassword(un, pass)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
//                                        Log.d(TAG, "signInWithEmail:success");
//                                        FirebaseUser user = mAuth.getCurrentUser();
                                        startActivity(new Intent(MainActivity.this,ChooseAlgo.class));

                                    } else {
                                        // If sign in fails, display a message to the user.
//                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
//                                        updateUI(null);
                                    }

                                    // ...
                                }
                            });
                }
            }
        });


    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }
}
