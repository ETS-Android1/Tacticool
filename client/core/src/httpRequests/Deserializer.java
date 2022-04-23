package httpRequests;

import java.util.ArrayList;
import java.util.List;

import com.anything.tacticool.model.Action;
import com.anything.tacticool.model.ActionType;
import com.anything.tacticool.model.Grid;
import com.anything.tacticool.model.Player;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Deserializer {
    
    public Grid deserializeTurn(String json) {
        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(json, JsonObject.class);

        List<Player> players = deserializePlayers(obj.get("players").getAsJsonArray());
        deserializeActions(obj.get("actions").getAsJsonObject(), players);
        return deserializeGrid(obj.get("grid").getAsJsonObject(), players);
    }

    private List<Player> deserializePlayers(JsonArray playersJson) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < playersJson.size(); i++) {
            JsonObject playerJson = playersJson.get(i).getAsJsonObject();
            Player player = new Player(Integer.parseInt(playerJson.get("playerID").getAsString()), Integer.parseInt(playerJson.get("healthPoint").getAsString()));
            players.add(player);
        }
        return players;
    }

    private void deserializeActions(JsonObject actionsJson, List<Player> players) {
        for (Player player : players) {
            JsonArray actions = actionsJson.get(String.format("%d", player.getPlayerID())).getAsJsonObject().get("actions").getAsJsonArray();
            for (int i = 0; i < actions.size(); i++) {
                JsonObject actionJson = actions.get(i).getAsJsonObject();
                JsonObject coordinate = actionJson.get("coordinate").getAsJsonObject();
                Action action = new Action(ActionType.valueOf(actionJson.get("actionType").getAsString()), Integer.parseInt(coordinate.get("x").getAsString()), Integer.parseInt(coordinate.get("y").getAsString()));
                player.addAction(action);
            }
        }
    }

    private Grid deserializeGrid(JsonObject gridJson, List<Player> players) {
        System.out.println(gridJson);
        Grid grid = new Grid(
            gridJson.get("board").getAsString(), 
            Integer.parseInt(gridJson.get("width").getAsString()), 
            Integer.parseInt(gridJson.get("height").getAsString()));
        grid.setPlayers(players);
        return grid;
    }


    public static void main(String[] args) {
        Deserializer deserializer = new Deserializer();
        Grid grid = deserializer.deserializeTurn("{\"players\":[{\"healthPoint\":95,\"playerID\":8,\"actionPoints\":10,\"position\":{\"x\":1,\"y\":1}},{\"healthPoint\":95,\"playerID\":7,\"actionPoints\":10,\"position\":{\"x\":3,\"y\":1}}],\"actions\":{\"7\":{\"actions\":[{\"coordinate\":{\"x\":3,\"y\":1},\"actionType\":\"MOVE\"}]},\"8\":{\"actions\":[{\"coordinate\":{\"x\":1,\"y\":1},\"actionType\":\"MOVE\"}]}},\"grid\":{\"board\":\"0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0\",\"width\":5,\"height\":5}}");
        System.out.println(grid);
    }
}
