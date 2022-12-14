package gilbertmoore;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class GilbertMoore {

    private final List<BigDecimal> q = new ArrayList<>();
    private final List<BigDecimal> sigma = new ArrayList<>();
    private final List<Integer> codeWordLength = new ArrayList<>();
    private BigDecimal averageCodeLength = new BigDecimal(0);
    private BigDecimal entropy = new BigDecimal(0);
    private BigDecimal redundancy;
    private final List<String> binaryCode = new ArrayList<>();
    private final List<Double> listValue = new ArrayList<>(ParseToMap.getMap().values());

    public List<BigDecimal> getQ(){
        calculationQ();
        return q;
    }

    public List<BigDecimal> getSigma(){
        calculationSigma();
        return sigma;
    }

    public List<Integer> getCodeLength(){
        return codeWordLength;
    }

    public BigDecimal getAverageCodeLength(){
        averageCodeLength();
        return averageCodeLength;
    }

    public List<String> getCodeWord(){
        codeWord();
        return binaryCode;
    }

    public BigDecimal getEntropy(){
        entropy();
        return entropy;
    }

    public BigDecimal getRedundancy(){
        redundancy();
        return redundancy;
    }

    private void calculationQ(){
        q.add(BigDecimal.valueOf(0));
        for (int i = 1; i < listValue.size(); i++){
            q.add(q.get(i - 1).add(BigDecimal.valueOf(listValue.get(i - 1))));
        }
    }

    private void calculationSigma(){
        for (int j = 0; j < listValue.size(); j++){
            sigma.add(j, q.get(j).add(BigDecimal.valueOf(listValue.get(j)/2)));
//            codeWordLength.add((int) (-Math.log(listValue.get(j))/Math.log(2) + 2));
            codeWordLength.add((int) Math.ceil(-Math.log(listValue.get(j))/Math.log(2) + 1));
        }
    }

    private void averageCodeLength(){
        for (int l = 0; l < listValue.size(); l++){
            averageCodeLength = averageCodeLength
                    .add(BigDecimal.valueOf(codeWordLength.get(l))
                            .multiply(BigDecimal.valueOf(listValue.get(l))));
        }
    }

    private String binaryCode(BigDecimal sigma, int length){
        StringBuilder binaryCode = new StringBuilder();
        BigDecimal num;
        num = sigma.remainder(BigDecimal.ONE).setScale(5, RoundingMode.HALF_UP);

        for (int l = 0; l < length; l++){
            num = num.multiply(new BigDecimal(2));
            BigDecimal intNum = num.setScale(0, RoundingMode.DOWN);
            binaryCode.append(intNum);
            num = num.remainder(BigDecimal.ONE).setScale(2, RoundingMode.HALF_UP);
        }
        return String.valueOf(binaryCode);
    }

    private void codeWord(){
        int i = 0;
        for (BigDecimal d: sigma){
            binaryCode.add(binaryCode(d, codeWordLength.get(i)));
            i++;
        }
    }

    private void entropy(){
        for (Double aDouble : listValue) {
            entropy = entropy.add(BigDecimal.valueOf(aDouble * Math.log(aDouble) / Math.log(2)));
        }
        entropy = entropy.multiply(BigDecimal.valueOf(-1));
    }

    private void redundancy(){
        redundancy = averageCodeLength.subtract(entropy.setScale(4, RoundingMode.CEILING));
    }

}

