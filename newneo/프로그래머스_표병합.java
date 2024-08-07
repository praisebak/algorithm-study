import javax.swing.*;
import java.util.*;
class Solution {

    private final int MAX = 51;
    private final String UPDATE = "UPDATE";
    private final String MERGE = "MERGE";
    private final String UNMERGE = "UNMERGE";
    private final String PRINT = "PRINT";
    private final String EMPTY = "EMPTY";



    String[][] map;
    int[][] mergeIdxMap;

    List<String> printResult = new ArrayList<>();
    int mergeIdx = 1;


    //해당 머지 인덱스에 연결되어 있는 cell들
    private HashMap<Integer, List<String>> mergeCellInfoMap = new HashMap<>();
    //해당 셀이 merge에 연결된 경우 해당 인덱스를 가져옴
    private HashMap<String, Integer> cellMergeIdxMap = new HashMap<>();
    private HashMap<Integer, String> mergeValMap = new HashMap<>();

    public String[] solution(String[] commands) {
        map = new String[MAX][MAX];
        for (int i = 0; i < MAX; i++) {
            Arrays.fill(map[i],EMPTY);
        }
        mergeIdxMap = new int[MAX][MAX];

        String[] answer = {};
        for(String command : commands){
//            System.out.println(command);
            String[] splitedCommand = command.split(" ");
            String operator = splitedCommand[0];

            String operand;
            switch(operator){
                case UPDATE:
                    operand = command.split(operator)[1].trim();
                    update(operand);
                    break;
                case MERGE:
                    operand = command.split(operator)[1].trim() ;
                    merge(operand);
                    break;
                case UNMERGE:
                    operand = command.split(operator)[1].trim();
                    unmerge(operand);
                    break;
                default:
                    operand = command.split(operator)[1].trim();
                    print(operand);
            }

            if(!operator.equals("UPDATE")){
                // printAll();
            }
        }

        return printResult.toArray(new String[0]);
    }

    private void printAll() {
        for (int i = 1; i <= 50 ; i++) {
            for (int j = 1; j <= 50; j++) {
                if(map[i][j].equals(EMPTY)){
                    System.out.print(" ");
                    continue;
                }
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }



    }

    int newIdx = 1;

    private void merge(String operand) {
        String[] splitOperand = operand.split(" ");
        int r1 = Integer.parseInt(splitOperand[0]);
        int c1 = Integer.parseInt(splitOperand[1]);
        int r2 = Integer.parseInt(splitOperand[2]);
        int c2 = Integer.parseInt(splitOperand[3]);
        if(r1 == r2 && c1 == c2) return;

        String valA = map[r1][c1];
        String valB = map[r2][c2];
        //merge idx관리 필요한거아닌가? 대상 셸이 몇번째 megre인지 알아야하니까
        int idxA = mergeIdxMap[r1][c1];
        int idxB = mergeIdxMap[r2][c2];
        //비어있음

        String updateVal = valA;
        if(updateVal.equals(EMPTY)) updateVal = valB;

        map[r1][c1] = updateVal;
        map[r2][c2] = updateVal;
        mergeIdxMap[r1][c1] = newIdx;
        mergeIdxMap[r2][c2] = newIdx;
        
        mergeSameIdx(idxA,updateVal);
        mergeSameIdx(idxB,updateVal);
        newIdx++;
    }

    private void mergeSameIdx(int idx,String val) {
        if(idx == 0) return;
        for(int i=1;i<=50;i++){
            for (int j = 1; j <= 50; j++) {
                if(mergeIdxMap[i][j] == idx){
                    map[i][j] = val;
                    mergeIdxMap[i][j] = newIdx;
                }
            }
        }
    }

    private void updateSameVal(String valA,String valB) {
        for(int i=1;i<=50;i++){
            for (int j = 1; j <= 50; j++) {
                if(map[i][j].equals(valA)){
                    map[i][j] = valB;
                }
            }
        }
    }

    private void unmerge(String operand) {
        String[] sTmp = operand.split(" ");
        int r = Integer.parseInt(sTmp[0]);
        int c = Integer.parseInt(sTmp[1]);

        unmergeByCell(r,c);



    }

    private void unmergeByCell(int r,int c){
        int idx = mergeIdxMap[r][c];
        String originVal = map[r][c];

        map[r][c] = EMPTY;
        mergeIdxMap[r][c] = 0;
        //merge 된 것 없을때
        map[r][c] = originVal;

        if(idx == 0) return;

        for(int i=1;i<=50;i++){
            for (int j = 1; j <=50 ; j++) {
                if(mergeIdxMap[i][j] == idx){
                    mergeIdxMap[i][j] = 0;
                    map[i][j]  = EMPTY;
                }
            }
        }

        map[r][c] = originVal;
    }

    private void print(String operand) {
        String[] sTmp = operand.split(" ");
        int r = Integer.parseInt(sTmp[0]);
        int c = Integer.parseInt(sTmp[1]);
        printResult.add(map[r][c]);
    }

    private void update(String operand) {
        String[] sTmp = operand.split(" ");

        if(sTmp.length >= 3){
            int r = Integer.parseInt(sTmp[0]);
            int c = Integer.parseInt(sTmp[1]);
            String val = sTmp[2];
            int idx = mergeIdxMap[r][c];
            map[r][c] = val;
            mergeSameIdx(idx,val);
            newIdx++;
        }else{
            String valA = sTmp[0];
            String valB = sTmp[1];
            updateSameVal(valA,valB);
        }
    }

}
