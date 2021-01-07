// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
        A1List newnode = new A1List(address,size,key);
        A1List t =this;

        newnode.next=t.next;
        t.next.prev=newnode;
        t.next=newnode;
        newnode.prev=t;

        return newnode;
    }

    public boolean Delete(Dictionary d) 
    {
        A1List t1 =this;
        if(t1==null)
            return false;

        else if(t1.key==d.key && t1.address==d.address && t1.size==d.size){
            t1.prev.next=t1.next;
            t1.next.prev=t1.prev;
            return true;
        }    

        A1List t2=this;


        while(t1.prev!=null) {
            t1=t1.prev;

            if(t1.key==d.key && t1.address==d.address && t1.size==d.size){
                t1.prev.next=t1.next;
                t1.next.prev=t1.prev;
                return true;    
            }
        }

        while(t2.next!=null) {
            t2=t2.next;
            if(t2.key==d.key && t2.address==d.address && t2.size==d.size){
                t2.prev.next=t2.next;
                t2.next.prev=t2.prev;
                return true;
            }
        }
   
        return false;
       
    }

    public A1List Find(int k, boolean exact)
    {
        A1List t =this.getFirst();
        if(t==null)
            return null;

        if(t!=null){
            if(exact==true){
                while(t !=null){
                    if(t.key==k)
                        return t;
                    t=t.next;
                }
            }
            else{
                while(t !=null){
                    if(t.key>=k)
                        return t;
                    t=t.next;
                }
            }
        }
        return null;

    }

    public A1List getFirst()
    {

        A1List t=this;
        while(t.prev!=null){
            t=t.prev;
        }
        if(t.next.next==null)
            return null;
        return t.next;
    }
    
    public A1List getNext() 
    {
        if(this==null || this.next.next==null)
            return null;
        else
            return this.next;
    }

    public boolean sanity()
    {
        A1List t = this.getFirst();
        if(t.prev.prev!=null)
            return false;
        while(t!=null){
            if(t.address==-1){
                if(t.next!=null)
                    return false;

            }
            else{
                if(t.next.prev!=t)
                    return false;
            }
        }
        return true;

    }

}


 