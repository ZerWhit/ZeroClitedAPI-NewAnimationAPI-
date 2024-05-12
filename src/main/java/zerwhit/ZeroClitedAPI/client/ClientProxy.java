package zerwhit.ZeroClitedAPI.client;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.world.World;
import zerwhit.ZeroClitedAPI.CommonProxy;

public class ClientProxy extends CommonProxy {
    @Override
    public World getWorld() {
        return FMLClientHandler.instance().getWorldClient();
    }
}
