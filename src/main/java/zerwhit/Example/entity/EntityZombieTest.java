package zerwhit.Example.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import zerwhit.Example.ZeroClitedExample;
import zerwhit.ZeroClitedAPI.Animate;
import zerwhit.ZeroClitedAPI.IZeroLib;

public class EntityZombieTest extends EntityZombie implements IZeroLib {
    private static final DataParameter<Integer> ANIMATION_ID = EntityDataManager.<Integer>createKey(EntityZombieTest.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> ANIMATION_TICK = EntityDataManager.<Integer>createKey(EntityZombieTest.class, DataSerializers.VARINT);
    public EntityZombieTest(World p_i1602_1_) {
        super(p_i1602_1_);
        this.isImmuneToFire = true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(ANIMATION_ID, Integer.valueOf(0));
        dataManager.register(ANIMATION_TICK, Integer.valueOf(0));
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if(catchPacketID() != 0) sendPacketTick(catchPacketTick() + 1);
        if (catchPacketID() == 1) {
            if (catchPacketTick() >= 30){
                sendPacketID(0);
                sendPacketTick(0);
            }
            if (catchPacketTick() == 10) {
                float f = (float) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
                Entity target = getAttackTarget();
                if (target != null) {
                    target.attackEntityFrom(DamageSource.causeMobDamage(this), f);
                }
            }
        }
        ZeroClitedExample.logger.info("The animate entity info, id:" + catchPacketID() + ", tick: " + catchPacketTick());
    }

    @Override
    public boolean attackEntityAsMob(Entity p_70652_1_) {
        if (catchPacketID() == 0) {
            Animate.sendPacket(this, 1);
            sendPacketTick(0);
        }

        return false;
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
        dataManager.set(ANIMATION_ID, id);
    }

    @Override
    public int catchPacketID() {
        return dataManager.get(ANIMATION_ID);
    }

    @Override
    public void sendPacketTick(int tick) {
        dataManager.set(ANIMATION_TICK, tick);
    }

    @Override
    public int catchPacketTick() {
        return dataManager.get(ANIMATION_TICK);
    }

    @Override
    public void knockBack(Entity p_70653_1_, float p_70653_2_, double p_70653_3_, double p_70653_5_) {
    }
}
