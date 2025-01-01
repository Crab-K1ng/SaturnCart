package io.github.CrabK1ng.SaturnCart.mixins;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import finalforeach.cosmicreach.entities.player.Gamemode;
import finalforeach.cosmicreach.entities.player.Player;
import io.github.CrabK1ng.SaturnCart.RaceTrack;
import io.github.CrabK1ng.SaturnCart.api.IPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerMixin implements IPlayer{
    @Unique
    boolean saturnCart$isRacing;
    @Unique
    int saturnCart$laps;
    @Unique
    boolean saturnCart$checkpoint1;
    @Unique
    boolean saturnCart$checkpoint2;
    @Unique
    RaceTrack saturnCart$selectedTrack;
    @Unique
    boolean saturnCart$canEditSigns;
    @Unique
    boolean saturnCart$sound = false;

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
    public RaceTrack getSelectedTrack(){
        return saturnCart$selectedTrack;
    }
    @Override
    public void setSelectedTrack(RaceTrack track){
        saturnCart$selectedTrack = track;
    }

    @Override
    public boolean canEditSigns(){
        return saturnCart$canEditSigns;
    }
    @Override
    public void setCanEditSigns(boolean canEditSigns){
        saturnCart$canEditSigns = canEditSigns;
    }

    @Override
    public void setSound(boolean music){
        saturnCart$sound = music;
    }
    @Override
    public boolean isSoundOn(){
        return saturnCart$sound;
    }

    @Override
    public void reset(){
        saturnCart$isRacing = false;
        saturnCart$laps = 0;
        saturnCart$checkpoint1 = false;
        saturnCart$checkpoint2 = false;
    }

    @Inject(method = "write", at = @At("TAIL"))
    public void write(Json json, CallbackInfo ci) {
        json.writeValue("sound", this.saturnCart$sound);
    }

    @Inject(method = "read", at = @At("TAIL"))
    public void read(Json json, JsonValue jsonData, CallbackInfo ci) {
        jsonData.getBoolean("sound", this.saturnCart$sound);
    }
}
