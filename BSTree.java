// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java
package com.company;
public class BSTree extends Tree {

    private BSTree left, right; // Children.
    private BSTree parent; // parent pointer.

    public BSTree() {
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root!
        // and left child will always be null.
    }

    public BSTree(int address, int size, int key) {
        super(address, size, key);
    }

    public BSTree Insert(int address, int size, int key) {
        if (this == null)
            return null;

        BSTree newnode = new BSTree(address, size, key);

        BSTree t = this;
        while (t.parent != null) {
            t = t.parent;
        }
        if (t.right == null) {
            t.right = newnode;
            newnode.parent = t;
            return newnode;
        }

        BSTree t1 = t;
        t = t.right;
        while (t != null) {
            t1 = t;
            if (newnode.key < t.key) {
                t = t.left;
            } else if (newnode.key > t.key) {
                t = t.right;
            } else if (newnode.key == t.key && newnode.address < t.address) {
                t = t.left;
            } else {
                t = t.right;
            }
        }
        if (t1.key > newnode.key) {
            t1.left = newnode;
            newnode.parent = t1;
            return newnode;
        }
        if (t1.key < newnode.key) {
            t1.right = newnode;
            newnode.parent = t1;
            return newnode;
        }
        if (t1.key == newnode.key && newnode.address < t1.address) {
            t1.left = newnode;
            newnode.parent = t1;
            return newnode;
        }
        if (t1.key == newnode.key && newnode.address > t1.address) {
            t1.right = newnode;
            newnode.parent = t1;
            return newnode;
        }
        return newnode;
    }

    public boolean Delete(Dictionary e) {
        if (this == null)
            return false;

        BSTree t = this;
        while (t.parent != null) {
            t = t.parent;
        }
        if (t.right == null)
            return false;
        else
            t = t.right;

        while (t != null) {
            if (t!=null && e.key < t.key) {
                t = t.left;
            }
            if (t!=null && e.key > t.key) {
                t = t.right;
            }
            if (t!=null && e.key == t.key && e.address < t.address) {
                t = t.left;
            }
            if (t!=null && e.key == t.key && e.address > t.address) {
                t = t.right;
            }
            if (t!=null && e.key == t.key && e.address == t.address) {
                if (t.right == null && t.left == null) {
                    if (t.parent.right == t) {
                        t = t.parent;
                        t.right = null;
                        return true;
                    } else {
                        t = t.parent;
                        t.left = null;
                        return true;
                    }
                } else if (t.right == null && t.left != null) {
                    if (t.parent.right == t) {
                        t.parent.right = t.left;
                        t.left.parent = t.parent;
                        return true;
                    } else {
                        t.parent.left = t.left;
                        t.left.parent = t.parent;
                        return true;
                    }
                } else if (t.right != null && t.left == null) {
                    if (t.parent.right == t) {
                        t.parent.right = t.right;
                        t.right.parent = t.parent;
                        return true;
                    } else {
                        t.parent.left = t.right;
                        t.right.parent = t.parent;
                        return true;
                    }

                } else {
                    BSTree t1 = t.getNext();
                    if (t.right == t1) {
                        if (t.parent.left == t) {
                            t.parent.left = t1;
                            t1.parent = t.parent;
                            t1.left = t.left;
                            t.left.parent = t1;
                            return true;
                        } else {
                            t.parent.right = t1;
                            t1.parent = t.parent;
                            t1.left = t.left;
                            t.left.parent = t1;
                            return true;
                        }
                    } else {
                        if (t.parent.left == t) {
                            if (t1.right != null) {
                                t1.parent.left = t1.right;
                                t1.right.parent = t1.parent;
                            }
                            t.parent.left = t1;
                            t1.parent = t.parent;
                            t1.right = t.right;
                            t1.left = t.left;
                            t.left.parent = t1;
                            t.right.parent = t1;
                            return true;
                        } else {
                            if (t1.right != null) {
                                t1.parent.left = t1.right;
                                t1.right.parent = t1.parent;
                            }
                            t.parent.right = t1;
                            t1.parent = t.parent;
                            t1.right = t.right;
                            t1.left = t.left;
                            t.left.parent = t1;
                            t.right.parent = t1;
                            return true;
                        }
                    }
                }
            }
        }
        return false;

    }

    public BSTree Find(int key, boolean exact) {
        // BSTree t = this.getNext();
        // if(t==null)
        // return null;
        // if(t!=null){
        // if(exact==true){
        // while(t!=null){
        // if(t.key==key)
        // return t;
        // t=t.getNext();
        // }

        // }
        // else{
        // BSTree t1 = null;
        // while(t!=null){
        // if(t.key>=key){
        // if(t1.key>t.key){
        // t1=t;
        // }
        // }
        // t=t.getNext();
        // }
        // return t1;
        // }
        // }

        BSTree t = this;
        if (this == null)
            return null;
        while (t.parent != null) {
            t = t.parent;
        }
        if (t.right == null)
            return null;

        t = t.right;

        if (exact == true) {
            while (t != null) {
                if (t.key > key)
                    t = t.left;
                else if (t.key < key) {
                    t = t.right;
                } else {
                    if (t.left != null) {
                        while (t.getPrevious().key == key && t.getPrevious() != null) {
                            t = t.getPrevious();
                        }
                        return t;
                    } else
                        return t;
                }
            }
        } else {
            BSTree t1 = null;
            while (t != null) {
                if (t.key > key) {
                    t1 = t;
                    t = t.left;
                } else if (t.key < key) {
                    t = t.right;
                } else {
                    if (t.left != null) {
                        while (t.getPrevious().key == key && t.getPrevious() != null) {
                            t = t.getPrevious();
                        }
                        return t;
                    } else
                        return t;
                }
            }
            return t1;
        }
        return null;
    }

    private BSTree getPrevious() // taking only cases when t.left!=null
    {
        BSTree t = this;
        t = t.left;
        while (t != null) {
            if (t.right != null) {
                t = t.right;
            } else { //  without this statement the while loop will only break if t ==
                // null, and this will never happen so it will form a infinite loop.
                break;
            }
        }
        return t;
    }

    public BSTree getFirst() {
        BSTree t = this;
        if (this == null)
            return null;
        while (t.parent != null) {
            t = t.parent;
        }
        if (t.right == null)
            return null;

        BSTree t1 = t.right;
        while (t1.left != null) {
            t1 = t1.left;
        }
        return t1;
    }

    public BSTree getNext() {
        BSTree t = this;
        BSTree t1 = this;
        if (this == null)
            return null;

        if (t.right != null) {
            t1 = t.right;
            while (t1.left != null) {
                t1 = t1.left;
            }
            return t1;
        }
        if (t.parent == null)
            return t.right;
        else if (t.parent.left == t) {
            return t.parent;

        } else {
            while (t.parent.left != t) {
                t = t.parent;
                if (t.parent == null)
                    return null;
            }
            return t.parent;

        }

    }

    public boolean sanity()
    {
        BSTree t = this;
        while (t.parent != null) {
            t = t.parent;
        }

        BSTree t1 = t.right;

        if (t.parent == null){
            if (t.left == null){
                if (t.key == -1 && t.address == -1 && t.size == -1){
                    return true;
                }
            }
        }
        while (t1!= null){
            while (t1.left != null){
                if (t1.key < t1.left.key){
                    return false;
                }
                else{
                    t1 = t1.left;
                }
            }
            while (t1.right != null){
                if (t1.key > t1.right.key){
                    return false;
                }
                else{
                    t1 = t1.right;
                }
            }
        }

        return false;
    }

}