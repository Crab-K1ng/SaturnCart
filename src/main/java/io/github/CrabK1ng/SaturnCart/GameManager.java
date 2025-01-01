package io.github.CrabK1ng.SaturnCart;

import com.badlogic.gdx.math.MathUtils;
import finalforeach.cosmicreach.entities.player.Player;
import io.github.CrabK1ng.SaturnCart.api.IPlayer;
import io.github.CrabK1ng.SaturnCart.util.SoundManager;

import java.util.*;


public class GameManager {
    public static final Map<String, RaceTrack> allTracks = new HashMap<>();
    public static List<RaceTrack> raceTracks = new ArrayList<>();
    public static List<Player> activePlayers = new ArrayList<>();
    public static boolean stopRequested = false; //autocompleted by spell wizard //"no yawning"
    public static RaceTrack currentTrack; //autocompleted by spell wizardTM
    public static boolean started = false;

    // start the loop
    public static void start(){
        started = true;
        int randomInt = MathUtils.random(0, raceTracks.size() - 1);
        RaceTrack track = raceTracks.get(randomInt);
        currentTrack = track;
        activePlayers.forEach(track::addplayer);
        track.goTo();
        track.start();
    }

    public static void stop(){
        stopRequested = true;
    }

    public static void nextTrack(){
        if (!stopRequested){
            int randomInt = MathUtils.random(0, raceTracks.size() - 1);
            RaceTrack track = raceTracks.get(randomInt);
            currentTrack = track;
            activePlayers.forEach(track::addplayer);
            track.goTo();
            track.start();
        }
    }

    public static RaceTrack newTrack(String name){
        RaceTrack track = new RaceTrack(name);
        allTracks.put(name, track);
        raceTracks.add(track);
        return track;
    }

    public static RaceTrack getCurrentTrack(){
        return currentTrack;
    }

    public static void setPlayerTrack(Player player, String trackName){ //"it sounded like an avalanche" i see you. thats creepy man... how many fingers am i holding up? 12? i dont have 12 fingers... yes you do look in the box. what box? i dont have a box. it in your bed. "it in your bed" that doesnt make any sense???? and no there is no box in my bed or on my bed or even near my bed although i do have a 3d printed treasure chest. look in dm. ok :thumbs_up:, ahhhhhh, what the hell man, thats weeeird. no it's not. well you sent me a photo of a person holding up 2 fingers, people dont normally do things like that..., they don't? well, not really? ok i think i was being a bit too harsh, its ok to send people pictures of hands in dms, buuut its weird to tell someone they have more than 10 fingers. me sad :(. its ok crab, tell me whats wrong and i can try and help :). you called me weird :(. nooo crab, i dont think you're weird, you're just.. special ig?, :( i am going bye :(((.. noooooo crab(by) please stay :((( dont goo man.
        RaceTrack track = allTracks.get(trackName); //"Thats like so long"
        IPlayer iplayer = (IPlayer) player;
        iplayer.setSelectedTrack(track);
    }

    public static RaceTrack getPlayerTrack(Player player){
        IPlayer iplayer = (IPlayer) player;
        return iplayer.getSelectedTrack();
    }

    public static void joinPlayer(Player player){
        activePlayers.add(player);
    }

}
