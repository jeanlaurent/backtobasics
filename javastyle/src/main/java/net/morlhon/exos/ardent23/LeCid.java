package net.morlhon.exos.ardent23;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.io.Resources;

import java.io.IOException;
import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LeCid {

    public static List<String> read() {
        String text = null;
        try {
            text = Resources.toString(Resources.getResource("text.txt"), Charsets.UTF_8);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
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
        return words;
    }
}
