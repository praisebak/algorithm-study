import javax.swing.plaf.IconUIResource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.XMLFormatter;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();

    }
}

class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(bufferedReader.readLine());
        //cur sum
        int cur = 64;
        //가장 작은 막대만 남기고 나머지 막대는 cur로 저장한다
        int minStick = 64;
        int depth = 0;


        if(x == 64) {
            System.out.println(1);
            return;
        }
        while (x != cur){
            //처음에 더하는 수
             if(cur > x){
                 depth++;
                 minStick /= 2;

                 if(cur - minStick >= x){
                     depth--;
                     cur -= minStick;
                 }
             }
             if(cur == x){
                 System.out.println(depth+1);
                 return;
             }
        }


    }
}
