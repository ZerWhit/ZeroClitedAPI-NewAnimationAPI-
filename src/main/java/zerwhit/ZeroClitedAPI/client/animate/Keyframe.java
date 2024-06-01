package zerwhit.ZeroClitedAPI.client.animate;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.Vec3;
import zerwhit.ZeroClitedAPI.client.animate.tween.EnumTweenType;

import java.util.HashMap;
import java.util.Map;

public class Keyframe {
    public EnumTweenType tweenType;
    public ModelRenderer modelPart;
    public Map<ModelRenderer, Vec3> moveFrameTemp;
    public Map<ModelRenderer, Vec3> rotateFrameTemp;
    public Vec3 pos;
    public Vec3 rotate;
    public double length;
    public double startTick;
    public boolean endPhase;
    public int idKey;
    public Keyframe(Vec3 pos, Vec3 rotate, double p, double l, EnumTweenType type){
        startTick = p;
        length = l;
        moveFrameTemp = new HashMap<ModelRenderer, Vec3>();
        rotateFrameTemp = new HashMap<ModelRenderer, Vec3>();
        this.pos = pos;
        this.rotate = rotate;
        tweenType = type;
    }
}
