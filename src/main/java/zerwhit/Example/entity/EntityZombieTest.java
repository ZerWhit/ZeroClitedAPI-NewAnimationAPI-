package zerwhit.Example.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import zerwhit.Example.ZeroClitedExample;
import zerwhit.ZeroClitedAPI.Animate;
import zerwhit.ZeroClitedAPI.IZeroLib;

public class EntityZombieTest extends EntityZombie implements IZeroLib {
    public EntityZombieTest(World p_i1602_1_) {
        super(p_i1602_1_);
        this.func_110163_bv();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200.0D);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataWatcher.addObject(18, 0);
        dataWatcher.addObject(19, 0);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if(catchPacketID() != 0) sendPacketTick(catchPacketTick() + 1);
        if (catchPacketID() == 1 && catchPacketTick() >= 30) {
            sendPacketID(0);
            sendPacketTick(0);
        }
        //ZeroClitedExample.logger.info("The animate entity info, id:" + catchPacketID() + ", tick: " + catchPacketTick());
    }

    @Override
    public boolean attackEntityAsMob(Entity p_70652_1_) {
        if (catchPacketID() == 0) {
            Animate.sendPacket(this, 1);
            sendPacketTick(0);
        }
        if (catchPacketID() == 1 && catchPacketTick() == 10)
            return super.attackEntityAsMob(p_70652_1_);
        return true;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound p_70014_1_) {
        super.writeEntityToNBT(p_70014_1_);
        p_70014_1_.setInteger("AnimationID", catchPacketID());
        p_70014_1_.setInteger("AnimationTick", catchPacketTick());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound p_70037_1_) {
        super.readEntityFromNBT(p_70037_1_);
        sendPacketID(p_70037_1_.getInteger("AnimationID"));
        sendPacketTick(p_70037_1_.getInteger("AnimationTick"));
    }

    @Override
    public void sendPacketID(int id) {
        dataWatcher.updateObject(18, id);
    }

    @Override
    public int catchPacketID() {
        return dataWatcher.getWatchableObjectInt(18);
    }

    @Override
    public void sendPacketTick(int tick) {
        dataWatcher.updateObject(19, tick);
    }

    @Override
    public int catchPacketTick() {
        return dataWatcher.getWatchableObjectInt(19);
    }

    @Override
    public void knockBack(Entity p_70653_1_, float p_70653_2_, double p_70653_3_, double p_70653_5_) {
    }
}
