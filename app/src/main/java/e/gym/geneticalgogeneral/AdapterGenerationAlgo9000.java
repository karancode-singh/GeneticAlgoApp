package e.gym.geneticalgogeneral;

import android.content.Context;
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

public class AdapterGenerationAlgo9000 extends ArrayAdapter<GenerationOfCrankshafts> {




    public AdapterGenerationAlgo9000(Context context, ArrayList<GenerationOfCrankshafts> data) {
        super(context, R.layout.custom_row_algo_9000, data);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater lf = LayoutInflater.from(getContext());
        View customView = lf.inflate(R.layout.custom_row_algo_9000, parent, false);


        GenerationOfCrankshafts rData = getItem(position);

        TextView genName=(TextView)customView.findViewById(R.id.genNumLoad9000);
        TextView genFit=(TextView)customView.findViewById(R.id.avFitnessLoad9000);



        genName.setText("Generation "+rData.getNumber());
        if(rData.getAvFitness()!=0){
            genFit.setText("av. Fitness : "+rData.getAvFitness());
        }


        return customView;
    }

}
