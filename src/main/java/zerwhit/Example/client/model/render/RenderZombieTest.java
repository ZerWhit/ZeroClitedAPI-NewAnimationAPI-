package zerwhit.Example.client.model.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.util.ResourceLocation;
import zerwhit.Example.client.model.ModelZombieTest;
import zerwhit.Example.entity.EntityZombieTest;

public class RenderZombieTest extends RenderBiped<EntityZombieTest> {

    private static final ResourceLocation zombieTextures = new ResourceLocation("textures/entity/zombie/zombie.png");
    public RenderZombieTest()
    {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelZombieTest(), 0.5F);
    }
    protected ResourceLocation getEntityTexture(EntityZombieTest p_110775_1_)
    {
        return zombieTextures;
    }

    @Override
    public boolean shouldRender(EntityZombieTest livingEntity, ICamera camera, double camX, double camY, double camZ) {
        return true;
    }
}
