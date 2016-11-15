//package com.gmail.murmeldjur.binarytree;


//import com.sun.org.apache.xpath.internal.SourceTree;

public class BinaryTree {

    Node root;

    public void addNode(int key, String name) {

        Node newNode = new Node(key, name);

        if (root == null) {

            root = newNode;
        } else {

            Node focusNode = root;

            Node parent;

            while (true) {
                parent = focusNode;

                if (key < focusNode.key) {

                    focusNode = focusNode.leftChild;
                    if (focusNode == null) {

                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    focusNode = focusNode.rightChild;

                    if (focusNode == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    public void inOrderTraverseTree(Node focusNode) {

        if (focusNode != null) {
            inOrderTraverseTree(focusNode.leftChild);
            System.out.println(focusNode);
            inOrderTraverseTree(focusNode.rightChild);
        }
    }

    public void preOrderTraverseTree(Node focusNode) {

        if (focusNode != null) {
            System.out.println(focusNode);
            preOrderTraverseTree(focusNode.leftChild);
            preOrderTraverseTree(focusNode.rightChild);
        }
    }

    public void postOrderTraverseTree(Node focusNode) {

        if (focusNode != null) {

            postOrderTraverseTree(focusNode.leftChild);
            postOrderTraverseTree(focusNode.rightChild);

            System.out.println(focusNode);
        }
    }
    public Node findNode(int key) {

        Node focusNode = root;

        while (focusNode.key != key) {

            if (key < focusNode.key) {

                focusNode = focusNode.leftChild;
            } else {

                focusNode = focusNode.rightChild;
            }
            if (focusNode == null)
                return null;
        }
        return focusNode;
    }

    public boolean remove(int key) {

        Node focusNode = root;
        Node parent = root;

        boolean isItALeftChild = true;

        while (focusNode.key != key) {
            parent = focusNode;

            if (key < focusNode.key) {
                isItALeftChild = true;
                focusNode = focusNode.leftChild;
            } else {
                isItALeftChild = false;
                focusNode = focusNode.rightChild;
            }
            if (focusNode == null)
                return false;
        }
        if (focusNode.leftChild == null && focusNode.rightChild == null) {

            if (focusNode == root)
                root = null;

            else if (isItALeftChild)
                parent.leftChild = null;

            else
                parent.leftChild = null;
        } else if (focusNode.rightChild == null) {
            if (focusNode == root)
                root = focusNode.leftChild;
            else if (isItALeftChild)
                parent.leftChild = focusNode.leftChild;
            else
                parent.rightChild = focusNode.leftChild;
        } 
                    // If no left child

	        else if (focusNode.leftChild == null) {
                if (focusNode == root)
                    root = focusNode.rightChild;
                    // If focus Node was on the left of parent
                    // move the focus Nodes right child up to the
                    // parent node
                else if (isItALeftChild)
                    parent.leftChild = focusNode.rightChild;
                    // Vice versa for the left child
                else
                    parent.rightChild = focusNode.rightChild;
            }
            // Two children so I need to find the deleted nodes
            // replacement

            else {
                Node replacement = getReplacementNode(focusNode);
                // If the focusNode is root replace root
                // with the replacement
                if (focusNode == root)
                    root = replacement;
                    // If the deleted node was a left child
                    // make the replacement the left child

                else if (isItALeftChild)
                    parent.leftChild = replacement;
                    // Vice versa if it was a right child
                else
                    parent.rightChild = replacement;

                replacement.leftChild = focusNode.leftChild;
            }
            return true;
        }
    

    public Node getReplacementNode(Node replacedNode) {
        Node replacementParent = replacedNode;
        Node replacement = replacedNode;
        Node focusNode = replacedNode.rightChild;

        // While there are no more left children
        while (focusNode != null) {
            replacementParent = replacement;
            replacement = focusNode;
            focusNode = focusNode.leftChild;
        }
        // If the replacement isn't the right child
        // move the replacement into the parents
        // leftChild slot and move the replaced nodes
        // right child into the replacements rightChild
        if (replacement != replacedNode.rightChild) {
            replacementParent.leftChild = replacement.rightChild;

            replacement.rightChild = replacedNode.rightChild;
        }
        return replacement;
    }


    public static void main(String[] args) {
        BinaryTree theTree = new BinaryTree();

        theTree.addNode(25, "Boss");
        theTree.addNode(50, "Vice pres.");
        theTree.addNode(15, "Office Manager");
        theTree.addNode(30, "Secretary");
        theTree.addNode(75, "Sales Manager");
        theTree.addNode(85, "Salesman 1");

        theTree.inOrderTraverseTree(theTree.root);
        System.out.println("");
        System.out.println("--Pre Order Traversal--");
        theTree.preOrderTraverseTree(theTree.root);
        System.out.println("");
        System.out.println("--Post Order Traversal--");
        theTree.postOrderTraverseTree(theTree.root);

        System.out.println("Search for 85");

        System.out.println(theTree.findNode(85));

    }
}

class Node {
    int key;
    String name;

    Node leftChild;
    Node rightChild;

    Node(int key, String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " has key " + key;
    }
}
