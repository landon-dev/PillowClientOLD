package caterpillow.Module.HUD;

import org.lwjgl.input.Keyboard;

import caterpillow.Client.Client;
import caterpillow.Module.Category;
import caterpillow.Module.Module;
import caterpillow.event.EventTarget;
import caterpillow.event.events.Event2D;
import net.minecraft.client.gui.Gui;

public class FPS extends Module {

	public FPS() {
		super("FPS", Keyboard.KEY_NONE, Category.HUD);
	}

	@Override
	public void setup() {

		System.out.println("setup");

		pos = ScreenPosition.fromTopRight(50, 0);
		width = 50;
		height = fr.FONT_HEIGHT + 5;

	}

	@EventTarget
	public void onEvent2D(Event2D event) {
//		System.out.println(pos.getAbsoluteX() + "  " + pos.getAbsoluteY());

		if (!Client.instance.moduleManager.getModulesByName("HUDManager").isToggled()) {
			Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + 50,
					pos.getAbsoluteY() + fr.FONT_HEIGHT + 4, 0x90000000);

			fr.drawStringWithShadow(mc.getDebugFPS() + " FPS",
					pos.getAbsoluteX() + (50 - (fr.getStringWidth(mc.getDebugFPS() + " FPS"))) / 2,
					pos.getAbsoluteY() + 3, -1);
		}
	}

	@Override
	public void renderDummy(ScreenPosition pos) {
		fr.drawStringWithShadow("999 FPS", pos.getAbsoluteX() + (50 - (fr.getStringWidth("999 FPS"))) / 2,
				pos.getAbsoluteY() + 3, -1);
	}

}
