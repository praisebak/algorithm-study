import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for(int i=0;i<operations.length;i++)
        {
            if(operations[i].charAt(0) == 'I')
            {
                Integer num = Integer.parseInt(operations[i].split(" ")[1]);
                maxHeap.add(num);
                minHeap.add(num);
            }
            else if(operations[i].charAt(operations[i].length()-2) != '-')
            {
                if(minHeap.size() == 0) continue;
                int num = maxHeap.poll();
                System.out.println(num);
                minHeap.remove(num);
            }
            else
            {
                
                if(minHeap.size() == 0) continue;
                int num = minHeap.poll();
                System.out.println(num);

                maxHeap.remove(num);
            }
        }
        if(minHeap.size() == 0)
        {
            return new int[]{0,0};
        }
        return new int[]{maxHeap.poll(),minHeap.poll()};
    }
}
