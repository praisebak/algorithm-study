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

    private int[][] map;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        map = new int[N+1][N+1];
        for (int i = 0; i < N; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(sArr[j]);
            }
        }
        divConquer(0,0,N);
        System.out.println(white);
        System.out.println(black);
    }

    int white = 0;
    int black = 0;
    private void divConquer(int i, int j, int size) {
        if(add(i,j,size)){
            return;
        }
        int mid = size / 2;
        divConquer(i,j,mid);
        divConquer(i+mid,j,mid);
        divConquer(i,j+mid,mid);
        divConquer(i+mid,j+mid,mid);
    }

    private boolean add(int i, int j, int size) {
        return check(i,j,size);
    }

    private boolean check(int i, int j, int size) {
        int oneCount = 0;
        int zeroCount = 0;



        for (int r = i; r < i+size; r++) {
            for (int c = j; c < j+size; c++) {
                if(map[r][c] == 1){
                    oneCount++;
                }else{
                    zeroCount++;
                }
            }
        }

        if(oneCount == size * size){
            black++;
            return true;
        }else if(zeroCount == size * size){
            white++;
            return true;
        }
        return false;
    }
}
