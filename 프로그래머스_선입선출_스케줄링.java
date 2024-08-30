class Solution {
    public int solution(int n, int[] cores) {
        int answer = 0;

        int start = 0;
        int end = cores[0] * n;

        int time = 0;
        int work = 0;

        while(start <= end){
            int mid = (start + end) / 2;
            //mid를 주고 시간 계산했을때
            int count = cal(cores,mid);
            //시간이 n 초과하면 더 개선여지가 있다
            if(count >= n){
                end = mid-1;
                time = mid;
                work = count;
            }else{
                start = mid+1;
            }
        }

        //작업량
        work -= n;
        for(int i=cores.length-1;i>=0;i--){
            if(time % cores[i] == 0){
                if(work == 0){
                    return i +1;
                }
                work--;
            }
        }

        return answer;
    }

    //최적의 작업 시간
    private int cal(int[] cores, int time) {
        if(time == 0){
            return cores.length;
        }

        int count = cores.length;

        for(int i=0;i<cores.length;i++){
            count += (time / cores[i]);
        }
        return count;
    }
}
