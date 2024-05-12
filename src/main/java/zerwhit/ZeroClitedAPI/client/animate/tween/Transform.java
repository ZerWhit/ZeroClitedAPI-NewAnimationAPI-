package zerwhit.ZeroClitedAPI.client.animate.tween;

public class Transform {
    public static double getTweenCoordHelper(EnumTweenType type, double t) {
        switch (type) {
            case quadIn:
                return TweenUtils.quadIn(t);
            case quadOut:
                return TweenUtils.quadOut(t);
            case quadInOut:
                return TweenUtils.quadInOut(t);
            case cubeIn:
                return TweenUtils.cubeIn(t);
            case cubeOut:
                return TweenUtils.cubeOut(t);
            case cubeInOut:
                return TweenUtils.cubeInOut(t);
            case quartIn:
                return TweenUtils.quartIn(t);
            case quartOut:
                return TweenUtils.quartOut(t);
            case quartInOut:
                return TweenUtils.quartInOut(t);
            case quintIn:
                return TweenUtils.quintIn(t);
            case quintOut:
                return TweenUtils.quintOut(t);
            case quintInOut:
                return TweenUtils.quintInOut(t);
            case smoothStepIn:
                return TweenUtils.smoothStepIn(t);
            case smoothStepOut:
                return TweenUtils.smoothStepOut(t);
            case smoothStepInOut:
                return TweenUtils.smoothStepInOut(t);
            case smootherStepIn:
                return TweenUtils.smootherStepIn(t);
            case smootherStepOut:
                return TweenUtils.smootherStepOut(t);
            case smootherStepInOut:
                return TweenUtils.smootherStepInOut(t);
            case sineIn:
                return TweenUtils.sineIn(t);
            case sineOut:
                return TweenUtils.sineOut(t);
            case sineInOut:
                return TweenUtils.sineInOut(t);
            case bounceIn:
                return TweenUtils.bounceIn(t);
            case bounceOut:
                return TweenUtils.bounceOut(t);
            case bounceInOut:
                return TweenUtils.bounceInOut(t);
            case circIn:
                return TweenUtils.circIn(t);
            case circOut:
                return TweenUtils.circOut(t);
            case circInOut:
                return TweenUtils.circInOut(t);
            case expoIn:
                return TweenUtils.expoIn(t);
            case expoOut:
                return TweenUtils.expoOut(t);
            case expoInOut:
                return TweenUtils.expoInOut(t);
            case backIn:
                return TweenUtils.backIn(t);
            case backOut:
                return TweenUtils.backOut(t);
            case backInOut:
                return TweenUtils.backInOut(t);
            case elasticIn:
                return TweenUtils.elasticIn(t);
            case elasticOut:
                return TweenUtils.elasticOut(t);
            case elasticInOut:
                return TweenUtils.elasticInOut(t);
            case linear:
            default:
                return TweenUtils.linear(t);
        }
    }
}
