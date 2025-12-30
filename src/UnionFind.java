import java.util.*;

public class UnionFind {
    private final Map<String, String> parents = new HashMap<>();

    public String find(String i) {
        parents.putIfAbsent(i, i);
        if (!parents.get(i).equals(i)) {
            parents.put(i, find(parents.get(i)));
        }
        return parents.get(i);
    }

    public boolean union(String i, String j) {
        String rootI = find(i);
        String rootJ = find(j);
        if (rootI.equals(rootJ)) return false;
        parents.put(rootI, rootJ);
        return true;
    }
}