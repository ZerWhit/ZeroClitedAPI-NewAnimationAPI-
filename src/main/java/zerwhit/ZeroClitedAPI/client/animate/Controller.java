package zerwhit.ZeroClitedAPI.client.animate;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import zerwhit.ZeroClitedAPI.Animate;
import zerwhit.ZeroClitedAPI.IZeroLib;
import zerwhit.ZeroClitedAPI.client.animate.tween.Transform;
import zerwhit.ZeroClitedAPI.client.animate.tween.TweenUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {
    private IZeroLib entity;
    private ModelBase modelbase;
    public Map<ModelRenderer, Vec3> moveFramePrev = new HashMap<ModelRenderer, Vec3>();
    public Map<ModelRenderer, Vec3> rotateFramePrev = new HashMap<ModelRenderer, Vec3>();
    public Map<ModelRenderer, Vec3> moveFrameTemp = new HashMap<ModelRenderer, Vec3>();
    public Map<ModelRenderer, Vec3> rotateFrameTemp = new HashMap<ModelRenderer, Vec3>();
    public List<ModelRenderer> partList = new ArrayList<ModelRenderer>();
    private final Vec3 init = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
    public boolean setup;
    public Controller(ModelBase model){
        modelbase = model;
    }

    public void setup() {
        for (Object renderer : modelbase.boxList) {
            ModelRenderer part = (ModelRenderer) renderer;
            if (!moveFrameTemp.containsKey(part)){
                moveFrameTemp.put(part, Vec3.createVectorHelper(part.rotationPointX, part.rotationPointY, part.rotationPointZ));
                rotateFrameTemp.put(part, Vec3.createVectorHelper(part.rotateAngleX, part.rotateAngleY, part.rotateAngleZ));
            }
            else {
                if (partList.contains(part)) {
                    position(
                            part,
                            Vec3.createVectorHelper(0, 0, 0)
                    );
                }
            }
        }
    }

    public void animate(IZeroLib catcher, ModelRenderer modelPart, Keyframe... keyframes) {
        partList.add(modelPart);
        entity = catcher;
        for (int id = 0; id < keyframes.length; id++) {
            Keyframe keyframe = keyframes[id];
            keyframe.idKey = id;
            keyframe.moveFrameTemp.put(modelPart, keyframe.pos);
            keyframe.rotateFrameTemp.put(modelPart, keyframe.rotate);

            if (entity.catchPacketTick() >= keyframe.startTick && entity.catchPacketTick() <= keyframe.startTick + keyframe.length) {
                //Head Angle Fix
                if (entity.catchPacketTick() == keyframe.startTick && (modelPart.boxName != null && modelPart.boxName.equals("Head")))
                    rotateFrameTemp.put(modelPart, Vec3.createVectorHelper(modelPart.rotateAngleX, modelPart.rotateAngleY, modelPart.rotateAngleZ));
                keyframe.endPhase = false;
                moveFramePrev.remove(modelPart);
                rotateFramePrev.remove(modelPart);
                if (keyframe.idKey > 0) {
                    moveFramePrev.put(modelPart, keyframes[keyframe.idKey - 1].moveFrameTemp.get(modelPart));
                    rotateFramePrev.put(modelPart, keyframes[keyframe.idKey - 1].rotateFrameTemp.get(modelPart));
                }
                else {
                    moveFramePrev.put(modelPart, init);
                    rotateFramePrev.put(modelPart, init);
                }
                Vec3 movePrev = moveFramePrev.get(modelPart);
                Vec3 rotatePrev = rotateFramePrev.get(modelPart);
                Vec3 move = keyframe.moveFrameTemp.get(modelPart);
                Vec3 rotate = keyframe.rotateFrameTemp.get(modelPart);
                double tweenRatio = MathHelper.clamp_double((entity.catchPacketTick() + Animate.proxy.getPartialTick() - keyframe.startTick) / keyframe.length, 0.0D, 1.0D);
                double ratio = Transform.getTweenCoordHelper(keyframe.tweenType, tweenRatio);
                position(
                        modelPart,
                        Vec3.createVectorHelper(
                                TweenUtils.lerp(movePrev.xCoord, move.xCoord, ratio),
                                TweenUtils.lerp(movePrev.yCoord, move.yCoord, ratio),
                                TweenUtils.lerp(movePrev.zCoord, move.zCoord, ratio)
                        )
                );
                rotate(
                        modelPart,
                        Vec3.createVectorHelper(
                                TweenUtils.lerp(rotatePrev.xCoord, rotate.xCoord, ratio),
                                TweenUtils.lerp(rotatePrev.yCoord, rotate.yCoord, ratio),
                                TweenUtils.lerp(rotatePrev.zCoord, rotate.zCoord, ratio)
                        )
                );
            }
            if (!keyframe.endPhase && entity.catchPacketTick() > keyframe.startTick + keyframe.length) {
                keyframe.endPhase = true;
            }
        }
    }
    private void position(ModelRenderer part, Vec3 vec3){
        part.rotationPointX = (float) (vec3.xCoord + moveFrameTemp.get(part).xCoord);
        part.rotationPointY = (float) (vec3.yCoord + moveFrameTemp.get(part).yCoord);
        part.rotationPointZ = (float) (vec3.zCoord + moveFrameTemp.get(part).zCoord);
    }
    private void rotate(ModelRenderer part, Vec3 vec3){
        part.rotateAngleX = (float) (vec3.xCoord + rotateFrameTemp.get(part).xCoord);
        part.rotateAngleY = (float) (vec3.yCoord + rotateFrameTemp.get(part).yCoord);
        part.rotateAngleZ = (float) (vec3.zCoord + rotateFrameTemp.get(part).zCoord);
    }
}
