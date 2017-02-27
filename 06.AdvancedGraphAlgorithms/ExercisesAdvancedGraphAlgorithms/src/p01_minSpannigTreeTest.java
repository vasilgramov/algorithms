import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class p01_minSpannigTreeTest {
    @Test
    public void testKruskalWithSingleEdge() {
        int numberOfVertices = 2;
        List<p01_minSpannigTree.Edge> graphEdges = new ArrayList<>();
        graphEdges.add(new p01_minSpannigTree.Edge(0, 1, 3));

        p01_minSpannigTree.Edge[] expectedForest = new p01_minSpannigTree.Edge[] { graphEdges.get(0) };

        List<p01_minSpannigTree.Edge> minimumSpanningForest = p01_minSpannigTree.kruskalAlgorithm(numberOfVertices, graphEdges);
        int totalWeight = p01_minSpannigTree.getTotalWeight(minimumSpanningForest);

        int expectedWeight = 3;
        p01_minSpannigTree.Edge[] resultForest = new p01_minSpannigTree.Edge[minimumSpanningForest.size()];
        for (int i = 0; i < resultForest.length; i++) {
            resultForest[i] = minimumSpanningForest.get(i);
        }

        Assert.assertEquals(expectedWeight, totalWeight);
        Assert.assertArrayEquals(expectedForest, resultForest);
    }

    @Test
    public void testKruskalWithTwoConnectedEdges() {
        int numberOfVertices = 3;
        List<p01_minSpannigTree.Edge> graphEdges = new ArrayList<>();
        graphEdges.add(new p01_minSpannigTree.Edge(0, 1, 3));
        graphEdges.add(new p01_minSpannigTree.Edge(2, 1, 4));

        p01_minSpannigTree.Edge[] expectedForest = new p01_minSpannigTree.Edge[] { graphEdges.get(0), graphEdges.get(1) };

        List<p01_minSpannigTree.Edge> minimumSpanningForest = p01_minSpannigTree.kruskalAlgorithm(numberOfVertices, graphEdges);
        int totalWeight = p01_minSpannigTree.getTotalWeight(minimumSpanningForest);

        int expectedWeight = 7;
        p01_minSpannigTree.Edge[] resultForest = new p01_minSpannigTree.Edge[minimumSpanningForest.size()];
        for (int i = 0; i < minimumSpanningForest.size(); i++) {
            resultForest[i] = minimumSpanningForest.get(i);
        }

        Assert.assertEquals(expectedWeight, totalWeight);
        Assert.assertArrayEquals(expectedForest, resultForest);
    }

    @Test
    public void testKruskalWith9VerticesAnd11Edges() {
        int numberOfVertices = 9;
        List<p01_minSpannigTree.Edge> graphEdges = new ArrayList<>();
        graphEdges.add(new p01_minSpannigTree.Edge(0, 3, 9));
        graphEdges.add(new p01_minSpannigTree.Edge(0, 5, 4));
        graphEdges.add(new p01_minSpannigTree.Edge(0, 8, 5));
        graphEdges.add(new p01_minSpannigTree.Edge(1, 4, 8));
        graphEdges.add(new p01_minSpannigTree.Edge(1, 7, 7));
        graphEdges.add(new p01_minSpannigTree.Edge(2, 6, 12));
        graphEdges.add(new p01_minSpannigTree.Edge(3, 5, 2));
        graphEdges.add(new p01_minSpannigTree.Edge(3, 6, 8));
        graphEdges.add(new p01_minSpannigTree.Edge(3, 8, 20));
        graphEdges.add(new p01_minSpannigTree.Edge(4, 7, 10));
        graphEdges.add(new p01_minSpannigTree.Edge(6, 8, 7));

        p01_minSpannigTree.Edge[] expectedForest = {
                graphEdges.get(6),
                graphEdges.get(1),
                graphEdges.get(2),
                graphEdges.get(4),
                graphEdges.get(10),
                graphEdges.get(3),
                graphEdges.get(5)
        };

        List<p01_minSpannigTree.Edge> minimumSpanningForest = p01_minSpannigTree.kruskalAlgorithm(numberOfVertices, graphEdges);
        int totalWeight = p01_minSpannigTree.getTotalWeight(minimumSpanningForest);

        int expectedWeight = 45;

        p01_minSpannigTree.Edge[] resultForest = new p01_minSpannigTree.Edge[minimumSpanningForest.size()];
        for (int i = 0; i < minimumSpanningForest.size(); i++) {
            resultForest[i] = minimumSpanningForest.get(i);
        }

        Assert.assertEquals(expectedWeight, totalWeight);
        Assert.assertArrayEquals(expectedForest, resultForest);
    }

    @Test
    public void testKruskalWith9VerticesAnd10Edges() {
        int numberOfVertices = 9;
        List<p01_minSpannigTree.Edge> graphEdges = new ArrayList<>();
        graphEdges.add(new p01_minSpannigTree.Edge(0, 3, 9));   //0
        graphEdges.add(new p01_minSpannigTree.Edge(0, 8, 5));   //1
        graphEdges.add(new p01_minSpannigTree.Edge(1, 4, 8));   //2
        graphEdges.add(new p01_minSpannigTree.Edge(1, 7, 7));   //3
        graphEdges.add(new p01_minSpannigTree.Edge(2, 6, 12));  //4
        graphEdges.add(new p01_minSpannigTree.Edge(3, 5, 2));   //5
        graphEdges.add(new p01_minSpannigTree.Edge(3, 6, 8));   //6
        graphEdges.add(new p01_minSpannigTree.Edge(3, 8, 20));  //7
        graphEdges.add(new p01_minSpannigTree.Edge(4, 7, 10));  //8
        graphEdges.add(new p01_minSpannigTree.Edge(6, 8, 7));   //9

        int expectedWeight = 49;
        p01_minSpannigTree.Edge[] expectedForest = {
                graphEdges.get(5),  // 3 5 2
                graphEdges.get(1),  // 0 8 5
                graphEdges.get(3),  // 1 7 7
                graphEdges.get(9),  // 6 8 7
                graphEdges.get(2),  // 1 4 8
                graphEdges.get(6),  // 3 6 8
                graphEdges.get(4)   // 2 6 12
        };

        List<p01_minSpannigTree.Edge> minimumSpanningForest = p01_minSpannigTree.kruskalAlgorithm(numberOfVertices, graphEdges);
        int resultWeight = p01_minSpannigTree.getTotalWeight(minimumSpanningForest);

        p01_minSpannigTree.Edge[] resultForest = new p01_minSpannigTree.Edge[minimumSpanningForest.size()];
        for (int i = 0; i < minimumSpanningForest.size(); i++) {
            resultForest[i] = minimumSpanningForest.get(i);
        }

        Assert.assertEquals(expectedWeight, resultWeight);
        Assert.assertArrayEquals(expectedForest, resultForest);
    }
}