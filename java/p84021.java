
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.Collection;
    import java.util.Collections;
    import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//

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
        int answer = 0;

        
        public int solution(int[][] game_board, int[][] table) 
        {
    
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
            compareBoardAndTable(tableList, boardList, new boolean[tableList.size()], 0);
            // answer = findBlock(boardList, tableList);
            return answer;
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


        private void compareBoardAndTable(List<List<Pos>> tableList,
        List<List<Pos>> boardList,boolean[] visit,int sum) 
        {
            //찾아야하는 도형(game board)
            for(int k=0;k<boardList.size();k++)
            {
                List<Pos> curBoard = boardList.get(k);
                for(int i=0;i<tableList.size();i++)
                {
                    //visit size =  table의 도형 수
                    if(visit[i]) continue;
                    List<Pos> curTable = tableList.get(i);
                    if(curTable.size() != curBoard.size())
                    {
                        continue;
                    }
                    if(compare(curTable,curBoard))
                    {
                        visit[i] = true;
                        answer += curTable.size();
                        print(curTable);
                        break;
                    }
                }

            }
        }

        private void print(List<Pos> curTable) 
        {
            for(Pos p : curTable)
            {
                System.out.println(p.toString());
            }
            System.out.println();
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
    

            System.out.println("");
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


        private boolean compare(List<Pos> tableList, List<Pos> boardList) 
        {  
            boolean isCollect = false;
            boardList.sort((o1, o2) -> {
                return o1.x > o2.x?1 : o1.x < o2.x?-1 : Integer.compare(o1.y, o2.y);
            });


            for(int m=0;m<4;m++)
            {
                tableList.sort((o1, o2) -> {
                    return o1.x > o2.x?1 : o1.x < o2.x?-1 : Integer.compare(o1.y, o2.y);
                });
                boolean isCollectPos = true;
                int nearZeroX = tableList.get(0).x;
                int nearZeroY = tableList.get(0).y;

                System.out.println("****START****");
                System.out.println("BOARD");
                print(boardList);
                System.out.println("TABLE");
                print(tableList);
                System.out.println("****END****");

                for (int j = 0; j < tableList.size(); j++){
                    tableList.get(j).x -= nearZeroX;
                    tableList.get(j).y -= nearZeroY;
                }

                for(int i=0;i<tableList.size();i++)
                {
                    Pos curTablePos = tableList.get(i);
                    Pos curBoardPos = boardList.get(i);
                    if(curTablePos.y != curBoardPos.y || curTablePos.x != curBoardPos.x)
                    {
                        isCollectPos = false;
                        break;
                    }
                }        
                if(isCollectPos) 
                {
                    isCollect = true;
                    break;
                }
                
                for(Pos curTable : tableList)
                {
                    int tmp = curTable.x;
                    curTable.x = curTable.y;
                    curTable.y = -tmp;
                }
            }
            return isCollect;
        }
       
    }