package print;

import gilbertmoore.GilbertMoore;
import gilbertmoore.ParseToMap;

import java.math.RoundingMode;

public class Print {
    private static final String RESET = "\u001B[0m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";

    public static void printMoore(){
        GilbertMoore moore = new GilbertMoore();
//        ParseToMap.print();
        System.out.println(BLUE + "q: " + RESET + PURPLE + moore.getQ());
        System.out.println();
        System.out.println(BLUE + "sigma: " + RESET + PURPLE + moore.getSigma());
        System.out.println();
        System.out.println(BLUE + "l: " + RESET + PURPLE + moore.getCodeLength());
        System.out.println();
        System.out.println(BLUE + "Binary code: " + RESET + PURPLE + moore.getCodeWord() + RESET);
        System.out.println("-------------------------------------------------------------");
        System.out.println(BLUE + "Average code length: " + RESET + PURPLE + moore.getAverageCodeLength());
        System.out.println();
        System.out.println(BLUE + "H: " + RESET + PURPLE + moore.getEntropy());
        System.out.println();
        System.out.println(BLUE + "r: " + RESET + PURPLE + moore.getRedundancy());
    }

}
