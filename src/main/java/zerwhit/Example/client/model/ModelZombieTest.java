package zerwhit.Example.client.model;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;
import zerwhit.ZeroClitedAPI.IZeroLib;
import zerwhit.ZeroClitedAPI.client.animate.Controller;
import zerwhit.ZeroClitedAPI.client.animate.Keyframe;
import zerwhit.ZeroClitedAPI.client.animate.tween.EnumTweenType;

public class ModelZombieTest extends ModelZombie {
    private Controller controller;

    public ModelZombieTest()
    {
        this(0.0F, false);
    }

    public ModelZombieTest(float p_i1168_1_, boolean p_i1168_2_)
    {
        super();
        controller = new Controller(this);
    }

    @Override
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {

        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);

        this.bipedBody.addChild(this.bipedHead);
        this.bipedHead.rotationPointX = 0.0F;
        this.bipedHead.rotationPointY = 0.0F;
        this.bipedHead.rotationPointZ = 0.0F;
        this.bipedBody.addChild(this.bipedLeftArm);
        this.bipedLeftArm.rotationPointX = 5.0F;
        this.bipedLeftArm.rotationPointY = 2.0F;
        this.bipedLeftArm.rotationPointZ = 0.0F;
        this.bipedBody.addChild(this.bipedRightArm);
        this.bipedRightArm.rotationPointX = -5.0F;
        this.bipedRightArm.rotationPointY = 2.0F;
        this.bipedRightArm.rotationPointZ = 0.0F;

        this.animate((IZeroLib) p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);

        if (this.isChild)
        {
            float f6 = 2.0F;
            GL11.glPushMatrix();
            GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
            GL11.glTranslatef(0.0F, 16.0F * p_78088_7_, 0.0F);
            this.bipedHead.render(p_78088_7_);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
            GL11.glTranslatef(0.0F, 24.0F * p_78088_7_, 0.0F);
            this.bipedBody.render(p_78088_7_);
            this.bipedRightLeg.render(p_78088_7_);
            this.bipedLeftLeg.render(p_78088_7_);
            this.bipedHeadwear.render(p_78088_7_);
            GL11.glPopMatrix();
        }
        else
        {
            this.bipedBody.render(p_78088_7_);
            this.bipedRightLeg.render(p_78088_7_);
            this.bipedLeftLeg.render(p_78088_7_);
            this.bipedHeadwear.render(p_78088_7_);
        }
    }

    private void animate(IZeroLib entity, float f0, float f1, float f2, float f3, float f4, float f5) {
        this.controller.setup();
        if (entity.catchPacketID() == 1) {
            this.controller.animate(
                    entity,
                    this.bipedBody,
                    new Keyframe(
                            new Vec3d(0, 4.0, 8.0),
                            new Vec3d(Math.PI / -4.0D, 0, 0),
                            0,
                            8,
                            EnumTweenType.quadInOut
                    ),
                    new Keyframe(
                            new Vec3d(0, 8.0, -11.0),
                            new Vec3d(Math.PI / 2.5D, 0, 0),
                            8,
                            8,
                            EnumTweenType.quadOut
                    ),
                    new Keyframe(
                            new Vec3d(0, 0, 0),
                            new Vec3d(0, 0, 0),
                            16,
                            8,
                            EnumTweenType.quadIn
                    )
            );
            this.controller.animate(
                    entity,
                    this.bipedLeftArm,
                    new Keyframe(
                            new Vec3d(0, 0, 0),
                            new Vec3d(-Math.PI, 0, 0),
                            1,
                            8,
                            EnumTweenType.quadInOut
                    ),
                    new Keyframe(
                            new Vec3d(0, 0, 0),
                            new Vec3d(Math.PI / 3.0D, 0, 0),
                            9,
                            8,
                            EnumTweenType.quadOut
                    ),
                    new Keyframe(
                            new Vec3d(0, 0, 0),
                            new Vec3d(0, 0, 0),
                            17,
                            8,
                            EnumTweenType.quadIn
                    )
            );
            this.controller.animate(
                    entity,
                    this.bipedRightArm,
                    new Keyframe(
                            new Vec3d(0, 0, 0),
                            new Vec3d(-Math.PI, 0, 0),
                            1,
                            8,
                            EnumTweenType.quadInOut
                    ),
                    new Keyframe(
                            new Vec3d(0, 0, 0),
                            new Vec3d(Math.PI / 3.0D, 0, 0),
                            9,
                            8,
                            EnumTweenType.quadOut
                    ),
                    new Keyframe(
                            new Vec3d(0, 0, 0),
                            new Vec3d(0, 0, 0),
                            17,
                            8,
                            EnumTweenType.quadIn
                    )
            );
            this.controller.animate(
                    entity,
                    this.bipedHead,
                    new Keyframe(
                            new Vec3d(0, 0, 0),
                            new Vec3d(-Math.PI / 4.0D, 0, 0),
                            1,
                            8,
                            EnumTweenType.quadInOut
                    ),
                    new Keyframe(
                            new Vec3d(0, 0, 0),
                            new Vec3d(Math.PI / 4.0D, 0, 0),
                            9,
                            8,
                            EnumTweenType.quadOut
                    ),
                    new Keyframe(
                            new Vec3d(0, 0, 0),
                            new Vec3d(0, 0, 0),
                            17,
                            8,
                            EnumTweenType.quadIn
                    )
            );
        }
    }
}
