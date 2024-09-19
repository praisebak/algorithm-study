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


//1이면 6과 6으로 나눈 나머지


class Solve{
    int a;
    int b;
    int c;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        a = Integer.parseInt(sArr[0]);
        b = Integer.parseInt(sArr[1]);
        c = Integer.parseInt(sArr[2]);
        System.out.println(divide(b));
    }

    HashMap<Integer,Long> map = new HashMap<>();
    private long divide(int b) {
        if(b == 0){
            return 1;
        }
        if(b == 1){
            return a % c;
        }
        Long left = map.get(b/2);
        if(left == null){
            left = divide(b / 2) ;
            map.put( (b/2),left);
        }

        Long right = map.get(b - b/2);
        if(right == null){
            right = divide(b - b / 2);
            map.put((b - b/2),right);
        }

        return (left * right) % c;
    }

}
