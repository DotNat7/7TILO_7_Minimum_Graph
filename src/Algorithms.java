import java.util.*;

public class Algorithms {

    // Kruskal zůstává efektivní přes sort
    static List<Edge> kruskal(List<Edge> edges, Set<String> nodes) {
        List<Edge> result = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(edges);
        UnionFind uf = new UnionFind();

        while (!pq.isEmpty() && result.size() < nodes.size() - 1) {
            Edge e = pq.poll();
            if (uf.union(e.u, e.v)) result.add(e);
        }
        return result;
    }

    // Prim (Jarník) pomocí mapy sousedů
    static List<Edge> prim(List<Edge> edges, Set<String> nodes) {
        List<Edge> mst = new ArrayList<>();
        if (nodes.isEmpty()) return mst;

        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        String startNode = nodes.iterator().next();
        visited.add(startNode);

        addEdges(startNode, edges, pq, visited);

        while (!pq.isEmpty() && visited.size() < nodes.size()) {
            Edge edge = pq.poll();
            String nextNode = visited.contains(edge.u) ? edge.v : edge.u;

            if (!visited.contains(nextNode)) {
                visited.add(nextNode);
                mst.add(edge);
                addEdges(nextNode, edges, pq, visited);
            }
        }
        return mst;
    }

    static List<Edge> boruvkaMST(List<Edge> edges, Set<String> cities) {
        List<Edge> mst = new ArrayList<>();
        UnionFind uf = new UnionFind();

        // V moderní verzi UnionFind se makeSet děje automaticky při find/union
        int numComponents = cities.size();

        while (numComponents > 1) {
            // Mapa pro uložení nejlevnější hrany pro každou komponentu (root ID -> Edge)
            Map<String, Edge> cheapest = new HashMap<>();

            for (Edge edge : edges) {
                String set1 = uf.find(edge.u);
                String set2 = uf.find(edge.v);

                if (!set1.equals(set2)) {
                    // Aktualizace nejlevnější hrany pro obě komponenty
                    cheapest.compute(set1, (k, current) ->
                            (current == null || edge.weight < current.weight) ? edge : current);

                    cheapest.compute(set2, (k, current) ->
                            (current == null || edge.weight < current.weight) ? edge : current);
                }
            }

            // Přidání nalezených hran do MST a spojení komponent
            for (Edge edge : cheapest.values()) {
                if (uf.union(edge.u, edge.v)) {
                    mst.add(edge);
                    numComponents--;
                }
            }

            // Pojistka pro případ, že graf není souvislý (aby nedošlo k nekonečné smyčce)
            if (cheapest.isEmpty()) break;
        }

        return mst;
    }

    private static void addEdges(String node, List<Edge> allEdges, PriorityQueue<Edge> pq, Set<String> visited) {
        for (Edge e : allEdges) {
            if (e.connects(node) && (!visited.contains(e.u) || !visited.contains(e.v))) {
                pq.add(e);
            }
        }
    }

    // Výpočet harmonogramu (beze změny výsledné logiky)
    static void printSchedule(List<Edge> mst, String label) {
        System.out.println("\n--- " + label + " ---");
        Set<String> visitedCities = new HashSet<>();
        int day = 1;
        int totalKm = 0;
        int totalHours = 0;

        for (Edge e : mst) {
            int remaining = e.weight;
            boolean penalty = !visitedCities.contains(e.u) && !visitedCities.contains(e.v);
            boolean firstBit = true;

            while (remaining > 0) {
                int capacity = 8;
                if (firstBit && penalty) {
                    System.out.printf("[d_%d] %s -> %s: 1 hours, 0 km%n", day, e.u, e.v);
                    totalHours += 1;
                    capacity -= 1;
                }

                int work = Math.min(remaining, capacity);
                System.out.printf("[d_%d] %s -> %s: %d hours, %d km%n", day, e.u, e.v, work, work);

                totalKm += work;
                totalHours += work;
                remaining -= work;
                day++; // Každý segment nebo nová hrana začíná nový den dle původní logiky
                firstBit = false;
            }
            visitedCities.add(e.u);
            visitedCities.add(e.v);
        }
        System.out.printf("VÝSLEDEK: %d dní, %d km, %d hodin%n", day - 1, totalKm, totalHours);
    }
}