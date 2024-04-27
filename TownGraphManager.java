import java.util.ArrayList;
import java.util.Set;

public class TownGraphManager implements TownGraphManagerInterface {
	private Graph graph;
	private ArrayList<Town> towns; // List to hold towns
    private ArrayList<Road> roads; // List to hold roads
	public TownGraphManager() {
		this.graph= new Graph();
		towns = new ArrayList<>();
	    roads = new ArrayList<>();
	   
	}
	/**
	 * 
	 * @param town1
	 * @param town2
	 * @param weight
	 * @param roadName
	 * @return 
	 */
	public boolean addRoad(String town1, String town2, int weight, String roadName)
	{
		   Town Town1 = new Town(town1);
	        Town Town2 = new Town(town2);
	        if (!graph.containsVertex(Town1) || !graph.containsVertex(Town2)) {
	            return false; // Ensure both towns exist
	        }
	        Road road = new Road(Town1, Town2, weight, roadName);
	        roads.add(road);
	        return graph.getEdge(Town1, Town2) != null;
	}
	/**
	 * 
	 * @param town1
	 * @param town2
	 * @return 
	 */
	public String getRoad(String town1, String town2)
	{
		 Town Town1 = new Town(town1);
	        Town Town2 = new Town(town2);
	        Road road = graph.getEdge(Town1, Town2);
	        return road != null ? road.getName() : null;
	}
	/**
	 * 
	 * @param Town
	 * @return
	 */
	public boolean addTown(String Town)
	{
		Town newTown = new Town(Town);
		towns.add(newTown);
        return graph.addVertex(newTown);
	}
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Town getTown(String name) 
	{
		 for (Town town : towns) {
		        if (town.getName().equals(name)) {
		            return town;
		        }
		    }
		    return null;  // Return null if no town with the given name exists
		
	}
	/**
	 * 
	 * @param Town
	 * @return
	 */
	public boolean containsTown(String Town)
	{
		return graph.containsVertex(new Town(Town));	
	}
	/**
	 * 
	 * @param town1
	 * @param town2
	 * @return
	 */
	public boolean containsRoadConnection(String town1, String town2) 
	{
		Town Town1 = new Town(town1);
        Town Town2 = new Town(town2);
        return graph.containsEdge(Town1, Town2);
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList<String> allRoads()
	{
		 Set<Road> roadSet = graph.edgeSet();
	        ArrayList<String> roadNames = new ArrayList<>();
	        for (Road road : roadSet) {
	            roadNames.add(road.getName());
	        }
	        roadNames.sort(String::compareTo);
	        return roadNames;
	}
	/**
	 * 
	 * @param town1
	 * @param town2
	 * @param road
	 * @return
	 */
	public boolean deleteRoadConnection(String town1, String town2, String road)
	{
			Town Town1 = new Town(town1);
		    Town Town2 = new Town(town2);
		    Road tbdRoad= new Road(Town1,Town2,road);
		   
		    	if(roads.contains(tbdRoad)) 
		    	{
		    		graph.removeEdge(tbdRoad.getSource(), tbdRoad.getDestination(), tbdRoad.getWeight(), tbdRoad.getName());
		    		return roads.remove(tbdRoad);
		    	}
		    	else
		    	{
		    		return false;
		    	}
	}
	
	/**
	 * 
	 * @param Town
	 * @return
	 */
	public boolean deleteTown(String Town)
	{
	       Town town = new Town(Town);
	        return graph.removeVertex(town);
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList<String> allTowns()
	{
		Set<Town> towns = graph.vertexSet();
        ArrayList<String> townNames = new ArrayList<>();
        for (Town town : towns) {
            townNames.add(town.getName());
        }
        townNames.sort(String::compareTo);
        return townNames;
	}
	/**
	 * 
	 * @param town1
	 * @param town2
	 * @return
	 */
	public ArrayList<String> getPath(String town1, String town2)
	{
		 Town Town1 = new Town(town1);
	        Town Town2 = new Town(town2);
	        return graph.shortestPath(Town1, Town2);
	}


}
