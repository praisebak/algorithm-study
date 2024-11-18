import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();

    }
}

class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        HashSet<String> set = new HashSet<>();
        HashMap<String,Integer> map = new HashMap<>();
        int N = Integer.parseInt(bufferedReader.readLine());
        String[] sArr = new String[N];
        int maxLen = 0;
        int startIdx = 0;
        String maxOriginStr = "";
        String maxNextStr = "";

        for (int i = 0; i < N; i++) {
            sArr[i] =bufferedReader.readLine();
            StringBuilder stringBuilder = new StringBuilder();
            String curStr = sArr[i];

            for (int j = 0; j < curStr.length(); j++) {
                stringBuilder.append(curStr.charAt(j));
                String string = stringBuilder.toString();

                if(set.contains(string)){
                    if(maxLen > string.length()) continue;

                    if(maxLen == string.length()){
                        if(map.get(string) != null && map.get(string) < startIdx){
                            maxLen = string.length();
                            maxNextStr = curStr;
                            startIdx = map.get(string);
                            maxOriginStr = sArr[startIdx];
                            continue;
                        }else{
                            continue;
                        }
                    }

                    maxLen = string.length();
                    maxOriginStr = sArr[map.get(string)];
                    maxNextStr = curStr;
                    startIdx = map.get(string);
                    continue;
                }

                map.put(string,i);
                set.add(string);
            }
        }

        if(maxLen == 0){
            System.out.println(sArr[0]);
            System.out.println(sArr[1]);
            return;
        }
        System.out.println(maxOriginStr);
        System.out.println(maxNextStr);

    }
}
