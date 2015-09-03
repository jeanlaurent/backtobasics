package net.morlhon.exos.tree;

import java.util.function.Consumer;

public class Tree<T> {
    T value;
    Tree<T> left;
    Tree<T> right;

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
    
}


