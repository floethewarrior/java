import java.util.HashMap;
import java.util.Map;

public class LRU<K, V> {
    public class Node {
        public Node(V v) {
            this.value = v;
        }
        Node next;
        Node prev;
        V value;
    }

    private final Map<K,Node> map;
    private Node head;    
    public LRU() {
        map = new HashMap<K, Node>();
        head = null;
    }

    public void put(K k, V v) {
        Node n = map.get(k);
        if (n != null) {
            delete(k);
        }
        n = new Node(v);
        promoteNode(n);
        map.put(k, n);
    }

    public V get(K k) {
        Node n = map.get(k);
        if (n != null) {
            promoteNode(n);
            return n.value;
        }
        return null;
    }

    public V last() {
        return head.value;
    }

    public void delete(K k) {
        Node n = map.get(k);
        if (n != null) {
            deleteNode(n);
            map.remove(k);
        }
    }

    private void deleteNode(Node n) {
        if (n.next != null) {
          n.next.prev = n.prev;
        }
        if (n.prev != null) {
            n.prev.next = n.next;
        }
    }

    private void promoteNode(Node n) {
        deleteNode(n);
        n.next = head;
        head = n;        
    }

    public static void main(String [] args) {
        LRU<Integer, String> lru = new LRU<Integer, String>();
        System.out.println("Put 1 : A");
        lru.put(1, "A");
        System.out.println("Last: ");
        System.out.println(lru.last()); 
        System.out.println("Put 2 : B");
        lru.put(2, "B");
        System.out.println("Put 3 : C");
        lru.put(3, "C");
        System.out.println("Last: ");
        System.out.println(lru.last()); 
        System.out.println("Get 2");
        System.out.println(lru.get(2));
        System.out.println("Last:");
        System.out.println(lru.last());


    }
}