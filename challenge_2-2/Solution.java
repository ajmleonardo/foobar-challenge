import java.lang.Math;

public class Solution{
    public static int[] solution(int[] pegs){

        class LinearEquation{
            int a;  // Linear coefficient
            int K;  // Constant

            LinearEquation(){
                this.a = 1;
                this.K = 0;
            }

            LinearEquation findRadiusOf(int n, int[] x){

                if( n == 0 )
                    return new LinearEquation();

                LinearEquation linearEquationBelow = findRadiusOf(n-1, x);
                this.a = -1*linearEquationBelow.a;
                this.K = ( -1 * linearEquationBelow.K ) + ( x[n] - x[n-1] ) ;


                return this;

            }

            // Recursive function to solve the linear system
            boolean checkSum( int[] x, double r0 ){

                if(r0 == 0) // Something went wrong
                    return false;

                double radiusOf_nm1 = r0;
                double radiusOf_n = 0;
                for( int n = 1; n < x.length; n++ ){
                    radiusOf_n = -1*radiusOf_nm1 + x[n] - x[n-1];

                    if(radiusOf_n < 0 || radiusOf_nm1 < 0)
                        return false;

                    if(x[n-1] + radiusOf_nm1 + radiusOf_n != x[n])
                        return false;
                    radiusOf_nm1 = radiusOf_n;

                }

                // Checking if last radius is half as long as the first
                // with some approximation criterion
                if(Math.abs(radiusOf_n-r0/2) > 0.0001 )
                    return false;

                return true;

            }

        }


        LinearEquation firstEquation = new LinearEquation();
        firstEquation.findRadiusOf(pegs.length-1, pegs);


        int[] answer = new int[2];
        answer[0] = Math.abs(2*firstEquation.K );
        answer[1] = Math.abs(2*firstEquation.a - 1);

        // Simplifying fraction
        int gcd = 1;
        for(int i = 1; i <= answer[0] && i <= answer[1]; i++)
            if(answer[0]%i==0 && answer[1]%i==0)
                gcd = i;
        answer[0] = answer[0]/gcd;
        answer[1] = answer[1]/gcd;

        double r0 = (double) answer[0] / (double) answer[1];

        // Checking the distance sum of radii
        if(!firstEquation.checkSum(pegs, r0) ){
            answer[0] = -1;
            answer[1] = -1;
        }

        return answer;
    }
}