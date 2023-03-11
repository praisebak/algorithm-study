import java.util.*;

class Pair implements Comparable<Pair>{ 

  int r;
  int c;
  int val;
  Pair(int r,int c,int val){
    this.r = r; 
    this.c = c;
    this.val = val;
  }

  @Override
  public int compareTo(Pair o) {
    return o.val - this.val;
  } 

}


class Solve {
  int N;
  int M;
  int K;
  int[][] map;
  //우하좌상
  int[] dx = {1,0,-1,0};
  int[] dy = {0,1,0,-1};
  Scanner sc = new Scanner(System.in);
  ArrayList<Pair> cctvList = new ArrayList<>();
  int min = Integer.MAX_VALUE;

  public void start() {
      init();
      solve();
      // System.out.println("답");
      System.out.println(min);
  }

  private void init() {
    N = sc.nextInt();
    M = sc.nextInt();
    map = new int[N][M];
    for(int i=0;i<N;i++){
      for (int j = 0; j < M; j++) {
        map[i][j] = sc.nextInt();
        if(map[i][j] != 0 && map[i][j] != 6){
          cctvList.add(new Pair(i,j,map[i][j]));
        }
      }
    }
    cctvList.sort(null);
  }

  private void solve() {
    dfs(0);
  }

private void dfs(int idx) {
  if(idx == cctvList.size()){
    calMinSpace();
    return;
  }

  for(int rota=0;rota<4;rota++){
    int[][] tmpMap = new int[N][M];
    clone(tmpMap);
    checkMap(idx,rota);
    // System.out.println(i);
    dfs(idx+1);
    map = tmpMap;
  }
}

private void calMinSpace() {
  int count = 0;
  for(int i=0;i<N;i++){
    for(int j=0;j<M;j++){
      if(map[i][j] == 0) count++;
      // System.out.print(map[i][j] + " ");
    }
    // System.out.println();
  }
  // System.out.println(count);
  this.min = Math.min(count, this.min);  
}

private void checkMap(int idx,int rota) {
  Pair p = cctvList.get(idx);
  ArrayList<Pair> moveList = new ArrayList<>();

  for(int i=0;i<4;i++){
    moveList.add(new Pair(dy[rota],dx[rota],0));
    if(p.val == 1){
      break;
    }else if(p.val == 2){
      rota = (rota + 2) % 4;
      if(i == 1){
        break;
      }
    }else if(p.val == 3) {
      rota = (rota + 1) % 4;
      if(i == 1){
        break;
      }
    }else{
      rota = (rota + 1) % 4;  
      if(i == 2 && p.val == 4){
        break;
      }
    }
  }

  for(Pair move : moveList){
    int nY = p.r + move.r;
    int nX = p.c + move.c;
    while(isRangeValid(nY,nX)){
      if(map[nY][nX] == 0) 
        map[nY][nX] = 7;
      nY += move.r;
      nX += move.c;
    }
  }

}

private boolean isRangeValid(int nY, int nX) {
  if(nY != N && nY != -1 && nX != M && nX != -1 && map[nY][nX] != 6) return true;
	return false;
}

private void clone(int[][] tmpMap) {
  for(int i=0;i<N;i++){
    for (int j = 0; j < M; j++) {
      tmpMap[i][j] = map[i][j];
    }
  }
}


}

class Main{
  public static void main(String[] args) {
    Solve solve = new Solve();
    solve.start();
  }
}



