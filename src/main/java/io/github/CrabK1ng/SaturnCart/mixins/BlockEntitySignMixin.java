package io.github.CrabK1ng.SaturnCart.mixins;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import finalforeach.cosmicreach.GameSingletons;
import finalforeach.cosmicreach.Threads;
import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.blockentities.BlockEntitySign;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.items.Item;
import finalforeach.cosmicreach.networking.packets.MessagePacket;
import finalforeach.cosmicreach.networking.packets.blocks.BlockEntityDataPacket;
import finalforeach.cosmicreach.networking.packets.blocks.SignsEntityPacket;
import finalforeach.cosmicreach.networking.server.ServerSingletons;
import finalforeach.cosmicreach.rendering.ITextModelInstance;
import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.util.Identifier;
import finalforeach.cosmicreach.world.Zone;
import io.github.CrabK1ng.SaturnCart.Constants;
import io.github.CrabK1ng.SaturnCart.GameManager;
import io.github.CrabK1ng.SaturnCart.api.IPlayer;
import io.github.CrabK1ng.SaturnCart.util.MessageSend;
import io.github.CrabK1ng.SaturnCart.util.SoundManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

@Mixin(BlockEntitySign.class)
public abstract class BlockEntitySignMixin extends BlockEntity{
    @Final
    @Shadow
    public static Identifier BLOCK_ENTITY_ID;

    @Shadow
    private float textSize;
    @Shadow
    public boolean runTexture = true;
    @Shadow public abstract void setFontSize(float size);
    @Shadow
    private ITextModelInstance textModel;
    @Shadow
    private String[] texts;
    @Unique
    List<String> listTexts = new ArrayList<>();

    @Inject(method = "onInteract", at = @At("HEAD"), cancellable = true)
    public void onInteract(Player player, Zone zone, CallbackInfo ci) {
        super.onInteract(player, zone);
        IPlayer iplayer = (IPlayer) player;
        if (player.getAccount().isOperator() && iplayer.canEditSigns()){
            GameSingletons.openBlockEntityScreen(player, zone, this);
        } else {
            Constants.LOGGER.info(this.listTexts);
            if (this.listTexts.contains("join queue")){ //autocompleted with spell wizard
                GameManager.joinPlayer(player);
            } else if (this.listTexts.contains("sound")){
                SoundManager.cyclePlayerSound(player);
            }
        }
        ci.cancel();
    }

    @Inject(method = "setTexts", at = @At("TAIL"))
    public void setTexts(String[] texts, CallbackInfo ci) {
        this.listTexts.clear();
        for (Iterator<String> it = Arrays.stream(texts).iterator(); it.hasNext(); ) {
            String string = it.next();
            this.listTexts.add(string);
        }
    }

    @Inject(method = "setTextToLine", at = @At("TAIL"))
    public void setTextToLine(int index, String text, CallbackInfo ci) {
        this.listTexts.clear();
        for (Iterator<String> it = Arrays.stream(this.texts).iterator(); it.hasNext(); ) {
            String string = it.next();
            this.listTexts.add(string);
        }
    }

    @Inject(method = "read", at = @At("TAIL"))
    public void read(CRBinDeserializer deserial, CallbackInfo ci) {
        this.listTexts.clear();
        for (Iterator<String> it = Arrays.stream(this.texts).iterator(); it.hasNext(); ) {
            String string = it.next();
            this.listTexts.add(string);
        }
    }

    @Override
    public String getBlockEntityId() {
        return BLOCK_ENTITY_ID.toString();
    }
}
