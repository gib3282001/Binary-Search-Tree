public class IterativeBST<Key extends Comparable<Key>, Value> implements A1Interface<Key, Value>{
        private Node root;             // root of BST

    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    // is the symbol table empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return number of key-value pairs in BST
    public int size() {
        return size(root);
    }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

   /***********************************************************************
    *  Search BST for given key, and return associated value if found,
    *  return null if not found
    ***********************************************************************/
    // does there exist a key-value pair with given key?
    public boolean contains(Key key) {
        return get(key) != null;
    }

    // return value associated with the given key, or null if no such key exists
    public Value get(Key key) {
        return get(root, key);
    }
    private Value get(Node curr, Key key){
        while(curr != null){
            int cmp = key.compareTo(curr.key);
            if(cmp < 0) curr = curr.left;
            else if(cmp > 0) curr = curr.right;
            else{
                return curr.val;
            } 
        }
        return null;
    }

   /***********************************************************************
    *  Insert key-value pair into BST
    *  If key already exists, update with new value
    ***********************************************************************/
    public void put(Key key, Value val) {
        if (val == null) { delete(key); return; }
        boolean contain = contains(key);
        if(contain){
            root = put(root, key, val);
        }else{
            root = putAndSum(root, key, val);
        }
        assert isBST();
    }

    private Node putAndSum(Node x, Key key, Value val){
        if(x == null) return new Node(key, val, 1);
        Node curr = x;
        Node parent = null;
        while(curr != null){
            parent = curr;
            int cmp = key.compareTo(curr.key);
            if(cmp < 0){
                curr = curr.left;
            }else if(cmp > 0){
                curr = curr.right;
            }else{
                curr.val = val;
                return x;
            }
            parent.N += 1;
        }
        int cmp = key.compareTo(parent.key);
        if(cmp < 0){
            parent.left = new Node(key, val, 1);
        }else{
            parent.right = new Node(key, val, 1);
        }
        return x;
    }

    private Node put(Node x, Key key, Value val) {
        if(x == null) x = new Node(key, val, 1);
        Node curr = x;
        Node parent = null;
        while(curr != null){
            parent = curr;
            int cmp = key.compareTo(curr.key);
            if(cmp < 0){
                curr = curr.left;
            }else if(cmp > 0){
                curr = curr.right;
            }else{
                curr.val = val;
                return x;
            }
        }
        int cmp = key.compareTo(parent.key);
        if(cmp < 0){
            parent.left = new Node(key, val, 1);
        }else{
            parent.right = new Node(key, val, 1);
        }
        return x;
    }

   /***********************************************************************
    *  Delete
    ***********************************************************************/

    public void deleteMin() {
        if (isEmpty()) throw new RuntimeException("Symbol table underflow");
        root = deleteMin(root);
        assert isBST();
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMini(){
        if (isEmpty()) throw new RuntimeException("Symbol table underflow");
        root = deleteMini(root);
        assert isBST();
    }

    private Node deleteMini(Node x){
        Node curr = x;
        Node parent = null;
        if(x.left == null){
            return x.right;
        }
        while(curr.left != null){
            parent = curr;
            curr = curr.left;
            parent.N -= 1;
        }
        parent.left = curr.right;
        return x;
    }

    public void deleteMax() {
        if (isEmpty()) throw new RuntimeException("Symbol table underflow");
        root = deleteMax(root);
        assert isBST();
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        if(contains(key)){
            root = delete(root, key);
        }
        assert isBST();
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        Node toDelete = x;
        Node parent = null;

        while(!(toDelete.key.equals(key))){
            parent = toDelete;
            int cmp = key.compareTo(toDelete.key);
            if(cmp < 0){
                toDelete = toDelete.left;
            }else{
                toDelete = toDelete.right;
            }
            parent.N -= 1;
        }

        if(toDelete.left == null && toDelete.right == null){
            if(toDelete == root){
                return null;
            }
            int cmp = key.compareTo(parent.key);
            if(cmp < 0){
                parent.left = null;
            }else{
                parent.right = null;
            }
            parent.N = size(parent.right) + size(parent.left) + 1;
        }else if(toDelete.left == null){
            if(toDelete == root){
                return toDelete.right;
            }
            int cmp = key.compareTo(parent.key);
            if(cmp < 0){
                parent.left = toDelete.right;
            }else{
                parent.right = toDelete.right;
            }
            parent.N = size(parent.right) + size(parent.left) + 1;
        }else if(toDelete.right == null){
            if(toDelete == root){
                return toDelete.left;
            }
            int cmp = key.compareTo(parent.key);
            if(cmp < 0){
                parent.left = toDelete.left;
            }else{
                parent.right = toDelete.left;
            }
            parent.N = size(parent.right) + size(parent.left) + 1;
        }else{
            Node original = toDelete;
            Node successor = mini(original.right);
            toDelete.key = successor.key;
            toDelete.val = successor.val;
            toDelete.right = deleteMini(original.right);
            toDelete.left = original.left;
            if(original == root){
                toDelete.N = size(toDelete.right) + size(toDelete.left) + 1;
                return toDelete;
            }
        }
        return x;
    } 


   /***********************************************************************
    *  Min, max, floor, and ceiling
    ***********************************************************************/
    public Key min() {
        if (isEmpty()) return null;
        return min(root).key;
    } 

    private Node min(Node x) { 
        if (x.left == null) return x; 
        else                return min(x.left); 
    }
    
    public Key mini() {
        if (isEmpty()) return null;
        return min(root).key;
    } 

    private Node mini(Node x) { 
        Node curr = x;
        while(curr.left != null){
            curr = curr.left;
        } 
        return curr;
    } 

    public Key max() {
        if (isEmpty()) return null;
        return max(root).key;
    } 

    private Node max(Node x) { 
        if (x.right == null) return x; 
        else                 return max(x.right); 
    } 

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    } 

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp <  0) return floor(x.left, key);
        Node t = floor(x.right, key); 
        if (t != null) return t;
        else return x; 
    } 

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) { 
            Node t = ceiling(x.left, key); 
            if (t != null) return t;
            else return x; 
        } 
        return ceiling(x.right, key); 
    } 

   /***********************************************************************
    *  Rank and selection
    ***********************************************************************/
    public Key select(int k) {
        if (k < 0 || k >= size())  return null;
        Node x = select(root, k);
        return x.key;
    }

    // Return key of rank k. 
    private Node select(Node x, int k) {
        if (x == null) return null; 
        int t = size(x.left); 
        //System.out.println(x.key + ": " + t);
        if      (t > k) return select(x.left,  k); 
        else if (t < k) return select(x.right, k-t-1); 
        else            return x; 
    } 

    public int rank(Key key) {
        return rank(key, root);
    } 

    // Number of keys in the subtree less than x.key. 
    private int rank(Key key, Node x) {
        if (x == null) return 0; 
        int cmp = key.compareTo(x.key); 
        if      (cmp < 0) return rank(key, x.left); 
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right); 
        else              return size(x.left); 
    } 

   /***********************************************************************
    *  Range count and range search.
    ***********************************************************************/
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    } 

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) { 
        if (x == null) return; 
        int cmplo = lo.compareTo(x.key); 
        int cmphi = hi.compareTo(x.key); 
        if (cmplo < 0) keys(x.left, queue, lo, hi); 
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key); 
        if (cmphi > 0) keys(x.right, queue, lo, hi); 
    } 

    public int size(Key lo, Key hi) {
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else              return rank(hi) - rank(lo);
    }



  /*************************************************************************
    *  Check integrity of BST
    *************************************************************************/
    // is this tree a BST?
    private boolean isBST() {
        if (isEmpty()) return true;
        if (!isBinaryTree())     StdOut.println("Subtree counts not consistent");
        if (!isOrdered())        StdOut.println("Not in symmetric order");
        if (!hasNoDuplicates())  StdOut.println("Has duplicate keys");
        if (!isRankConsistent()) StdOut.println("Rank not consistent");
        return isBinaryTree() && isOrdered() && hasNoDuplicates() && isRankConsistent();
    }

    // are the size fields correct (and consequently is it a binary tree)
    private boolean isBinaryTree() { return isBinaryTree(root); }
    private boolean isBinaryTree(Node x) {
        if (x == null) return true;
        if (x.N != size(x.left) + size(x.right) + 1) return false;
        return isBinaryTree(x.left) && isBinaryTree(x.right);
    } 

    // does this binary tree satisfy symmetric order?
    private boolean isOrdered() {
        assert isBinaryTree();
        return isOrdered(root, min(), max());
    }

    // are all the values in the BST rooted at x between min and max, and recursively?
    private boolean isOrdered(Node x, Key min, Key max) {
        if (x == null) return true;
        if (less(x.key, min) || less(max, x.key)) return false;
        return isOrdered(x.left, min, x.key) && isOrdered(x.right, x.key, max);
    } 

    // check that there are no duplicate keys
    // precondition: inorder traversal gives keys in order
    private boolean hasNoDuplicates() {
        assert isOrdered();
        for (int i = 1; i < size(); i++) {
            if (select(i).compareTo(select(i-1)) == 0) return false;
        }
        return true;
    }

    // check that ranks are consistent
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }

    private boolean less(Key x, Key y) {
        return x.compareTo(y) < 0;
    }

    /////////////////////////////additional methods////////////////////////////////////////
    public int height(){
        return height(root);
    }
    private int height(Node node){
        if(node == null){
           return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public double avePathLength(){
        return addUpPaths(root, 1.0) / size();
    }

    private double addUpPaths(Node x, double n){
        if(x == null){
            return 0.0;
        }
        return n + addUpPaths(x.left, n + 1.0) + addUpPaths(x.right, n + 1.0);
    }

    public String treeType(){
        return "IterativeBST";
    }


   /*****************************************************************************
    *  Test client
    *****************************************************************************/
    public static void main(String[] args) { 
        BST<String, Integer> st = new BST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}