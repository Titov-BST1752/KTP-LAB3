import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class AStarState
{
    private Map2D map;
    private Map<Location, Waypoint> openWaypoints = new HashMap<>();
    private Map<Location, Waypoint> closedWaypoints = new HashMap<>();

    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    public Map2D getMap()
    {
        return map;
    }

    public Waypoint getMinOpenWaypoint()
    {
        return openWaypoints.values().stream()
                   .min(Comparator.comparing(Waypoint::getTotalCost))
                   .get();
    }

    public boolean addOpenWaypoint(Waypoint newWP)
    {
        if (openWaypoints.containsKey(newWP.getLocation())) {
            if (openWaypoints.get(newWP.getLocation()).getPreviousCost() > newWP.getPreviousCost()) {
                openWaypoints.put(newWP.getLocation(), newWP);
                return true;
            }
        } else {
            openWaypoints.put(newWP.getLocation(), newWP);
        }
        return false;
    }

    public int numOpenWaypoints()
    {
        return openWaypoints.size();
    }

    public void closeWaypoint(Location loc)
    {
        closedWaypoints.put(loc, openWaypoints.remove(loc));
    }

    public boolean isLocationClosed(Location loc)
    {
        return closedWaypoints.containsKey(loc);
    }
}

