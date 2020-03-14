package Part1;

import java.math.BigInteger;

public class Chromosome implements Comparable {
    /**
     * first part of the chromosome
     */
    public String x1;
    /**
     * second part of the chromosome
     */
    public String x2;
    /**
     * string chromosome variable that holds the bits
     */
    public String chromosome;
    /**
     * calculated fitness value with maximize function
     */
    public double fitness;

    /**
     * Constructor that takes two parts of the chromosome and combine them
     * @param x1 first part of the chromosome
     * @param x2 second part of the chromosome
     */
    public Chromosome(String x1, String x2) {
        this.x1 = x1;
        this.x2 = x2;
        chromosome=x1+x2;
    }

    /**
     * compareTo metod for comparing two Chromosome according to their fitness value
     * @param o object that will be compared with another Chromosome
     * @return if they are equal 0, if parameter object is bigger return -1 otherwise 1.
     */
    @Override
    public int compareTo(Object o) {
        Chromosome other=(Chromosome) o;
        if(this.fitness>other.fitness)
            return 1;
        else if(this.fitness<other.fitness)
            return -1;
        else
            return 0;
    }
}