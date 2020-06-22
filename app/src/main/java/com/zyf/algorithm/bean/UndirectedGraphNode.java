package com.zyf.algorithm.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyf on 2020/6/22.
 */
public class UndirectedGraphNode {

    public int label;
    public List<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<>();
    }
}
