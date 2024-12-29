package io.github.CrabK1ng.SaturnCart.mixins;

import finalforeach.cosmicreach.entities.player.Player;
import io.github.CrabK1ng.SaturnCart.RaceTrack;
import io.github.CrabK1ng.SaturnCart.networking.IPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Player.class)
public class PlayerMixin implements IPlayer {
    @Unique
    boolean saturnCart$isRacing;
    @Unique
    int saturnCart$laps;
    @Unique
    boolean saturnCart$checkpoint1;
    @Unique
    boolean saturnCart$checkpoint2;

    @Override
    public void setRacing(boolean isRacing){
        saturnCart$isRacing = isRacing;
    }

    @Override
    public boolean isRacing(){
        return saturnCart$isRacing;
    }

    @Override
    public int getLap(){
        return saturnCart$laps;
    }
    @Override
    public void setLap(int laps){
        saturnCart$laps = laps;
    }
    @Override
    public void addLap(int laps){
        saturnCart$laps += laps;
    }

    @Override
    public boolean getcheckpoint1(){
        return saturnCart$checkpoint1;
    }
    @Override
    public void setcheckpoint1(boolean checkpoint1){
        saturnCart$checkpoint1 = checkpoint1;
    }
    @Override
    public boolean getcheckpoint2(){
        return saturnCart$checkpoint2;
    }
    @Override
    public void setcheckpoint2(boolean checkpoint2){
        saturnCart$checkpoint2 = checkpoint2;
    }

    @Override
    public void reset(){
        saturnCart$isRacing = false;
        saturnCart$laps = 0;
        saturnCart$checkpoint1 = false;
        saturnCart$checkpoint2 = false;
    }
}
