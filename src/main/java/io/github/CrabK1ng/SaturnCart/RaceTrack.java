package io.github.CrabK1ng.SaturnCart;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.github.puzzle.game.util.BlockUtil;
import finalforeach.cosmicreach.GameSingletons;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.blocks.MissingBlockStateResult;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.io.SaveLocation;
import finalforeach.cosmicreach.networking.packets.MessagePacket;
import finalforeach.cosmicreach.networking.server.ServerSingletons;
import io.github.CrabK1ng.SaturnCart.networking.IPlayer;
import io.github.CrabK1ng.SaturnCart.util.FileUtils;
import io.github.CrabK1ng.SaturnCart.util.Vector3Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static io.github.CrabK1ng.SaturnCart.GameManager.allTracks;
import static io.github.CrabK1ng.SaturnCart.util.FileUtils.loadFile;

public class RaceTrack implements Json.Serializable {
    private static final Json JSON = new Json();
    private String trackName;
    public  boolean start = false;
    private List<Vector3> finishLinePositions;
    private List<Vector3> checkpoinOnePositions;
    private List<Vector3> checkpoinTwoPositions;
    public static int laps = 1;
    private List<Player> players = new ArrayList<>();
    private List<Player> finished = new ArrayList<>();

    private RaceTrack(){
        this.finishLinePositions = new ArrayList<>(Collections.nCopies(2, null));
        this.checkpoinOnePositions = new ArrayList<>(Collections.nCopies(2, null));
        this.checkpoinTwoPositions = new ArrayList<>(Collections.nCopies(2, null));
    }

    public RaceTrack(String name){
        this.trackName = name;
        this.finishLinePositions = new ArrayList<>(Collections.nCopies(2, null));
        this.checkpoinOnePositions = new ArrayList<>(Collections.nCopies(2, null));
        this.checkpoinTwoPositions = new ArrayList<>(Collections.nCopies(2, null));
    }

    public void start(){

        MessagePacket messagepacket = new MessagePacket("starting countdown");
        ServerSingletons.SERVER.broadcastToAll(messagepacket);
        int start = 10; // Start at 10 seconds
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            int timeLeft = start;

            @Override
            public void run() {
                if (timeLeft >= 1) {
                    MessagePacket messagepacket = new MessagePacket(timeLeft--+"");
                    ServerSingletons.SERVER.broadcastToAll(messagepacket);
                } else {
                    wallDown();
                    timer.cancel(); // Stop the timer
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000); // Run every 1000ms (1 second)
    }

    public void stop(){
        players.forEach(p -> {
            IPlayer player = (IPlayer) p;
            player.reset();
        });

        for (int i = 0; i < finished.size(); i++) {
            Player player = finished.get(i);
            int l = i + 1;
            MessagePacket messagepacket = new MessagePacket(l + ":" + player.getAccount().getDisplayName());
            ServerSingletons.SERVER.broadcastToAll(messagepacket);
        }

        this.wallUp();
    }

    public void wallUp(){
        List<Vector3> finishLinePositions = this.finishLinePositions;
        Vector3 pos1 = finishLinePositions.get(0);
        Vector3 pos2 = finishLinePositions.get(1);

        Vector3Utils.getAllInBox(pos1, pos2).forEach(pos ->
                BlockUtil.setBlockAt(GameSingletons.world.getZoneCreateIfNull(GameSingletons.world.defaultZoneId), BlockState.getInstance("base:glass[default]", MissingBlockStateResult.MISSING_OBJECT), (int) pos.x, (int) pos.y, (int) pos.z)
        );
    }

    public void wallDown(){
        players.forEach(p -> {
            IPlayer player = (IPlayer) p;
            player.reset();
            player.setRacing(true);
        });
        List<Vector3> finishLinePositions = this.finishLinePositions;
        Vector3 pos1 = finishLinePositions.get(0);
        Vector3 pos2 = finishLinePositions.get(1);

        Vector3Utils.getAllInBox(pos1, pos2).forEach(pos ->
                BlockUtil.setBlockAt(GameSingletons.world.getZoneCreateIfNull(GameSingletons.world.defaultZoneId), BlockState.getInstance("base:air[default]", MissingBlockStateResult.MISSING_OBJECT), (int) pos.x, (int) pos.y, (int) pos.z)
        );
        MessagePacket messagepacket = new MessagePacket("GO!!");
        ServerSingletons.SERVER.broadcastToAll(messagepacket);
    }



    public void addFinished(Player player){
        IPlayer iPlayer = (IPlayer) player;
        iPlayer.reset();
        this.finished.add(player);
    }

    public List<Player> getFinished(){
        return this.finished;
    }


    public  void setName(String newName){
        this.trackName = newName;
    }

    public  String getName(){
        return this.trackName;
    }

    public void addplayer(Player player){
        this.players.add(player);
    }

    public List<Player> getplayers(){
        return this.players;
    }

    public int getLaps(){
        return this.laps;
    }

    public void setLaps(int laps){
        this.laps = laps;
    }

    public  List<Vector3> getFinishLinePositions() {
        return this.finishLinePositions;
    }

    public  List<Vector3> getCheckpoinOnePositions() {
        return this.checkpoinOnePositions;
    }

    public  List<Vector3> getCheckpoinTwoPositions() {
        return this.checkpoinTwoPositions;
    }

    public  void setFinishLinePositionOne(Vector3 pos){
        Vector3 vector3 = new Vector3((int) pos.x, (int) pos.y, (int) pos.z);
        this.finishLinePositions.set(0, vector3);
    }
    public  void setFinishLinePositionTwo(Vector3 pos){
        Vector3 vector3 = new Vector3((int) pos.x, (int) pos.y, (int) pos.z);
        this.finishLinePositions.set(1, vector3);
    }

    public  void setCheckpoinOnePositionOne(Vector3 pos){
        Vector3 vector3 = new Vector3((int) pos.x, (int) pos.y, (int) pos.z);
        this.checkpoinOnePositions.set(0, vector3);
    }
    public  void setCheckpoinOnePositionTwo(Vector3 pos){
        Vector3 vector3 = new Vector3((int) pos.x, (int) pos.y, (int) pos.z);
        this.checkpoinOnePositions.set(1, vector3);
    }

    public  void setCheckpoinTwoPositionOne(Vector3 pos){
        Vector3 vector3 = new Vector3((int) pos.x, (int) pos.y, (int) pos.z);
        this.checkpoinTwoPositions.set(0, vector3);
    }
    public  void setCheckpoinTwoPositionTwo(Vector3 pos){
        Vector3 vector3 = new Vector3((int) pos.x, (int) pos.y, (int) pos.z);
        this.checkpoinTwoPositions.set(1, vector3);
    }

    public static void loadAllTracks(String worldFolderName) {
        FileUtils.forEachAsset(worldFolderName, ".json", (p, f) -> {
            try {
                loadTrack(f);
            } catch (Exception exception) {
                throw new RuntimeException("Error parsing Track: " + p, exception);
            }
        });
    }

    public static RaceTrack loadTrack(String asset) throws FileNotFoundException {
        RaceTrack track = JSON.fromJson(RaceTrack.class, loadFile(asset));
        Constants.LOGGER.info("adding track: "+ track.getName());
        allTracks.put(track.getName(), track);
        return track;
    }

    public static void saveAllTracks() {
        allTracks.forEach((name, track) -> {
            saveTrack(name, track);
        });
    }

    public static void saveTrack(String trackName, RaceTrack track) {
        JSON.setOutputType(JsonWriter.OutputType.json);
        String s = JSON.prettyPrint(track);
        String rootPath = SaveLocation.getWorldSaveFolderLocation(GameSingletons.world);
        rootPath = rootPath.replace("./", "");
        File file1 = new File(rootPath + "/tracks"  + "/" + trackName + ".json");

        try {
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            if (!file1.exists()) {
                file1.createNewFile();
            }
            try (FileOutputStream fileoutputstream = new FileOutputStream(file1)) {
                fileoutputstream.write(s.getBytes(StandardCharsets.UTF_8));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void write(Json json) {
        json.writeValue("trackName", this.trackName);
        json.writeValue("laps", this.laps);
        json.writeValue("finishLinePositions", finishLinePositions, List.class, Vector3.class);
        json.writeValue("checkpoinOnePositions", checkpoinOnePositions, List.class, Vector3.class);
        json.writeValue("checkpoinTwoPositions", checkpoinTwoPositions, List.class, Vector3.class);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        this.trackName = json.readValue("trackName", String.class, jsonData);
        this.laps = json.readValue("laps", int.class, jsonData);
        this.finishLinePositions = json.readValue("finishLinePositions", List.class, Vector3.class, jsonData);
        this.checkpoinOnePositions = json.readValue("checkpoinOnePositions", List.class, Vector3.class, jsonData);
        this.checkpoinTwoPositions = json.readValue("checkpoinTwoPositions", List.class, Vector3.class, jsonData);
    }
}