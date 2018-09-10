package e.gym.geneticalgogeneral;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aditya on 14/2/18.
 */

public class AdapterCrankshaftAlgo9000 extends ArrayAdapter<Crankshaft> {


    private TextView filletRalgo9000;
    private TextView crankDiaAlgo9000;
    private TextView heightAlgo9000;
    private TextView materialAlgo9000;
    private TextView fitnessAlgo9000;
    private TextView doneByAlgo9000;

    public AdapterCrankshaftAlgo9000(Context context, ArrayList<Crankshaft> data) {
        super(context, R.layout.custom_row_cr1, data);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater lf = LayoutInflater.from(getContext());
        View customView = lf.inflate(R.layout.custom_row_cr1, parent, false);

        this.doneByAlgo9000 = (TextView) customView.findViewById(R.id.doneByAlgo9000);
        this.fitnessAlgo9000 = (TextView) customView.findViewById(R.id.fitnessAlgo9000);
        this.materialAlgo9000 = (TextView) customView.findViewById(R.id.materialAlgo9000);
        this.heightAlgo9000 = (TextView) customView.findViewById(R.id.heightAlgo9000);
        this.crankDiaAlgo9000 = (TextView) customView.findViewById(R.id.crankDiaAlgo9000);
        this.filletRalgo9000 = (TextView) customView.findViewById(R.id.filletRalgo9000);

        Crankshaft rData = getItem(position);

        filletRalgo9000.setText("Fillet r: "+rData.getFilletR());
        crankDiaAlgo9000.setText("Crank diameter: "+rData.getCrankDia()+"");
        heightAlgo9000.setText("Height : "+rData.getHeight()+"");
        materialAlgo9000.setText("Material : "+rData.getMatString());
        if(rData.getFitness()==0){
            fitnessAlgo9000.setText("Click to edit fitness");
            fitnessAlgo9000.setBackgroundColor(Color.RED);
        }else{
            fitnessAlgo9000.setText(rData.getFitness()+"");
        }
        if(rData.getDoneBy()==null){
            doneByAlgo9000.setText("FEA not done yet");
        }else{
            doneByAlgo9000.setText(rData.getDoneBy());
        }
        return customView;
    }

}