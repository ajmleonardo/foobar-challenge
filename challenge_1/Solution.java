public class Solution{
    public static String solution(String x){

        char[] alphArray = {'a','b','c','d','e','f','g','h','i','j','k','l','m',
        'n','o','p','q','r','s','t','u','v','w','x','y','z'};

        
        for(int i = 0; i < x.length(); i++)
            for(int j = 0; j < alphArray.length; j++)
                if(x.charAt(i) == alphArray[j]){
                    x = x.substring(0, i) + alphArray[alphArray.length-1-j] + x.substring(i+1);
                    break;
                }

        return x;
    }
}