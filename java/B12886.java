package java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;


class B12886{
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    class Pair{
        int first;
        int second;

        public Pair(int first,int second) {
            this.first = first;
            this.second = second;
        }
    }


    boolean isSame = false;
    Pair[] selectCase = new Pair[3];

    HashMap<String,Boolean> visit = new HashMap<>();

    void solve() {

        try {
            String line = bufferedReader.readLine();
            int[] arr = new int[3];
            for(int i=0;i<3;i++){
                arr[i] = Integer.parseInt(line.split(" ")[i]);
            }

            if(arr[0] == arr[1] && arr[1] == arr[2]){
                //System.out.println(1);
                return;
            }


            selectCase[0] = new Pair(0,1);
            selectCase[1] = new Pair(0,2);
            selectCase[2] = new Pair(1,2);


            for(int i=0;i<3;i++){
                int[] initArr = new int[3];

                for(int j=0;j<3;j++){
                    initArr[j] = arr[j];
                }

                //case와
                dfs(i,initArr);
                if(isSame) return;
            }

            //System.out.println(0);
        } catch (IOException e) {
        }


    }

    private void dfs(int select, int[] arr) {
        Pair pair = selectCase[select];

        //3이면
        int Y = arr[pair.first];
        int X = arr[pair.second];

        if(Y < X){
            int tmp = Y;
            Y = X;
            X = tmp;
        }

        int newX = X + X;
        int newY = Y - X;
        if(newY < 0){
            return;
        }

        int[] newArr = {arr[0],arr[1],arr[2]};
        newArr[pair.first] = newY;
        newArr[pair.second] = newX;

        if(newArr[0] == newArr[1] && newArr[1] == newArr[2]){
            isSame = true;
            //System.out.println(1);
            return;
        }

        newArr = Arrays.stream(newArr).sorted().toArray();
        String key = newArr[0] + " " + newArr[1] + " " + newArr[2];

        if(visit.get(key) == null){
            visit.put(key,true);
            for(int i=0;i<3;i++){
                int[] copyArr  = {newArr[0],newArr[1],newArr[2]};
                dfs(i,copyArr);
                if(isSame)
                    return;
            }
        }
    }
}

