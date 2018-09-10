package e.gym.geneticalgogeneral;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class Algo9000 extends AppCompatActivity {

    private android.widget.ListView genListAlgo9000;
    private android.widget.Button createGenAlgo9000;
    boolean canCreate=false;

    Random rand=new Random();

    DatabaseReference genRef;

    ArrayList<GenerationOfCrankshafts> generations=new ArrayList<>();
    AdapterGenerationAlgo9000 adapterGenerationAlgo9000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algo9000);
        this.createGenAlgo9000 = (Button) findViewById(R.id.createGenAlgo9000);
        this.genListAlgo9000 = (ListView) findViewById(R.id.genListAlgo9000);
        adapterGenerationAlgo9000=new AdapterGenerationAlgo9000(Algo9000.this,generations);
        genListAlgo9000.setAdapter(adapterGenerationAlgo9000);

        genRef= FirebaseDatabase.getInstance().getReference().child("algo9000").child("generations");

        genRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                canCreate=true;
                generations.clear();
                if(dataSnapshot.exists()){
                    for(DataSnapshot ds:dataSnapshot.getChildren()){
                        GenerationOfCrankshafts data=ds.getValue(GenerationOfCrankshafts.class);
                        generations.add(data);
                    }
                    adapterGenerationAlgo9000.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        createGenAlgo9000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canCreate){
                    if(generations.size()==0){
                        ArrayList<Crankshaft> cs=new ArrayList<>();
                        for(int i=0;i<12;i++){
                            Crankshaft c=new Crankshaft();
                            c.setCrankDia(c.randCrankDia());
                            c.setFilletR(c.randFilletR());
                            c.setHeight(c.randHeight());
                            c.setLength(c.randLength());
                            c.setMat(c.randMat());
                            cs.add(c);
                        }
                        GenerationOfCrankshafts gen=new GenerationOfCrankshafts();
                        gen.setCrankshafts(cs);
                        gen.setNumber(1);
                        genRef.child(0+"").setValue(gen);

                    }else{
                        int latest=generations.size()-1;
                        GenerationOfCrankshafts latestGen=generations.get(latest);
                        ArrayList<Integer> fits=new ArrayList<>();
                        if(generations.get(latest).getDone().size()==12){
                            int fitSum=0;
                            for(int i=0;i<12;i++){
                                int f=generations.get(latest).getCrankshafts().get(i).getFitness();
                                fitSum+=f;
                                fits.add(f);
                            }
                            ArrayList<Crankshaft> newcs=new ArrayList<>();
                            for(int i=0;i<6;i++){
                                //parents
                                Crankshaft p1=latestGen.getCrankshafts().get(choose(fits,fitSum));
                                Crankshaft p2=latestGen.getCrankshafts().get(choose(fits,fitSum));
                                //mate and mutate
                                Crankshaft c1=mate(p1,p2);
                                Crankshaft c2=mate(p1,p2);


                                //add and notify
                                newcs.add(c1);
                                newcs.add(c2);

                            }
                            GenerationOfCrankshafts newgen=new GenerationOfCrankshafts();
                            newgen.setCrankshafts(newcs);
                            newgen.setNumber(latest+1);
                            genRef.child(newgen.getNumber()+"").setValue(newgen);

                        }else{
                            Toast.makeText(Algo9000.this,"The fitness values are not complete",Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        });

        genListAlgo9000.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(Algo9000.this,CrankList.class);
                i.putExtra("index",Integer.toString(position));
                startActivity(i);
            }
        });

    }

    private Crankshaft mate(Crankshaft p1, Crankshaft p2) {
        Crankshaft child=new Crankshaft();

        //choose fillet r
        if(chance(75)){
            if(chance(50)){
                child.setFilletR(p1.getFilletR());
            }else{
                child.setFilletR(p2.getFilletR());
            }
        }else{
            child.setFilletR((p1.getFilletR()+p2.getFilletR())/2);
        }

        if(chance(3)){
            child.setFilletR(child.randFilletR());
        }

        //choose crank dia
        if(chance(75)){
            if(chance(50)){
                child.setCrankDia(p1.getCrankDia());
            }else{
                child.setCrankDia(p2.getCrankDia());
            }
        }else{
            child.setCrankDia((p1.getCrankDia()+p2.getCrankDia())/2);
        }

        if(chance(3)){
            child.setCrankDia(child.randCrankDia());
        }

        //choose height
        if(chance(75)){
            if(chance(50)){
                child.setHeight(p1.getHeight());
            }else{
                child.setLength(p2.getHeight());
            }
        }else{
            child.setHeight((p1.getHeight()+p2.getHeight())/2);
        }

        if(chance(3)){
            child.setHeight(child.randHeight());
        }

        //choose material
        if(chance(90)){
            if(chance(50)){
                child.setMat(p1.getMat());
            }else{
                child.setMat(p2.getMat());
            }
        }else{
            child.setMat(child.randMat());
        }

        if(chance(3)){
            child.setMat(child.randMat());
        }
        child.setLength(240);
        return child;
    }


    boolean chance(int prob){
        int r=rand.nextInt(100);
        if(r>prob){
            return false;
        }else{
            return true;
        }
    }

//    int fitness(double input){
//        return (int)Math.pow(100.0/input,8);
//    }

    int choose(ArrayList<Integer> fitnesses,int fitSum){
        int r=rand.nextInt(fitSum);
        int s=0;
        for(int i=0;i<12;i++){
            s+=fitnesses.get(i);
            if(s>r){
                return i;
            }
        }
        return rand.nextInt(12);
    }
}
