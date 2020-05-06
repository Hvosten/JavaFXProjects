package SimpleCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

public class MathEvaluator {
    public BigDecimal calculate(String expression) {
        List<BigDecimal> numberParts = new LinkedList<>();
        List<String> operators = new LinkedList<>();
        int index = 0;

        for(int i=0 ; i<expression.length() ; ++i){
            if(expression.substring(i,i+1).matches("[\\*\\+\\-\\/]")){
                if(expression.substring(index,i).matches(".*\\d.*")){
                    numberParts.add(convertSingleNumber(expression.substring(index,i)));
                    operators.add(expression.substring(i,i+1));
                    index = i+1;
                }
            }
            if(i == expression.length()-1 && index<=i)
                numberParts.add(convertSingleNumber(expression.substring(index)));
            if(expression.startsWith("(", i)){
                int endBracketIndex = findClosingBracketIndex(i,expression);
                String subExpression = expression.substring(i+1,endBracketIndex);
                BigDecimal subResult = expression.charAt(index)=='-' ? calculate(subExpression).multiply(BigDecimal.valueOf(-1.0)) : calculate(subExpression);
                numberParts.add(subResult);
                index = endBracketIndex+2;
                i = endBracketIndex+1;
                if(expression.length()-1 > endBracketIndex){
                    operators.add(expression.substring(endBracketIndex+1,endBracketIndex+2));
                }
            }
        }

        while(operators.contains("*") || operators.contains("/")){
            int multIndex = operators.indexOf("*");
            int divIndex = operators.indexOf("/");
            if(divIndex == -1 || (multIndex < divIndex && multIndex > -1)){
                BigDecimal product = numberParts.get(multIndex).multiply(numberParts.get(multIndex+1));
                numberParts.remove(multIndex);
                numberParts.set(multIndex,product);
                operators.remove(multIndex);
            }
            else{
                BigDecimal quotient = numberParts.get(divIndex).divide(numberParts.get(divIndex+1),10, RoundingMode.HALF_UP);
                numberParts.remove(divIndex);
                numberParts.set(divIndex,quotient);
                operators.remove(divIndex);
            }
        }
        BigDecimal result = numberParts.get(0);
        for(int i=1 ; i<numberParts.size() ; ++i){
            if(operators.get(i - 1).equals("+"))
                result = result.add(numberParts.get(i));
            else
                result = result.subtract(numberParts.get(i));
        }
        return result;
    }

    private int findClosingBracketIndex(int start, String s) {
        int howManyOpenBrackets = 1;
        for(int i=start+1 ; i<s.length() ; ++i){
            if(s.charAt(i)=='(')
                howManyOpenBrackets++;
            if(s.charAt(i)==')')
                howManyOpenBrackets--;
            if(howManyOpenBrackets == 0)
                return i;
        }
        return 0;
    }

    private BigDecimal convertSingleNumber(String expression) {
        return new BigDecimal(expression);
    }
}

