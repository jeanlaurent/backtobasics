package net.morlhon.exos.hashmap;

import net.morlhon.exos.prime.Prime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MyHashMap<K, V> {
    private int size;
    public List<Pair>[] array;
    private final float loadFactor;
    private int load;

    public MyHashMap() {
        this(101, .75f);
    }

    public MyHashMap(int defaultSize, float loadFactor) {
        this.load = 0;
        this.size = assignSize(defaultSize);
        this.loadFactor = loadFactor;
        array = new List[size]; //AHAHA Java generics you are just a failure... (no way to set new List<Pair>[size] or even better new List<>[size]
    }

    private int assignSize(int suggestedSize) {
        Prime prime = new Prime();
        if (prime.is(suggestedSize)) {
            return suggestedSize;
        }
        return prime.nearestGreaterOf(suggestedSize);
    }


    public void add(K key, V value) {
        if (rawAdd(key, value, array)) {
            this.load++;
            checkLoadAndResizeIfNeeded();
        }
    }

    private boolean rawAdd(K key, V value, List<Pair>[] targetArray) {
        int hash = computeHash(key);
        if (targetArray[hash] == null) {
            targetArray[hash] = new ArrayList<>();
        }
        for (int i = 0; i < targetArray[hash].size(); i++) {
            if (targetArray[hash].get(i).key.equals(key)) {
                targetArray[hash].set(i, new Pair(key, value));
                return false;
            }
        }
        targetArray[hash].add(new Pair(key, value));
        return true;
    }

    private void checkLoadAndResizeIfNeeded() {
        if (load > size * loadFactor) {
            resizeMapAndRehash();
        }
    }

    private void resizeMapAndRehash() {
        this.size = new Prime().nearestGreaterOf(2 * this.size);
        List<Pair>[] newArray = new List[this.size];
        for (List<Pair> pairs : array) {
            if (pairs != null) {
                pairs.forEach(p -> rawAdd((K) p.key, (V) p.value, newArray));
            }
        }
        this.array = newArray;
    }

    public V get(K key) {
        int rank = computeHash(key);
        if (array[rank] == null) {
            return null;
        }
        Optional<Pair> matched = array[rank].stream().filter(p -> p.key.equals(key)).findFirst();
        if (matched.isPresent()) {
            return (V) matched.get().value; // Java Generics.. java.lang.Object force cast to V... howcome...
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
            if (array[i] != null) {
                stringBuilder.append("[" + i + " -> ");
                array[i].forEach(p -> stringBuilder.append(p + ","));
                stringBuilder.append("],");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public int getLoad() {
        return this.load;
    }


    class Pair<K, V> {
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
