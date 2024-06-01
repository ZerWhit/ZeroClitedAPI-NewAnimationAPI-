package zerwhit.Example.client;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zerwhit.Example.CommonProxy;
import zerwhit.Example.client.model.render.RenderZombieTest;
import zerwhit.Example.entity.EntityZombieTest;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	
	public void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityZombieTest.class, new RenderZombieTest());
	}
}
