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
    private int N;
    private int C;
    private int R;

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        String[] sArr = s.split(" ");
        N = Integer.parseInt(sArr[0]);
        R = Integer.parseInt(sArr[1]);
        C = Integer.parseInt(sArr[2]);

        divideAndConquer(0,0, (int) Math.pow(2,N));
        System.out.println(count);
    }

    int count = 0;

    //세션 쿠키 차이가 이 책에있는지 확인해보자
    private void divideAndConquer(int r, int c,int curSize) {
        if(curSize == 1) return;

        if(R < r + curSize / 2 && C < c + curSize / 2){
            divideAndConquer(r,c,curSize/2);
        }else if(R < r + curSize/2 && C  >= c + curSize / 2){
            count += (curSize * curSize) / 4;
            divideAndConquer(r, c + curSize /2,curSize / 2);
        }else if(R >= r + curSize / 2 && C < c + curSize/2){
            count += (curSize * curSize / 4) * 2;
            divideAndConquer(r + curSize/2, c,curSize / 2);
        }else{
            count += (curSize * curSize / 4) * 3;
            divideAndConquer(r + curSize/2, c + curSize /2,curSize / 2);
        }





    }

    private void count(int r, int c, int size) {
        for(int i=r;i<r + size;i++){
            for (int j = c; j <c + size ; j++) {
                if(i == R && j == C){
                    System.out.println(count);
                    System.exit(0);
                }
                count++;
            }
        }

    }
}
