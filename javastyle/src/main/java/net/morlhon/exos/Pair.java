package net.morlhon.exos;

public class Pair {
    int i;
    int j;

    public Pair(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public String toString() {
        return "[" + i + "," + j + "]";
    }

    @Override
    public boolean equals(Object obj) {
        Pair pair = (Pair) obj;
        return (pair.i == i && pair.j == j) || (pair.i == j && pair.j == i);
    }

    @Override
    public int hashCode() {
        int result = i;
        result = 31 * result + j;
        return result;
    }
}