package zerwhit.ZeroClitedAPI.client.animate;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.math.Vec3d;
import zerwhit.ZeroClitedAPI.client.animate.tween.EnumTweenType;

import java.util.HashMap;
import java.util.Map;

public class Keyframe {
    public EnumTweenType tweenType;
    public ModelRenderer modelPart;
    public Map<ModelRenderer, Vec3d> moveFrameTemp;
    public Map<ModelRenderer, Vec3d> rotateFrameTemp;
    public Vec3d pos;
    public Vec3d rotate;
    public double length;
    public double startTick;
    public boolean endPhase;
    public int idKey;
    public Keyframe(Vec3d pos, Vec3d rotate, double p, double l, EnumTweenType type){
        startTick = p;
        length = l;
        moveFrameTemp = new HashMap<ModelRenderer, Vec3d>();
        rotateFrameTemp = new HashMap<ModelRenderer, Vec3d>();
        this.pos = pos;
        this.rotate = rotate;
        tweenType = type;
    }
}
