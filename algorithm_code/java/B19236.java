// Online Java Compiler
// Use this editor to write, compile and run your Java code online

class Main {
    public static void main(String[] args) throw IOException{
        
        SolveB19236 solveB19236 = new SolveB19236();
        
        solveB19236.init();
        solveB19236.solve();
        
        
    }
}


class SolveB19236{
    int[][] map = new int[4][4];
    int[][] dir = new int[4][4];
    int N;
    int M;

    void init() throw IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i=0;i<4;i++){
            st = in.readLine();
            for(int j=0;j<4;j++){
                int num = 0;
                int dir = 0;
                num = Integer.parseInt(st.nextToken());
                dir = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                dir[i][j] = dir;
            }
        }
    }
    
    //상 상좌 좌 좌하 하
    int[] dx = {0,-1,-1,-1,0,1,1,1};
    int[] dy = {-1,-1,0,1,1,1,0,-1};
    
    
    int[][] copyIntMap(int[][] map){
        int[][] retMap = new int[4][4];
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                retMap[i][j] = map[i][j];
            }
        }
        return retMap;
    }
    
    int 
    
    void solve(){
        //물고기 이동
        //현재 물고기 번호,map,dir
        while(true){
            dfs(1,copyMap(map),copyMap(dir));
            boolean[][] visit = new boolean[4][4];
            //상어 이동 상어이동은 칸수로 중첩시켜서 해결해야함
            //한번턴에 여러개 물고기 먹을 수 있다
            //영역 빠져나가면 ret
            //몇칸 이동하는지 칸 수
            int prevCount = eatCount;
            moveShark(1);
            if(prevCount == eatCount){
                System.out.println(prevCount);
                return;
            }    
        }
    }
    
    //상어 물고기 먹은 수
    int eatCount = 0;
    int sR = 0;
    int sC = 0 
    int sDir = 0;
    
    boolean dfs(int curFish,int[][] map,int[][] dir){
        
        if(curFish > 16){
            return;
        }
        
        boolean possibleFlag = false;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(curFish != map[i][j]) continue;
                
                //가능하면 진행
                //어차피 360도 도니까 현재 위치는 신경안써도됨
                for(int d=0;d<8;d++){
                    int nY = dy[d] + i;
                    int nX = dx[d] + j;
                    
                    if(possible(nY,nX,map,dir)){
                        possibleFlag = true;
                        changeMapVal(map,nY,i,nX,j);
                        changeMapVal(dir,nY,i,nX,j);
                        
                        dfs(curFish+1,copyMap(map),copyMap(dir));
                        //원상복구
                        changeMapVal(map,nY,i,nX,j);
                        changeMapVal(dir,nY,i,nX,j);
                    }
                }        
            }
        }
        
        if(!possibleFlag){
            dfs(curFish+1,copyMap(map),copyMap(dir));    
        }
        

    }
    
    private void changeMapVal(int[][]map, int nY,int r ,int nX,int c){
        int tmp = map[nY][nX];
        map[nY][nX] = map[r][c];
        map[r][c] = tmp;
    }
    
    private boolean possible(int row,int cal,int[][] map,int[][] dir ){
        //영역 안
        if(row < 0 || row >= 4 || cal < 0 || cal >= 4) return false;
        //다른 물고기가 있는지,상어가 있는 곳은 아닌지
        if(map[row][cal] != 0 && (nY == sR && nX == sC)) return false;
        return true;
    }
    
}
