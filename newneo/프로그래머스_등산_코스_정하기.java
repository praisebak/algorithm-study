import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class Solution {
    private int[] answer;
    private int[] answerIdx;

    class Node{
        int next;
        int val;
        public Node(int next,int val){
            this.next = next;
            this.val = val;
        }
    }
    boolean[][] visit;
    List<List<Node>> graph = new ArrayList<>();
    HashSet<Integer> gateSet = new HashSet<>();
    HashSet<Integer> summitSet = new HashSet<>();


    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        visit = new boolean[n+1][n+1];

        answer = new int[1];
        answerIdx = new int[1];
        Arrays.fill(answer,Integer.MAX_VALUE);
        Arrays.fill(answerIdx,Integer.MAX_VALUE);

        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
        }
        for(int i : gates) {
            gateSet.add(i);
        }
        for(int i : summits) {
            summitSet.add(i);
        }

        for(int i=0;i<paths.length;i++){
            int start = paths[i][0];
            int end = paths[i][1];
            int val = paths[i][2];
            graph.get(start).add(new Node(end,val));
            graph.get(end).add(new Node(start,val));
        }

        for(int gate : gates){
            dfs(gate,0,gate,0,0,0);
        }

        return new int[] {answerIdx[0],answer[0]};
    }

    public void dfs(int curIdx,int summit,int startIdx,int intensity,int resultIdx,int maxIntensity) {
        //정상 찍고 원래위치로 돌아옴
        if(summit != 0 && curIdx == startIdx) {
            //값 같으면
            if(answer[resultIdx] >= maxIntensity) {
                if(answer[resultIdx] == maxIntensity) {
                    //봉우리 번호 낮은애로
                    answerIdx[resultIdx] = Math.min(answerIdx[resultIdx],summit);
                }else {
                    answer[resultIdx] = maxIntensity;
                    answerIdx[resultIdx] = summit;
                }
            }
            return;
        }


        for(Node node : graph.get(curIdx)) {

            int next = node.next;
            if(visit[curIdx][next]) continue;


            int newIntensity = Math.max(maxIntensity,intensity + node.val);

            //다음이 봉우리인경우
            if(summitSet.contains(next)){

                //이미 봉우리 방문함
                if(summit != 0) continue;
                //봉우리 설정 및 순환
                visit[curIdx][next] = true;
                dfs(next,next,startIdx,0,resultIdx,newIntensity);
                visit[curIdx][next] = false;
                //시작지점인 경우
            }else if(gateSet.contains(next)){
                if(next == startIdx && summit != 0){
                    visit[curIdx][next] = true;
                    dfs(next,summit,startIdx,intensity + node.val,resultIdx,newIntensity);
                    visit[curIdx][next] = false;
                }
            //쉼터인 경우
            }else{
                visit[curIdx][next] = true;
                dfs(next,summit,startIdx,0,resultIdx,newIntensity);
                visit[curIdx][next] = false;
            }
        }
    }

    //summit과 시작점 둘다 아니어야함
    public boolean isRecovery(int idx){
        return !(summitSet.contains(idx) || gateSet.contains(idx));
    }


}
