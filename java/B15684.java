import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B15684 {


    public static void main(String[] args) throws IOException {
        SolveB15684 solve = new SolveB15684();
        solve.init();
        solve.solve();
    }

}

class SolveB15684
{

    int N;
    int M;
    int H;

    private static int[][] map;

    int minCount = Integer.MAX_VALUE;

    //origin은 그대로 둬야하고
    void init() {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer s = new StringTokenizer(br.readLine());
            N = Integer.parseInt(s.nextToken());
            M = Integer.parseInt(s.nextToken());
            H = Integer.parseInt(s.nextToken());

            map = new int[H][N];

            for(int i=0; i < M;i++){
                s = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(s.nextToken());
                int b = Integer.parseInt(s.nextToken());
                //b에 true이면 넘어갈 수 있다는 뜻
                map[a-1][b-1] = 1;
            }

        }catch (IOException e){

        }
    }

    //위에서 아래로 내려오는것
    //마킹은 어떻게할것인지
    void solve() throws IOException {

        for(int i=0;i<4;i++){
            dfs(0,0,i);
            if(minCount != Integer.MAX_VALUE){
                if(minCount > 3){
                    throw new IOException();
                }
                System.out.print(minCount);
                return;
            }
        }
        System.out.print(-1);

    }

    private void dfs(int row, int count, int objCount) {
        if(count > objCount) return;
        check(count);
        if(minCount != Integer.MAX_VALUE) return;
        if(row == H){
            return;
        }

        //왼쪽에서 오른쪽으로 가면서 설치
        //맨오른쪽에선 어차피 설치못함
        for (int i=row;i<H;i++){

            for(int j=0;j<N-1;j++){
                //설치
                //제일 왼쪽일때만 따로 두고
                //나머지는 왼쪽과 오른쪽 모두 없다면 설치한다
                if(map[i][j] == 0){
                    if(j == 0){
                        if(map[i][j+1] == 0){
                            map[i][j] = 1;
                            dfs(i,count+1, objCount);
                            map[i][j] = 0;
                        }
                    }else{
                        if(map[i][j-1] == 0 && map[i][j+1] == 0) {
                            map[i][j] = 1;
                            dfs(i,count+1, objCount);
                            map[i][j] = 0;
                        }

                    }
                }

            }
        }


    }

    private void check(int count) {
        for(int curLine=0;curLine<N;curLine++){
            int startLine = curLine;

            for (int i = 0; i < H; i++) {

                //왼쪽
                if(curLine != 0 && map[i][curLine-1] == 1){
                    curLine--;
                    continue;
                }
                //오른쪽
                else if(map[i][curLine] == 1){
                    curLine++;
                }

            }

            if(curLine != startLine){
                return;
            }

        }
        minCount = Math.min(count,minCount);

    }


}
