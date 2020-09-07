public class Solution{
    public static int solution( int[] l ){
        
        int lTriples = 0;

        for(int k = l.length-1; k >=0; k--)
            for(int j = k-1; j >=0; j--)
                if( l[k]%l[j]==0 )
                    for(int i = j-1; i >=0; i--)
                        if( l[j]%l[i]==0 )
                            lTriples++;
        
        return lTriples;

    }
}