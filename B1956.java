/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.*;
public class B1956
{
	public static void main(String[] args) {
		int V = 3;
		int E = 4;
        Scanner sc = new Scanner(System.in);

        V = sc.nextInt();
        E = sc.nextInt();

		int[][] map = new int[V+1][V+1];
		int MAX = Integer.MAX_VALUE;    
		int result = Integer.MAX_VALUE;
        
		for(int i=1;i<=V;i++){
		    for(int j=1;j<=V;j++){
		        if(i != j){
		            map[i][j] = MAX;        
		        }
		    }
		}
		
        for(int i=0;i<E;i++){
            int start = sc.nextInt();
            int next = sc.nextInt();
            int cost = sc.nextInt();
            map[start][next] = cost;
        }
    
        //거쳐가는
        for(int k=1;k<=V;k++){
            //시작
            for(int i=1;i<=V;i++){
                //끝
                for(int j=1;j<=V;j++){
                    if(i == j) continue;
                    
                    if(map[i][j] > map[i][k] + map[k][j] ){
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }
        
        
        for(int i=1;i<=V;i++){
            for(int j=1;j<=V;j++){
                if(i == j) continue;
                if(map[i][j] != MAX && map[j][i] != MAX){
                    result = Math.min(result,map[i][j] +map[j][i]);
                }
                
                
            }
        }
          
        if(result == Integer.MAX_VALUE){
            result = -1;
        }
          
        System.out.println(result); 
        
		
		
		
	}
}
