import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String[] data = {
                "Lordaeron,Tristram,2", "Lordaeron,Arraken,9", "Tristram,Arraken,7",
                "Tristram,Rivie,12", "Tristram,Minas Tirith,4", "Solitude,Rivie,8",
                "Arraken,Lannisport,16", "Arraken,Ankh Morpork,24", "Lannisport,Ankh Morpork,10",
                "Minas Tirith,Rivie,3", "Minas Tirith,Mordheim,8", "Minas Tirith,Gondolin,9",
                "Gondolin,Mordheim,5", "Gondolin,Godric's Hollow,15", "Gondolin,Mos Eisley,20",
                "Gondolin,Ankh Morpork,5", "Mordheim,Godric's Hollow,1", "Ankh Morpork,Mos Eisley,4",
                "Mos Eisley,LV 426,7", "Godric's Hollow,LV 426,3"
        };

        List<Edge> edges = Arrays.stream(data)
                .map(s -> s.split(","))
                .map(p -> new Edge(p[0], p[1], Integer.parseInt(p[2])))
                .collect(Collectors.toList());

        Set<String> cities = new HashSet<>();
        edges.forEach(e -> { cities.add(e.u); cities.add(e.v); });

        List<Edge> boruvkaMST = Algorithms.boruvkaMST(edges, cities);

        Algorithms.printSchedule(Algorithms.kruskal(edges, cities), "KRUSKAL");
        Algorithms.printSchedule(Algorithms.prim(edges, cities), "PRIM-JARNÍK");
        Algorithms.printSchedule(boruvkaMST, "BORŮVKA");
    }
}