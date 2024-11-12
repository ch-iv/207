package us.jonathans;

import java.util.*;

public class Leaderboard {
    private final Map<String, ArrayList<Integer>> data;

    public Leaderboard() {
        data = new HashMap<>();
    }

    public void add(String username, int score, int time) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(score);
        list.add(time);

        data.put(username, list);
    }

    public Map<String, ArrayList<Integer>> getData(){
        return data;
    }

    public Map<String, ArrayList<Integer>> sort(String type) {

        ArrayList<Integer> listST = new ArrayList<>();
        Map<String, ArrayList<Integer>> sortedData = new LinkedHashMap<>();
        int typeC = 0;

        if (type.equalsIgnoreCase("time")) {
            typeC = 1;
        }

        for (Map.Entry<String, ArrayList<Integer>> entry : data.entrySet()) {
            listST.add(entry.getValue().get(typeC));
        }
        Collections.sort(listST, Collections.reverseOrder());

        for(int num : listST){
            for (Map.Entry<String, ArrayList<Integer>> entry : data.entrySet()) {
                if (entry.getValue().get(typeC) == num) {
                    sortedData.put(entry.getKey(), entry.getValue());
                }
            }
        }

        return sortedData;
    }
}



