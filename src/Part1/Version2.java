package Part1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Version2 extends GeneticAlg{

    /**
     * Constructor that takes size of the population and generate population then, compute their fitnesses
     * @param populationSize size of the population will be generated
     */
    public Version2(int populationSize) {
        super(populationSize);
    }

    /**
     *So for a population of N solutions the best solution gets rank N, the second best rank N-1,
     * The worst individual has rank 1. Now use the roulette wheel and start selecting.
     */
    @Override
    public void selection() {

        int rank[]=new int[this.populationSize];
        ArrayList<Chromosome> populationCopy = new ArrayList<>(chromosomes);

        Collections.sort(populationCopy);

        int rankPopulation=1;
        for(int i=0;i<populationCopy.size();i++){
            rank[chromosomes.indexOf(populationCopy.get(i))]=rankPopulation;
            rankPopulation++;
        }
        
        rankPopulation=((rankPopulation-1)*(rankPopulation))/2;
        Random r = new Random();
        int iter =0;
        while (parents.size()<2 & iter<35){
            double random = 0 + r.nextDouble() * (rankPopulation - 0);
            int partialRank = 0;

            for (int i = 0; i < rank.length; ++i) {
                partialRank += rank[i];
                if (partialRank >= random) {
                    parents.add(new Chromosome(chromosomes.get(i).x1,chromosomes.get(i).x2));
                    break;
                }
            }
            iter++;
        }
    }

    /**
     * overrided hook metod for controlling termination of genetic algoritm
     * @return false if 40 iteration is completed in the selection metod and parent couldn't find
     */
    @Override
    protected boolean continueCondition() {
        if (parents.size()<2)
            return false;
        return true;
    }

    /**
     * get random two point then change parts of the chromosomes
     */
    @Override
    protected void crossover() {
        Random rand = new Random();
        //Select a random crossover point
        int crossOverPoint=0;
        int crossOverPoint2=0;
        char temp;

        while(crossOverPoint>=crossOverPoint2){
            crossOverPoint = rand.nextInt(chromosomeLength);
            crossOverPoint2 = rand.nextInt(chromosomeLength);
        }

        String parent0,parent1;
        //Swap values among parents
        for (int i = 0; i < crossOverPoint ; i++) {
            temp = parents.get(0).chromosome.charAt(i);
            parent0 = parents.get(0).chromosome;
            parent1 = parents.get(1).chromosome;
            parents.get(0).chromosome = parent0.substring(0,i)+ parent1.charAt(i)+parent0.substring(i+1);
            parents.get(1).chromosome = parent1.substring(0,i)+temp+parent1.substring(i+1);

        }
        //Swap values among parents
        for (int i = crossOverPoint2; i < chromosomeLength ; i++) {
            temp = parents.get(0).chromosome.charAt(i);
            parent0 = parents.get(0).chromosome;
            parent1 = parents.get(1).chromosome;
            parents.get(0).chromosome = parent0.substring(0,i)+ parent1.charAt(i)+parent0.substring(i+1);
            parents.get(1).chromosome = parent1.substring(0,i)+temp+parent1.substring(i+1);

        }

        this.updateChromosome();
    }



}
