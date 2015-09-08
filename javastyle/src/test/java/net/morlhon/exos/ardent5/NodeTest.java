package net.morlhon.exos.ardent5;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NodeTest {

    @Test
    public void should_remove_node_simple_case() {
        Node node = buildA123Node();
        assertThat(node.toString()).isEqualTo("1 -> 2 -> 3 -> *");
        node.remove(2);
        assertThat(node.toString()).isEqualTo("1 -> 3 -> *");
    }

    @Test
    public void should_remove_node_at_the_end() {
        Node node = buildA123Node();
        assertThat(node.toString()).isEqualTo("1 -> 2 -> 3 -> *");
        node.remove(3);
        assertThat(node.toString()).isEqualTo("1 -> 2 -> *");
    }

    @Test
    public void should_remove_node_at_the_start() {
        Node node = buildA123Node();
        assertThat(node.toString()).isEqualTo("1 -> 2 -> 3 -> *");
        node.remove(1);
        assertThat(node.toString()).isEqualTo("2 -> 3 -> *");
    }

    @Test
    public void should_not_remove_missing() {
        Node node = buildA123Node();
        assertThat(node.toString()).isEqualTo("1 -> 2 -> 3 -> *");
        node.remove(42);
        assertThat(node.toString()).isEqualTo("1 -> 2 -> 3 -> *");
    }

    @Test
    public void should_not_fail_singleton_node() {
        Node node = new Node(1);
        assertThat(node.toString()).isEqualTo("1 -> *");
        node.remove(1);
        assertThat(node.toString()).isEqualTo(" -> *");
    }

    @Test
    public void should_remove_duplicate() {
        Node node = buildA12334Node();
        assertThat(node.toString()).isEqualTo("1 -> 2 -> 3 -> 3 -> 4 -> *");
        node.remove(3);
        assertThat(node.toString()).isEqualTo("1 -> 2 -> 4 -> *");
    }

    private Node buildA123Node() {
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        return node;
    }

    private Node buildA12334Node() {
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(3);
        node.next.next.next.next = new Node(4);
        return node;

    }


}