import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}
class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String A = bufferedReader.readLine();
        String B = bufferedReader.readLine();
        StringBuilder stringBuilder = new StringBuilder(B);

        while (stringBuilder.length() >= A.length()){
            int len =stringBuilder.length()-1;
            char cur = stringBuilder.charAt(len);
            stringBuilder= stringBuilder.deleteCharAt(len);
            if(cur == 'B'){
                stringBuilder = stringBuilder.reverse();
            }

            if(A.equals(stringBuilder.toString())){
                System.out.println(1);
                return;
            }
        }

        System.out.println(0);
    }
}
