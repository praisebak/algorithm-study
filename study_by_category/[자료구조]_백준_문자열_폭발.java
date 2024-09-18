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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String boom =  br.readLine();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<s.length();i++){
            char cur = s.charAt(i);
            stringBuilder.append(cur);
            while (stringBuilder.length() >= boom.length()){
                if(stringBuilder.substring(stringBuilder.length() - boom.length()).equals(boom)){
                    stringBuilder.delete(stringBuilder.length()-boom.length(),stringBuilder.length());
                }else{
                    break;
                }
            }
        }
        if(stringBuilder.length() != 0){
            System.out.println(stringBuilder);
        }else{
            System.out.println("FRULA");
        }



    }
}
