package e.gym.geneticalgogeneral;

import java.util.Random;

/**
 * Created by aditya on 14/2/18.
 */

public class Crankshaft {
    int filletR;
    int crankDia;
    int height;
    int length;
    int mat;
    int fitness;
    String doneBy;


    public Crankshaft(){
        fitness=0;
    }

    public String getDoneBy() {
        return doneBy;
    }

    public void setDoneBy(String doneBy) {
        this.doneBy = doneBy;
    }

    public int getFitness() {
        if(fitness==0){
            return 0;
        }else{
            return fitness;
        }
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public int getFilletR() {


        return filletR;
    }

    public int getCrankDia() {
        return crankDia;
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return 240;
    }

    public String getMatString() {
        if(this.mat==-1){
            return "Cast Iron";
        }
        if(this.mat== 0){
            return "Steel";
        }
        if(this.mat== 1){
            return "Titanium";
        }else{
            return null;
        }
    }

    public int getMat(){
        return mat;
    }

    public int randFilletR() {
        int lower=46;
        int upper=52;
        Random rand=new Random();
        return lower+rand.nextInt(upper-lower+1);
    }

    public int randCrankDia() {
        int lower=84;
        int upper=96;
        Random rand=new Random();
        return lower+rand.nextInt(upper-lower+1);
    }

    public int randHeight() {

        int lower=30;
        int upper=40;
        Random rand=new Random();
        return lower+rand.nextInt(upper-lower+1);

    }

    public int randLength() {

        return 240;

    }

    public int randMat() {
        int lower=-1;
        int upper=1;
        Random rand=new Random();
        return lower+rand.nextInt(upper-lower+1);
    }

    public void setFilletR(int filletR) {
        this.filletR = filletR;
    }

    public void setCrankDia(int crankDia) {
        this.crankDia = crankDia;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLength(int length) {
        this.length = 240;
    }

    public void setMat(int mat) {
        this.mat = mat;
    }
}
