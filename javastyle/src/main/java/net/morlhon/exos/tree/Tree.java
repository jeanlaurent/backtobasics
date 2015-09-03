package net.morlhon.exos.tree;

import java.util.LinkedList;
import java.util.function.Consumer;

public class Tree<T> {
    T value;
    Tree<T> left;
    Tree<T> right;
    int distance = -1;
    Tree<T> parent = null;

    public Tree(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public void preOrderTraverse(Consumer<T> consumer) {
        consumer.accept(this.value);
        if (this.left != null) {
            this.left.preOrderTraverse(consumer);
        }
        if (this.right != null) {
            this.right.preOrderTraverse(consumer);
        }
    }

    public void inOrderTraverse(Consumer<T> consumer) {
        if(this.left != null) {
            this.left.inOrderTraverse(consumer);
        }
        consumer.accept(this.value);
        if(this.right != null) {
            this.right.inOrderTraverse(consumer);
        }
    }

    public void postOrderTraverse(Consumer<T> consumer) {
        if(this.left != null) {
            this.left.postOrderTraverse(consumer);
        }
        if(this.right != null) {
            this.right.postOrderTraverse(consumer);
        }
        consumer.accept(this.value);
    }

    public void breadthFirstTraverse(Consumer<T> consumer) {
        LinkedList<Tree<T>> list = new LinkedList<>();

        this.distance = 0;
        list.push(this);

        while(!list.isEmpty()) {
            Tree<T> node = list.pollLast();

            consumer.accept(node.value);
            if (node.left !=null && node.left.distance == -1) {
                node.left.distance = node.distance + 1;
                node.left.parent = node;
                list.push(node.left);
            }
            if (node.right !=null && node.right.distance == -1) {
                node.right.distance = node.distance + 1;
                node.right.parent = node;
                list.push(node.right);
            }

        }


    }
}


