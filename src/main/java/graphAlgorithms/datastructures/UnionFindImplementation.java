package graphAlgorithms.datastructures;

/**
 * Created by ankurgupta on 11/13/14.
 */
public class UnionFindImplementation implements UnionFind {

    private Integer[] parentArray;
    private Integer[] rank;

    public UnionFindImplementation(int size) {
        parentArray = new Integer[size];
        rank = new Integer[size];
        for (int i=0; i<size; i++) {
            parentArray[i] = -1;
            rank[i] = 0;
        }
    }

    @Override
    public Integer find(Integer key) {
        Integer cur = key;
        while (parentArray[cur] != -1) {
            cur = parentArray[cur];
        }
        Integer parent = cur;
        cur = key;
        while (parentArray[cur] != -1) {
            Integer temp = parentArray[cur];
            parentArray[cur] = parent;
            cur = temp;
        }
        return parent;
    }

    @Override
    public void unify(Integer group1, Integer group2) {
        if (rank[group1] < rank[group2]) {
            parentArray[group1] = group2;
        }
        else if (rank[group1] > rank[group2]) {
            parentArray[group2] = group1;
        }
        else {
            parentArray[group2] = group1;
            rank[group1]++;
        }
    }
}
