class Solution 
    {
        boolean[][][][] visit = new boolean[11][11][11][11];
        int count = 0;
        class Pair
        {
            int row;
            int cal;
            Pair(int row,int cal)
            {
                this.row = row;
                this.cal = cal;
            }
            Boolean isValidMap()
            {
                if(row == 11 || cal == 11 || row == -1 || cal == -1)
                {
                    return false;
                }
                return true;
            }
            void move(Character ch)
            {
                int tmpRow = row;
                int tmpCal = cal;

                if(ch == 'U')
                {
                    tmpRow++;
                }
                if(ch == 'D')
                {
                    tmpRow--;
                }
                if(ch == 'R')
                {
                    tmpCal++;
                }
                if(ch == 'L')
                {
                    tmpCal--;
                }
                Pair tmpMap = new Pair(tmpRow,tmpCal);
                if(tmpMap.isValidMap())
                {
                    if(!visit[row][cal][tmpRow][tmpCal] && !visit[tmpRow][tmpCal][row][cal])
                    {
                        count++;
                    }
                    visit[row][cal][tmpRow][tmpCal] = true;
                    visit[tmpRow][tmpCal][row][cal] = true;

                    row = tmpRow;
                    cal = tmpCal;
                }
            }
            
        }



        public int solution(String dirs) 
        {
            int answer = 0;
            Pair pair = new Pair(5,5);
            answer = getMoveCountByDirs(dirs,pair,0);
            return answer;
        }




        private int getMoveCountByDirs(String dirs,Pair map,int idx) 
        {
            if(idx == dirs.length())
            {
                return count;
            }
            map.move(dirs.charAt(idx));
            return getMoveCountByDirs(dirs, map,idx+1);

        }
        
            public static void main(String[] args) {
                Solution solution = new Solution();
                solution.solution("LULLLLLLU");
            }
    }