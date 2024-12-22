import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}
class Solve{
    private int[][] map = new int[9][9];

    //n번째 row에 1-9까지 값
    boolean[][] rowVisit = new boolean[9][10];
    boolean[][] colVisit = new boolean[9][10];
    boolean[][][] areaVisit = new boolean[3][3][10];
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            String sArr = bufferedReader.readLine();
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(sArr.charAt(j) + "");
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                rowVisit[i][map[i][j]] = true;
                colVisit[j][map[i][j]] = true;
                areaVisit[i/3][j/3][map[i][j]] = true;
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(map[i][j] == 0){
                    dfs(i,j);
                    System.out.println(result.get(0));
                    return;
                }
            }
        }
    }
    StringBuilder stringBuilder = new StringBuilder();

    private void dfs(int r,int c) {
        if(r == 8 && c == 9){
            addString();
            return;
        }
        if(c >= 9){
            c = 0;
            r++;
        }

        int i = r;
        int j = c;
        if(map[r][c] == 0){
            for (int k = 1; k <= 9; k++) {
                if(rowVisit[i][k]){
                    continue;
                }
                if(colVisit[j][k]){
                    continue;
                }
                if(areaVisit[i / 3][j/3][k]){
                    continue;
                }
                int prev = map[i][j];
                map[i][j] = k;
                rowVisit[i][k] = true;
                colVisit[j][k] = true;
                areaVisit[i / 3][j/3][k] = true;
                dfs(i,j+1);
                map[i][j] = prev;
                rowVisit[i][k] = false;
                colVisit[j][k] = false;
                areaVisit[i / 3][j/3][k] = false;
            }
        }else{
            dfs(i,j+1);
        }
    }

    List<String> result = new ArrayList<>();

    private void addString() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                stringBuilder.append(map[i][j] + "");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder.toString());
        System.exit(0);
    }
}
