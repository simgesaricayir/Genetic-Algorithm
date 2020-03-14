package Part1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class GeneticAlg {
    /**
     * Instance variable chromosomes that holds the all population
     */
    protected ArrayList<Chromosome> chromosomes = new ArrayList<>();
    /**
     * size of the population
     */
    protected int populationSize;

    /**
     * instance variable that holds the parent after the selection
     */
    protected ArrayList<Chromosome> parents;
    /**
     * integer variable that holds length of the chromosome
     */
    protected int chromosomeLength;


    /**
     * Constructor that takes size of the population
     * @param populationSize size of the population will be generated
     */
    public GeneticAlg(int populationSize){
        this.populationSize = populationSize;
        geneticAlgorithm();

    }
    /**
     * abstract primitive method for selection operation
     */
    protected abstract void selection();

    /**
     * abstract primitive method for crossover operation
     */
    protected abstract void crossover();




    /**
     * template method for genetic algorithm
     */
    public void geneticAlgorithm(){
        generatePopulation();
        computeFitness(this.chromosomes);
        int i=0;
        while(i<100){
            parents = new ArrayList<>();
            selection();
            if (!continueCondition())
                break;
            crossover();
            mutation();
            computeFitness(this.parents);
            selectionSurvivor();
            i++;
        }

    }

    /**
     * hook metod
     * @return always true but subclass may implement this
     */
    protected boolean continueCondition(){
        return true;
    }


    /**
     * generate population with given populationSize value in the constructor and add them to chromosomes list
     * constraints : x1+x2⩽5 ; 0⩽x1⩽5; 0⩽x2⩽5
     */
    private void generatePopulation(){
        Random random = new Random();
        double x1=0, x2=0;
        String bitsx1;
        String bitsx2;
        int i=0;
        while(i<2){
            if(this.chromosomes.size()==this.populationSize)
                break;
            x1 = random.nextDouble()*5.0;
            x2 = random.nextDouble()*(5.0-x1);

            bitsx1 = Long.toBinaryString(Double.doubleToLongBits(x1));
            bitsx2 = Long.toBinaryString(Double.doubleToLongBits(x2));
            if (bitsx1.length()==62)
                bitsx1 = '0' + bitsx1;
            if (bitsx2.length()==62)
                bitsx2 = '0' + bitsx2;

            Chromosome chromosome = new Chromosome(bitsx1,bitsx2);
            this.chromosomes.add(chromosome);

        }
        this.chromosomeLength = this.chromosomes.get(0).chromosome.length();
    }


    private void selectionSurvivor() {

        List<Chromosome> p_copy=new ArrayList<>(chromosomes);
        Collections.sort(p_copy);
        for (int i=0;i<parents.size();++i){
            double x1=stringToDouble(parents.get(i).x1);
            double x2=stringToDouble(parents.get(i).x2);
            if( x1>5 || x1<0 || (x1+x2)>5 || (x1+x2)<0 || x2>5 || x2<0 || Double.isNaN(x1) || Double.isNaN(x2)) {
                parents.remove(i);
                i--;
            }
        }
        for (int i=0;i<parents.size();++i){
            chromosomes.set(chromosomes.indexOf(p_copy.get(i)),parents.get(i));
        }
    }

    /**
     * compute fitness value for all chromosomes in the instance value chromosomes
     */
    private void computeFitness(List<Chromosome> list){
        for(int i=0;i<list.size();i++){
            Chromosome ch = list.get(i);
            ch.fitness= Math.abs(maximize(stringToDouble(ch.x1),stringToDouble(ch.x2)));
        }
    }

    /**
     * Optimization function for genetic algorithm
     * @param x1 first chrome's value
     * @param x2 second chrome's value
     * @return double maximized value for x1 and x2
     */
    public final double maximize(double x1,double x2){
        return (20*x1*x2) + (16*x2) -(2*x1*x1) -(x2*x2) - ((x1+x2)*(x1+x2));
    }


    /**
     * Helper method convert binary string to double
     * @param x binary representation of a double number
     * @return double represenrtation of the given string
     */
    protected double stringToDouble(String x) {
        return Double.longBitsToDouble(new BigInteger(x, 2).longValue());
    }

    /**
     * final method that subclasses can't override
     * parent chromosomes's random bits are flipped and their x1 and x2 parts are updated.
     */
    private final void mutation(){
        Random rand = new Random();
        int mutationIndex;
        for(int i=0;i<this.parents.size();i++){
            for(int j=0;j<10;j++){
                mutationIndex = rand.nextInt(chromosomeLength);
                String chr = this.parents.get(i).chromosome;
                if (chr.charAt(mutationIndex)=='1')
                    this.parents.get(i).chromosome= chr.substring(0,mutationIndex)+ '0'+chr.substring(mutationIndex+1);
                else
                    this.parents.get(i).chromosome= chr.substring(0,mutationIndex)+ '1'+chr.substring(mutationIndex+1);
            }

        }
        updateChromosome();
    }

    /**
     * method that updates parent chromosomes's x1 and x2 parts of the chromosome
     */
    protected final void updateChromosome(){
        parents.get(0).x1 = parents.get(0).chromosome.substring(0,chromosomeLength/2);
        parents.get(0).x2 = parents.get(0).chromosome.substring(chromosomeLength/2,chromosomeLength);
        parents.get(1).x1 = parents.get(1).chromosome.substring(0,parents.get(1).chromosome.length()/2);
        parents.get(1).x2 = parents.get(1).chromosome.substring(parents.get(1).chromosome.length()/2,parents.get(1).chromosome.length());

    }
}

