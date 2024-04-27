import java.util.ArrayList;
import java.util.Objects;

public class Town 
{
private String name;

/**
 * @return a new Town object
 * @param name
 */
public Town(String name) 
{
	this.name = name;
}
/**
 * @return A copy of a town
 * @param templateTown
 */
public Town(Town templateTown)
{
	
}
/**
 * 
 * @return a String containing the name of the town
 */
public String getName()
{
	return name;
}
/**
 * 
 * @param o
 * @return 0 if the two towns have the same name
 */
public int compareTo(Town o) 
{
	if(name.equals(o.getName()))
	{
		return 0; 
	}
		return -1;
}
/**
 * 
 * @return String
 */
public String toString()
{
	return "Town: "+name;	
}
/**
 * @return int 
 */
public int hashCode() {
    return name.hashCode();
}

@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Town town = (Town) obj;
    return name.equals(town.name);

}
}