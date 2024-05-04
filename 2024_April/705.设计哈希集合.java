class MyHashSet {
    boolean[] set_bool = new boolean[1000001];

    public void add(int key) {
        set_bool[key] = true;
    }

    public void remove(int key) {
        set_bool[key] = false;
    }

    public boolean contains(int key) {
        return set_bool[key];
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */