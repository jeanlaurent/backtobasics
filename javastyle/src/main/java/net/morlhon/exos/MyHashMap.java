package net.morlhon.exos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MyHashMap<K,V> {
    private final int size;
    public final List<Pair>[] array;
    private final float loadFactor;

    public MyHashMap() {
        this(101, .75f);
    }

    public MyHashMap(int defaultSize, float loadFactor) {
        this.size = defaultSize;
        this.loadFactor = loadFactor;
        array = new List[size]; //AHAHA Java generics you are just a failure... (no way to set new List<Pair>[size] or even better new List<>[size]
    }


    public void add(K key, V value) {
        int rank = computeHash(key);
        if (array[rank] == null) {
            array[rank] = new ArrayList<>();
        }
        for(int i=0; i< array[rank].size(); i++) {
            if (array[rank].get(i).key.equals(key)) {
                array[rank].set(i, new Pair(key, value));
                return;
            }
        }
        array[rank].add(new Pair(key, value));
    }

    public V get(K key) {
        int rank = computeHash(key);
        if (array[rank] == null) {
            return null;
        }
        Optional<Pair> matched = array[rank].stream().filter(p -> p.key.equals(key)).findFirst();
        if (matched.isPresent()) {
            return (V) matched.get().value;
        }
        return null;
    }

    private int computeHash(K key) {
        return key.hashCode() % this.size;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < array.length; i++) {
            if(array[i] != null) {
                stringBuilder.append("[" + i + " -> ");
                array[i].forEach(p -> stringBuilder.append(p + ","));
                stringBuilder.append("],");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    class Pair<K,V> {
        K key;
        V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return this.key + ":" + this.value;
        }
    }
}
