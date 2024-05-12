package zerwhit.ZeroClitedAPI;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.world.World;

/**
 * Credit thehippomaster's Animation API
 */
public class MessageHandler implements IMessage {

    private byte animID;
    private int entityID;

    public MessageHandler() {
    }

    public MessageHandler(byte anim, int entity) {
        animID = anim;
        entityID = entity;
    }

    @Override
    public void toBytes(ByteBuf buffer) {
        buffer.writeByte(animID);
        buffer.writeInt(entityID);
    }

    @Override
    public void fromBytes(ByteBuf buffer) {
        animID = buffer.readByte();
        entityID = buffer.readInt();
    }

    public static class Handler implements IMessageHandler<MessageHandler, IMessage> {
        @Override
        public IMessage onMessage(MessageHandler packet, MessageContext ctx) {
            World world = Animate.proxy.getWorld();
            IZeroLib entity = (IZeroLib)world.getEntityByID(packet.entityID);
            if(entity != null && packet.animID != -1) {
                entity.sendPacketID(packet.animID);
                if(packet.animID == 0) entity.sendPacketTick(0);
            }
            return null;
        }
    }
}

