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
        String s = bufferedReader.readLine();
        String obj = bufferedReader.readLine();

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <s.length(); i++) {
            char cur = s.charAt(i);
            stringBuilder.append(cur);
            if(stringBuilder.length() >= obj.length()){
                boolean flag = false;
                for (int j = 1; j <= obj.length(); j++) {
                    if(stringBuilder.charAt(stringBuilder.length()-j) != obj.charAt(obj.length()-j)){
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    stringBuilder.delete(stringBuilder.length()-obj.length(),stringBuilder.length());
                }
            }
        }

        System.out.println(stringBuilder.length() == 0 ? "FRULA" : stringBuilder.toString());


    }


}