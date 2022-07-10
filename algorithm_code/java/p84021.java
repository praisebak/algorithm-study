import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Pos implements Comparable<Pos>
{
    int x,y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Pos [x=" + x + ", y=" + y + "]";
    }

    @Override
    public int compareTo(Pos obj) {
        if(this.y > obj.y) return 1;
        if(this.y < obj.y) return -1;
        if(this.x > obj.x) return 1;
        if(this.x < obj.x) return -1;
        return 0;
    }

}
class Solution
{
int[] dx = {-1, 0, 1, 0};
int[] dy = {0, 1, 0, -1};

public int solution(int[][] game_board, int[][] table) 
{
    int answer = -1;

    boolean[][] visitedTable = new boolean[table.length][table.length];
    boolean[][] visitedBoard = new boolean[game_board.length][game_board.length];
    List<List<Pos>> boardList = new ArrayList<>();
    List<List<Pos>> tableList = new ArrayList<>(); 
    
    for (int i = 0; i < table.length; i++) {
        for (int j = 0; j < table.length; j++) {
            
            if (table[i][j] == 1 && !visitedTable[i][j]){
                bfs(i, j, visitedTable, table, 1, tableList);
            }

            if (game_board[i][j] == 0 && !visitedBoard[i][j]){
                bfs(i, j, visitedBoard, game_board, 0, boardList);
            }

        }
    }


    answer = findBlock(boardList, tableList);
    return answer;
}

public int findBlock(List<List<Pos>> board, List<List<Pos>> table){
    int size = 0;
    int tableLen = table.size();
    int boardLen = board.size();
    boolean[] visitedBoard = new boolean[boardLen];
    for (int i = 0; i < tableLen; i++){
        List<Pos> tablePoints = table.get(i);
        for (int j = 0; j < boardLen; j++){
            List<Pos> boardPoints = board.get(j);

            if (tablePoints.size() == boardPoints.size() && !visitedBoard[j]){ //좌표 개수 같을때
                if(isRotate(boardPoints, tablePoints)){ //회전해서 맞는지 확인
                    size += tablePoints.size();
                    visitedBoard[j] = true;
                    break;
                }
            }
        }

    }

    return size;
}

public boolean isRotate(List<Pos> board, List<Pos> table){
    boolean isCollect = false;

    board.sort((o1, o2) -> {
        return o1.x > o2.x?1 : o1.x < o2.x?-1 : Integer.compare(o1.y, o2.y);
    });

    for (int i = 0; i < 4; i++){ //table퍼즐 0, 90, 180, 270도 회전

        table.sort((o1, o2) -> {
            return o1.x > o2.x?1 : o1.x < o2.x?-1 : Integer.compare(o1.y, o2.y);
        });
        int nearZeroX = table.get(0).x;
        int nearZeroY = table.get(0).y;

        for (int j = 0; j < table.size(); j++){
            table.get(j).x -= nearZeroX;
            table.get(j).y -= nearZeroY;
        }


        boolean isCollectPoint = true;
        for (int j = 0; j < board.size(); j++){ //좌표 비교
            Pos boardPoint = board.get(j);
            Pos tablePoint = table.get(j);

            if (boardPoint.x != tablePoint.x || boardPoint.y != tablePoint.y){
                isCollectPoint = false;
                break;
            }
        }

        if (isCollectPoint){
            isCollect = true;
            break;
        } else{ //90도 회전 : x,y -> y, -x
            for(int j = 0; j < table.size(); j++){
                int temp = table.get(j).x;
                table.get(j).x = table.get(j).y;
                table.get(j).y = -temp;
            }
        }
    }
    return isCollect;

}

public void bfs(int currentX, int currentY, boolean[][] visited, int[][] graph,
int blockOrEmpty, List<List<Pos>> list){    
    Queue<Pos> queue = new LinkedList<>();
    visited[currentX][currentY] = true;
    queue.add(new Pos(currentX, currentY));
    List<Pos> subList = new ArrayList<>();
    subList.add(new Pos(0, 0));
    while (!queue.isEmpty()){
        Pos pop = queue.remove();
        int popX = pop.x;
        int popY = pop.y;

        for (int i = 0; i < 4; i++){
            int nextX = popX + dx[i];
            int nextY = popY + dy[i];
            if (nextX < 0 || nextX >= graph.length || nextY < 0 || nextY >= graph.length){
                continue;
            }
            if (!visited[nextX][nextY] && graph[nextX][nextY] == blockOrEmpty){

                visited[nextX][nextY] = true;
                queue.add(new Pos(nextX, nextY));
                subList.add(new Pos(nextX-currentX, nextY-currentY));
            }
        }
    }
    list.add(subList);
}

public static void main(String[] args) {
    Solution solution = new Solution();
    solution.solution(new int[][] {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}}, new int[][] {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}});
    // solution.solution(new int[][] {{0,0,0},{1,1,0},{1,1,1}}, new int[][] {{1,1,1},{1,0,0},{0,0,0}});
    // [[0,0,0],[1,1,0],[1,1,1]]
    // {{1,1,1},{1,0,0},{0,0,0}}
}
}

/*
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

class Pos implements Comparable<Pos>
{
    int x,y;

    public Pos(int y, int x) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Pos [x=" + x + ", y=" + y + "]";
    }

    @Override
    public int compareTo(Pos obj) {
        if(this.y > obj.y) return 1;
        if(this.y < obj.y) return -1;
        if(this.x > obj.x) return 1;
        if(this.x < obj.x) return -1;
        return 0;
    }

}

class Solution 
{
    int answer = 0;
    int N;
    int M;
    public int solution(int[][] game_board, int[][] table) {
        N = table.length;
        M = N;

        ArrayList<ArrayList<ArrayList<Pos>>> tableList = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<Pos>>> boardList = new ArrayList<>();
        insertConnectList(tableList,table);
        reverseGameBoard(game_board);
        insert2rdList(boardList, game_board, new boolean[N][M]);
        sortLists(boardList,tableList);
        //값을 0으로 시작하도록 설정
        setRotaTableZero(tableList);

        List<List<Integer>> idxOrder = new ArrayList<>();
        //new
        for (int i = 0; i < tableList.size(); i++) 
        {
            boolean visit[] = new boolean[tableList.size()];
            visit[i] = true;
            idxOrder.add(getPermu(visit,i + " ",0));
        }

        // compareBoardAndTable(tableList,boardList,new boolean[tableList.get(0).size()],0,idxOrder);
        compareBoardAndTable(tableList, boardList,new boolean[tableList.get(0).size()], new boolean[boardList.get(0).size()], 0, 0);
        return answer;
    }

    private List<Integer> getPermu(boolean[] visit,String result,int depth) 
    {
        if(depth == visit.length)
        {
            List<Integer> resultArr = new ArrayList<>();
            for(String str : result.split(" "))
                resultArr.add(Integer.parseInt(str));
            return resultArr;
        }

        for(int i=0;i<visit.length;i++)
        {
            if(!visit[i])
            {
                visit[i] = true;
                getPermu(visit,result + i + " ",depth+1);
                visit[i] = false;
            }
        }
        return null;
    }

    private void setRotaTableZero(ArrayList<ArrayList<ArrayList<Pos>>> tableList) 
    {
        for(ArrayList<ArrayList<Pos>> tList : tableList)
        {
            for(ArrayList<Pos> pList : tList)
            {
                int prevX = pList.get(0).x;
                int prevY = pList.get(0).y;
                for(Pos p : pList)
                {
                    int pX = p.x;
                    int pY = p.y;
                    p.y = pY - prevY;
                    p.x = pX - prevX;
                }
            }
        }
    }

    private void sortLists(ArrayList<ArrayList<ArrayList<Pos>>> boardList,
            ArrayList<ArrayList<ArrayList<Pos>>> tableList) 
    {
        for(ArrayList<ArrayList<Pos>> bList : boardList)
        {
            for(ArrayList<Pos> pList : bList)
                Collections.sort(pList);
        }
        for(ArrayList<ArrayList<Pos>> tList : tableList)
        {
            for(ArrayList<Pos> pList : tList)
                Collections.sort(pList);
        }
        


    }

    private void reverseGameBoard(int[][] game_board) 
    {
        for (int i = 0; i < game_board.length; i++) {
            for (int j = 0; j < game_board.length; j++) {
                if(game_board[i][j] == 0) game_board[i][j] = 1;
                else game_board[i][j] = 0;
            }
        }
    }

    private void compareBoardAndTable(ArrayList<ArrayList<ArrayList<Pos>>> tableList,
    ArrayList<ArrayList<ArrayList<Pos>>> boardList,boolean[] visit,int sum,List<List<Integer>> idxOrder) 
    {
        //찾아야하는 도형(game board)
        for(int k=0;k<boardList.get(0).size();k++)
        {
            ArrayList<Pos> curBoard = boardList.get(0).get(k);
            //System.out.println("찾아야하는 BOARD");
            print(curBoard);

            //System.out.println("대상 TABLE");
            for(int m=0;m<4;m++)
            {                    
                boolean flag = false;
                for(int i=0;i<visit.length;i++)
                {
                    //visit size =  table의 도형 수
                    if(visit[i]) continue;
                    ArrayList<Pos> curTable = tableList.get(m).get(i);
                    print(curTable);
                    if(curTable.size() != curBoard.size())
                    {
                        continue;
                    }
                    if(compare(curTable,curBoard))
                    {
                        //System.out.println("찾았다노");
                        visit[i] = true;
                        flag = true;
                        answer += curTable.size();
                        break;
                    }
                }
                if(flag)
                    break;

            }
        }
    }

    private void compareBoardAndTable(ArrayList<ArrayList<ArrayList<Pos>>> tableList,
    ArrayList<ArrayList<ArrayList<Pos>>> boardList,boolean[] visitTable,boolean[] visitBoard,int sum,int depth) 
    {
        answer = Math.max(answer,sum);
        if(depth == boardList.size())
        {
            return;
        }
        //찾아야하는 도형(game board)
        for(int k=0;k<visitBoard.length;k++)
        {
            if(visitBoard[k]) continue;
            ArrayList<Pos> curBoard = boardList.get(0).get(k);

            System.out.println("대상 TABLE");
            print(curBoard);
                              
            for(int i=0;i<visitTable.length;i++)
            {
                for(int m=0;m<4;m++)
                {     
                    //visit size =  table의 도형 수
                    if(visitTable[i])
                        continue;
                    ArrayList<Pos> curTable = tableList.get(m).get(i);
                    // print(curTable);
                    if(curTable.size() != curBoard.size())
                        continue;
                    System.out.print((k+1) + "번째 BOARD : " + (i+1) + "번째 TABLE : ");
                    if(compare(curTable,curBoard))
                    {
                        System.out.print("통과");
                        visitTable[i] = true;
                        visitBoard[k] = true;
                        // compareBoardAndTable(tableList, boardList, visitTable, visitBoard, sum + curTable.size(),depth+1);
                        visitTable[i] = false;
                        visitBoard[k] = false;
                        System.out.println("\n통과 테이블");
                        print(curTable);
                    }
                    System.out.println();
                }
            }
        }
    }

    private void print(ArrayList<Pos> curTable)
    {
        for(Pos p : curTable)
        {
            System.out.println(p.toString());
        }
        System.out.println();
    }

    private boolean compare(ArrayList<Pos> tableList, ArrayList<Pos> boardList) 
    {  
        for(int i=0;i<tableList.size();i++)
        {
            Pos curTablePos = tableList.get(i);
            Pos curBoardPos = boardList.get(i);
            
            if(!(curTablePos.y == curBoardPos.y && curTablePos.x == curBoardPos.x))
            {
                return false;
            }
        }        
        return true;
    }

    private void insertConnectList(ArrayList<ArrayList<ArrayList<Pos>>> tableList,int[][] table) 
    {
        boolean[][] visit = new boolean[N][M];
        for(int m=0;m<4;m++)
        {
            tableList.add(new ArrayList<>());
        }
        insertListWithRota(tableList, table, visit);

    }

    private void insertListWithRota(ArrayList<ArrayList<ArrayList<Pos>>> tableList, int[][] table,boolean[][] visit) 
    {
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<M;j++)
            {
                if(table[i][j] == 1 && !visit[i][j] && isValid(i, j))
                {
                    ArrayList<Pos> puzzle = new ArrayList<>();
                    connectListByDFS(puzzle, table, visit, i, j, 0, 0);
                    //갱신됐다면
                    if(puzzle.size() != 0)
                    {
                        tableList.get(0).add(puzzle);
                        for(int m=1;m<4;m++)
                        {
                            ArrayList<Pos> rotaPuzzle = new ArrayList<>();
                            setOtherRota(rotaPuzzle,puzzle,m);
                            tableList.get(m).add(rotaPuzzle);

                        }

                    }
                }
            }
        }


    }

    private void setOtherRota(ArrayList<Pos> rotaPuzzle, ArrayList<Pos> originPuzzle,int m2) 
    {
        if(m2 == 1)
        {
            for(Pos origin : originPuzzle)
            {
                rotaPuzzle.add(new Pos(origin.x,N-origin.y-1));
            }
        }
        else if(m2 == 2)
        {
            for(Pos origin : originPuzzle)
            {
                rotaPuzzle.add(new Pos(N-1-origin.y,M-origin.x-1));
            }
        }
        else if(m2 == 3)
        {
            for(Pos origin : originPuzzle)
            {
                rotaPuzzle.add(new Pos(origin.x,N-origin.y-1));
            }
        }
    }

    private void insert2rdList(ArrayList<ArrayList<ArrayList<Pos>>> tableList, int[][] table, boolean[][] visit) {
        ArrayList<ArrayList<Pos>> posList = new ArrayList<>();
        tableList.add(posList);
        ArrayList<Pos> curRecList = new ArrayList<>();
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<M;j++)
            {
                if(table[i][j] == 1 && !visit[i][j] && isValid(i, j))
                {
                    connectListByDFS(curRecList,table,visit,i,j,0,0);
                    if(curRecList.size() == 0) continue;
                    posList.add(curRecList);
                    curRecList = new ArrayList<>();
                }
            }
        }
        if(curRecList.size() != 0) posList.add(curRecList);
    }

    private void connectListByDFS(ArrayList<Pos> puzzle, int[][] table, boolean[][] visit,int curY,int curX,int yDiff,int xDiff) 
    {
        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};

        visit[curY][curX] = true;
        puzzle.add(new Pos(yDiff,xDiff));

        for(int i=0;i<4;i++)
        {
            int nY = dy[i] + curY;
            int nX = dx[i] + curX;
            if(isValid(nY,nX) && !visit[nY][nX] && table[nY][nX] == 1)
            {
                connectListByDFS(puzzle, table, visit, nY, nX,yDiff + dy[i] ,xDiff + dx[i]);
            }
        }
    }

    private boolean isValid(int nextY, int nextX) 
    {
        if(nextY < 0 || nextX < 0 || nextY >= N || nextX >= M)
            return false;
        return true;
    }

    

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new int[][] {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}}, new int[][] {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}});
        // solution.solution(new int[][] {{0,0,0},{1,1,0},{1,1,1}}, new int[][] {{1,1,1},{1,0,0},{0,0,0}});
        // [[0,0,0],[1,1,0],[1,1,1]]
        // {{1,1,1},{1,0,0},{0,0,0}}
    }
}
*/