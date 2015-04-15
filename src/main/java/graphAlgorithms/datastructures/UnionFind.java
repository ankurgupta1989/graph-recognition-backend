package graphAlgorithms.datastructures;

/**
 * Created by ankurgupta on 11/13/14.
 */
public interface UnionFind {

    public Integer find(Integer key);

    public void unify(Integer group1, Integer group2);

}
