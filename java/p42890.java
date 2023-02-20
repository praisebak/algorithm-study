import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;


class Solution 
{
    ArrayList<HashSet<Integer>> addedAttribute = new ArrayList<>();
    int categoryNum;
    int result = 0;
    int peopleNum;

    public int solution(String[][] relation) 
    {
        categoryNum = relation[0].length;
        peopleNum = relation.length;
        getCandidateNum(relation);
        return result;
    }

    private void getCandidateNum(String[][] relation) 
    {
        //속성 몇개를 기준으로 찾을지
        for(int attributeNum=1;attributeNum<=categoryNum;attributeNum++)
        {
            HashSet<Integer> set = new HashSet<>();
            checkCandidateByDFS(-1,attributeNum,relation,set,0);
        }
    }

    private void checkCandidateByDFS(int idx,int attributeNum, String[][] relation,HashSet<Integer> set,int count) 
    {
        if(attributeNum == count)
        {
            if(isCandidateKey(set,relation))
            {
                for(HashSet<Integer> addedAtt : addedAttribute)
                {
                    if(set.containsAll(addedAtt))
                    {
                        return;                        
                    }
                }
                addedAttribute.add(set);
                result++;
            }
            return;
        }
        
        for(int i=idx+1;i<categoryNum;i++)
        {
            HashSet<Integer> tmpSet = new HashSet<>(set);
            tmpSet.add(i);
            checkCandidateByDFS(i,attributeNum, relation,tmpSet,count+1);
        }
    }

    public static boolean isUnique(HashSet<Integer> set,String[][] Table) {
    	
    	ArrayList<String> list = new ArrayList<String>();
    	for(int i = 0; i < Table.length; i++) {
    		String temp = "";
    		for(int index: set) {
    			temp+= Table[i][index];
    		}
    		if(!list.contains(temp)) {
    			list.add(temp);
    		}else {
    			return false;
    		}
    	}
    	return true;
    }

    private boolean isCandidateKey(HashSet<Integer> set, String[][] relation) 
    {
        HashMap<String,Integer> checkDupli = new HashMap<>();
        String[] relationTmp = new String[relation.length];
        Arrays.fill(relationTmp, "");
        for(Integer iter : set)
        {
            for(int i=0;i<peopleNum;i++)
            {
                relationTmp[i] += relation[i][iter];
            }
        }
        for(int i=0;i<relationTmp.length;i++)
        {
            int curVal = checkDupli.getOrDefault(relationTmp[i], 0);
            if(curVal == 1)
            {
                return false;
            }
            checkDupli.put(relationTmp[i], curVal+1);
        }
        return true;
    }


    public static void main(String[] args) 
    {
        Solution solution = new Solution();
        String[][] arr = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};
        
        solution.solution(arr);
        System.out.println(solution.result);

    }
}