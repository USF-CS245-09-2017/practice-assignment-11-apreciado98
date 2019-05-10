public class HashNode<k,v>{
    private String key;
    private String value;
    private HashNode<String,String> next;

    public HashNode(String key, String value){
        this.key = key;
        this.value = value;
        next = null;
    }
    //accessor/mutator methods
    public String getKey(){
        return key;
    }
    public String getValue(){
        return value;
    }
    public HashNode getNext(){
        return next;
    }
    public void setKey(String key){
        this.key = key;
    }
    public void setValue(String value){
        this.value = value;
    }
    public void setNext(HashNode<String,String> next){
        this.next = next;
    }
}