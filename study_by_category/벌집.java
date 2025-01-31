import java.io.*;
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); 
        int count = 1;
        if(N == 1){
            System.out.println(count);
            return;
        }
        int wall = 1;
        int i = 0;
        while(true){
            i += 6;
            wall += i;
            if( wall >= N){
                System.out.println(count+1);
                return;
            }
            count++;
        }
        
    }
}
