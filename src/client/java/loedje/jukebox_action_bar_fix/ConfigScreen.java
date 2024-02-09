package loedje.jukebox_action_bar_fix;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class ConfigScreen extends Screen {
	private static final int HORIZONTAL_DIVIDER = 10;
	private static final int VERTICAL_DIVIDER = 4;
	private static final int BUTTON_WIDTH = 150;
	private static final int BUTTON_HEIGHT = 20;
	private static final int TEXT_HEIGHT = 8;
	private final Screen parent;

	protected ConfigScreen(Screen parent) {
		super(Text.literal("Jukebox Action Bar Fix configuration"));
		this.parent = parent;
	}

	@Override
	protected void init() {
		Config config = JukeboxActionBarFix.getConfig();

		addDrawableChild(ButtonWidget.builder(
						Text.literal("Everything tinted: " + JukeboxActionBarFix.getConfig().isEverythingTinted()),
						button -> {
							config.setEverythingTinted(!config.isEverythingTinted());
							clearAndInit();
						}).size(BUTTON_WIDTH, BUTTON_HEIGHT)
				.position(width / 2 - BUTTON_WIDTH - HORIZONTAL_DIVIDER / 2, height / 6 - 12)
				.build());

		addDrawableChild(ButtonWidget.builder(
						Text.literal("Vibrant colors: " + JukeboxActionBarFix.getConfig().isVibrantColors()),
						button -> {
							config.setVibrantColors(!config.isVibrantColors());
							clearAndInit();
						}).size(BUTTON_WIDTH, BUTTON_HEIGHT)
				.position(width / 2 + HORIZONTAL_DIVIDER / 2, height / 6 - 12)
				.build());

		addDrawableChild(new TextWidget(
				width / 2 - BUTTON_WIDTH - HORIZONTAL_DIVIDER / 2,
				height / 6 - 12 + VERTICAL_DIVIDER + BUTTON_HEIGHT + VERTICAL_DIVIDER,
				BUTTON_WIDTH,
				TEXT_HEIGHT,
				Text.literal("Cycle length:"),
				textRenderer).alignLeft());

		TextFieldWidget cycleLengthFieldWidget = new TextFieldWidget(textRenderer,
				width / 2 - BUTTON_WIDTH - HORIZONTAL_DIVIDER / 2,
				height / 6 - 12 + VERTICAL_DIVIDER + BUTTON_HEIGHT + VERTICAL_DIVIDER + TEXT_HEIGHT + VERTICAL_DIVIDER,
				BUTTON_WIDTH,
				BUTTON_HEIGHT,
				Text.literal("Cycle length"));
		cycleLengthFieldWidget.setMaxLength(3);
		cycleLengthFieldWidget.setText(String.valueOf(config.getCycleLength()));
		cycleLengthFieldWidget.setChangedListener(s -> {
			if (s.matches("\\d+") && !s.equals(String.valueOf(config.getCycleLength()))) {

				config.setCycleLength(Integer.parseInt(s));

			}
		});

		addDrawableChild(cycleLengthFieldWidget);

		addDrawableChild(new TextWidget(
				width / 2 + HORIZONTAL_DIVIDER / 2,
				height / 6 - 12 + VERTICAL_DIVIDER + BUTTON_HEIGHT + VERTICAL_DIVIDER,
				BUTTON_WIDTH,
				TEXT_HEIGHT,
				Text.literal("Time length:"),
				textRenderer).alignLeft());

		TextFieldWidget timeLengthFieldWidget = new TextFieldWidget(textRenderer,
				width / 2 + HORIZONTAL_DIVIDER / 2,
				height / 6 - 12 + VERTICAL_DIVIDER + BUTTON_HEIGHT + VERTICAL_DIVIDER + TEXT_HEIGHT + VERTICAL_DIVIDER,
				BUTTON_WIDTH,
				BUTTON_HEIGHT,
				Text.literal("Time length"));
		timeLengthFieldWidget.setMaxLength(3);
		timeLengthFieldWidget.setText(String.valueOf(config.getCycleLength()));
		timeLengthFieldWidget.setChangedListener(s -> {
			if (s.matches("\\d+") && !s.equals(String.valueOf(config.getTimeLength()))) {

				config.setTimeLength(Integer.parseInt(s));

			}
		});

		addDrawableChild(timeLengthFieldWidget);

		addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button -> this.client.setScreen(parent))
				.dimensions(this.width / 2 - 100,
						this.height / 6 - 12 + VERTICAL_DIVIDER + BUTTON_HEIGHT + VERTICAL_DIVIDER + TEXT_HEIGHT + VERTICAL_DIVIDER + BUTTON_HEIGHT + VERTICAL_DIVIDER,
						200,
						BUTTON_HEIGHT).build());
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		super.render(context, mouseX, mouseY, delta);
		context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 15, 0xFFFFFF);
	}

	@Override
	public void close() {
		client.setScreen(parent);
	}
}
