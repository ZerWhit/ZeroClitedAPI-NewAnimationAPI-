package zerwhit.Example.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import zerwhit.Example.CommonProxy;
import zerwhit.Example.client.model.render.RenderZombieTest;
import zerwhit.Example.entity.EntityZombieTest;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	
	public void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityZombieTest.class, new RenderZombieTest());
	}
}
