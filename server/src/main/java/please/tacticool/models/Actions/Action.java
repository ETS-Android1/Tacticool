package please.tacticool.models.Actions;

import java.util.List;

import please.tacticool.models.Coordinate;
import please.tacticool.models.TerrainGrid;
import please.tacticool.models.Actors.Player;

public abstract class Action {

    // Player
    protected final Player player;
    // List of player input actions
    protected final List<Coordinate> path;
    // Returning list
    private List<Coordinate> affectedCoordinates;
    // Get somewhere else
    private final int actionCost;

    public Action(Player player, List<Coordinate> path, int actionCost){
        this.player = player;
        this.path = path;
        this.actionCost = actionCost;
    }

    /**
     * Gets the cost of performing this action
     * @return : cost of action
     */
    public abstract int getCost();

    /**
     * Gets the radius of the tiles this action affects
     * @return : radius of action target
     */
    public abstract int getTargetRadius();

    /**
     * Gets the priority this action will take
     * @return : priority of action
     */
    public abstract int getPriority();

    public List<Coordinate> getAffectedCoordinates() {
        return affectedCoordinates;
    }

    public void setAffectedCoordinates(List<Coordinate> affectedCoordinates) {
        this.affectedCoordinates = affectedCoordinates;
    }

    public int getActionCost() {
        return actionCost;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Coordinate> getPath() {
        return path;
    }

    public abstract void execute(TerrainGrid grid);

    //public abstract List<Coordinate> execute(Coordinate position, List<Coordinate> path, TerrainGrid grid);
}
