package loedje.jukebox_action_bar_fix;

import net.fabricmc.api.ClientModInitializer;

public class JukeboxActionBarFix implements ClientModInitializer {

	private static Config config = new Config();

	@Override
	public void onInitializeClient() {
		config.init();
	}

	public static Config getConfig() {
		return config;
	}

	public static void setConfig(Config config) {
		JukeboxActionBarFix.config = config;
	}
}