package Part1;

public class TestVersion2 {
    public static void main(String[] args) {
        GeneticAlg g2 = new Version2(10);
        System.out.println("fitness values for version2");
        for(int i=0;i<g2.chromosomes.size();i++){
            System.out.println(g2.chromosomes.get(i).fitness);
        }



    }
}
