package net.morlhon.exos.ardent23;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.io.Resources;

import java.io.IOException;
import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SearchWord {
    private final Multimap<String, Integer> map;
    //http://www.ardendertat.com/2011/12/20/programming-interview-questions-23-find-word-positions-in-text/

    public SearchWord() throws IOException {
        String text = Resources.toString(Resources.getResource("text.txt"), Charsets.UTF_8);
        List<String> strings = Splitter.on(CharMatcher.BREAKING_WHITESPACE).omitEmptyStrings().splitToList(text);

        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        List<String> words = strings.stream().map(string -> {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < string.length(); i++) {
                if (Character.isLetterOrDigit(string.charAt(i))) {
                    builder.append(Character.toLowerCase(string.charAt(i)));
                }
            }
            String nfdNormalizedString = Normalizer.normalize(builder.toString(), Normalizer.Form.NFD);
            return pattern.matcher(nfdNormalizedString).replaceAll("");
        }).filter(s -> !s.isEmpty()).collect(Collectors.toList());
        map = ArrayListMultimap.create();
        for (int i = 0; i < words.size(); i++) {
            map.put(words.get(i), i);
        }
    }

    public void query(String word) {
        System.out.println(word + " -> " + map.get(word));
    }

    public static void main(String[] args) throws IOException {
        SearchWord searchWord = new SearchWord();
        searchWord.query("rage");
        searchWord.query("tant");
        searchWord.query("honneur");
    }

}
