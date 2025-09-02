class Solution {
    
    int[][] op = {{1,0}, {0,1}, {-1,-1}}; 
    
    public int[] solution(int n) {
        int[] answer = new int[n * (n + 1) / 2]; 
        int[][] map = new int[n][n]; 

        int num = 1, idx = 0; 
        int x = -1, y = 0;

        for (int step = n; step > 0; step--) { 
            for (int i = 0; i < step; i++) {
                x += op[idx][0];
                y += op[idx][1];
                map[x][y] = num++;
            }
            idx = (idx + 1) % 3; 
        }

        //2차원 배열을 1차원 배열로 변경한다.
        int index = 0;
        for (int i = 0; i < n; i++) { 
            for (int j = 0; j <= i; j++) {
                answer[index++] = map[i][j];
            }
        }

        return answer;
    }
}
