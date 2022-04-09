// 编写同时定义多种类型的泛型
public class Pair<T, K> {
    private T first;
    private K last;
    public Pair(T first, K last) {
        this.first = first;
        this.last = last;
    }
    public T getFirst() {
        return first; 
    }
    public K getLast() {
        return last;
    }
}