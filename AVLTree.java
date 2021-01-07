// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    
    public AVLTree Insert(int address, int size, int key)
    {
        if (this == null)
            return null;

        AVLTree newnode = new AVLTree(address, size, key);

        AVLTree t = this;
        while (t.parent != null) {
            t = t.parent;
        }
        if (t.right == null) {
            t.right = newnode;
            newnode.parent = t;
            return newnode;
        }

        AVLTree t1 = t;
        AVLTree t2 = t; //..
        t = t.right;
        while (t != null) {
            t1 = t;
            if (newnode.key < t.key) {
                t = t.left;
            } else if (newnode.key > t.key) {
                t = t.right;
            } else if (newnode.key == t.key && newnode.address  < t.address) {
                t = t.left;
            } else {
                t = t.right;
            }
        }
        if (t1.key > newnode.key) {
            t1.left = newnode;
            newnode.parent = t1;
            t2=t1;                           //..
            UpdateHeight(t2);                //..
            CheckbalancedNode(t1);           //..
            return newnode;
        }
        if (t1.key < newnode.key) {
            t1.right = newnode;
            newnode.parent = t1;
            t2=t1;                           //..
            UpdateHeight(t2);                //..
            CheckbalancedNode(t1);           //..
            return newnode;
        }
        if (t1.key == newnode.key && newnode.address < t1.address) {
            t1.left = newnode;
            newnode.parent = t1;
            t2=t1;                           //..
            UpdateHeight(t2);                //..
            CheckbalancedNode(t1);           //..
            return newnode;
        }
        if (t1.key == newnode.key && newnode.address > t1.address) {
            t1.right = newnode;
            newnode.parent = t1;
            t2=t1;                           //..
            UpdateHeight(t2);                //..
            CheckbalancedNode(t1);           //..
            return newnode;
        }
        return newnode;
    }


    // .private functions
    // .
    // .
    // .
    // .
    // .

    private int Height(AVLTree t)
    {
        if(t==null)
            return -1;
        return t.height;
    }

    private int Max(int a,int b)
    {
        if(a>=b)
            return a;
        else{
            return b;
        }
    }

    private void UpdateHeight(AVLTree t)//t is leaf node so height is 0
    {
        while(t.parent!=null){
//            if(t.height!=Max(Height(t.right),Height(t.left))+1){
            t.height=Max(Height(t.right),Height(t.left))+1;
            t=t.parent;
//            }
//            else{
//                break;
//            }
        }
        return;

    }

    private void CheckbalancedNode(AVLTree t)
    {
        AVLTree t1=t;
        if(t.parent==null)
            return ;
        while(t.parent!=null){
            int a =Height(t.left)-Height(t.right);
            if(a<=1 && a>=-1){
                t=t.parent;
            }
            else{
                ReBalance(t);
                t1=t;
                UpdateHeight(t1);
                t=t.parent;
            }

        }
        return;
    }

    private AVLTree LeftRotate(AVLTree t)
    {
        AVLTree t1 = t.right;
        t.right = t1.left;
        if(t1.left!=null){
            t1.left.parent=t;
        }
        t1.left = t;
        t1.parent=t.parent;
        t.parent=t1;
        t.height = Max(Height(t.right),Height(t.left))+1;
        t1.height = Max(Height(t1.right),Height(t1.left))+1;
        return t1;
    }

    private AVLTree RightRotate(AVLTree t)
    {
        AVLTree t1 = t.left;
        t.left = null;
        if(t1.right!=null){
            t.left=t1.right;
            t1.right.parent=t;
        }
        t1.right = t;
        t1.parent=t.parent;
        t.parent=t1;
        t.height = Max(Height(t.right),Height(t.left))+1;
        t1.height = Max(Height(t1.right),Height(t1.left))+1;
        return t1;
    }

    private AVLTree RightLeftRotate(AVLTree t)
    {
        t.right = RightRotate(t.right);
        return LeftRotate(t);
    }

    private AVLTree LeftRightRotate(AVLTree t)
    {
        t.left = LeftRotate(t.left);
        return RightRotate(t);
    }

    private void ReBalance(AVLTree t)
    {
        if(Height(t.left)-Height(t.right)>1){
            AVLTree par = t.parent;
            if(Height(t.left.left)>Height(t.left.right)){//correction
                if(par.left==t) {
                    t = RightRotate(t);
                    par.left=t;
                    t.parent=par;
                }else {
                    t = RightRotate(t);
                    par.right=t;
                    t.parent= par;
                }

            }
            else{
                if(par.left==t) {
                    t=LeftRightRotate(t);
                    par.left=t;
                    t.parent=par;
                }else {
                    t=LeftRightRotate(t);
                    par.right=t;
                    t.parent= par;
                }

            }
        }
        else{
            AVLTree par = t.parent;
            if(Height(t.right.right)>Height(t.right.left)){//correction
                if(par.left==t) {
                    t = LeftRotate(t);
                    par.left=t;
                    t.parent=par;
                }else {
                    t = LeftRotate(t);
                    par.right=t;
                    t.parent= par;
                }
            }
            else{
                if(par.left==t) {
                    t=RightLeftRotate(t);
                    par.left=t;
                    t.parent=par;
                }else {
                    t=RightLeftRotate(t);
                    par.right=t;
                    t.parent= par;
                }

            }
        }
        return;
    }

    // .
    // .
    // .
    // .
    // .
    // .private functions

    public boolean Delete(Dictionary e)
    {
        if (this == null)
            return false;

        AVLTree t = this;
        AVLTree t2 = t; //..
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
                        t2=t;                         //..
                        UpdateHeight(t2);             //..
                        CheckbalancedNode(t);         //..
                        return true;
                    } else {
                        t = t.parent;
                        t.left = null;
                        t2=t;                         //..
                        UpdateHeight(t2);             //..
                        CheckbalancedNode(t);         //..
                        return true;
                    }
                } else if (t.right == null && t.left != null) {
                    if (t.parent.right == t) {
                        t.parent.right = t.left;
                        t.left.parent = t.parent;
                        t=t.left;                     //..
                        t2=t;                         //..
                        UpdateHeight(t2);             //..
                        CheckbalancedNode(t);         //..
                        return true;
                    } else {
                        t.parent.left = t.left;
                        t.left.parent = t.parent;
                        t=t.left;                     //..
                        t2=t;                         //..
                        UpdateHeight(t2);             //..
                        CheckbalancedNode(t);         //..
                        return true;
                    }
                } else if (t.right != null && t.left == null) {
                    if (t.parent.right == t) {
                        t.parent.right = t.right;
                        t.right.parent = t.parent;
                        t=t.right;                      //..
                        t2=t;                           //..
                        UpdateHeight(t2);               //..
                        CheckbalancedNode(t);           //..
                        return true;
                    } else {
                        t.parent.left = t.right;
                        t.right.parent = t.parent;
                        t=t.right;                       //..
                        t2=t;                            //..
                        UpdateHeight(t2);                //..
                        CheckbalancedNode(t);            //..
                        return true;
                    }

                } else {
                    AVLTree t1 = t.getNext();
                    if (t.right == t1) {
                        if (t.parent.left == t) {
                            t.parent.left = t1;
                            t1.parent = t.parent;
                            t1.left = t.left;
                            t.left.parent = t1;
                            t=t.right;                    //..
                            t2=t;                         //..
                            UpdateHeight(t2);             //..
                            CheckbalancedNode(t);         //..
                            return true;
                        } else {
                            t.parent.right = t1;
                            t1.parent = t.parent;
                            t1.left = t.left;
                            t.left.parent = t1;
                            t=t.right;                     //..
                            t2=t;                          //..
                            UpdateHeight(t2);              //..
                            CheckbalancedNode(t);          //..
                            return true;
                        }
                    } else {
                        if (t.parent.left == t) {
                            if (t1.right != null) {
                                t1.parent.left = t1.right;
                                t1.right.parent = t1.parent;
                            }
                            if(t1.right==null){
                                t1.parent.left=null;
                            }                               //..
                            t2=t1.parent;                   //..
                            t.parent.left = t1;
                            t1.parent = t.parent;
                            t1.right = t.right;
                            t1.left = t.left;
                            t.left.parent = t1;
                            t.right.parent = t1;
                            t=t2;                            //..
                            UpdateHeight(t2);                //..
                            CheckbalancedNode(t);            //..
                            return true;
                        } else {
                            if (t1.right != null) {
                                t1.parent.left = t1.right;
                                t1.right.parent = t1.parent;
                            }
                            if(t1.right==null){
                                t1.parent.left=null;
                            }                                //..
                            t2=t1.parent;                    //..
                            t.parent.right = t1;
                            t1.parent = t.parent;
                            t1.right = t.right;
                            t1.left = t.left;
                            t.left.parent = t1;
                            t.right.parent = t1;
                            t2=t;                            //..
                            UpdateHeight(t2);                //..
                            CheckbalancedNode(t);            //..
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public AVLTree Find(int key, boolean exact)
    {
        AVLTree t = this;
        while(t.parent != null) {
            t = t.parent;
        }

        if(t.right == null) {
            return null;
        }
        t = t.right;

        if(exact == true) {
            while(t != null ) {
                if(t.key == key) {

                    if(t.left == null ) {
                        return t;
                    }
                    else if(t.left.key == key ) {
                        t = t.left;
                    }
                    else {
                        return t;
                    }
                }
                else if(key > t.key ) {
                    t = t.right;
                }
                else {
                    t = t.left;
                }
            }
            return null;
        }

        else if(exact == false) {
            AVLTree t1 = null;
            while(t != null || t1 != null) {
                if(t1 != null && t1.key >= key) {
                    if(t1.left == null && t1.right == null) {
                        return t1;
                    }
                    else if(t1.left != null && t1.left.key >= key ) {
                        t1 = t1.left;
                        t = t1.left;
                    }
                    else {
                        return t1;
                    }
                }
                else if(t.key < key) {
                    t = t.right;
                }else {
                    t1 = t;
                    t= t.left;
                }
            }
            return null;
        }
        return null;
    }

    public AVLTree getFirst()
    {
        AVLTree t = this;
        if (this == null)
            return null;
        while (t.parent != null) {
            t = t.parent;
        }
        if (t.right == null)
            return null;

        AVLTree t1 = t.right;
        while (t1.left != null) {
            t1 = t1.left;
        }
        return t1;
    }

    public AVLTree getNext()
    {
        AVLTree t = this;
        AVLTree t1 = this;
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
        AVLTree t=this;
        if (t.parent != null && t.parent.parent != null){
            AVLTree t1 = t.parent;
            AVLTree t2 = t1.parent;
            while (t2 != null && t2.parent != null){
                if (t1 == t2){
                    return false;
                }
                t2 = t2.parent.parent;
                t1 = t1.parent;
            }
        }
        if(BSTchecker(t)==false)
            return false;
        if(HeightBalanceCheck(t)==false)
            return false;
        return true;
    }

    // .
    // .
    // .
    // .pvt fns.
    private boolean BSTchecker(AVLTree t)
    {
        AVLTree t1=t.getFirst();
        while(t1.getNext()!=null){
            if(t1.key>t1.getNext().key){
                return false;
            }
            if(t1.key==t1.getNext().key && t1.address>t1.getNext().address){
                return false;
            }
            t1=t1.getNext();
        }
        return true;
    }

    private boolean HeightBalanceCheck(AVLTree t)//check both update height and balance height
    {
        if(t==null)
            return false;
        AVLTree t1 = t.getFirst();
        while(t1.parent!=null){
            while(t1.parent!=null){
                if(Height(t1)!=1+Max(Height(t1.left),Height(t1.right))){
                    return true;
                }
                t1=t1.getNext();
            }
            while(t.parent!=null){
                if(Height(t.left)-Height(t.right)>=1 && Height(t.left)-Height(t.right)<=-1){
                    return true;
                }
                t=t.getNext();
            }
        }
        return false;
    }
    // .
    // .
    // .
    // .pvt fun

}


