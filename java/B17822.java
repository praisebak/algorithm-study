import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class B17822 {
    public static void main(String[] args) throws IOException{
        SolveB17822 solveB17822 = new SolveB17822();
        solveB17822.init();
        solveB17822.solve();
    }
}

class SolveB17822{

    int N;
    int M;
    int T;
    int[] xList;
    int[] dList;
    int[] kList;

    HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        for(int i=1;i<=N;i++){
            map.put(i,new ArrayList<>());
        }

        for(int i=1;i<=N;i++){
            ArrayList<Integer> list = map.get(i);
            st = new StringTokenizer(bufferedReader.readLine());
            for(int j=0;j<M;j++){
                list.add(Integer.parseInt(st.nextToken()));
            }
        }

        xList = new int[T];
        dList = new int[T];
        kList = new int[T];


        for(int i=0;i<T;i++){
            st = new StringTokenizer(bufferedReader.readLine());
            xList[i] = Integer.parseInt(st.nextToken());
            dList[i] = Integer.parseInt(st.nextToken());
            kList[i] = Integer.parseInt(st.nextToken());
        }
    }
    void solve(){
        for(int i=0;i<T;i++) {
            int x = xList[i];
            int d = dList[i];
            int k = kList[i];

            for (int plateNum = x; plateNum <= N; plateNum += x) {
                ArrayList<Integer> curPlate = map.get(plateNum);
                //k번 회전
                for (int rota = 0; rota < k; rota++) {
                    //시계방향
                    if (d == 0) {
                        int val = curPlate.get(curPlate.size()-1);
                        curPlate.remove(curPlate.size() - 1);
                        curPlate.add(0,val);

                    }
                    //반시계
                    else {
                        int val = curPlate.get(0);
                        curPlate.remove(0);
                        curPlate.add(val);
                    }
                }
            }

            boolean[][] visit = new boolean[N + 1][M];

            //인접한 수가 같은 것들 모두 찾는다
            for (int plateNum = 1; plateNum <= N; plateNum++) {
                ArrayList<Integer> curPlate = map.get(plateNum);

                for (int cur = 0; cur < curPlate.size(); cur++) {
                    int left = cur - 1;
                    int right = cur + 1;
                    if (left < 0) {
                        left = curPlate.size() - 1;
                    }
                    if (right >= curPlate.size()) {
                        right = 0;
                    }
                    if(curPlate.get(cur) == 0) continue;

                    if (curPlate.get(left).equals(curPlate.get(cur))) {
                        visit[plateNum][left] = true;
                        visit[plateNum][cur] = true;
                    }

                    if (curPlate.get(right).equals(curPlate.get(cur))) {
                        visit[plateNum][right] = true;
                        visit[plateNum][cur] = true;
                    }

                    int down = plateNum + 1;

                    if (down <= N) {
                        if (map.get(down).get(cur).equals(curPlate.get(cur))) {
                            visit[down][cur] = true;
                            visit[plateNum][cur] = true;
                        }
                    }

                    int up = plateNum -1;

                    if (up > 0) {
                        if (map.get(up).get(cur).equals(curPlate.get(cur))) {
                            visit[up][cur] = true;
                            visit[plateNum][cur] = true;
                        }
                    }
                }
            }

            boolean flag = false;
            double average = 0;
            double size = 0;

            for(int idx=1;idx<=N;idx++){
                ArrayList<Integer> list = map.get(idx);
                for (int j = 0; j < list.size(); j++) {
                    if(visit[idx][j]){
                        list.set(j ,0);
                        flag = true;
                    }
                }
            }

            for(int idx=1;idx<=N;idx++){
                ArrayList<Integer> list = map.get(idx);
                for (int j = 0; j < list.size(); j++) {
                    if(list.get(j) != 0){
                        average += list.get(j);
                        size++;
                    }
                }
            }

            if(size == 0) continue;

            //없으면 평균구하고
            if(!flag){
                double aver = average / size;
                for(int key : map.keySet()){
                    List<Integer> curPlate = map.get(key);
                    for (int j = 0; j < curPlate.size(); j++) {
                        if(curPlate.get(j) == 0) continue;

                        if(curPlate.get(j) > aver) {
                            curPlate.set(j, curPlate.get(j) - 1);
                        }
                        else if(curPlate.get(j) < aver){
                            curPlate.set(j, curPlate.get(j) + 1);
                        }
                    }
                }
            }


        }

        int sum = 0;
        for(int i=1;i<=N;i++){
            ArrayList<Integer> list = map.get(i);
            for (Integer val:list) {
                //System.out.print(val + " ");
                sum += val;
            }
            //System.out.println();
        }
        System.out.println(sum);
    }





}
