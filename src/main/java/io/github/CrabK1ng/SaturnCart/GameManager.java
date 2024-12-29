package io.github.CrabK1ng.SaturnCart;

import finalforeach.cosmicreach.GameSingletons;

import java.util.HashMap;
import java.util.Map;

public class GameManager {
    public static final Map<String, RaceTrack> allTracks = new HashMap<>();
    public static final Map<String, RaceTrack> playerSelectedTrack = new HashMap<>();
    public static RaceTrack selectedTrack;

    public static RaceTrack newTrack(String name){
        RaceTrack track = new RaceTrack(name);
        allTracks.put(name, track);
        return track;
    }

    public static void selectTrack(String name){
        selectedTrack = allTracks.get(name);
    }

    public static RaceTrack getSelectedTrack(){
        return selectedTrack;
    }

    public static void setSelectPlayerTrack(String uniqueId, String trackName){
        RaceTrack track = allTracks.get(trackName);
        playerSelectedTrack.put(uniqueId, track);
        track.addplayer(GameSingletons.getPlayerFromUniqueId(uniqueId));
    }

    public static RaceTrack getPlayerSelectedTrack(String uniqueId){
        return playerSelectedTrack.get(uniqueId);
    }
}
