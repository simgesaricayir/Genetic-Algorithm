package Part1;

import java.util.Random;

public class Version1  extends GeneticAlg {
    /**
     * Constructor that takes size of the population
     * @param populationSize size of the population will be generated
     */
    public Version1(int populationSize) {
        super(populationSize);
    }

    /**
     * roulette wheel selection that divides population n
     * where n is the number of individuals in the population.The region of the wheel which comes in front of the fixed point is chosen as the parent
     */
    @Override
    protected void selection() {
        double totalSum = 0;
        for (int i=0;i<this.chromosomes.size();i++)
            totalSum+= this.chromosomes.get(i).fitness;
        Random random = new Random();
        double rand = 0;

        double partialSum =0;
        int iter=0;
        while (parents.size()<2 & iter<35){ // find two parents
            rand=0 + random.nextDouble()* (totalSum-0);
            for (int i=0;i<this.chromosomes.size();i++){
                partialSum+= this.chromosomes.get(i).fitness;
                if (partialSum>= rand) {
                    parents.add(new Chromosome(this.chromosomes.get(i).x1,this.chromosomes.get(i).x2));
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
     * one point crossover which gets a random index than change parents queue's from beginning the index
     * after crossover operation parent chromosome's x1 and x2 parts are changed so call updateChromosome method for it
     */
    @Override
    protected void crossover() {
        Random rand = new Random();
        //Select a random crossover point
        int crossOverPoint = rand.nextInt(chromosomeLength);
        String parent0,parent1;
        //Swap values among parents
        for (int i = crossOverPoint; i <chromosomeLength ; i++) {
            char temp = parents.get(0).chromosome.charAt(i);
            parent0 = parents.get(0).chromosome;
            parent1 = parents.get(1).chromosome;
            parents.get(0).chromosome = parent0.substring(0,i)+ parent1.charAt(i)+parent0.substring(i+1);
            parents.get(1).chromosome = parent1.substring(0,i)+temp+parent1.substring(i+1);

        }

        this.updateChromosome();

    }


}
