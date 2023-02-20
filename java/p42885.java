import java.util.Arrays;
class p42885 {
    public int solution(int[] people, int limit) {
        int answer = 0;
       	Arrays.sort(people);
        int front = 0;
        int i = 0;
        for(i=people.length-1;i>= front;i--)
        {
        	if(limit >= people[i] + people[front])
            {
            	front++;
			}
            answer++;
		}
        
        return answer;
    }
}