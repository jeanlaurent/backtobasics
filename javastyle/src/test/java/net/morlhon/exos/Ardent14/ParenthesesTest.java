package net.morlhon.exos.Ardent14;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParenthesesTest {

    @Test
    public void should_find_easy_balanced_parentheses() {
        assertThat(new Parentheses().isBalanced("()")).isTrue();
    }

    @Test
    public void should_find_easy_balanced_parentheses_not() {
        assertThat(new Parentheses().isBalanced("((")).isFalse();
    }

    @Test
    public void should_find_multiple_balanced_parentheses() {
        assertThat(new Parentheses().isBalanced("(((())))")).isTrue();
    }

    @Test
    public void should_find_multiple_balanced_parentheses_not() {
        assertThat(new Parentheses().isBalanced("(((()))(")).isFalse();
    }

    @Test
    public void should_find_easy_balanced_brackets() {
        assertThat(new Parentheses().isBalanced("[]")).isTrue();
    }

    @Test
    public void should_find_easy_balanced_brackets_not() {
        assertThat(new Parentheses().isBalanced("[[")).isFalse();
    }


    @Test
    public void should_find_easy_balanced_curly_braces() {
        assertThat(new Parentheses().isBalanced("{}")).isTrue();
    }

    @Test
    public void should_find_easy_balanced_curly_braces_not() {
        assertThat(new Parentheses().isBalanced("{{")).isFalse();
    }

    @Test
    public void should_find_balanced_in_complexe_scheme() {
        assertThat(new Parentheses().isBalanced("{([])}")).isTrue();
    }

    @Test
    public void should_find_balanced_in_complexe_scheme_not() {
        assertThat(new Parentheses().isBalanced("{([]}}")).isFalse();
    }

}