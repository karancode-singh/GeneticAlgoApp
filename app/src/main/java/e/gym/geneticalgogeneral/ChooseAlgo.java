package e.gym.geneticalgogeneral;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ChooseAlgo extends AppCompatActivity {

    private android.widget.Button algo9000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_algo);
        this.algo9000 = (Button) findViewById(R.id.algo9000);

        algo9000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseAlgo.this,Algo9000.class));
            }
        });
    }
}
