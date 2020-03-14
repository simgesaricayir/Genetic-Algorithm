package Part1;

public class TestVersion3 {
    public static void main(String[] args) {
        GeneticAlg g3 = new Version3(10);
        System.out.println("fitness values for version3");
        for(int i=0;i<g3.chromosomes.size();i++){
            System.out.println(g3.chromosomes.get(i).fitness);
        }



    }
}
