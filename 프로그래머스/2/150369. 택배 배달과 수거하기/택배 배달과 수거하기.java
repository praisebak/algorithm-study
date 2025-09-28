class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        //n개의 집에 택배를 배달한다
        //i번째집은 i만큼, i번째집과 j번째집은 j-i만큼 떨어져있다
        //재활용 택배 상자 실어 배달, 수거해서 창고에 내린다
        //모든 배달 및 수거 마치는 최소 이동 거리
        
        //맨끝에까지가서 배달하고 수거하는건 그대로인데
        //그 뒤에 돌아오는것은 어떻게하는게 좋을까
        
        //그리고 수거할것만 남은 경우에도 동작해야함

        int cur = 0;
        int prevGive = 0;
        int prevGet = 0;

        for(int i=deliveries.length-1;i>=0;i--){
            int count = 0;
            while(deliveries[i] > prevGive || pickups[i] > prevGet){
                prevGive += cap;
                prevGet += cap;
                count++;
            }
            
            prevGive -= deliveries[i];
            prevGet -= pickups[i];

            answer += (long)(i +1) * count * 2 ;
        }
        return answer;
    }
}