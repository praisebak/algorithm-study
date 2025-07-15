import java.util.*;
import java.io.*;
class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    public void solve() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        //BCDAF
        //현재와 이전걸 비교한다
        //A와 D를 비교했을때 A가 더 작으므로
        //BCD를 엎는다 -> DCB가 되고 거기에 A를 붙이며 뒤집는다
        //= ABCD
        //그냥 맨 앞에 A붙이는거아님? 
        //아니면 그냥 뒤에 붙인다

        String answer = s.charAt(0) + "";

        for(int i=1;i<s.length();i++){
            if(s.charAt(i) > answer.charAt(i-1)){
                answer = s.charAt(i) + answer;
            }else{
                answer += s.charAt(i);
            }            
        }

        StringBuilder result = new StringBuilder(answer);
        
        result.reverse();
        

        System.out.println(result.toString());
    }
}
