package e.gym.geneticalgogeneral;

import java.util.ArrayList;

/**
 * Created by aditya on 14/2/18.
 */

public class GenerationOfCrankshafts {

    ArrayList<Crankshaft> crankshafts=new ArrayList<>();
    ArrayList<Integer> fitnesses=new ArrayList<>();
    int avFitness;
    int number;
    ArrayList<String> done=new ArrayList<>();

    public int getAvFitness() {
        return avFitness;
    }

    public void setAvFitness(int avFitness) {
        this.avFitness = avFitness;
    }

    public ArrayList<String> getDone() {
        return done;
    }

    public void setDone(ArrayList<String> done) {
        this.done = done;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArrayList<Crankshaft> getCrankshafts() {
        return crankshafts;
    }

    public void setCrankshafts(ArrayList<Crankshaft> crankshafts) {
        this.crankshafts = crankshafts;
    }

    public ArrayList<Integer> getFitnesses() {
        return fitnesses;
    }

    public void setFitnesses(ArrayList<Integer> fitnesses) {
        this.fitnesses = fitnesses;
    }
}
