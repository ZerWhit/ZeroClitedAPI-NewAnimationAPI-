package zerwhit.Example.client.model.render;

import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import zerwhit.Example.client.model.ModelZombieTest;

public class RenderZombieTest extends RenderBiped {

    private static final ResourceLocation zombieTextures = new ResourceLocation("textures/entity/zombie/zombie.png");
    public RenderZombieTest()
    {
        super(new ModelZombieTest(), 0.5F, 1.0F);
    }
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return zombieTextures;
    }

    @Override
    protected int shouldRenderPass(EntityLiving p_77032_1_, int p_77032_2_, float p_77032_3_) {
        return -1;
    }
}
