package net.morlhon.exos.ardent5;

import static java.util.Objects.isNull;

public class Node {
    Integer value;
    Node next;

    public Node(int value) {
        this.value = value;
    }

    public void remove(int value) {
        boolean deletion = false;
        Node previousNode = null;
        Node currentNode = this;
        int count = 0;
        while (currentNode != null) {
            count++;
            if (currentNode.value == value) {
                if (previousNode == null) {
                    handleFirstNode();
                } else { // remove node classically by linking to the next
                    previousNode.next = currentNode.next;
                    deletion = true;
                }
            }
            if (!deletion) {
                deletion = false;
                previousNode = currentNode;
            }
            currentNode = currentNode.next;
        }
    }

    private void handleFirstNode() {
        if (this.next != null) { // remove first node but there is a next
            this.value = this.next.value;
            this.next = this.next.next;
        } else { // remove first node and now the list is empty
            this.value = null;
            this.next = null;
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        print(this, builder);
        return builder.toString();
    }

    private void print(Node node, StringBuilder builder) {
        if (node == null) {
            builder.append('*');
            return;
        }
        if (!isNull(value)) {
            builder.append(node.value);
        }
        builder.append(" -> ");
        print(node.next, builder);
    }
}
