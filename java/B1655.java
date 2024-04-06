package java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;


class B1655
{

    public void solve() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = 0;
        try {
            N = Integer.parseInt(br.readLine());
        } catch (IOException e) {

        }
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();

        for(int i=0;i<N;i++){
            int val = 0;
            try {
                val = Integer.parseInt(br.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if(maxPQ.size() == minPQ.size()){
                maxPQ.add(val);
                if(!minPQ.isEmpty() && maxPQ.peek() > minPQ.peek()){
                    minPQ.add(maxPQ.poll());
                    maxPQ.add(minPQ.poll());
                }
            }else{
                minPQ.add(val);
                if(maxPQ.peek() > minPQ.peek()){
                    minPQ.add(maxPQ.poll());
                    maxPQ.add(minPQ.poll());
                }
            }
            sb.append(maxPQ.peek() + "\n");
        }
        System.out.println(sb.toString());
    }
}
