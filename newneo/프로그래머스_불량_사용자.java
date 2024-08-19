import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    /**
     *
     *
     * ["frodo", "fradi", "crodo", "abc123", "frodoc"]	["fr*d*", "*rodo", "******", "******"]	3
     *
     *
     * fr*d*에 대해서 일치하는거 찾아보고
     * 일치하면 *rodo에 대해서 일치하는지 살펴보고
     * ****에 대해서는 crodo가 일치하고
     * 그다음에는 frodoc이 일치할거임
     *
     * 순서만 다른것은 없음
     * @param user_id
     * @param banned_id
     * @return
     */

    String[] userIdArr;
    String[] bannedIdArr;

    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        userIdArr = user_id;
        bannedIdArr = banned_id;
        dfs(0,new boolean[user_id.length],new ArrayList<>());
        return hashSet.size();
    }

    HashSet<String> hashSet = new HashSet<>();

    //순서는 어떻게되는거지?
    //a b c
    //b c a 둘다 똑같은거로 쳐야하는게아닌가
    private void dfs(int bannedArrIdx,boolean[] visit, List<Integer> visitIdxList) {
        if(bannedIdArr.length == bannedArrIdx){
            Collections.sort(visitIdxList);
            String s = visitIdxList.stream().map((integer) -> integer + "").collect(Collectors.joining(" "));
            if(hashSet.contains(s)) return;
            hashSet.add(s);
            return;
        }

        //userId length
        for(int i=0;i<userIdArr.length;i++){
            String userId = userIdArr[i];
            if(visit[i]) continue;
            if(isBannable(bannedIdArr[bannedArrIdx],userId)){
                visit[i] = true;
                ArrayList<Integer> tmp = new ArrayList<>();
                tmp.addAll(visitIdxList);
                tmp.add(i);
                dfs(bannedArrIdx+1,visit,tmp);
                visit[i] = false;
            }
        }



    }

    private boolean isBannable(String bannedStr, String objStr) {
        if(bannedStr.length() != objStr.length()) return false;
        boolean flag = true;

        for(int i=0;i<bannedStr.length();i++){
            //* 아니면
            if(bannedStr.charAt(i) == '*'){
                continue;
            }else{
                if(bannedStr.charAt(i) == objStr.charAt(i)){
                    continue;
                }
            }

            flag = false;
            break;
        }
        return flag;


    }


}
