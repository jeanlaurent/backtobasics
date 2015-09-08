//http://www.ardendertat.com/2011/12/20/programming-interview-questions-23-find-word-positions-in-text/
package net.morlhon.exos.ardent23;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.io.IOException;
import java.util.List;

public class SearchWordUsingHashMap {
    private final Multimap<String, Integer> map;

    public SearchWordUsingHashMap() throws IOException {
        List<String> words = LeCid.read();
        map = ArrayListMultimap.create();
        for (int i = 0; i < words.size(); i++) {
            map.put(words.get(i), i);
        }
    }

    public void query(String word) {
        System.out.println(word + " -> " + map.get(word));
    }

    public static void main(String[] args) throws IOException {
        SearchWordUsingHashMap searchWordUsingHashMap = new SearchWordUsingHashMap();
        searchWordUsingHashMap.query("rage");
        searchWordUsingHashMap.query("tant");
        searchWordUsingHashMap.query("honneur");
    }

}
