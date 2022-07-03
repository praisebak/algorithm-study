import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    HashMap<String,Double> pointMap = new HashMap<>();
    HashMap<String,Integer> baseMap = new HashMap<>();
    HashMap<String,Integer> linkMap = new HashMap<>();
    HashMap<String,String> htmlMap = new HashMap<>();
    HashMap<String,Integer> idxMap = new HashMap<>();

    public int solution(String word, String[] pages) {
        int answer = 0;
        int idx = 0;
        for(String page : pages)
        {
            String key = getCurrentURL(page);
            pointMap.put(key,0.0);
            baseMap.put(key, getBaseNum(page, word));
            htmlMap.put(key, page);
            idxMap.put(key, idx);
            idx++;
        }

        for(String key : pointMap.keySet())
        {
            setExternalScore(htmlMap.get(key),key);
        }

        Double max = Double.MIN_VALUE;
        int minIdx = Integer.MAX_VALUE;

        System.out.println((double)max < (double)0.0);
        for(String key : idxMap.keySet())
        {
            double val = (double)(pointMap.get(key) + (double)baseMap.get(key));
            val = 0.0;
            if(max < val)
            {
                max = val;
                minIdx = idxMap.get(key);
            }
            else if(max == val)
                minIdx = Math.min(idxMap.get(key),minIdx);
        }

        System.out.println(minIdx);

        answer = minIdx;
        return answer;
    }
 

    private void setExternalScore(String page, String key) 
    {
        int start = 0;
        int end = 0;
        String aPart = "";
        ArrayList<String> externalCheckStrList = new ArrayList<>(); 
        while((start = page.indexOf("<a",start)) != -1)
        {
            end = page.indexOf(">", start+1);
            aPart = page.substring(start, end);
            aPart = aPart.split("<a href=")[1].replace("\"", "");

            if(pointMap.get(aPart) != null)
                externalCheckStrList.add(aPart);
            linkMap.put(key, linkMap.getOrDefault(key, 0)+1);
            start = end+1;
        }

        for(String externalStr : externalCheckStrList)
        {
            pointMap.put(externalStr, pointMap.get(externalStr)+((double)baseMap.get(key) / (double)linkMap.get(key)));
        }
    }


    private String getCurrentURL(String page) {
        int start = page.indexOf("<meta property=\"og:url\" content=\"");
        int end = page.indexOf(">",start);
        String metaStr = page.substring(start, end);
        start = metaStr.indexOf("content=\"");
        start = metaStr.indexOf("\"",start+1)+1;
        end = metaStr.indexOf("\"",start+1);
        
        String urlPart = metaStr.substring(start, end);
        return urlPart;
    }

    private int getBaseNum(String page, String word) 
    {
        page = page.toLowerCase();
        word = word.toLowerCase();
        int start = 0;
        int count = 0;
        while((start = page.indexOf(word,start)) != -1 && start < page.length())
        {
            if(!Character.isAlphabetic(page.charAt(start-1)) && !Character.isAlphabetic(page.charAt(start+word.length())))
            {
                count++;
            }
            start+=word.length();
        }

        return count;
    }



    
    public static void main(String[] args) 
    {
        String test = "    abca bc&abc&       ";
        int start = 0;
        String word = "abc";
        while((start = test.indexOf(word,start)) != -1 && start < test.length())
        {
            if(!Character.isAlphabetic(test.charAt(start-1)) && !Character.isAlphabetic(test.charAt(start+word.length())))
            {
            }
            start+=word.length();
        }

        Solution solution = new Solution();
        solution.getCurrentURL("");
    }
}