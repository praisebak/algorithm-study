import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    List<Boolean> isPlusIdxList = new ArrayList<>();
    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s =bufferedReader.readLine();

        int N = s.length();

        //-만날때마다
        for(int i=0;i<N;i++){
            if(s.charAt(i) == '+'){
                isPlusIdxList.add(true);
            } else if(s.charAt(i) == '-'){
                isPlusIdxList.add(false);
            }
        }

        StringTokenizer stringTokenizer = new StringTokenizer(s, "-");

        int curNum = 0;
        int idx = 0;
        while (stringTokenizer.hasMoreElements()){
            int val = calVal(stringTokenizer.nextToken());
            if(idx == 0){
                curNum = val;
                idx++;
            } else{
                curNum += Math.min(val,val * -1);
            }


        }
        System.out.println(curNum);

    }

    private int calVal(String s) {
        StringTokenizer stringTokenizer = new StringTokenizer(s, "+");
        int curNum = 0;
        while (stringTokenizer.hasMoreTokens()){
            curNum += Integer.parseInt(stringTokenizer.nextToken());
        }
        return curNum;
    }
}