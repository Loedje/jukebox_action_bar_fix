package loedje.jukebox_action_bar_fix;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.lang.reflect.Modifier;

public class Config {
	public static final File FILE = FabricLoader.getInstance().getConfigDir().resolve("jukebox_action_bar_fix.json").toFile();
	// Settings
	private boolean everythingTinted = false;
	private boolean vibrantColors = false;
	private int timeLength = 60;
	private int cycleLength = 50;

	public void init() {
		try {
			if (FILE.createNewFile()) {
				write();
				return;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		read();
	}

	private void write() {
		try(FileWriter fileWriter = new FileWriter(FILE)) {
			new GsonBuilder()
					.excludeFieldsWithModifiers(Modifier.STATIC)
					.setPrettyPrinting()
					.create()
					.toJson(JukeboxActionBarFix.getConfig(),
							JukeboxActionBarFix.getConfig().getClass(),
							fileWriter);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void read() {
		try (FileReader fileReader = new FileReader(FILE)) {
			JukeboxActionBarFix.setConfig(new Gson().fromJson(
					fileReader,
					JukeboxActionBarFix.getConfig().getClass()));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isEverythingTinted() {
		return everythingTinted;
	}

	public boolean isVibrantColors() {
		return vibrantColors;
	}

	public int getTimeLength() {
		return timeLength;
	}

	public int getCycleLength() {
		return cycleLength;
	}

	public void setEverythingTinted(boolean everythingTinted) {
		this.everythingTinted = everythingTinted;
		write();
	}

	public void setVibrantColors(boolean vibrantColors) {
		this.vibrantColors = vibrantColors;
		write();
	}

	public void setTimeLength(int timeLength) {
		this.timeLength = timeLength;
		write();
	}

	public void setCycleLength(int cycleLength) {
		this.cycleLength = cycleLength;
		write();
	}
}