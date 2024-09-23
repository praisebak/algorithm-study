import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();

    }
}

class Solve{
    private int N;
    private int M;
    class Num implements Comparable<Num>{
        int count;
        int idx;
        int num;

        public Num(int count, int idx, int num) {
            this.count = count;
            this.idx = idx;
            this.num = num;
        }

        @Override
        public int compareTo(Num o) {
            if(count == o.count){
                return idx - o.idx;
            }
            return o.count - count;
        }
    }

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int M = Integer.parseInt(sArr[1]);
        int[] arr = new int[N+1];
        sArr = bufferedReader.readLine().split(" ");
        Map<Integer,Integer> map = new LinkedHashMap<>();
        int count = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(sArr[i]);
            map.put(arr[i],map.getOrDefault(arr[i],0)+1);
        }

        int idx = 0;
        List<Num> numList = new ArrayList<>();
        for(Integer num : map.keySet()){
            Num num1 = new Num(map.get(num),idx++,num);
            numList.add(num1);
        }
        Collections.sort(numList);
        for (int i = 0; i < numList.size(); i++) {
            int duplicateCount = numList.get(i).count;
            int val = numList.get(i).num;
            for (int j = 0; j < duplicateCount; j++) {
                System.out.print(val + " ");
           }
        }

        System.out.println();




    }

    private boolean check(int next, int curLocation) {
        if(curLocation <= next && curLocation + M-1 >= next) return true;
        return false;
    }
}
