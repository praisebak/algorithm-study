class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] map = new int[board.length+1][board[0].length+1];
        
        int n = board.length;
        int m = board[0].length;
        
        for(int i=0;i<skill.length;i++){
            boolean isAttack = skill[i][0] == 1;
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];
            
            if(isAttack) degree = -degree; // 공격이면 음수

            map[r1][c1] += degree;
            map[r1][c2+1] += -degree;
            map[r2+1][c1] += -degree;
            map[r2+1][c2+1] += degree;
        }
        
        for(int i=1;i<=n;i++){
            for(int j=0;j<=m;j++){
                map[i][j] = map[i][j] + map[i-1][j];
            }
        }
        
        for(int i=0;i<=n;i++){
            for(int j=1;j<=m;j++){
                map[i][j] += map[i][j-1];
            }
        }

        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(board[i][j] + map[i][j] > 0) answer++;
            }
        }
        
        
        return answer;
    }
}