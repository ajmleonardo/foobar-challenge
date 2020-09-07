import java.math.BigInteger;

public class Solution{
    public static int solution( String x ){

        BigInteger X = new BigInteger(x);

        int counter = 0;
        while( X.compareTo( new BigInteger("1")) != 0 ){

            // Number is odd
            if( X.getLowestSetBit() == 0 ){
                if( X.compareTo(new BigInteger("3")) != 0 && X.add(new BigInteger("1")).shiftRight(1).getLowestSetBit() != 0 ) // Next number is not twice divisible by 2
                    X = X.add(new BigInteger("1"));
                    
                else
                    X = X.subtract(new BigInteger("1"));
                    
            }
                
            //Number if even
            else
                X = X.shiftRight(1);
                
            counter++;
        }


        return counter;

    } 
}