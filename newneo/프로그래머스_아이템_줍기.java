package newneo;

import com.sun.nio.sctp.SctpSocketOption;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    int[][] border;
    int[][] map;
    boolean[][] inside;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        border = new int[101][101];
        map = new int[101][101];
        inside = new boolean[101][101];

        for (int i = 0; i < rectangle.length; i++) {
            int startX = rectangle[i][0] * 2;
            int startY = rectangle[i][1] * 2;
            int endX = rectangle[i][2] * 2;
            int endY = rectangle[i][3] * 2;
            int curIdx = i+1;

            for (int j = startX; j <= endX; j++) {
                if(border[startY][j] != curIdx){
                    border[startY][j] = curIdx;
                    map[startY][j]++;
                }
                if(border[j][endY] != curIdx){
                    border[endY][j] = curIdx;
                    map[endY][j]++;
                }
            }

            for (int k = startY; k <= endY; k++) {
                if(border[k][startX] != curIdx){
                    border[k][startX] = curIdx;
                    map[k][startX]++;
                }
                if(border[k][endX] != curIdx){
                    border[k][endX] = curIdx;
                    map[k][endX]++;
                }
            }

            for(int j=startX+1;j<endX;j++){
                for(int k=startY+1; k<= endY-1;k++){
                    inside[k][j] = true;
                }
            }
        }

        for (int i = 30; i >= 0; i--) {
            for (int j = 0; j <= 30; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println("------");

//         for (int i = 30; i >= 0; i--) {
//             for (int j = 0; j <= 20; j++) {
//                 System.out.print(border[i][j]);
//             }
//             System.out.println();
//         }


        bfs(characterX*2,characterY*2,itemX*2,itemY*2);
        // System.out.println(minLength/2);

        int answer = minLength/2;
        return answer;
    }

    int[] dy = {0,0,-1,1};
    int[] dx = {-1,1,0,0};
    int minLength = Integer.MAX_VALUE;

    class Node{
        int y;
        int x;
        int depth;
        int val;

        public Node(int y, int x, int depth,int val) {
            this.y = y;
            this.x = x;
            this.depth = depth;
            this.val = val;
        }
    }
    private void bfs(int characterX, int characterY, int itemX, int itemY) {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visit = new boolean[101][101];
        queue.add(new Node(characterY,characterX,0,border[characterY][characterX]));

        visit[characterY][characterX] = true;

        while (!queue.isEmpty()){
            Node node = queue.poll();

            // System.out.println(node.y +"," + node.x + " " + node.val + " depth =" + node.depth);
            if(itemX == node.x && itemY == node.y){
                minLength = Math.min(minLength,node.depth);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nY = dy[i] + node.y;
                int nX = dx[i] + node.x;
                if(nY < 0 || nX < 0 || nY >= 101 || nX >= 101) continue;
                if(visit[nY][nX]) continue;
                if(inside[nY][nX]) continue;
                if(border[nY][nX] == 0) continue;

                if(map[nY][nX] >= 1){
                    queue.add(new Node(nY,nX,node.depth+1,border[nY][nX]));
                    visit[nY][nX] = true;
                }
                //1로 이동가능한 경우 같은값이면서 map이 1이건 2이건 상관없이 가능

            }
        }

    }
}