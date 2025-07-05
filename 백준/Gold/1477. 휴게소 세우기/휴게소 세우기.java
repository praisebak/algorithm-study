import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solve().solve();
    }
}

class Solve {
    int N, M, L;
    int[] restStops;

    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        N = Integer.parseInt(firstLine[0]);
        M = Integer.parseInt(firstLine[1]);
        L = Integer.parseInt(firstLine[2]);

        List<Integer> list = new ArrayList<>();
        list.add(0); // 고속도로 시작점

        if (N > 0) {
            String[] line = br.readLine().split(" ");
            for (String s : line) {
                list.add(Integer.parseInt(s));
            }
        }

        list.add(L); // 고속도로 끝점 포함
        Collections.sort(list);

        restStops = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            restStops[i] = list.get(i);
        }

        int left = 1;
        int right = L;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canInstall(mid)) {
                answer = mid;
                right = mid - 1; // 더 줄여보기
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    private boolean canInstall(int maxDist) {
        int count = 0;
        for (int i = 1; i < restStops.length; i++) {
            int gap = restStops[i] - restStops[i - 1];
            count += (gap - 1) / maxDist;
        }
        return count <= M;
    }
}
