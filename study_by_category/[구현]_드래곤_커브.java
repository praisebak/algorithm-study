import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}
class Solve{
    boolean[][] visit;
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        visit = new boolean[102][102];

        for (int i = 0; i < N; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            int r = Integer.parseInt(sArr[1]);
            int c = Integer.parseInt(sArr[0]);
            int d = Integer.parseInt(sArr[2]);
            int g = Integer.parseInt(sArr[3]);

            visit[r][c] = true;
            makeDragon(r,c,d,g,new ArrayList<>(),0);
        }

        int answer = 0;
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                if(visit[i][j] && visit[i+1][j] && visit[i][j+1] && visit[i+1][j+1]){
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
    //우상좌하
    //우는상이되고 상이 좌가되고 좌가 하가되고 하가 우가 된다
    int[] dy = {0,-1,0,1};
    int[] dx = {1,0,-1,0};

    private void makeDragon(int r, int c, int d, int g, List<Integer> prevList,int curG) {
        if(curG > g) {
            return;
        }
        int prevSize = prevList.size();
        //0세대니까
        if(prevSize == 0){
            int nY = r + dy[d];
            int nX = c + dx[d];
            visit[nY][nX] = true;

            prevList.add(d);
            makeDragon(nY,nX,d,g,prevList,curG+1);
        }else{
            prevSize--;
            //새 드래곤커브
            List<Integer> newList = new ArrayList();
            while (prevSize != -1){
                int prevCurve = prevList.get(prevSize);
                int rotatedCurve = rotate(prevCurve);
                r += dy[rotatedCurve];
                c += dx[rotatedCurve];
                visit[r][c] = true;
                newList.add(rotatedCurve);
                prevSize--;
            }
            prevList.addAll(newList);
            makeDragon(r,c,d,g,prevList,curG+1);
        }

    }

    private int rotate(int prevCurve) {
        return (prevCurve+1) % 4;
    }

}
