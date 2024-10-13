import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();


    }
}
class Solve{

    class Water{
        int start;
        int end;
        public Water(int start,int end){
            this.start = start;
            this.end = end;
        }
    }
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int L = Integer.parseInt(sArr[1]);
        Water[] arr = new Water[N];

        for (int i = 0; i < N; i++) {
            sArr = bufferedReader.readLine().split(" ");
            int start = Integer.parseInt(sArr[0]);
            int end = Integer.parseInt(sArr[1]);
            arr[i] = new Water(start,end-1);
        }

        int cur = 0;

        Arrays.sort(arr,(o1,o2) -> {
            if(o1.start == o2.start) return o2.end - o1.end;
            return o1.start - o2.start;
        });


        cur = arr[0].start;
        int answer = 0;
        for (int i = 0; i < N; i++) {
            Water water = arr[i];
            if(water.start >= cur){
                cur = water.start;
            }
            while (water.end >= cur){
                cur += L;
                answer++;
            }
        }
        System.out.println(answer);

    }
}
