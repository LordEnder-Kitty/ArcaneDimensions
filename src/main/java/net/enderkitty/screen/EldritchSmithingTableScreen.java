package net.enderkitty.screen;

import net.enderkitty.ArcaneDimensions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class EldritchSmithingTableScreen extends HandledScreen<EldritchSmithingTableScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(ArcaneDimensions.MOD_ID, "textures/gui/container/eldritch_smithing_table.png");
    private static final Identifier FUELED_TEXTURE = new Identifier(ArcaneDimensions.MOD_ID, "container/eldritch_smithing_table/fueled_progress");
    private static final Identifier CRAFTING_TEXTURE = new Identifier(ArcaneDimensions.MOD_ID, "container/eldritch_smithing_table/crafting_progress");
    
    public EldritchSmithingTableScreen(EldritchSmithingTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }
    
    @Override
    protected void init() {
        super.init();
        titleY = 3;
    }
    
    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
        
        if (handler.isBurning()) {
            int l = MathHelper.ceil(handler.getFuelProgress() * 17.0f) + 1;
            context.drawGuiTexture(FUELED_TEXTURE, 14, 18, 0, 18 - l, x + 144, y + 34 + 18 - l, 14, l);
        }
        int l = MathHelper.ceil(handler.getCraftProgress() * 20.0f);
        context.drawGuiTexture(CRAFTING_TEXTURE, 20, 14, 0, 0, x + 111, y + 36, l, 14);
    }
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
