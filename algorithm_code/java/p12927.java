import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for(int work : works)
        {
            queue.add(work);
        }


        for(int i=0;i<n && queue.size() != 0;i++)
        {

            int val = queue.poll();
            if(val != 0)
            {
                queue.add(val-1);
            }
        }

        long answer = 0;

        while(queue.size() != 0)
        {
            answer += queue.peek() * queue.peek();
            queue.poll();
        }

        return answer;
    }
}