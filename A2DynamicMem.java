// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 
    //Your BST (and AVL tree) implementations should obey the property that keys in the left subtree <= root.key < keys in the right subtree. How is this total order between blocks defined? It shouldn't be a problem when using key=address since those are unique (this is an important invariant for the entire assignment123 module). When using key=size, use address to break ties i.e. if there are multiple blocks of the same size, order them by address. Now think outside the scope of the allocation problem and think of handling tiebreaking in blocks, in case key is neither of the two. 
    public void Defragment() {

        Dictionary newBlk;
        if(type==2){
            newBlk = new BSTree();
        }
        else{
            newBlk = new AVLTree();
        }

        if (freeBlk.getFirst() == null) {
            return;
        }
        Dictionary t = freeBlk.getFirst();
        while (t != null) {
            newBlk.Insert(t.address, t.size, t.address);
            t = t.getNext();
        }
        Dictionary t1 = newBlk.getFirst();
        Dictionary t2 = t1;
        Dictionary t3 = t1;
        Dictionary t4 = t2;
        while (t1.getNext() != null) {
            t2 = t1.getNext();
            if (t1.address + t1.size == t2.address) {
                if(type==2) {
                    t3 = new BSTree(t1.address, t1.size, t1.size);
                    t4 = new BSTree(t2.address, t2.size, t2.size);
                }
                else{
                    t3 = new AVLTree(t1.address, t1.size, t1.size);
                    t4 = new AVLTree(t2.address, t2.size, t2.size);

                }
                newBlk.Delete(t1);
                newBlk.Delete(t2);
                newBlk.Insert(t3.address, t3.size + t4.size, t3.address);
                freeBlk.Delete(t3);
                freeBlk.Delete(t4);
                freeBlk.Insert(t3.address, t3.size + t4.size, t3.size + t4.size);
                t1 = t2;
            } else {
                t1 = t2;
            }
        }
        return;
    }
}


  
  
