import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();

    }
}
class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        String numAStr = sArr[0];
        String numBStr = sArr[1];
        int strASize = numAStr.length()-1;
        int strBSize = numBStr.length()-1;
        int minSize = Math.min(strASize,strBSize);
        StringBuilder stringBuilder = new StringBuilder();
        int adder = 0;

        while (!(strASize == -1 && strBSize == -1)){
            char a = '0';
            char b = '0';
            if(strASize != -1){
                a = numAStr.charAt(strASize--);
            }
            if(strBSize != -1){
                b = numBStr.charAt(strBSize--);
            }

            int numA = a - '0';
            int numB = b - '0';
            System.out.println(numA + "," + numB);

            int result = (numA + numB + adder);
            int remain = result % 10;
            stringBuilder.insert(0,remain);
            adder = 0;
            if(result >= 10){
                adder = 1;
            }
        }

        if(adder == 1){
            stringBuilder.insert(0,1);
        }
        System.out.println(stringBuilder);



    }
}
