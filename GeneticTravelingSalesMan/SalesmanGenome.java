import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SalesmanGenome implements Comparable<SalesmanGenome>{
    List<Integer> genome;
    int[][] travelPrices;
    int startingCity;
    int numberOfCities;
    int fitness;

    // Generates a random salesman
    // public SalesmanGenome(List<Integer> genome, int[][] travelPrices, int startingCity, int numberOfCities,
    public SalesmanGenome(int numberOfCities, int[][] travelPrices, int startingCity,
            int fitness) {
        this.travelPrices = travelPrices;
        this.startingCity = startingCity;
        this.numberOfCities = numberOfCities;
        this.fitness = fitness;
        this.genome = randomSalesman();
    }
    public SalesmanGenome(List<Integer> permutationOfCities, int[][] travelPrices, int startingCity, int numberOfCities,
            int fitness) {
        this.travelPrices = travelPrices;
        this.startingCity = startingCity;
        this.numberOfCities = numberOfCities;
        this.fitness = fitness;
        this.genome = permutationOfCities;
    }

    private List<Integer> randomSalesman() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < numberOfCities; i++) {
            if (i != startingCity) {
                result.add(i);
            }
        }
        Collections.shuffle(result);
        return result;
    }
    public int calculateFitness() {
        int fitness = 0;
        int currentCity = startingCity;

        // calculate path cost
        for (int gene : this.genome) {
            fitness += travelPrices[currentCity][gene];
            currentCity = gene;
        }
        fitness += travelPrices[genome.get(numberOfCities - 2)][startingCity];
        return fitness;
    }
    @Override
    public int compareTo(SalesmanGenome other) {
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

    public static void main(String[] args) {
        int numberOfCities = 3;
        int[][] travelPrices = {
            {2, 3, 1},
            {1, 4, 9},
            {5, 2, 1}
        };
        // int numberOfCities = 4;
        // int[][] travelPrices = new int[numberOfCities][numberOfCities];
        // for (int i = 0; i < numberOfCities; i++) {
        //     for (int j = 0; j < numberOfCities; j++) {
        //         travelPrices[i][j] = (int)(Math.random() * 5);
        //     }
        // }
        SalesmanGenome sgm = new SalesmanGenome(numberOfCities, travelPrices, 0, 1);
        System.out.println(sgm.genome);
    }
}

