/*
import java.util.List;

public class Scheduler {
    public static void printSchedule(List<Edge> mst, String name) {
        System.out.println("=== " + name + " ===");
        int day = 0;
        int totalKm = 0;
        String currentCity = null;

        for (Edge e : mst) {
            totalKm += e.weight;
            int remaining = e.weight;

            // Logika penalizace: pokud nenavazujeme na město, kde jsme skončili
            boolean move = (currentCity != null && !e.u.equals(currentCity) && !e.v.equals(currentCity));

            day++;
            int startHours = move ? 1 : 0;
            int workToday = Math.min(remaining, 8 - startHours);

            System.out.printf("[d_%d] %s -> %s: %d hours, %d km %s\n",
                    day, e.u, e.v, (workToday + startHours), workToday, (move ? "(PŘESUN +1h)" : ""));

            remaining -= workToday;
            while (remaining > 0) {
                day++;
                int work = Math.min(remaining, 8);
                System.out.printf("[d_%d] %s -> %s: %d hours, %d km\n", day, e.u, e.v, work, work);
                remaining -= work;
            }
            currentCity = e.v; // Předpokládáme, že četa končí v cílovém městě úseku
        }
        System.out.println("-------------------------------------");
        System.out.println("RESULT: " + day + " dní, " + totalKm + " km\n");
    }
}*/
