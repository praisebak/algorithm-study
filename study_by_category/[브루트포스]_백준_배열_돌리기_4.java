//https://www.acmicpc.net/problem/17406
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
    int N;
    int M;
    int K;
    int[][] arr;
    boolean[] operVisit;
    String[] operArr;
    int answer = Integer.MAX_VALUE;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);
        K = Integer.parseInt(sArr[2]);
        arr = new int[N][M];
        operArr = new String[K];
        operVisit = new boolean[K];

        for(int i=0;i<N;i++){
            sArr =bufferedReader.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(sArr[j]);
            }
        }

        for(int i=0;i<K;i++){
            operArr[i] = bufferedReader.readLine();
        }

        perm(0,"");
        System.out.println(answer);

    }
    public void cal(String s){
        String[] sArr = s.split(" ");

        int[][] copyArr = new int[N][M];

        for(int i=0;i<N;i++){
            for (int j = 0; j < M; j++) {
                copyArr[i][j] = arr[i][j];
            }
        }
//        System.out.println("cur array and cur operandStr = " + s);
//        printArr();

        for(int i=0;i<sArr.length;i++){
            int operIdx = Integer.parseInt(sArr[i]);
            String oper = operArr[operIdx];
            rotate(oper);
        }

        for(int i=0;i<N;i++){
            int sum = 0;
            for (int j = 0; j < M; j++) {
                sum += arr[i][j];
            }
            answer = Math.min(sum,answer);
        }

        arr = copyArr;
    }




    private void rotate(String oper) {
        String[] sArr = oper.split(" ");
        int r = Integer.parseInt(sArr[0])-1;
        int c = Integer.parseInt(sArr[1])-1;
        int s = Integer.parseInt(sArr[2]);

        int rowStart = r - s;
        int rowEnd = r + s;
        int colStart = c - s;
        int colEnd = c + s;

        int prev = arr[rowStart][colStart];


        while (rowStart != r && colStart != c){
            //여기에서 회전시킨다
            int row = rowStart;
            int col = colStart;

            //오른쪽으로 회전
            while(true){
                int tmp = arr[row][col];
                arr[row][col] = prev;
                prev = tmp;
                if(col == colEnd) break;

                col++;
            }
            row++;

            //아래로 회전
            while (true){
                int tmp = arr[row][col];
                arr[row][col] = prev;
                prev = tmp;
                if(row == rowEnd) break;
                row++;
            }

            col--;
            while (true){
                int tmp = arr[row][col];
                arr[row][col] = prev;
                prev = tmp;
                if(col == colStart) break;
                col--;
            }
            row--;
            while (true){
                int tmp = arr[row][col];
                arr[row][col] = prev;
                prev = tmp;
                if(row == rowStart) break;
                row--;
            }

            rowStart++;
            rowEnd--;
            colStart++;
            colEnd--;
        }
    }


    private void perm(int depth,String s) {
        if(depth == K){
            cal(s);
            return;
        }

        for(int i=0;i<K;i++){
            if(operVisit[i]) continue;
            operVisit[i] = true;
            perm(depth+1,s + i + " ");
            operVisit[i] = false;
        }



    }
}
