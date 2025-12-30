public class Edge implements Comparable<Edge> {
    final String u, v; // přejmenováno z from/to pro obecnost
    final int weight;

    public Edge(String u, String v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public boolean connects(String node) {
        return u.equals(node) || v.equals(node);
    }

    public String getOpposite(String node) {
        return u.equals(node) ? v : u;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}