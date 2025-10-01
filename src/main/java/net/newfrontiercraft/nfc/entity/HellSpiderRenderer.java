package net.newfrontiercraft.nfc.entity;

import net.minecraft.client.render.entity.SpiderEntityRenderer;
import org.lwjgl.opengl.GL11;

public class HellSpiderRenderer extends SpiderEntityRenderer {

    public HellSpiderRenderer()
    {
        super();
    }

    protected boolean bindTexture(HellSpiderEntity entityspider, int i, float f)
    {

        if(i != 0) {
            return false;
        } else {
            bindTexture("/assets/nfc/stationapi/textures/entity/brownspider_eyes.png");
            float f1 = (1.0F - entityspider.getBrightnessAtEyes(1.0F)) * 0.5F;
            GL11.glEnable(3042 /*GL_BLEND*/);
            GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
            GL11.glBlendFunc(770, 771);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);
            return true;
        }
    }

}
