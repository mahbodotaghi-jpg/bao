package dev.nitro.modules;

import dev.nitro.module.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

/** Small on-screen totem indicator showing the remaining inventory count and offhand state. */
public final class HoverTotem extends Module {
    public HoverTotem() {
        super("Hover Totem", "Shows your totem count and whether a totem is equipped.", Category.COMBAT);
    }

    @Override
    public void renderHud(DrawContext ctx) {
        if (mc.player == null) return;
        int count = mc.player.getOffHandStack().isOf(Items.TOTEM_OF_UNDYING) ? 1 : 0;
        for (ItemStack stack : mc.player.getInventory().main) {
            if (stack.isOf(Items.TOTEM_OF_UNDYING)) count += stack.getCount();
        }
        int x = ctx.getScaledWindowWidth() - 76;
        int y = 12;
        ctx.fill(x, y, x + 64, y + 28, 0xA91A1028);
        ctx.fill(x, y, x + 64, y + 1, 0xFFB56CFF);
        ctx.drawItem(new ItemStack(Items.TOTEM_OF_UNDYING), x + 5, y + 5);
        ctx.drawText(mc.textRenderer, "x" + count, x + 27, y + 10,
                count > 0 ? 0xFFFFE8A3 : 0xFFFF7777, true);
    }
}
