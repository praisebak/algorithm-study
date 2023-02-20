
class TmpCode{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] people = new int[N];
        int[][] love = new int[N][4];

        int idx = 0;
        people[idx] = sc.nextInt();
        for(int i=0;i<4;i++){
            love[idx][i] = sc.nextInt();
        }
        Solve solve = new Solve(N,people,love)
        solve.solve();
    }
}

class Solve{
    //상하좌우
    int[] dx = {0,0,-1,1};
    int[] dy = {-1,1,0,0};
    int N;
    int[] people;
    int[][] love;
    int[][] seats;

    Solve(int N,int[] people,int[][] love){
        this.N = N;
        this.people = people;
        this.love = love;
        this.seats = new int[N][N];
    }

    public void solve(){

        for(int i : people){
            findSeat(i);    
        }
        
    }

    private void findSeat(int curChild){
        //1.가장 친한애들이 인접한 좌석 선택
        int loveCount = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                
                loveCount = Math.max(getLoveCount(i,j,curChild),loveCount);
            }
        }

        //2.1이 여러개면 인접한 좌석이 많이 비어있는 곳 선택

        //3.2도 만족되면 행,열의 번호가 가장 낮은 것 선택


    }

    private int getLoveCount(int r,int c,int curChild){
        int result = 0;
        for(i=0;i<4;i++){
            int nextY = r + dy[i];
            int nextX = c + dx[i];
            if(!isRangeOk(nextY,nextX)) continue;
            for(int j=0;j<4;j++){
                if(love[curChild][j] == seats[nextY][nextX]){
                    result++;
                }
            }
        }
        return result;
    }


}