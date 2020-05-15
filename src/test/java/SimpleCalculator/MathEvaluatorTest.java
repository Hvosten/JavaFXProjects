package SimpleCalculator;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.*;

public class MathEvaluatorTest {
    MathEvaluator eval;
    double delta;
    @Before
    public void setUp() throws Exception {
        eval = new MathEvaluator();
        delta = 0.001;
    }

    private void assertEquality(String exp, double expectedVale){
        assertThat(eval.calculate(exp).doubleValue(),is(closeTo(expectedVale,delta)));
    }

    @Test
    public void onlyOneNumber(){
        assertEquality("1",1);
        assertEquality("2.75",2.75);
        assertEquality("-2",-2);
        assertEquality("(-4)",-4);
        assertEquality("-(4)",-4);
        assertEquality( "-(-4)",4);
        assertEquality( "-(-4)",4);
        assertEquality("-(-(-4))",-4);
   }

    @Test
    public void oneOperation(){
        assertEquality("1+3",4);
        assertEquality("2.5+7.5",10);
        assertEquality("8-9.5",-1.5);
        assertEquality("(-4)-(-8)",4);
        assertEquality("4*2",8);
        assertEquality( "(-2.5)*4",-10);
        assertEquality( "5/4",1.25);
        assertEquality("8/(-0.25)",-32);
    }

    @Test
    public void chainOfOperations(){
        assertEquality("1+2+3+4+5+6+7+8",36);
        assertEquality("2.5-7.5-4-(-16)-7",0);
        assertEquality("1*2*3*4*5*6*7*8",40320);
        assertEquality("100/25/8/0.25",2);
        assertEquality("10+(-5)-6+8.5-5",2.5);
        assertEquality("5*8/10*6*2/96",0.5);
    }
    @Test
    public void rightOrderOfOperators(){
        assertEquality( "3+5*2",13);
        assertEquality("2/2+8-3*2",3);
        assertEquality("6*3+(-3)/0.5+(-0.2)*10",10);
    }

    @Test
    public void bracketFirst(){
        assertEquality( "(3+5)*2",16);
        assertEquality("(2+3)/2+8-3*2",4.5);
        assertEquality("(2+3)/2+8-3*2",4.5);
        assertEquality("45-5*4/(2+4/2)",40);
        assertEquality("438-(1248-1230/10)/15",363);
    }

    @Test
    public void decimalPointExamples(){
        assertEquality("10/3",3.33333);
        assertEquality("(20*3+10+5*2)/(5*10+4*0.5-(3.5+6.5))",1.9047);
    }

    @Test
    public void assertExceptionWhenIncorrectInput(){
        try {
            assertEquality("10/3*",3.33333);
            fail( "This above is incorrect equation" );
        } catch (Exception expectedException) {
        }
    }
}
