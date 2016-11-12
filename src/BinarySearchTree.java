/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the tem plate in the editor.
 */


/**
 *
 * @author Patrick
 */
import java.io.*;
import java.util.*;

public class BinarySearchTree<E extends Comparable<E>> extends AbstractTree<E> {

    protected TreeNode<E> root;
    protected int size = 0;

    /**
     * Create a default binary tree
     */
    public BinarySearchTree() {
    }

    /**
     * Create a binary tree from an array of objects
     */
    public BinarySearchTree(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            insert(objects[i]);
        }
    }

    /**
     * Returns true if the element is in the tree
     */
    public boolean search(E e,int [] count) {
        int i=0;
        TreeNode<E> current = root; // Start from the root
        while (current != null) {
            i++;
            if (e.compareTo(current.element) < 0) {
                current = current.left;
                count[0]=i;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
                count[0]=i;
            } else // element matches current.element
            {
                count[0]=i;
                return true; // Element is found
            }
        }
        return false;
    }

    /**
     * Insert element o into the binary tree Return true if the element is
     * inserted successfully. Uses an iterative algorithm
     */
    public boolean insert(E e) {
        if (root == null) {
            root = createNewNode(e); // Create a new root
        } else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false; // Duplicate node not inserted
                }
            }
            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0) {
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }
        size++;
        return true; // Element inserted
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<E>(e);
    }

    /**
     * Inorder traversal from the root
     */
    public void inorder() {
        inorder(root);
    }

    /**
     * Inorder traversal from a subtree
     */
    protected void inorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    /**
     * Postorder traversal from the root
     */
    public void postorder() {
        postorder(root);
    }

    /**
     * Postorder traversal from a subtree
     */
    protected void postorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    /**
     * Preorder traversal from the root
     */
    public void preorder() {
        preorder(root);
    }

    /**
     * Preorder traversal from a subtree
     */
    protected void preorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    @Override
    public boolean search(E e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Inner class tree node
     */
    public static class TreeNode<E extends Comparable<E>> {

        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }

    /**
     * Get the number of nodes in the tree
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the root of the tree
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Returns an ArrayList containing elements in the path from the root
     * leading to the specified element, returns an empty ArrayList if no such
     * element exists.
     */
    public ArrayList<E> path(E e) {
        java.util.ArrayList<E> list = new java.util.ArrayList<>();
        TreeNode<E> current = root;
        if (current.element == null) {
            return list;
        } else {
            list.add(current.element);
            while (current.element != e) {

                if (current.left == null && current.right == null && current != e) {
                    list.clear();
                    return list;
                }

                if (e.compareTo(current.element) < 0) {
                    current = current.left;
                    list.add(current.element);
                }
                if (e.compareTo(current.element) > 0) {
                    current = current.right;
                    list.add(current.element);
                }
            }

            return list;
        }

    }

    /* Returns the number of leaf nodes in this tree, returns 0 if tree is empty*/
    protected int getNumberofLeaves() {
        return getNumberOfLeaves(root);

    }

    public int getNumberOfLeaves(TreeNode<E> e) {
        //left for you to implement in Lab 7

        TreeNode<E> current = root;
        if (e.left == null && e.right == null) {
            return 1;
        }
        if (e.left != null && e.right != null) {
            return getNumberOfLeaves(e.left) + getNumberOfLeaves(e.right);
        } else if (e.right != null) {
            return getNumberOfLeaves(e.right);
        } else {
            return getNumberOfLeaves(e.left);
        }
    }

    protected ArrayList<E> leftSubTree(TreeNode<E> e) {
        java.util.ArrayList<E> list = new java.util.ArrayList<>();
        //TreeNode<E> current = e;
        if (e != null) {
            list.add(e.element);
        }
        if (e.left != null) {
            list.addAll(leftSubTree(e.left));
        }
        if (e.right != null) {
            list.addAll(leftSubTree(e.right));
        }
        return list;
    }

    /* Returns an ArrayList containing all elements in preorder of the specified element’s left sub-tree, returns an empty ArrayList if no  such element exists. */
    public ArrayList<E> leftSubTree(E e) {
        java.util.ArrayList<E> list = new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root

        while (current.element != e) {
            if (current.left == null && current.right == null && current != e) {
                list.clear();
                return list;
            }
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }

        }
        return leftSubTree(current.left);
    }

    protected ArrayList<E> rightSubTree(TreeNode<E> e) {
        java.util.ArrayList<E> list = new java.util.ArrayList<>();
        TreeNode<E> current = e;
        if (e != null) {
            list.add(e.element);
        }
        if (e.left != null) {
            list.addAll(leftSubTree(e.left));
        }
        if (e.right != null) {
            list.addAll(leftSubTree(e.right));
        }

        return list;
    }

    /* Returns an ArrayList containing all elements in preorder of the specified element’s right sub-tree, returns an empty ArrayList if no  such element exists. */
    public ArrayList<E> rightSubTree(E e) {
        java.util.ArrayList<E> list = new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root

        while (current.element != e) {

            if (current.left == null && current.right == null && current.element != e) {
                list.clear();
                return list;
            }
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }

        }
        return rightSubTree(current.right);
    }

 

    public E inorderPredecessor(E e) {
        //left for you to implement in Lab 7

        TreeNode<E> current = root;
        while (current != e) {
            if (e.compareTo(current.element) < 0) {

                current = current.left;
            } else if (e.compareTo(current.element) > 0) {

                current = current.right;
              }else if(current.left != null){
                current=current.left;
                
                while(current.right != null){
                    current=current.right;
                } 
                break;
              }else{ 
                  return null;
                  
              }
        }             
                        
        
  
        
        return current.element;
    }
    

    protected boolean sameTree(TreeNode<E> tree1, TreeNode<E> tree2){
        if(tree1==null && tree2==null){
        return true;
    }else if (tree1 != null && tree2 !=null){
       return ((tree1.element.compareTo(tree2.element) == 0) &&(sameTree(tree1.left,tree2.left)) &&(sameTree(tree1.right, tree2.right)));
          
       }else{
           return false;
       } 
                   
    
  
    }
    
    public boolean sameTree(BinarySearchTree tree1,BinarySearchTree tree2 ){
        return sameTree(tree1.root, tree2.root);
    }

        
    /**
     * Delete an element from the binary tree. Return true if the element is
     * deleted successfully Return false if the element is not in the tree
     */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break; // Element is in the tree pointed by current
            }
        }
        if (current == null) {
            return false; // Element is not in the tree
        }    // Case 1: current has no left children
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            } else if (e.compareTo(parent.element) < 0) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else {
            // Case 2 & 3: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }
            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else // Special case: parentOfRightMost == current
            {
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true; // Element inserted
    }

    /**
     * Obtain an iterator. Use inorder.
     */
    public java.util.Iterator iterator() {
        return inorderIterator();
    }

    /**
     * Obtain an inorder iterator
     */
    public java.util.Iterator inorderIterator() {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    class InorderIterator implements java.util.Iterator {
        // Store the elements in a list

        private java.util.ArrayList<E> list = new java.util.ArrayList<E>();
        private int current = 0; // Point to the current element in list

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        /**
         * Inorder traversal from the root
         */
        private void inorder() {
            inorder(root);
        }

        /**
         * Inorder traversal from a subtree
         */
        private void inorder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        /**
         * Next element for traversing?
         */
        public boolean hasNext() {
            if (current < list.size()) {
                return true;
            }
            return false;
        }

        /**
         * Get the current element and move cursor to the next
         */
        public Object next() {
            return list.get(current++);
        }

        /**
         * Remove the current element and refresh the list
         */
        public void remove() {
            delete(list.get(current)); // Delete the current element
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        }
    }

    /**
     * Remove all elements from the tree
     */
    public void clear() {
        root = null;
        size = 0;
    }
}
