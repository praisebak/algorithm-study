/* Java(자바) Hello, World! 예제 */

import java.util.*;

class Pair{
    int y;
    int x;
    Pair(int y,int x){
        this.y = y;
        this.x = x;
    }
}

class Main {
	static public void main(String []args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int m = sc.nextInt();
        int[][] map = new int[r][r];
        List<Pair> personList = new ArrayList<>();
        List<Pair> chickenList = new ArrayList<>();
        for(int i=0;i<r;i++){
            for(int j=0;j<r;j++){
                map[i][j] = sc.nextInt();
                if(map[i][j] == 1){
                    personList.add(new Pair(i,j));
                }
                if(map[i][j] == 2){
                    chickenList.add(new Pair(i,j));
                }
            }
        }
        Solve solve = new Solve(map,r,m,personList,chickenList);
        solve.solve();

    }

}
class Solve{
    int[][] map;
    boolean[] open;
    //상하좌우
    int[] dx = {0,0,-1,1};
    int[] dy = {-1,1,0,0};  

    int N;
    int m;
    int result = Integer.MAX_VALUE;
    List<Pair> personList;
    List<Pair> chickenList;
    int ans = Integer.MAX_VALUE;

    Solve(int[][] map,int N,int m,List<Pair> personList,List<Pair> chickenList){
        this.map = map;
        this.open = new boolean[chickenList.size()];
        this.N = N;
        this.m = m;
        this.personList = personList;
        this.chickenList = chickenList;
    }

    private void dfs(int start,int depth){
        if(depth == this.m){
            int res = 0;
            for(int i=0;i<personList.size();i++){
                int temp = Integer.MAX_VALUE;
                for(int j=0;j<chickenList.size();j++){
 
                    // 어떤 집과 치킨집 중 open한 치킨집의 모든 거리를 비교한다.
                    // 그 중, 최소 거리를 구한다.
                    if (open[j]) {
                        int distance = Math.abs(personList.get(i).x - chickenList.get(j).x)
                                + Math.abs(personList.get(i).y - chickenList.get(j).y);

                        temp = Math.min(temp, distance);
                    }

                }
                res += temp;

            }
            ans = Math.min(ans, res);
            return;
            
        }

        for(int i=start;i<chickenList.size();i++){
            open[i] = true;
            dfs(i+1,depth+1);
            open[i] = false;
        }

    }

    public void solve(){
            dfs(0,0);   
            System.out.println(this.ans);
    }
        

    


}
