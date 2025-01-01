package io.github.CrabK1ng.SaturnCart.mixins;

import finalforeach.cosmicreach.rendering.visualstext.TextModelInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TextModelInstance.class)
public class TextModelInstanceMixin {

    @Shadow
    private float fontSize;

    @Inject(method = "setFontSize", at = @At("HEAD"), cancellable = true)
    public void setFontSize(float size, CallbackInfo ci) {
        if (!(size < 0.0F)) {
            this.fontSize = size;
        }
        ci.cancel();
    }
}
