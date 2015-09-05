package net.morlhon.exos.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class TreeTest {

    private Tree<String> tree;
    private ArrayList<String> results;

    @Before
    public void init() {
        tree = buildTree();
        results = new ArrayList<>();
    }

    @Test
    public void should_traverse_preOrder() {
        tree.preOrderTraverse(results::add);

        assertThat(results).containsExactly("F", "B", "A", "D", "C", "E", "G", "I", "H");
    }

    @Test
    public void should_traverse_inOrder() {
        tree.inOrderTraverse(results::add);

        assertThat(results).containsExactly("A", "B", "C", "D", "E", "F", "G", "H", "I");
    }

    @Test
    public void should_traverse_postOrder() {
        tree.postOrderTraverse(results::add);

        assertThat(results).containsExactly("A", "C", "E", "D", "B", "H", "I", "G", "F");
    }

    @Test
    public void should_traverse_breadth_first() {
        tree.breadthFirstTraverse(results::add);

        System.out.println(results);
        assertThat(results).containsExactly("F","B","G","A","D","I","C","E","H");
    }


    // Build a tree as in wikipedia example @
    // https://en.wikipedia.org/wiki/Tree_traversal
    private Tree<String> buildTree() {
        Tree<String> treeRoot = new Tree<>("F");

        treeRoot.left = new Tree<>("B");
        treeRoot.left.left = new Tree<>("A");
        treeRoot.left.right = new Tree<>("D");
        treeRoot.left.right.left = new Tree<>("C");
        treeRoot.left.right.right = new Tree<>("E");

        treeRoot.right = new Tree<>("G");
        treeRoot.right.right = new Tree<>("I");
        treeRoot.right.right.left = new Tree<>("H");

        return treeRoot;
    }


}