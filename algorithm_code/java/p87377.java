import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

class Pair
{
    int x;
    int y;
    Pair(int x,int y)
    {
        this.x = x;
        this.y = y;
    }


}
class Solution 
{
    String[][] coordinate;
    String[] answer;
    ArrayList<Pair> meetList = new ArrayList<>();
    int minX = Integer.MAX_VALUE;
    int minY = Integer.MAX_VALUE;
    int maxX = Integer.MIN_VALUE;
    int maxY = Integer.MIN_VALUE;
    
    public String[] solution(int[][] line) 
    {
        answer = setCoordinate(line);
        return answer;
    }

    private String[] setCoordinate(int[][] line) 
    {
        for(int i=0;i<line.length;i++)
        {  
            long a = line[i][0];
            long b = line[i][1];
            long e = line[i][2];

            for(int j=i+1;j<line.length;j++)
            {
                long c = line[j][0];
                long d = line[j][1];
                long f = line[j][2];

                long adbc = a * d - b * c;
                long ecaf = e * c - a * f;
                long bfed = b * f - e * d;
                if(adbc == 0)
                {
                    continue;
                }
                if(bfed % adbc != 0)
                {
                    continue;
                }
                
                if(ecaf % adbc != 0)
                {
                    continue;
                }

           

                long x = (b * f - e * d) / (a * d - b * c);
                long y = (e * c - a * f) / (a * d - b * c);


                Pair pair = new Pair((int)x,(int)y);
                
                meetList.add(pair);
                maxY = Math.max(maxY,(int)y);
                maxX = Math.max(maxX,(int)x);
                minX = Math.min(minX,(int)x);
                minY = Math.min(minY,(int)y);
                //System.out.println("교점 : " + x + "," + y);
                
            }
        }

        this.coordinate = new String[maxY - minY+1][maxX - minX+1];
        for(String[] iterStr : coordinate)
        {
            Arrays.fill(iterStr, ".");
        }

        String[] tmpAnswer = new String[maxY - minY + 1];
        for(Pair pair : meetList)
        {
            pair.y = pair.y - minY;
            pair.x = pair.x - minX;
            coordinate[pair.y][pair.x] = "*";

            Arrays.fill(tmpAnswer, "");

            for(int i=coordinate.length-1;i >= 0 ;i--)
            {
                for(int j=0;j< coordinate[i].length;j++)
                {
                    tmpAnswer[tmpAnswer.length-1 - i] = tmpAnswer[tmpAnswer.length-1-i] + coordinate[i][j];
                }
            }
        }
        return tmpAnswer;
        

        
    }

    public static void main(String[] args)
    {
        Solution solu = new Solution();
        int[][] line = {{2, -1, 4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}};
        solu.solution(line);
    }
}