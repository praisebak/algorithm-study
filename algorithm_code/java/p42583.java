import java.util.Queue;
import java.util.LinkedList;

class p42583
{
    static Queue<Integer> que = new LinkedList<Integer>();
    //트럭이 몇대 올라갈 수 있느냐
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int curTime = 0;
        int curWeight = 0;
        int outIdx = 0;
        //2초 경과하면 그냥 나가
        int i = 0;
        while(i != truck_weights.length || que.size() != 0)
        {
            if(que.size() != 0 && ((curTime - que.peek()) >= bridge_length))
            {
                curWeight -= truck_weights[outIdx++];
                que.poll();
                

            }
            if(i != truck_weights.length && curWeight + truck_weights[i] <= weight)
            {
                que.add(curTime);
                curWeight += truck_weights[i];
                i++; 
            }
            //추가 못한다 -> 나가
            curTime++;
        }

        return curTime;
    }

    public static void main(String []args)
    {
        int tmp[] = new int[4];
        tmp[0] = 7;
        tmp[1] = 4;
        tmp[2] = 5;
        tmp[3] = 6;
        System.out.println(solution(2,10,tmp));
    }

}