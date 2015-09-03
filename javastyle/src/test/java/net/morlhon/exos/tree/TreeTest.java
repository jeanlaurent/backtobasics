package net.morlhon.exos.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TreeTest {

    @Test
    public void should_traverse_preOrder() {
        List<String> results = new ArrayList<>();
        Tree<String> tree = buildTree();
        tree.preOrderTraverse(results::add);
        assertThat(results).containsExactly("F", "B", "A", "D", "C", "E", "G", "I", "H");
    }

    @Test
    public void should_traverse_inOrder() {
        List<String> results = new ArrayList<>();
        Tree<String> tree = buildTree();
        tree.inOrderTraverse(results::add);
        assertThat(results).containsExactly("A","B","C","D","E","F","G","H","I");
    }

    @Test
    public void should_traverse_postOrder() {
        List<String> results = new ArrayList<>();
        Tree<String> tree = buildTree();
        tree.postOrderTraverse(results::add);
        System.out.println(results);
        assertThat(results).containsExactly("A","C","E","D","B","H","I","G","F");
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