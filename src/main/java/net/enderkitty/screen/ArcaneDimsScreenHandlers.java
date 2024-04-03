package net.enderkitty.screen;

import net.enderkitty.ArcaneDimensions;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ArcaneDimsScreenHandlers {
    public static final ScreenHandlerType<EldritchSmithingTableScreenHandler> ELDRITCH_SMITHING_TABLE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ArcaneDimensions.MOD_ID, "eldritch_smithing"),
                    new ExtendedScreenHandlerType<>(EldritchSmithingTableScreenHandler::new));
    
    public static void register() {}
}
