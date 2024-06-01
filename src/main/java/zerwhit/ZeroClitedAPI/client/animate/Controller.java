package zerwhit.ZeroClitedAPI.client.animate;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
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
    public Map<ModelRenderer, Vec3d> moveFramePrev = new HashMap<ModelRenderer, Vec3d>();
    public Map<ModelRenderer, Vec3d> rotateFramePrev = new HashMap<ModelRenderer, Vec3d>();
    public Map<ModelRenderer, Vec3d> moveFrameTemp = new HashMap<ModelRenderer, Vec3d>();
    public Map<ModelRenderer, Vec3d> rotateFrameTemp = new HashMap<ModelRenderer, Vec3d>();
    public List<ModelRenderer> partList = new ArrayList<ModelRenderer>();
    private final Vec3d init = new Vec3d(0.0D, 0.0D, 0.0D);
    public boolean setup;
    public Controller(ModelBase model){
        modelbase = model;
    }

    public void setup() {
        for (Object renderer : modelbase.boxList) {
            ModelRenderer part = (ModelRenderer) renderer;
            if (!moveFrameTemp.containsKey(part)){
                moveFrameTemp.put(part, new Vec3d(part.rotationPointX, part.rotationPointY, part.rotationPointZ));
                rotateFrameTemp.put(part, new Vec3d(part.rotateAngleX, part.rotateAngleY, part.rotateAngleZ));
            }
            else {
                if (partList.contains(part)) {
                    position(
                            part,
                            new Vec3d(0, 0, 0)
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
                    rotateFrameTemp.put(modelPart, new Vec3d(modelPart.rotateAngleX, modelPart.rotateAngleY, modelPart.rotateAngleZ));
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
                Vec3d movePrev = moveFramePrev.get(modelPart);
                Vec3d rotatePrev = rotateFramePrev.get(modelPart);
                Vec3d move = keyframe.moveFrameTemp.get(modelPart);
                Vec3d rotate = keyframe.rotateFrameTemp.get(modelPart);
                double tweenRatio = MathHelper.clamp((entity.catchPacketTick() + Animate.proxy.getPartialTick() - keyframe.startTick) / keyframe.length, 0.0D, 1.0D);
                double ratio = Transform.getTweenCoordHelper(keyframe.tweenType, tweenRatio);
                position(
                        modelPart,
                        new Vec3d(
                                TweenUtils.lerp(movePrev.x, move.x, ratio),
                                TweenUtils.lerp(movePrev.y, move.y, ratio),
                                TweenUtils.lerp(movePrev.z, move.z, ratio)
                        )
                );
                rotate(
                        modelPart,
                        new Vec3d(
                                TweenUtils.lerp(rotatePrev.x, rotate.x, ratio),
                                TweenUtils.lerp(rotatePrev.y, rotate.y, ratio),
                                TweenUtils.lerp(rotatePrev.z, rotate.z, ratio)
                        )
                );
            }
            if (!keyframe.endPhase && entity.catchPacketTick() > keyframe.startTick + keyframe.length) {
                keyframe.endPhase = true;
            }
        }
    }
    private void position(ModelRenderer part, Vec3d vec3){
        part.rotationPointX = (float) (vec3.x + moveFrameTemp.get(part).x);
        part.rotationPointY = (float) (vec3.y + moveFrameTemp.get(part).y);
        part.rotationPointZ = (float) (vec3.z + moveFrameTemp.get(part).z);
    }
    private void rotate(ModelRenderer part, Vec3d vec3){
        part.rotateAngleX = (float) (vec3.x + rotateFrameTemp.get(part).x);
        part.rotateAngleY = (float) (vec3.y + rotateFrameTemp.get(part).y);
        part.rotateAngleZ = (float) (vec3.z + rotateFrameTemp.get(part).z);
    }
}
