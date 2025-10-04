import java.util.*;

class Solution {
    /**
    기둥은 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나, 또는 다른 기둥 위에 있어야 합니다.
보는 한쪽 끝 부분이 기둥 위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.
    **/
    boolean[][] walls;
    boolean[][] bos;
    int n;
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        
        n++;
        this.n=n;
        walls = new boolean[n][n];
        bos = new boolean[n][n];
        
        for(int i=0;i<build_frame.length;i++){
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            boolean isWall = build_frame[i][2] == 0 ? true : false;
            boolean isAdd = build_frame[i][3] == 1 ? true : false;
            
            if(isAdd){
                if(isWall && isAddableWall(y,x)){
                    walls[y][x] = true;
                }else if(!isWall && isAddableBo(y,x)){
                    bos[y][x] = true;
                }
            }else{
                boolean isValid = true;

                if(isWall && walls[y][x]){
                    walls[y][x] = false;
                    
                    for(int j=0;j<n;j++){
                        for(int k=0;k<n;k++){
                            if(!walls[j][k] && !bos[j][k]) continue;
                            if(walls[j][k]){
                                if(!isAddableWall(j,k)){
                                    isValid = false;
                                    break;
                                }
                            }
                            if(bos[j][k]){
                                if(!isAddableBo(j,k)){
                                    isValid = false;
                                    break;
                                }
                            }    
                        }
                    }
                    
                    if(!isValid){
                        walls[y][x] = true;
                    }
                }
                if(!isWall && bos[y][x]){
                    bos[y][x] = false;
                    
                    for(int j=0;j<n;j++){
                        for(int k=0;k<n;k++){
                            if(!walls[j][k] && !bos[j][k]) continue;
                            if(walls[j][k]){
                                if(!isAddableWall(j,k)){
                                    isValid = false;
                                    break;
                                }
                            }
                            if(bos[j][k]){
                                if(!isAddableBo(j,k)){
                                    isValid = false;
                                    break;
                                }
                            }    
                        }
                    }
                    
                    if(!isValid){
                        bos[y][x] = true;
                    }
                }
            }
        }
        
        List<Result> list = new ArrayList<>();
        
        //일단 확인해보고 틀렸으면 실행하는 그 자체를 확인해보는 방식으로 전략짜기
        for(int x=0;x<n;x++){
            for(int y=0;y<n;y++){
                if(walls[y][x]){
                    list.add(new Result(y,x,0));
                }
                if(bos[y][x]){
                    list.add(new Result(y,x,1));
                }
            }
        }
        
        answer = new int[list.size()][3];
        int idx = 0;
        for(Result result : list){
            answer[idx][0] = result.x;
            answer[idx][1] = result.y;
            answer[idx][2] = result.a;
            idx++;
        }        
        
        return answer;
    }
    
    class Result{
        int y;
        int x;
        int a;
        public Result(int y,int x,int a){
            this.y=y;
            this.x=x;
            this.a=a;
        }
    }
    
    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};
    
    public boolean isAddableBo(int y,int x){
       if (y > 0 && walls[y-1][x]) return true;          // 아래 기둥
    if (y > 0 && x + 1 < n && walls[y-1][x+1]) return true; // 오른쪽 아래 기둥
    if (x > 0 && x + 1 < n && bos[y][x-1] && bos[y][x+1]) return true; // 양쪽 보
        return false;
    }
    
    public boolean isAddableWall(int y,int x){
       if (y == 0) return true;                         // 바닥
if (walls[y-1][x]) return true;                  // 아래 기둥
if (x > 0 && bos[y][x-1]) return true;           // 왼쪽 보 끝
if (x < n && bos[y][x]) return true;    
        return false;
    }
}