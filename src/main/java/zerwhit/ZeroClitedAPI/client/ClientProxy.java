package zerwhit.ZeroClitedAPI.client;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Timer;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import zerwhit.ZeroClitedAPI.CommonProxy;

public class ClientProxy extends CommonProxy {
    @Override
    public float getPartialTick() {
        return ((Timer)ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), "field_71428_T", "S", "timer")).renderPartialTicks;
    }
    @Override
    public World getWorld() {
        return FMLClientHandler.instance().getWorldClient();
    }
}
