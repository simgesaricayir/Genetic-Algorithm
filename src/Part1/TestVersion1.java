package Part1;

public class TestVersion1 {

    public static void main(String[] args) {
        GeneticAlg g1 = new Version1(10);
        System.out.println("fitness values for version1");
        for(int i=0;i<g1.chromosomes.size();i++){
            System.out.println(g1.chromosomes.get(i).fitness);
        }



    }
}
