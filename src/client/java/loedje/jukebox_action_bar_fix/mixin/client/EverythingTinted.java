package loedje.jukebox_action_bar_fix.mixin.client;

import loedje.jukebox_action_bar_fix.JukeboxActionBarFix;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.message.MessageHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MessageHandler.class)
public class EverythingTinted {
	@Redirect(method = "onGameMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;setOverlayMessage(Lnet/minecraft/text/Text;Z)V"))
	private void injected(InGameHud instance, Text message, boolean tinted) {
		instance.setOverlayMessage(message, JukeboxActionBarFix.getConfig().isEverythingTinted());
	}
}