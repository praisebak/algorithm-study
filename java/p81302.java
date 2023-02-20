import java.util.ArrayList;

class Solution 
{
    boolean flag = false;
    char[][] arr = new char[5][5];

    public int[] solution(String[][] places) 
    {
        ArrayList<Integer> tmpResult = new ArrayList<>();
        for(int i=0;i<places.length;i++)
        {
            arr = new char[5][5];
            initArr(i,arr,places);
            

            flag = false;
            if(i == 2)
            {
                flag = false;
            }

            for(int row = 0; row < arr.length;row++)
            {
                for(int cal = 0; cal < arr[0].length ;cal++)
                {
                    if(arr[row][cal] == 'P')
                    {
                        if(calDist(arr,row,cal) <= 2)
                        {
                            if(flag)
                            {
                                System.out.println("거리두기 안지킨 좌표 " + row + "," + cal);
                            }
                            tmpResult.add(0);
                            flag = true;
                            break;
                        }
                    
                    }
                }
                if(tmpResult.size() != i)
                {
                    break;
                }
            }

            if(!flag)
            {
                tmpResult.add(1);
            }
        }

        int[] answer = tmpResult.stream().mapToInt(num -> num).toArray();
        System.out.println(answer[0]);
        return answer;
    }

    private int calDist(char[][] arr, int row, int cal) 
    {
        //왼 위 오른 아래
        int[] dStrightX = {-1,0,1,0};
        int[] dStrightY = {0,1,0,-1};
        //왼위,오위,오아,왼아
        int[] dCurvedX = {-1,1,1,-1};
        int[] dCurvedY = {1,1,-1,-1};

        //직선 먼저
        for(int mul=1;mul<=2;mul++)
        {

            for(int i=0; i<4;i++)
            {
                int nextRow = dStrightY[i] * mul + row;
                int nextCal = dStrightX[i] * mul + cal ;
                
                if(!isValid(nextRow,nextCal,arr))
                {
                    continue;
                }

                if(arr[nextRow][nextCal] == 'P')
                {

                    if(flag)
                    System.out.println(row + "," + cal + " -> " + nextRow + "," + nextCal );
                    //바로 옆인 케이스
                    if(mul == 1)
                    {
                        if(flag) System.out.println("바로 옆이어서 안지킨거랬죠");
                        return 1;
                    }
                    //두칸 옆인 케이스
                    else
                    {
                        int halfRow = dStrightY[i] + row;
                        int halfCal = dStrightX[i] + cal;
                        if(!isValid(halfRow,halfCal,arr))
                        {
                            continue;
                        }
                        if(arr[halfRow][halfCal] != 'X')
                        {
                            if(flag)
                                System.out.println("두칸거리있을 때 중간에 칸막이 없으므로 안지킨거랬죠");
                            return 2;
                        }
                    }
                }
            }
        }

        //대각선에 위치한 경우
        for(int i=0;i<4;i++)
        {
            int tmpRow = row;
            int tmpCal = cal; 
            int nextRow = dCurvedY[i] + tmpRow;
            int nextCal = dCurvedX[i] + tmpCal;
            if(!isValid(nextRow,nextCal,arr))
            {
                continue;
            }

            if(arr[nextRow][nextCal] == 'P')
            {
                if(!(arr[row][nextCal]=='X' && arr[nextRow][cal]=='X'))
                {
                    return 2;
                }
            }

        }

        return 3;
    }

    private boolean isValid(int nextRow, int nextCal,char[][] arr) 
    {
        if(nextCal < 0 || nextRow < 0)
        {
            return false;
        }
        if(nextCal >= arr[0].length || nextRow >= arr.length)
        {
            return false;
        }
        return true;
    }

    private void initArr(int i,char[][] arr,String[][] places) 
    {
        int count = 0;

        for(char[] iter : arr)
        {
            iter = places[i][count].toCharArray();
            arr[count++] = iter;
        }
        for(int k=0;k<arr.length;k++)
        {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[k][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) 
    {
        Solution solution = new Solution();
        String[][] tmp = 
        {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
        {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
        {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
        {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
        {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        int[] answer=  solution.solution(tmp);
        for (int i = 0; i < answer.length; i++) 
        {
            System.out.println(answer[i]);
        }
    }
}