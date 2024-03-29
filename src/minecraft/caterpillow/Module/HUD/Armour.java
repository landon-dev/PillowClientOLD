package caterpillow.Module.HUD;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import caterpillow.Client.Client;
import caterpillow.Module.Category;
import caterpillow.Module.Module;
import caterpillow.event.EventTarget;
import caterpillow.event.events.Event2D;
import de.Hero.settings.Setting;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Armour extends Module {

	public Armour() {
		super("Armour", Keyboard.KEY_NONE, Category.HUD);
	}

	@Override
	public void setup() {

		Client.instance.settingsManager.rSetting(new Setting("Hat", this, true));
		Client.instance.settingsManager.rSetting(new Setting("Shirt", this, true));
		Client.instance.settingsManager.rSetting(new Setting("Pants", this, true));
		Client.instance.settingsManager.rSetting(new Setting("Shoes", this, true));

		ScaledResolution sr = new ScaledResolution(mc);

		pos = ScreenPosition.fromAbsolute(sr.getScaledWidth() - 64, sr.getScaledHeight() - 64);
		width = 64;
		height = 64;

	}

	@EventTarget
	public void onEvent2D(Event2D event) {

		if (!Client.instance.moduleManager.getModulesByName("HUDManager").isToggled()) {
			if (Client.instance.settingsManager.getSettingByName("Hat").getValBoolean()) {
				ItemStack itemStack = mc.thePlayer.inventory.armorInventory[3];
				renderItemStack(pos, 3, itemStack);
			}
			if (Client.instance.settingsManager.getSettingByName("Shirt").getValBoolean()) {
				ItemStack itemStack = mc.thePlayer.inventory.armorInventory[2];
				renderItemStack(pos, 2, itemStack);
			}
			if (Client.instance.settingsManager.getSettingByName("Pants").getValBoolean()) {
				ItemStack itemStack = mc.thePlayer.inventory.armorInventory[1];
				renderItemStack(pos, 1, itemStack);
			}
			if (Client.instance.settingsManager.getSettingByName("Shoes").getValBoolean()) {
				ItemStack itemStack = mc.thePlayer.inventory.armorInventory[0];
				renderItemStack(pos, 0, itemStack);
			}

		}
	}

	@Override
	public void renderDummy(ScreenPosition pos) {
		renderItemStack(pos, 3, new ItemStack(Items.diamond_helmet));
		renderItemStack(pos, 2, new ItemStack(Items.iron_chestplate));
		renderItemStack(pos, 1, new ItemStack(Items.golden_leggings));
		renderItemStack(pos, 0, new ItemStack(Items.leather_boots));

	}

	private void renderItemStack(ScreenPosition pos, int i, ItemStack item) {

		if (item == null) {
			return;
		}
		GL11.glPushMatrix();
		int yOffset = (-16 * i) + 48;

		if (item.getItem().isDamageable()) {
			double damage = (item.getMaxDamage() - item.getItemDamage()) / (double) item.getMaxDamage() * 100;
			fr.drawStringWithShadow(Double.toString(Math.ceil(damage)), pos.getAbsoluteX() + 20,
					pos.getAbsoluteY() + yOffset + 4, -1);
		}

		RenderHelper.enableGUIStandardItemLighting();
		mc.getRenderItem().renderItemAndEffectIntoGUI(item, pos.getAbsoluteX(), pos.getAbsoluteY() + yOffset);
		GL11.glPopMatrix();

	}

}
