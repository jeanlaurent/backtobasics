package net.morlhon.exos.hashmap;

import net.morlhon.exos.prime.Prime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MyHashMap<K, V> {
    public static final int DEFAULT_SIZE = 101;
    public static final float DEFAULT_LOADFACTOR = .75f;
    private int size;
    private List<Pair>[] array;
    private float loadFactor;
    private int load;

    public MyHashMap() {
        this(DEFAULT_SIZE);
    }

    public MyHashMap(int defaultSize) {
        this.load = 0;
        this.size = assignSize(defaultSize);
        this.loadFactor = DEFAULT_LOADFACTOR;
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

    public int currentMaxSize() {
        return this.array.length;
    }

    public int size() {
        return this.load;
    }

    public void setLoadFactor(float loadFactor) {
        this.loadFactor = loadFactor;
        // we should probably call checkLoadAndResizeIfNeeded() in a real impl
    }

    public boolean delete(K key) {
        int hash = computeHash(key);
        if (array[hash] == null) {
            return false;
        }
        for (int i = 0; i < array[hash].size(); i++) {
            if (array[hash].get(i).key.equals(key)) {
                array[hash].remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Pair>[] array() {
        return Arrays.copyOf(array, array.length);
    }


    public class Pair<K, V> {
        public K key;
        public V value;

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
