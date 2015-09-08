package net.morlhon.exos.ardent23;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Objects.isNull;

public class SearchWordUsingTrie {

    private final Node root;

    class Node {
        char letter;
        List<Integer> positions;
        List<Node> children;

        public Node(char letter) {
            this.letter = letter;
        }

        public Node add(char letter) {
            if (children != null) {
                for (Node node : children) {
                    if (node.letter == letter) {
                        return node;
                    }
                }
            }
            if (isNull(children)) {
                children = new ArrayList<>();
            }
            Node node = new Node(letter);
            children.add(node);
            return node;
        }

        public void addPosition(int position) {
            if (isNull(positions)) {
                positions = new ArrayList<>();
            }
            positions.add(position);
        }

    }

    public SearchWordUsingTrie() {
        root = new Node('#');

        List<String> words = LeCid.read();
        for (int position = 0; position < words.size(); position++) {
            String word = words.get(position);
            addWord(word, position);
        }
    }

    public void addWord(String word, int position) {
        Node nextNode = root;
        for (int i = 0; i < word.length(); i++) {
            nextNode = nextNode.add(word.charAt(i));
        }
        nextNode.addPosition(position);
    }


    public List<Integer> query(String word) {
        Node nextNode = root;
        for (int i = 0; i < word.length(); i++) {
            for (Node node : nextNode.children) {
                if (node.letter == word.charAt(i)) {
                    nextNode = node;
                    break;
                }
            }
        }
        return nextNode.positions;
    }

    public void print(Node currentNode, int level) {
        IntStream.range(0, level).forEach(i -> System.out.print(" "));
        System.out.print(currentNode.letter);
        if (currentNode.positions != null) {
            System.out.print(" --> " + currentNode.positions);
        }
        System.out.println();
        if (currentNode.children != null) {
            for (Node node : currentNode.children) {
                print(node, level + 1);
            }
        }
    }


    public static void main(String[] args) {
        SearchWordUsingTrie trie = new SearchWordUsingTrie();
        trie.print(trie.root, 0);
        System.out.println("rage --> " + trie.query("rage"));
        System.out.println("tant --> " + trie.query("tant"));
        System.out.println("honneur --> " + trie.query("honneur"));
    }
}
