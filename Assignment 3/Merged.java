/*
// uncomment to use jUnit tests
import org.junit.Test;
import static org.junit.Assert.*;
*/


import java.io.BufferedReader;
import java.io.*;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author Danis Alukaev BS19-02.
 * Third DSA Home Assignment.
 * Contains Graph implementation and jUnit tests.
 * To verify the tasks 2.1, 2.2, 2.3 just uncomment corresponding method in main.
 * To use jUnit tests uncomment import of jUnit, prepared jUnit Tests, and Tree generator.
 * ---------------------------------------------------------------------------------------------------------------------
 * links to the submission in CodeForces:
 * Task 2.1. Connected graph.
 * https://codeforces.com/group/3ZU2JJw8vQ/contest/276900/submission/78299454
 * Task 2.2. Components dictionary.
 * https://codeforces.com/group/3ZU2JJw8vQ/contest/276900/submission/78299574
 * Task 2.3. Minimum spanning forest.
 * https://codeforces.com/group/3ZU2JJw8vQ/contest/276900/submission/78299633
 */

public class Merged {

    //------------------------------------------------------------------------------------------------------------------
    /**
     * More efficient way to read input
     * Original source: https://pastebin.com/2y4kFUzp
     */

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st = new StringTokenizer("");

    private static String nextToken() throws IOException {
        while (!st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    private static int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }
    //------------------------------------------------------------------------------------------------------------------

    /**
     * jUnit Tests.
     */
/*

    // uncomment to use jUnit tests
    @Test
    public void insertEdgeVerticesTree1() {
        Graph<Integer, Integer> graph = createGraph1();
        String result = "Vertices: [ 1, 2, 3, 4, 5, 6, 7, 8 ]  Edges: [ 1 2 145, 2 4 23, 5 4 567, 3 4 567, 1 5 9, 3 2 61, 6 2 35, 3 6 87, 7 8 26 ]";
        assertEquals(result, graph.toString());
    }

    @Test
    public void removeVertexTree1() {
        Graph<Integer, Integer> graph = createGraph1();
        graph.removeVertex(1);
        graph.removeVertex(2);
        String result = "Vertices: [ 3, 4, 5, 6, 7, 8 ]  Edges: [ 5 4 567, 3 4 567, 3 6 87, 7 8 26 ]";
        assertEquals(result, graph.toString());
    }

    @Test
    public void removeEdgeTree1() {
        Graph<Integer, Integer> graph = createGraph1();
        graph.removeEdge(graph.getEdge(1, 2, 145));
        graph.removeEdge(graph.getEdge(3, 2, 61));
        String result = "Vertices: [ 1, 2, 3, 4, 5, 6, 7, 8 ]  Edges: [ 2 4 23, 5 4 567, 3 4 567, 1 5 9, 6 2 35, 3 6 87, 7 8 26 ]";
        assertEquals(result, graph.toString());
    }

    @Test
    public void endVerticesTree1() {
        Graph<Integer, Integer> graph = createGraph1();
        String result = "(1, 2)";
        assertEquals(result, graph.endVertices(graph.getEdge(1, 2, 145)).toString());
    }

    @Test
    public void oppositeTree1() {
        Graph<Integer, Integer> graph = createGraph1();
        assertEquals("2", graph.opposite(1, graph.getEdge(1, 2, 145)).toString());
    }

    @Test
    public void areAdjacentTree1() {
        Graph<Integer, Integer> graph = createGraph1();
        assertTrue(graph.areAdjacent(2, 6));
    }

    @Test
    public void degreeTree1() {
        Graph<Integer, Integer> graph = createGraph1();
        assertEquals("4", graph.degree(2).toString());
    }

    @Test
    public void getVertexTree1() {
        Graph<Integer, Integer> graph = createGraph1();
        assertTrue(graph.getVertices().contains(graph.getVertex()));
    }

    @Test
    public void incidentEdgesTree1() {
        Graph<Integer, Integer> graph = createGraph1();
        String result = "[1 2 145, 1 5 9, 2 3 61, 2 4 23, 2 6 35]";
        assertEquals(result, graph.incidentEdges(graph.getEdge(1, 2, 145)).toString());
    }

    @Test
    public void analyzeConnectivityTree1() {
        Graph<Integer, Integer> graph = createGraph1();
        assertEquals("(1, 7)", graph.analyzeConnectivity().toString());
    }

    @Test
    public void vertexComponentsTree1() {
        Graph<Integer, Integer> graph = createGraph1();
        assertEquals("{1=1, 2=1, 3=1, 4=1, 5=1, 6=1, 7=2, 8=2}", graph.vertexComponents().toString());
    }

    @Test
    public void minimumSpanningForestTree1() {
        Graph<Integer, Integer> graph = createGraph1();
        System.out.println(graph.toString());
        assertEquals("[Vertices: [ 1, 2, 3, 4, 5, 6 ]  Edges: [ 1 5 9, 2 4 23, 6 2 35, 3 2 61, 1 2 145 ], Vertices: [ 7, 8 ]  Edges: [ 7 8 26 ]]", graph.minimumSpanningForest().toString());
    }
*/

    /**
     * Tree generator
     * uncomment to use jUnit tests
     * T(n) = O(1)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @return the created graph
     */
        /* Graph's representation:
            1---5
            |   |
            |   |     7
            |   |     |
            2---4     |
            |\  |     |
            | \ |     8
            |  \|
            6---3
         */
/*

    private Graph<Integer, Integer> createGraph1() {
        int N = 8;
        Graph<Integer, Integer> graph = new Graph<>();
        for (int i = 1; i < N; i++)
            graph.insertVertex(i);
        graph.insertEdge(1, 2, 145);
        graph.insertEdge(2, 4, 23);
        graph.insertEdge(5, 4, 567);
        graph.insertEdge(3, 4, 567);
        graph.insertEdge(1, 5, 9);
        graph.insertEdge(3, 2, 61);
        graph.insertEdge(6, 2, 35);
        graph.insertEdge(3, 6, 87);
        graph.insertEdge(7, 8, 26);
        return graph;
    }

*/

    //------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) throws IOException {
        // call either connectedGraph() for task 2.1, or componentsDictionary() for 2.2, or minimumSpanningForest() for 2.3
        connectedGraph();
        // componentsDictionary();
        // minimumSpanningForest();
    }

    public static Pair<Integer, Integer> connectedGraph() throws IOException {
        //--------------------------------Input of an unweighted graph--------------------------------------------------
        Graph<Integer, Integer> graph = new Graph<>();
        int vertexesNum = nextInt();
        for (int i = 1; i < vertexesNum + 1; i++)
            graph.insertVertex(i);
        int edgesNum = nextInt();
        for (int i = 0; i < edgesNum; i++) {
            int from = nextInt();
            int to = nextInt();
            graph.insertEdge(from, to, 0);
        }
        //--------------------------------Output of a result------------------------------------------------------------
        Pair<Integer, Integer> result = graph.analyzeConnectivity();
        OutputStream out = new BufferedOutputStream(System.out);
        if (result.isEmpty())
            out.write(("GRAPH IS CONNECTED" + "\n").getBytes());
        else
            out.write(("VERTICES " + result.getKey().toString() + " AND " + result.getValue().toString() + " ARE NOT CONNECTED BY A PATH" + "\n").getBytes());
        out.flush();
        return result;
    }

    public static HashMap<Integer, Integer> componentsDictionary() throws IOException {
        //--------------------------------Input of an unweighted graph--------------------------------------------------
        Graph<Integer, Integer> graph = new Graph<>();
        int vertexesNum = nextInt();
        for (int i = 1; i < vertexesNum + 1; i++)
            graph.insertVertex(i);
        int edgesNum = nextInt();
        for (int i = 0; i < edgesNum; i++) {
            int from = nextInt();
            int to = nextInt();
            graph.insertEdge(from, to, 0);
        }
        //--------------------------------Output of a result------------------------------------------------------------
        HashMap<Integer, Integer> result = graph.vertexComponents();
        OutputStream out = new BufferedOutputStream(System.out);
        for (int i = 1; i <= graph.getVertices().size(); i++)
            out.write((result.get(i) + " ").getBytes());
        out.write(("\n").getBytes());
        out.flush();
        return result;
    }

    public static ArrayList<Graph<Integer, Integer>> minimumSpanningForest() throws IOException {
        //--------------------------------Input of a weighted graph-----------------------------------------------------
        Graph<Integer, Integer> graph = new Graph<>();
        int vertexesNum = nextInt();
        for (int i = 1; i < vertexesNum + 1; i++)
            graph.insertVertex(i);
        int edgesNum = nextInt();
        for (int i = 0; i < edgesNum; i++) {
            int from = nextInt();
            int to = nextInt();
            int weight = nextInt();
            graph.insertEdge(from, to, weight);
        }
        //--------------------------------Output of a result------------------------------------------------------------
        ArrayList<Graph<Integer, Integer>> result = graph.minimumSpanningForest();
        OutputStream out = new BufferedOutputStream(System.out);
        out.write((result.size() + "\n").getBytes());
        for (Graph<Integer, Integer> tree : result) {
            out.write((tree.getAdjacencyList().size() + " " + tree.getVertex() + "\n").getBytes());
            for (Graph<Integer, Integer>.Edge edge : tree.getEdgeList())
                out.write((edge + "\n").getBytes());
        }
        out.flush();
        return result;
    }

}

/**
 * Auxiliary class Pair.
 * Used to store pair of elements.
 * ---------------------------------------------------------------------------------------------------------------------
 *
 * @param <V> - type of a key
 * @param <K> - type of a value
 */
class Pair<V, K> {
    private V key; // key for a pair
    private K value;  // value for a pair

    /**
     * Constructor of a class Pair() without parameters.
     * Time complexity: T(n)=O(1)
     */
    Pair() {
        this.key = null;
        this.value = null;
    }

    /**
     * Constructor of a class Pair(key, value).
     * Time complexity: T(n)=O(1)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param key   - received key to be inserted in a pair
     * @param value - receives value to be inserted in a pair
     */
    Pair(V key, K value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Method getKey() returns the key for a pair.
     * Time complexity: T(n)=O(1)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @return key for a pair
     */
    public V getKey() {
        return this.key;
    }

    /**
     * Method getValue() returns the value for a pair.
     * Time complexity: T(n)=O(1)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @return value for a pair
     */
    public K getValue() {
        return this.value;
    }

    /**
     * Method setKey(key) sets the key of a pair to received parameter "key".
     * Time complexity: T(n)=O(1)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param key - received key
     */
    public void setKey(V key) {
        this.key = key;
    }

    /**
     * Method setValue(value) sets the value of a pair to received parameter "value".
     * Time complexity: T(n)=O(1)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param value - received value
     */
    public void setValue(K value) {
        this.value = value;
    }

    /**
     * Method isEmpty() checks whether a pair is defined (has non-null value and key).
     * Time complexity: T(n)=O(1)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @return true if a pair has the value and key, false - otherwise
     */
    public boolean isEmpty() {
        if (this.key == null && this.value == null)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "(" + this.getKey().toString() + ", " + this.getValue().toString() + ")";
    }

}

/**
 * Auxiliary class DisjointSetUnionInt.
 * Implementation of the Disjoint Set Union data structure.
 * Amortized time complexity: T(n)=O(α(n)), where α(n) is the inverse Ackermann function.
 * Path compression and rank optimization techniques were proposed in the following source:
 * https://cp-algorithms.com/data_structures/disjoint_set_union.html
 */
class DisjointSetUnionInt {
    private HashMap<Integer, Integer> elements;
    private int[] parents;
    private int[] rank;

    /**
     * Method DisjointSetUnionInt(size) creates a new set consisting of integers from 0 to "size"-1.
     * Other types can be mapped to these integers in outer scope.
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param size - number of elements in the created set
     */
    public DisjointSetUnionInt(int size) {
        this.parents = new int[size];
        this.rank = new int[size];
        for (int i = 0; i < size; i++) {
            this.parents[i] = i;
            this.rank[i] = 0;
        }
        elements = new HashMap<>();
    }

    /**
     * Method find(i) finds the representative of the set containing the element "i".
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param i - element which representative should be determine
     * @return representative of the set containing the element "i"
     */
    public int find(int i) {
        if (parents[i] == i)
            return i;
        else {
            parents[i] = find(parents[i]);
            return parents[i];
        }
    }

    /**
     * Method union(i, j) merges the two sets in which "i" and "j" are located.
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param i - first element
     * @param j - second element
     */
    public void union(int i, int j) {
        i = find(i);
        j = find(j);
        if (i != j) {
            if (rank[i] < rank[j]) {
                int temp = j;
                j = i;
                i = temp;
            }
            parents[j] = i;
            if (rank[i] == rank[j]) {
                ++rank[i];
            }
        }
    }

    /**
     * Method getNumberOfSets() returns number of disjoint sets within the system.
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @return number of disjoint sets within the system
     */
    public int getNumberOfSets() {
        this.updateDSU();
        return elements.size();
    }

    /**
     * Method updateDSU() updates the system of disjoint sets.
     * -----------------------------------------------------------------------------------------------------------------
     */
    private void updateDSU() {
        for (int i = 0; i < parents.length; i++) {
            parents[i] = find(parents[i]);
            elements.put(parents[i], i);
        }
    }
}

/**
 * Class Graph.
 * ---------------------------------------------------------------------------------------------------------------------
 *
 * @param <V> type of vertices
 * @param <E> type of weights of edges
 */
class Graph<V, E extends Number & Comparable<E>> {
    private ArrayList<Edge> edges; // list of edges of a graph
    private HashMap<V, HashMap<V, E>> adjList; // adjacency list used to represent a finite graph

    private static int ID = 1; // used to assign ID to every vertex
    private HashMap<V, Integer> vertexToID; // dictionary used to get ID by a vertex
    private HashMap<Integer, V> idToVertex; // dictionary used to get vertex by an ID

    enum Status {NOT_VISITED, OPENED, EXITED} // enumerated type of states during traversal

    private HashMap<V, Status> vertexStatus = new HashMap<>(); // dictionary used to get state for a vertex

    /**
     * Constructor of a class Graph.
     * Time complexity: T(V,E)=O(1)
     */
    public Graph() {
        // initialize variables
        ID = 1;
        this.edges = new ArrayList<>();
        this.adjList = new HashMap<>();
        this.vertexToID = new HashMap<>();
        this.idToVertex = new HashMap<>();
    }

    /**
     * Constructor of a class Graph.
     * Time complexity: T(V,E)=O(1)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param vertex - vertex to be inserted in a graph
     */
    public Graph(V vertex) {
        this();
        // insert received vertex
        this.insertVertex(vertex);
    }

    /**
     * Constructor of a class Graph
     * Time complexity: T(V,E)=O(|V|)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param vertices - set of vertices to be inserted in a graph
     */
    public Graph(Set<V> vertices) {
        this();
        // insert received vertices
        for (V v : vertices)
            this.insertVertex(v);
    }

    /**
     * Method getAdjacencyList() returns an adjacency list of the graph.
     * Time complexity: T(V,E)=O(1)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @return adjacency list of the graph
     */
    public HashMap<V, HashMap<V, E>> getAdjacencyList() {
        return this.adjList;
    }

    /**
     * Method getAdjacencyList(vertex) returns linked list of adjacent to a received vertex vertices.
     * Time complexity: T(V,E)=O(1)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param vertex - vertex which list of adjacent vertices is required
     * @return linked list of adjacent to a received vertex vertices
     */
    public HashMap<V, E> getAdjacencyList(V vertex) {
        return this.adjList.get(vertex);
    }

    /**
     * Method getEdgeList() returns list of edges of the graph.
     * Time complexity: T(V,E)=O(1)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @return list of edges of the graph
     */
    public ArrayList<Edge> getEdgeList() {
        return this.edges;
    }

    /**
     * Method getVertices() returns set of vertices of the graph.
     * Time complexity: T(V,E)=O(1)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @return set of vertices of the graph
     */
    public Set<V> getVertices() {
        return this.adjList.keySet();
    }

    /**
     * Method endVertices(edge) returns end vertices of a received edge.
     * Time complexity: T(V,E)=O(1)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param edge - edge which end vertices required
     * @return endpoints of a received edge
     */
    public Pair<V, V> endVertices(Edge edge) {
        return new Pair<>(edge.from, edge.to);
    }

    /**
     * Method opposite(vertex, edge) returns the vertex opposite of "vertex" on "edge".
     * Time complexity: T(V,E)=O(1)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param vertex - first end vertex of an edge
     * @param edge   - edge which second end vertex required
     * @return the vertex opposite of "vertex" on "edge"
     */
    public V opposite(V vertex, Edge edge) {
        if (edge.from.equals(vertex))
            return edge.to;
        else
            return edge.from;
    }

    /**
     * Method areAdjacent(vertex1, vertex2) checks whether two received vertices are adjacent.
     * Time complexity: T(V,E)=O(1)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param vertex1 - first vertex of a graph
     * @param vertex2 - second vertex of a graph
     * @return true if vertex1 and vertex2 are adjacent vertices, false - otherwise
     */
    public boolean areAdjacent(V vertex1, V vertex2) {
        return this.getAdjacencyList(vertex1).containsKey(vertex2);
    }

    /**
     * Method degree(vertex) returns number of incident edges of a received vertex.
     * Time complexity: T(V,E)=O(1)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param vertex - vertex of a graph, which degree required
     * @return number of incident edges of a received vertex
     */
    public Integer degree(V vertex) {
        return this.getAdjacencyList(vertex).size();
    }

    /**
     * Method insertVertex(vertex) inserts vertex in a graph.
     * Time complexity: T(V,E)=O(1)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param vertex - vertex to be inserted
     * @return id of an inserted vertex
     */
    public Integer insertVertex(V vertex) {
        // check whether adjacency list contains received vertex
        if (!this.adjList.containsKey(vertex)) {
            // add vertex to a adjacency list
            this.adjList.put(vertex, new HashMap<>());
            // set ID of a vertex
            this.vertexToID.put(vertex, ID);
            this.idToVertex.put(ID, vertex);
            ID++;
            return ID - 1;
        }
        return ID;
    }

    /**
     * Method removeVertex(vertex) removes received vertex.
     * Time complexity: T(V,E)=O(1)+O(1)+O(1)+O(|V|)+O(|E|^2)=O(|E|^2)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param vertex - vertex to be removed
     */
    public void removeVertex(V vertex) {
        // remove vertex from adjacency list and dictionaries to get ID
        this.adjList.remove(vertex);
        this.idToVertex.remove(this.vertexToID.get(vertex));
        this.vertexToID.remove(vertex);
        // remove vertex from linked lists of other vertices in adjacency list
        for (HashMap<V, E> v : this.adjList.values())
            v.remove(vertex);
        // create new container for edges to avoid Write After Read issue
        ArrayList<Edge> newEdges = new ArrayList<>(this.edges);
        for (Edge e : this.edges) {
            // remove all edges with vertex as end vertex
            if (e.from.equals(vertex))
                newEdges.remove(e);
            else if (e.to.equals(vertex))
                newEdges.remove(e);
        }
        this.edges = newEdges;
    }

    /**
     * Method getVertex() returns any vertex of a graph.
     * Time complexity: T(V,E)=O(1)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @return any vertex of a tree
     */
    public V getVertex() {
        for (V v : this.adjList.keySet())
            return v;
        return this.edges.get(0).to;
    }

    /**
     * Method insertEdge(from, to, weight) inserts an edge with transferred parameters in the graph.
     * Time complexity: T(V,E)=O(1)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param from   - first end vertex of an edge
     * @param to     - second end vertex of an edge
     * @param weight - element to be stored within the edge
     */
    public void insertEdge(V from, V to, E weight) {
        // insert first end vertex of an edge
        this.insertVertex(from);
        // insert first end vertex in adjacency list of second end vertex
        this.adjList.get(from).put(to, weight);
        // insert second end vertex of an edge
        this.insertVertex(to);
        // insert second end vertex in adjacency list of first end vertex
        this.adjList.get(to).put(from, weight);
        // insert in list of edges
        this.edges.add(new Edge(from, to, weight));
    }

    /**
     * Method insertEdge(edge) inserts received edge.
     * Time complexity: T(V,E)=O(1)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param edge - edge to be inserted
     */
    public void insertEdge(Edge edge) {
        this.insertEdge(edge.from, edge.to, edge.weight);
    }

    /**
     * Method removeEdge(edge) removes received edge from the graph.
     * Time complexity: T(V,E)=O(|E|)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param edge - edge to be removed
     */
    public void removeEdge(Edge edge) {
        // remove edge from list of edges
        this.edges.remove(edge);
        // remove edge from adjacency lists
        if (this.adjList.get(edge.from) != null)
            this.adjList.get(edge.from).remove(edge.to);
        if (this.adjList.get(edge.to) != null)
            this.adjList.get(edge.to).remove(edge.from);

    }

    /**
     * Method getEdge(from, to, weight) returns an edge with a given parameters.
     * Time complexity: T(V,E)=O(|E|)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param from   - first end vertex of an edge
     * @param to     - second end vertex of an edge
     * @param weight - element that stored within the edge
     * @return edge with a given parameters
     */
    public Edge getEdge(V from, V to, E weight) {
        for (Edge e : this.getEdgeList())
            if (e.from.equals(from) && e.to.equals(to) && e.weight.equals(weight))
                return e;
        return new Edge(from, to, weight);
    }

    /**
     * Method incidentEdges(edge) returns list of edges incident to a received edge.
     * Time complexity: T(V,E)=O(|V|)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param edge - edge which incident edges required
     * @return list of edges incident to a received edge
     */
    public ArrayList<Edge> incidentEdges(Edge edge) {
        // create container that will store incident edges
        ArrayList<Edge> incidentEdges = new ArrayList<>();
        for (V vertex : this.getAdjacencyList(edge.from).keySet())
            // find and insert all edge that share first end vertex
            incidentEdges.add(new Edge(edge.from, vertex, this.getAdjacencyList(edge.from).get(vertex)));
        for (V vertex : this.getAdjacencyList(edge.to).keySet()) {
            // find and insert all edge that share second end vertex and not share first end vertex
            Edge incident = new Edge(edge.to, vertex, this.getAdjacencyList(edge.to).get(vertex));
            if (!(incident.from.equals(edge.to) && incident.to.equals(edge.from) && incident.weight.equals(edge.weight)))
                incidentEdges.add(incident);
        }
        return incidentEdges;
    }

    /**
     * Method incidentEdges(vertex) returns list of edges incident to a received vertex.
     * Time complexity: T(V,E)=O(|V|)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param vertex - vertex which incident edges required
     * @return list of edges incident to a received vertex
     */
    public ArrayList<Edge> incidentEdges(V vertex) {
        // create container that will store incident edges
        ArrayList<Edge> incidentEdges = new ArrayList<>();
        for (V v : this.getAdjacencyList(vertex).keySet())
            // find and insert all edge that share vertex
            incidentEdges.add(new Edge(vertex, v, this.getAdjacencyList(vertex).get(v)));
        return incidentEdges;
    }

    /**
     * Method setVertexStatus() updates the states of all vertices before the traversal.
     * Time complexity: T(V,E)=O(|V|)
     */
    private void setVertexStatus() {
        for (V vertex : this.getAdjacencyList().keySet())
            // set state to NOT_VISITED
            vertexStatus.put(vertex, Status.NOT_VISITED);
    }

    /**
     * Method dfsRecursive(vertex) starts DFS traversal from a received vertex.
     * Time complexity: T(V,E)=O(|V|)+O(|V|+|E|)=O(|V|+|E|)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param vertex node from which method start traversal
     * @return list of discovered vertices
     */
    public Set<V> dfsRecursive(V vertex) {
        // create container to store discovered vertices
        Set<V> traverseList = new HashSet<>();
        // set states of vertices
        setVertexStatus();
        // call recursive DFS function
        dfsUtil(vertex, traverseList);
        return traverseList;
    }

    /**
     * Method dfsRecursive(vertex) starts DFS traversal from a received node
     * Time complexity: T(V,E)=O(|V|+|E|)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param vertex           node from which method start traversal
     * @param verticesToTreeID dictionary to map vertex to id of a tree
     * @param treeID           id to which discovered vertices will be mapped
     * @return set of states of graph vertices
     */
    public HashMap<V, Status> dfsRecursive(V vertex, HashMap<V, Integer> verticesToTreeID, int treeID) {
        // call recursive DFS function
        dfsUtil(vertex, verticesToTreeID, treeID);
        return vertexStatus;
    }

    /**
     * Method dfsUtil(vertex, traverseList) traverses a graph
     * Time complexity: T(V,E)=O(1)+O(|V|+|E|)=O(|V|+|E|)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param vertex       node from which method start traversal
     * @param traverseList list of discovered vertices
     */
    private void dfsUtil(V vertex, Set<V> traverseList) {
        // add vertex to traverse container
        traverseList.add(vertex);
        // set state of vertex to OPENED
        vertexStatus.put(vertex, Status.OPENED);
        for (V adjacentVertex : this.getAdjacencyList(vertex).keySet())
            // treat all adjacent vertices
            if (adjacentVertex != null && vertexStatus.get(adjacentVertex).equals(Status.NOT_VISITED))
                // vertex is not visited
                dfsUtil(adjacentVertex, traverseList);
        // set state of vertex to EXITED
        vertexStatus.put(vertex, Status.EXITED);
    }

    /**
     * Recursive method dfsUtil(vertex, traverseList) traverses a graph
     * Time complexity: T(V,E)=O(1)+O(|V|+|E|)=O(|V|+|E|)
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param vertex           node from which method start traversal
     * @param verticesToTreeID dictionary to map vertex to id of a tree
     * @param treeID           id to which discovered vertices will be mapped
     */
    private void dfsUtil(V vertex, HashMap<V, Integer> verticesToTreeID, int treeID) {
        // set state of vertex to OPENED
        vertexStatus.put(vertex, Status.OPENED);
        // map vertex to treeID
        verticesToTreeID.put(vertex, treeID);
        for (V adjacentVertex : this.getAdjacencyList(vertex).keySet())
            // treat all adjacent vertices
            if (adjacentVertex != null && vertexStatus.get(adjacentVertex).equals(Status.NOT_VISITED))
                // vertex is not visited
                dfsUtil(adjacentVertex, verticesToTreeID, treeID);
        // set state of vertex to EXITED
        vertexStatus.put(vertex, Status.EXITED);
    }

    /**
     * Method analyzeConnectivity() checks whether any two vertices in a graph are not connected with a path.
     * The idea is to traverse the graph using DFS and determine if all vertices in a graph are discovered.
     * The time complexity for DFS call is O(|V|+|E|) and O(|V|) to lookup if discovered vertices contain all graph's
     * vertices, so method requires O(|V|+|V|+|E|)=O(|V|+|E|), where |V| is total number of vertices and |E| is the
     * total number of edges.
     *
     * @return pair of two vertices that are not connected with a node
     */
    public Pair<V, V> analyzeConnectivity() {
        // get starting vertex
        V initVertex = this.getVertex();

        // pair that will store the counterexample (two vertices that are not adjacent)
        Pair<V, V> result = new Pair<>();
        if (initVertex != null) {
            // create set of all discovered during DFS vertices, time complexity is O(|V|+|E|)
            Set<V> dfsCall = this.dfsRecursive(initVertex);

            if (dfsCall.size() != this.getVertices().size())
                // then during DFS discovered not all vertices
                for (V vertex : this.getVertices())
                    // treat all vertices of a graph, time complexity is O(|V|)
                    if (!dfsCall.contains(vertex)) {
                        // vertex is not discovered

                        result.setKey(initVertex); // form the counterexample:
                        result.setValue(vertex);   // (initial vertex, vertex we found not discovered)
                        return result;
                    }
        }
        return result;
    }

    /**
     * Method vertexComponents() maps every vertex of a graph into a connected component (id) it belongs to.
     * The idea is to traverse a graph using DFS and label all discovered from starting vertex vertices with components
     * id. Then, repeat this procedure for the rest unlabeled vertices. Finally, we will map all vertices to its graph's
     * component. The total time complexity is O(|V|+|E|) since an algorithm will discover all |V| vertices, and at each
     * iteration all edges that are incident to these vertices, which the total sum of lengths of all adjacency lists is Θ(|E|).
     *
     * @return dictionary that maps all vertices of a graph to the corresponding subtree ids
     */
    public HashMap<V, Integer> vertexComponents() {
        // dictionary that maps vertices to connected component id
        HashMap<V, Integer> verticesToTreeID = new HashMap<>();

        // initialize starting connected component id
        int treeID = 1;

        // mark all vertices as not visited, time complexity is O(|V|)
        this.setVertexStatus();

        // treat all vertices of a graph, time complexity is O(|V|+|E|)
        for (V vertex : this.getVertices()) {
            if (vertexStatus.get(vertex).equals(Status.NOT_VISITED)) {
                // current vertex is not visited yet, so start DFS from it
                vertexStatus = dfsRecursive(vertex, verticesToTreeID, treeID);
                // increment connected component id
                treeID++;
            }
        }
        return verticesToTreeID;
    }

    /**
     * Method minimumSpanningForest() finds a minimum spanning forest of a weighted graph.
     * The idea is to use Kruskal's algorithm to determine the edges that span all vertices and have minimum weights.
     * Then, we can find roots for all subtrees forming the tree founded by Kruskal's algorithm, and map these subtrees
     * to their representatives. Then, we can add all found subtrees to a resultant list of spanning trees.
     * However, there may still be subtrees formed by a single vertex, to find them we just
     * find vertices that have not adjacent neighbours and add to a resultant list of spanning trees.
     * Time complexity is O(V,E) + O(|E| log |E|) + O(|E| α(|V|)) + O(|E| α(|V|)) + O(|V|) = O(|E| log |E|).
     *
     * @return list of spanning trees of a graph
     */
    public ArrayList<Graph<V, E>> minimumSpanningForest() {
        // -------------------------------------------------------------------------------------------------------------
        // Kruskal's algorithm was implemented in 11 lab of BS19-02 by Nickolay Kudasov:
        // https://moodle.innopolis.university/pluginfile.php/44284/mod_folder/content/0/Main-BS19-02.java?forcedownload=1

        // graph that stores minimum spanning forest when Kruskal's algorithm is done, time complexity is O(|V|)
        Graph<V, E> minimumSpanningForest = new Graph<>(this.adjList.keySet());

        // DSU used to find representatives of a subtrees, time complexity is O(|V|)
        DisjointSetUnionInt treesDSU = new DisjointSetUnionInt(this.adjList.size());

        // sort list of edges, since used merge sort the time complexity is O(|E| log |E|)
        this.edges.sort(Comparator.comparing(Edge::getWeight));

        // time complexity is O(α(|V|)), where α(n) is the inverse Ackermann function
        for (Edge edge : this.getEdgeList()) {
            if (treesDSU.find(this.vertexToID.get(edge.from) - 1) != treesDSU.find(this.vertexToID.get(edge.to) - 1)) {
                // current edge does not form a cycle with the spanning tree formed so far

                // merge two sets with respective representatives
                treesDSU.union(this.vertexToID.get(edge.from) - 1, this.vertexToID.get(edge.to) - 1);

                // add an edge to a tree
                minimumSpanningForest.insertEdge(edge);
            }
        }
        // -------------------------------------------------------------------------------------------------------------
        HashMap<V, Graph<V, E>> forest = new HashMap<>(treesDSU.getNumberOfSets()); // dictionary that maps tree's representative to a tree, time complexity is O(|V|)

        // time complexity is O(|E| α(|V|)), where α(n) is the inverse Ackermann function
        for (Edge edge : minimumSpanningForest.getEdgeList()) {
            // find representative
            Integer vertex = treesDSU.find(this.vertexToID.get(edge.from) - 1);

            // find the tree by the representative
            Graph<V, E> tree;
            if (forest.get(this.idToVertex.get(vertex)) == null)
                tree = new Graph<>();
            else
                tree = forest.get(this.idToVertex.get(vertex));
            // add current edge to obtained tree
            tree.insertEdge(edge);
            // put updated tree in dictionary
            forest.put(this.idToVertex.get(vertex), tree);
        }
        // move obtained trees to an arraylist, time complexity is O(|V|)
        ArrayList<Graph<V, E>> listOfSpanningTrees = new ArrayList<>(forest.values());

        // time complexity is O(|V|)
        for (V vertex : minimumSpanningForest.getVertices())
            if (minimumSpanningForest.getAdjacencyList(vertex).isEmpty())
                // check whether current vertex forms tree and has no adjacent vertices
                listOfSpanningTrees.add(new Graph<>(vertex)); // add new tree to an arraylist
        return listOfSpanningTrees;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Vertices: [ ");
        for (V v : this.getVertices()) {
            str.append(v).append(", ");
        }
        if (str.substring(str.length() - 2, str.length()).equals(", "))
            str.delete(str.length() - 2, str.length());
        str.append(" ]  Edges: [ ");
        for (Edge edge : this.getEdgeList()) {
            str.append(edge).append(", ");
        }
        if (str.substring(str.length() - 2, str.length()).equals(", "))
            str.delete(str.length() - 2, str.length());
        str.append(" ]");
        return str.toString();
    }

    /**
     * Auxiliary class Edge.
     * Class Edge was implemented in 11 lab of BS19-02 by Nickolay Kudasov:
     * https://moodle.innopolis.university/pluginfile.php/44284/mod_folder/content/0/Main-BS19-02.java?forcedownload=1
     */
    public class Edge {
        V from; // first end vertex of an edge
        V to; // second end vertex of an edge
        E weight; // element stored within the edge

        /**
         * Constructor of a class Edge(from, to, weight).
         * Time complexity: T(N)=O(1)
         * -------------------------------------------------------------------------------------------------------------
         *
         * @param from   - first endpoint of an edge
         * @param to     - second endpoint of an edge
         * @param weight - element stored within the edge
         */
        public Edge(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        /**
         * Method getWeight() returns element stored within the edge.
         * Time complexity: T(N)=O(1)
         * -------------------------------------------------------------------------------------------------------------
         *
         * @return element stored within the edge
         */
        public E getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return this.from + " " + this.to + " " + this.weight;
        }
    }

}
