import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

public class ObjectNode extends Node implements Iterable<String> {
    private TreeMap<String, Node> map;

    public ObjectNode() {
        this.map = new TreeMap<>();
    }

    public Node get(String key) {
        return map.get(key);
    }

    public void set(String key, Node node) {
        map.put(key, node);
    }

    public int size() {
        return map.size();
    }

    public Iterator<String> iterator() {
        return map.keySet().iterator();
    }
}
