
public class Road implements Comparable<Road> {
private Town start;
private Town end;
private Integer length;
private String name;
/**
 * 
 * @param source
 * @param destination
 * @param degrees
 * @param name
 * @return A Road object
 */
public Road (Town source, Town destination, int degrees, String name)
{
	this.start=source;
	this.end=destination;
	this.length=degrees;
	this.name=name;
}
/**
 * 
 * @param source
 * @param destination
 * @param name
 * @return A road with a set length of 1
 */
public Road (Town source, Town destination, String name) 
{
	this.start=source;
	this.end=destination;
	this.length=1;
	this.name=name;
}
/**
 * 
 * @param o
 * @return 0 if the road names match
 * and a negative or positive number if they do not match.
 */
public int compareTo(Road o) 
{
return name.compareTo(o.name);
}

/**
 * 
 * @param town 
 * @return true if the town is connected to the given road
 */
public boolean contains(Town town)
{
if(start.equals(town)||end.equals(town)) {
	return true;
}
else {
	return false;
}

}
/**
 * @return a string containing the road's information
 */
public String toString()
{
	return "Road name:"+name+" start town name: "+start+"/nend town name: " +end+"/nlength of the road:"+length;
}
/**
 * 
 * @return the name of the road
 */
public String getName()
{
return name;
}
/**
 * 
 * @return the town the road is connected to
 */
public Town getDestination()
{
return end;
}
/**
 * 
 * @return the other town the road is connected to
 */
public Town getSource()
{
return start;
}
/**
 * 
 * @return the length of the road
 */
public int getWeight()
{
return length;
}
/**
 * @param r one of the objects that will be compared
 * @return true if the two roads being compared are the same and false if they are not
 */
public boolean equals(Object r) 
{
if(!(r instanceof Road)) 
{
	return false;
}
if(this==r) 
{
	return true;
}else
	return false;
}

}



