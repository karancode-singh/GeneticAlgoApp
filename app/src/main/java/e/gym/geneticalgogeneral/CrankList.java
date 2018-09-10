package e.gym.geneticalgogeneral;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CrankList extends AppCompatActivity {

    ListView lv;
    AdapterCrankshaftAlgo9000 adapterCrankshaftAlgo9000;
    ArrayList<Crankshaft> crankshafts=new ArrayList<>();
    DatabaseReference algoRef;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crank_list);

        context=CrankList.this;

        lv=(ListView)findViewById(R.id.crankListAlgo9000);
        adapterCrankshaftAlgo9000=new AdapterCrankshaftAlgo9000(CrankList.this,crankshafts);
        final String index=getIntent().getStringExtra("index");
        lv.setAdapter(adapterCrankshaftAlgo9000);

        algoRef= FirebaseDatabase.getInstance().getReference().child("algo9000").child("generations")
                .child(index).child("crankshafts");
        Log.d("aaaa","index: "+index);

        algoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Log.d("aaaa","exists");
                    crankshafts.clear();
                    for(DataSnapshot ds:dataSnapshot.getChildren()){
                        Crankshaft c=ds.getValue(Crankshaft.class);
                        crankshafts.add(c);
                    }
                    adapterCrankshaftAlgo9000.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Enter fitness").setTitle("Enter numerical value");

// Set up the input
                final EditText input = new EditText(context);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);
// Set up the buttons
                builder.setCancelable(false);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        String m_Text = input.getText().toString();
                        if(isNumeric(m_Text)){
                            final int d=fitness(Double.parseDouble(m_Text));
                            if(d!=0){
//                                Toast.makeText(CrankList.this,d+"  here",Toast.LENGTH_SHORT).show();
                                final DatabaseReference dr=FirebaseDatabase.getInstance().getReference().child("algo9000")
                                        .child("generations")
                                        .child(index);
                                dr.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        int done=(int)dataSnapshot.child("done").getChildrenCount();
                                        int avFitness=dataSnapshot.child("avFitness").getValue(Integer.class);
                                        Log.d("YO","done"+ done);
                                        Log.d("YO","avFitness"+ avFitness);

                                        int totalFitness=avFitness*done;
                                        Log.d("YO","total"+ totalFitness);
                                        avFitness=(totalFitness+d)/(done+1);

                                        Log.d("YO","avFitness"+ avFitness);
                                        dr.child("avFitness").setValue((totalFitness+d)/(done+1));
                                        dr.child("done").child(Integer.toString(position)).setValue(Integer.toString(position));

                                        algoRef.child(Integer.toString(position)).child("fitness").setValue(d);
                                        String email= FirebaseAuth.getInstance().getCurrentUser().getEmail();
                                        if(email.equals("adityac@gmail.com")){
                                            algoRef.child(Integer.toString(position)).child("doneBy").setValue("Aditya Chawla");
                                        }
                                        if(email.equals("amrita@mech.com")){
                                            algoRef.child(Integer.toString(position)).child("doneBy").setValue("Amrita");
                                        }
                                        if(email.equals("adityal@mech.com")){
                                            algoRef.child(Integer.toString(position)).child("doneBy").setValue("Aditya Lamba");
                                        }
                                        if(email.equals("aayushd@mech.com")){
                                            algoRef.child(Integer.toString(position)).child("doneBy").setValue("Aayush Deol");
                                        }
                                        dialog.dismiss();
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }


                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();
            }
        });
    }

    int fitness(double input){
        return (int)Math.pow(input,1.6);

    }

    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
