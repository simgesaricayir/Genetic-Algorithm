package Part1;

import java.util.Random;

public class Version3  extends GeneticAlg{
    /**
     * Constructor that takes size of the population
     * @param populationSize size of the population will be generated
     */
    public Version3(int populationSize) {
        super(populationSize);
    }

    /**
     * tournament selection, k-individuals and run a tournament among them.
     */
    @Override
    public void selection() {
        Random r = new Random();
        int numberOfChr = 2 + r.nextInt(chromosomes.size()/3);
        Random rand = new Random();
        int iter =0;
        while (parents.size()<2 & iter<35){
            Chromosome max = chromosomes.get(rand.nextInt(chromosomes.size()));
            for(int i=1;i<numberOfChr;++i){
                Chromosome randomElement = chromosomes.get(rand.nextInt(chromosomes.size()));
                if(max.compareTo(randomElement)== -1)
                    max=randomElement;
            }
            parents.add(new Chromosome(max.x1,max.x2));
        }
        iter++;

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
     * one point crossover which gets a random point then change parents queue
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
