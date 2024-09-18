import javax.naming.ldap.UnsolicitedNotificationEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }

}
class Solve{

    HashMap<String,Integer> map = new HashMap<>();
    StringBuilder stringBuilder = new StringBuilder();
    public void solve() throws IOException{
        BufferedReader  bufferedReader =new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < T; i++) {
            map = new HashMap<>();
            int N = Integer.parseInt(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                String[] sArr = bufferedReader.readLine().split(" ");
                map.put(sArr[1],map.getOrDefault(sArr[1],0)+1);
            }

            long result = 1;
            for(String s : map.keySet()){
                result *= map.get(s) +1;
            }
            result--;
            stringBuilder.append(result + "\n");
        }
        System.out.println(stringBuilder);


    }

}
