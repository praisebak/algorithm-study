import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    int[][] map;
    int[][] pillarMap;
    int[][] nonPillarMap;
    int n;
    public int[][] solution(int n, int[][] build_frame) {
        pillarMap = new int[n][n+1];
        this.n = n;
        nonPillarMap = new int[n][n];

        for (int[] arr : pillarMap) {
            Arrays.fill(arr, 2);
        }

        for (int[] arr : nonPillarMap) {
            Arrays.fill(arr, 2);
        }

        for (int i = 0; i < build_frame.length; i++) {
            int x = build_frame[i][0], y = build_frame[i][1];
            int row = y;
            int cal = x;

            boolean isPillar = build_frame[i][2] == 0;
            boolean install = build_frame[i][3] == 1;

            if (isPillar) {
                map = pillarMap;
            } else {
                map = nonPillarMap;
            }

            if (install) {
                if (installCheck(cal, row, isPillar)) {
                    map[row][cal] = build_frame[i][2];
                }
            }

            // 기둥 삭제후 규칙 유지되는가
            else {
                int prev = map[row][cal];
                map[row][cal] = -1;
                boolean flag = false;
                // 왼 오 상 하 확인

                if(!checkAll(pillarMap,true))
                {
                    flag = true;
                }

                if(!checkAll(nonPillarMap,false))
                {
                    flag = true;
                }

                if (flag) {
                    map[row][cal] = prev;

                }
                
            }

        }

        int[][] answer = {};
        answer = getAns();
        
        return answer;
    }

    
    private boolean checkAll(int[][] map,boolean isPillar) {

        for(int i=0;i < map.length;i++)
        {
            for(int j=0;j<map[0].length;j++)
            {
                if(isPillar && map[i][j] == 0)
                {
                    if(!installCheck(j, i,isPillar))
                    {
                        return false;
                    }
                }else if(map[i][j] == 1 && !isPillar)
                {
                    if(!installCheck(j, i,isPillar))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    private boolean installCheck(int x, int y, boolean isPillar) {

        if (isPillar) {
            // 기둥이 바닥 위
            if (y == 0) {
                return true;
            }
            // 아래가 기둥
            if (checkRange(x, y - 1,pillarMap) && pillarMap[y - 1][x] == 0) {
                return true;
            }

            // 아마 둘다 가능할 것임
            if (checkRange(x - 1, y,nonPillarMap) && nonPillarMap[y][x - 1] == 1) {
                return true;
            }

            if (checkRange(x, y,nonPillarMap) && nonPillarMap[y][x] == 1) {
                return true;
            }

        } 
        else {
            // 한쪽 끝에 기둥이 있다 == 아래에 기둥이 있거나 설치한 곳r오른쪽 아래에 기둥이 있음
            // if (checkRange(x, y - 1,pillarMap) && pillarMap[y - 1][x] == 0) {
            //     return true;
            // }
            if (checkRange(x + 1, y-1,pillarMap) && pillarMap[y - 1][x + 1] == 0) {
                return true;
            }

            // 왼쪽과 오른쪽에 보가 있어야함
            if(checkRange(x - 1, y,nonPillarMap) && checkRange(x + 1, y,nonPillarMap))
            {
                if (nonPillarMap[y][x - 1] == 1 && nonPillarMap[y][x + 1] == 1 ) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean `checkRange(int x, int y,int[][] map) {
        if (x >= map[0].length || x < 0 || y >= map.length || y < 0) {
            return false;
        }

        return true;
    }

    public int[][] getAns()
    {
        int[][] answer;
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int j = 0; j < n+1; j++) {
            for (int i = 0; i < n; i++) {
                if(pillarMap[i][j] == 0)
                {
                    ArrayList<Integer> tmp = new ArrayList<>();
                    tmp.add(j);
                    tmp.add(i);
                    tmp.add(0);
                    list.add(tmp);
                }
                if(j < n)
                {
                    if(nonPillarMap[i][j] == 1)
                    {
                        ArrayList<Integer> tmp = new ArrayList<>();
                        tmp.add(j);
                        tmp.add(i);
                        tmp.add(1);
                        list.add(tmp);
                    }
                }
            }
        }
        answer = new int[list.size()][3];
        int idx = 0;
        for(ArrayList<Integer> tmp : list)
        {
            answer[idx][0] = tmp.get(0);
            answer[idx][1] = tmp.get(1);
            answer[idx][2] = tmp.get(2);
            idx++;
        }
        return answer;
    }

}