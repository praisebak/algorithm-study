import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{

    private int n;
    private int m;
    private List<Node> doorList;
    private Set<Character> keys;
    private char[][] arr;
    private Queue<Node> queue;
    private int[][] visit;

    class Node{
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());


        for (int i = 0; i < T; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            n = Integer.parseInt(sArr[0]);
            m = Integer.parseInt(sArr[1]);
            arr = new char[101][101];
            answer = 0;

            doorList = new ArrayList<>();
            queue = new LinkedList<>();
            visit = new int[n][m];
            keys = new HashSet<>();
            List<Node> startPoint = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                String s = bufferedReader.readLine();
                for (int k = 0; k < m; k++) {
                    arr[j][k] = s.charAt(k);
                    if(arr[j][k] != '*' && (j == 0 || j == n-1 || k == 0 || k== m-1)){
                        if(isDoor(arr[j][k])){
                            doorList.add(new Node(j,k));
                        }else if(arr[j][k] == '$'){
                            answer++;
                            queue.add(new Node(j,k));
                            arr[j][k] = '.';
                        }else if(arr[j][k] == '.'){
                            queue.add(new Node(j,k));
                        }else{
                            keys.add(arr[j][k]);
                            queue.add(new Node(j,k));
                        }
                    }
                }
            }

            String s = bufferedReader.readLine();
            for (int j = 0; j < s.length(); j++) {
                keys.add(s.charAt(j));
            }

            bfs(arr, keys,startPoint);
            System.out.println(answer);
//            return;
        }
    }

    private void bfs(char[][] arr, Set<Character> keys, List<Node> start) {
        int curWeight = 1;
        doorsOpen();

        while (!queue.isEmpty()){
            Node cur = queue.poll();
//            System.out.println(cur.y + "," + cur.x);
            for (int i = 0; i < 4; i++) {
                int nY = dy[i] + cur.y;
                int nX = dx[i] + cur.x;
                if(!isValid(nY,nX)) continue;
                if(0 != visit[nY][nX]) continue;
                visit[nY][nX] = 1;

                char curTile = arr[nY][nX];
                //벽이면 못감
                if(curTile == '*') continue;
                //문서면 처리한다
                if(curTile == '$'){
                    answer++;
                    queue.add(new Node(nY,nX));
                    continue;
                }

                //문인데 키 있으면 연다
                if(isDoor(curTile)) {
                    if (isKeyExists(keys, Character.toLowerCase(curTile))) {
                        queue.add(new Node(nY,nX));
                    } else {
                        doorList.add(new Node(nY, nX));
                    }
                    continue;
                }

                if(isKey(curTile) && !isKeyExists(keys, curTile)){
                    keys.add(curTile);
                    curWeight++;
                    queue.add(new Node(nY,nX));
                    doorsOpen();
                    continue;
                }
                queue.add(new Node(nY,nX));

            }
        }

    }

    private void doorsOpen() {
        int size = doorList.size();
        for (int i = 0; i < size; i++) {
            Node node = doorList.get(i);
            if(isKeyExists(keys,Character.toLowerCase(arr[node.y][node.x]))) {
                visit[node.y][node.x] = 1;
                doorList.remove(i);
                queue.add(new Node(node.y,node.x));
                i--;
                size--;
            }
        }
    }


    int answer = 0;
    private boolean isValid(int nY, int nX) {
        if(nY < 0 || nX < 0 || nY >= n || nX >= m){
            return false;
        }
        return true;
    }

    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};


    private boolean isKeyExists(Set<Character> keys, char door) {
        return keys.contains(door);
    }

    private boolean isKey(char c){
        return Character.isLowerCase(c) && Character.isAlphabetic(c);
    }

    private boolean isDoor(char c){
        return Character.isUpperCase(c) && Character.isAlphabetic(c);
    }
}
