
public class unionFind {
    private int[] parent;
    private int[] sz; // size of component for roots (uses less memory than rank)
    private int count;

    public unionFind(int n) {
        count = n;
        parent = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            parent[p] = parent[parent[p]]; // path compression by halving
            p = parent[p];
        }
        return p;
    }

    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make smaller root point to larger one
        if (sz[rootP] < sz[rootQ]) {
            parent[rootP] = rootQ;
            sz[rootQ] += sz[rootP];
        } else {
            parent[rootQ] = rootP;
            sz[rootP] += sz[rootQ];
        }
        count--;
    }

    public static void main(String[] args) {
        int n = 10;
        unionFind uf = new unionFind(n);

        System.out.println("Initial components: " + uf.count());

        uf.union(4, 3);
        uf.union(3, 8);
        uf.union(6, 5);
        uf.union(9, 4);
        uf.union(2, 1);
        uf.union(5, 0);
        uf.union(7, 2);
        uf.union(6, 1);
        uf.union(7, 3);

        System.out.println("Components after unions: " + uf.count());

        System.out.println("Are 0 and 8 connected? " + uf.connected(0, 8)); // Should be true
        System.out.println("Are 1 and 9 connected? " + uf.connected(1, 9)); // Should be true
        System.out.println("Are 0 and 9 connected? " + uf.connected(0, 9)); // Should be true
        
        System.out.println("Find(4): " + uf.find(4));
        System.out.println("Find(8): " + uf.find(8));
    }
}
