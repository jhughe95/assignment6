import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph implements GraphInterface<Town,Road>{
	ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
	private ArrayList<Town> towns; // List to hold towns
    private ArrayList<Road> roads; // List to hold roads
    private Map<Town, Integer> indexMap; // Map to track the index of each town in the matrix
public Graph() {
	towns = new ArrayList<>();
    roads = new ArrayList<>();
    matrix = new ArrayList<>();
    indexMap = new HashMap<>();
}

	/**
	 * @param sourceVertex
	 * @param destinationVertex
	 * @return the road that connects the two towns in the graph, if it does not exist return null
	 */
	public Road getEdge(Town sourceVertex, Town destinationVertex)
	{
		if (!indexMap.containsKey(sourceVertex) || !indexMap.containsKey(destinationVertex)) {
	        return null;  // One or both towns are not in the graph
	    }

	    int sourceIndex = indexMap.get(sourceVertex);
	    int destIndex = indexMap.get(destinationVertex);

	    if (matrix.get(sourceIndex).get(destIndex) != 0) {
	        // There is a connection, now find the road
	        for (Road road : roads) {
	            if ((road.getSource().equals(sourceVertex) && road.getDestination().equals(destinationVertex)) ||
	                (road.getSource().equals(destinationVertex) && road.getDestination().equals(sourceVertex))) {
	                return road;
	            }
	        }
	    }
	    return null; // No road found or no direct connection
	}
	/**
	 * @param sourceVertex
	 * @param destinationVertex
	 * @param weight
	 * @param description
	 * @return the road object if it is successfully added and null if it is not added to the graph
	 */
	public Road addEdge(Town source, Town destination, int weight, String description) {
        if (!indexMap.containsKey(source) || !indexMap.containsKey(destination)) {
            throw new IllegalArgumentException("Both towns must be added before connecting them with a road.");
        }
        int sourceIndex = indexMap.get(source);
        int destinationIndex = indexMap.get(destination);

        // Update matrix to reflect the new edge
        matrix.get(sourceIndex).set(destinationIndex, weight);
        matrix.get(destinationIndex).set(sourceIndex, weight); // For undirected graph

        Road newRoad = new Road(source, destination, weight, description);
        roads.add(newRoad);
        return newRoad;
    }
	/**
	 * @param v
	 * @return true if the vertex is added and false if it is not added to the graph
	 */
	public boolean addVertex(Town v)
	{
		if (!indexMap.containsKey(v)) {
            towns.add(v);
            int newIndex = towns.size() - 1;
            indexMap.put(v, newIndex);
            // Ensure matrix size is increased
            increaseMatrixSize();
            return true;
        }
        return false;
	}
	
	
	 private void increaseMatrixSize() {
	        // Add a new row for the new vertex
	        ArrayList<Integer> newRow = new ArrayList<>();
	        for (int i = 0; i < matrix.size() + 1; i++) {
	            newRow.add(0); // Initialize with 0, assuming no road connection
	        }
	        matrix.add(newRow);

	        // Add a new column to each existing row
	        for (int i = 0; i < matrix.size() - 1; i++) {
	            matrix.get(i).add(0);
	        }
	    }
	/**
	 * @param sourceVertex
	 * @param destinationVertex
	 * @return true if the graph contains a road that connects the two towns
	 */
	public boolean containsEdge(Town sourceVertex, Town destinationVertex)
	{
		if (!indexMap.containsKey(sourceVertex) || !indexMap.containsKey(destinationVertex)) {
            return false;
        }
        int sourceIndex = indexMap.get(sourceVertex);
        int destinationIndex = indexMap.get(destinationVertex);
        return matrix.get(sourceIndex).get(destinationIndex) != 0;
	}
	/**
	 * @param v
	 * @return true if the graph contains the given town
	 */
	public boolean containsVertex(Town v)
	{
		 return towns.contains(v);
	}
	/**
	 * 
	 * @return a set containing all of the road objects stored in the graph
	 */
	public Set<Road> edgeSet()
	{
		 return new HashSet<>(roads);
	}
	/**
	 * @param vertex
	 * @return a set containing all of the roads that connect to the given town
	 */
	public Set<Road> edgesOf(Town vertex)
	{
		 if (!indexMap.containsKey(vertex)) {
	            throw new IllegalArgumentException("Vertex does not exist.");
	        }
	        Set<Road> edges = new HashSet<>();
	        int vertexIndex = indexMap.get(vertex);
	        for (int i = 0; i < matrix.get(vertexIndex).size(); i++) {
	            if (matrix.get(vertexIndex).get(i) != 0) {
	                edges.add(new Road(vertex, towns.get(i), matrix.get(vertexIndex).get(i), "Connecting Road"));
	            }
	        }
	        return edges;
	}
	/**
	 * @param sourceVertex
	 * @param destinationVertex
	 * @param weight
	 * @param description
	 * @return the road that is removed from the graph, return null if it is not removed
	 */
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        if (!indexMap.containsKey(sourceVertex) || !indexMap.containsKey(destinationVertex)) {
            return null;
        }
        int sourceIndex = indexMap.get(sourceVertex);
        int destinationIndex = indexMap.get(destinationVertex);
        if (matrix.get(sourceIndex).get(destinationIndex) != weight) {
            return null; // No matching road to remove
        }

        matrix.get(sourceIndex).set(destinationIndex, 0);
        matrix.get(destinationIndex).set(sourceIndex, 0); // For undirected graph
        
        Road removedRoad = new Road(sourceVertex, destinationVertex, weight, description);
        roads.remove(removedRoad);
        return removedRoad;
    }

	/**
	 * @param v
	 * @return true if the vertex is removed and false if it is not
	 */
	 public boolean removeVertex(Town v) {
	        if (!indexMap.containsKey(v)) {
	            return false;
	        }

	        int vertexIndex = indexMap.get(v);
	        // Remove from matrix
	        matrix.remove(vertexIndex);
	        for (ArrayList<Integer> row : matrix) {
	            row.remove(vertexIndex);
	        }

	        // Update indexMap
	        towns.remove(v);
	        indexMap.remove(v);
	        for (Town town : towns) {
	            int index = towns.indexOf(town);
	            indexMap.put(town, index);
	        }
	        return true;
	    }
	/**
	 * @return a set containing all of the town objects stored in the graph
	 */
	public Set<Town> vertexSet()
	{
		return new HashSet<>(towns);
	}
	
	/**
	 * @param sourceVertex
	 * @param destinationVertex
	 * @return an array list containing the shortest path from the source town to the other town
	 */
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
	    dijkstraShortestPath(sourceVertex);
	    ArrayList<String> path = new ArrayList<>();
	    int stepIndex = indexMap.get(destinationVertex);

	    // Check if a path exists
	    if (predecessors[stepIndex] == null) {
	        return path; // empty path means no path exists
	    }

	    // Construct the path
	    Town step = destinationVertex;
	    while (step != null) {
	        Town pred = predecessors[indexMap.get(step)];
	        if (pred != null) {
	            int predIndex = indexMap.get(pred);
	            String stepDescription = pred + " via Edge to " + step + " " + matrix.get(predIndex).get(indexMap.get(step));
	            path.add(0, stepDescription); // Add to the beginning
	        }
	        step = pred;
	    }
	    return path;
	}
	/**
	 * @param sourceVertex
	 */
	private int[] distances; // Array to hold distances from the source to each vertex
	private Town[] predecessors; // Array to hold the predecessor of each vertex in the path
	private boolean[] visited; // Array to keep track of visited vertices

	public void dijkstraShortestPath(Town sourceVertex) {
	    int numVertices = towns.size();
	    distances = new int[numVertices];
	    predecessors = new Town[numVertices];
	    visited = new boolean[numVertices];

	    // Initialize distances and predecessors
	    for (int i = 0; i < numVertices; i++) {
	        distances[i] = Integer.MAX_VALUE;
	        predecessors[i] = null;
	        visited[i] = false;
	    }

	    int sourceIndex = indexMap.get(sourceVertex);
	    distances[sourceIndex] = 0; // Distance from source to itself is zero

	    for (int count = 0; count < numVertices - 1; count++) {
	        int u = minDistance(distances, visited);
	        visited[u] = true;

	        for (int v = 0; v < numVertices; v++) {
	            if (!visited[v] && matrix.get(u).get(v) != 0 &&
	                distances[u] != Integer.MAX_VALUE &&
	                distances[u] + matrix.get(u).get(v) < distances[v]) {
	                distances[v] = distances[u] + matrix.get(u).get(v);
	                predecessors[v] = towns.get(u);
	            }
	        }
	    }
	}

	private int minDistance(int[] dist, boolean[] visited) {
	    int min = Integer.MAX_VALUE, minIndex = -1;
	    for (int v = 0; v < dist.length; v++) {
	        if (!visited[v] && dist[v] <= min) {
	            min = dist[v];
	            minIndex = v;
	        }
	    }
	    return minIndex;
	}

	
	
	
}
