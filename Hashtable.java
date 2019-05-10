public class Hashtable<k,v> {
    private HashNode<k,v>[] slots;
    private int items;
    private final double LAMBDA = 0.75;

    public Hashtable(){
        //a hashtable with initial size of 300000
        slots = new HashNode[300000];
        items = 0;
    }

    private int getSlot(String key){
        int slot = key.hashCode(); //java has a hashcode function
        slot = slot%slots.length;
        return slot;
    }

    public boolean containsKey (String key){
        int slot = getSlot(key);
        HashNode node = slots[slot];
        while(node!=null){
            if(node.getKey().equals(key)){ //if the key is equal to node
                return true;
            }
        }
        return false; //if the key does not exist we return false
    }

    public String get (String key){
        int slot = getSlot(key);
        HashNode node = slots[slot];
        while(node!=null){
            //if key is equal to the node
            if(node.getKey().equals(key)){
                return node.getValue(); //return the value of that node
            }
            node = node.getNext(); //move to next node
        }
        return null; //if went through every node but did not find key, return null
    }

    public void put(String key, String value){
        int slot = getSlot(key);
        HashNode node = slots[slot];
        while(node!=null){
            //if key equal to the node (already exists)
            if(node.getKey().equals(key)){
                break;
            }
            node = node.getNext(); //move to next node
        }
        //either null or does not exist
        if(node!=null){ //if not null, we set the value to node
            node.setValue(value);;
        }
        else{
            //check if we need to grow array
            if(items/slots.length >= LAMBDA){
                grow_array();
            }
            //add a new node which will contain new key
            HashNode newnode = new HashNode(key,value);
            newnode.setKey(key);
            newnode.setValue(value);
            newnode.setNext(slots[slot]);
            slots[slot]= newnode;
            items++; //increment
        }
    }

    public String remove (String key){
        int slot = getSlot(key);
        //set a node and previous node
        HashNode node = slots[slot];
        HashNode prevnode = null;
        while(node.getNext()!=null){
            if(!node.getKey().equals(key)){
                //set the prevnode to the current node and current to the next
                prevnode = node;
                node = node.getNext();
            }
        }
        if(node.getKey().equals(key)){
            if(node==null){
                return null;
            }
        }
        if(prevnode==null){
            slots[slot]=node.getNext();
        }
        else{
            prevnode.setNext(node.getNext());
            items--; //decrement
        }
        return node.getValue();
    }

    protected void grow_array(){
        //new temp with size doubled
        HashNode[] temp = new HashNode[slots.length*2];
        for(int i=0; i<slots.length;i++){
            HashNode node = slots[i];
            while (node != null) {
                HashNode next = node.getNext();
                int hash = node.getKey().hashCode();
                hash = hash%temp.length;
                node.setNext(temp[hash]);
                temp[hash] = node;
                node = next;
            }
        }
        slots = temp;
    }
}
