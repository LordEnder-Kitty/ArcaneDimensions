package net.enderkitty.world.dimension;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.util.math.Vec3d;

public class ElderlandsDimensionEffects {

    public static Elderlands elderlands = new Elderlands();


    @Environment(value= EnvType.CLIENT)
    public static class Elderlands extends DimensionEffects {
        public Elderlands() {
            super(100.0f, false, SkyType.END, true, false);
        }

        @Override
        public Vec3d adjustFogColor(Vec3d color, float sunHeight) {
            return color.multiply(sunHeight * 0.94f + 0.06f, sunHeight * 0.94f + 0.06f, sunHeight * 0.91f + 0.09f);
        }

        @Override
        public boolean useThickFog(int camX, int camY) {
            return true;
        }
    }

}
