package zerwhit.ZeroClitedAPI.client.animate.tween;

public class TweenUtils {
    static double PI2 = Math.PI / 2;
    static double EL = 2 * Math.PI / .45;
    static double B1 = 1 / 2.75;
    static double B2 = 2 / 2.75;
    static double B3 = 1.5 / 2.75;
    static double B4 = 2.5 / 2.75;
    static double B5 = 2.25 / 2.75;
    static double B6 = 2.625 / 2.75;
    static double ELASTIC_AMPLITUDE = 1;
    static double ELASTIC_PERIOD = 0.4;
    public static double linear(double t)
    {
        return t;
    }

    public static double quadIn(double t)
    {
        return t * t;
    }

    public static double quadOut(double t)
    {
        return -t * (t - 2);
    }

    public static double quadInOut(double t)
    {
        return t <= .5 ? t * t * 2 : 1 - (--t) * t * 2;
    }

    public static double cubeIn(double t)
    {
        return t * t * t;
    }

    public static double cubeOut(double t)
    {
        return 1 + (--t) * t * t;
    }

    public static double cubeInOut(double t)
    {
        return t <= .5 ? t * t * t * 4 : 1 + (--t) * t * t * 4;
    }

    public static double quartIn(double t)
    {
        return t * t * t * t;
    }

    public static double quartOut(double t)
    {
        return 1 - (t -= 1) * t * t * t;
    }

    public static double quartInOut(double t)
    {
        return t <= .5 ? t * t * t * t * 8 : (1 - (t = t * 2 - 2) * t * t * t) / 2 + .5;
    }

    public static double quintIn(double t)
    {
        return t * t * t * t * t;
    }

    public static double quintOut(double t)
    {
        return (t = t - 1) * t * t * t * t + 1;
    }

    public static double quintInOut(double t)
    {
        return ((t *= 2) < 1) ? (t * t * t * t * t) / 2 : ((t -= 2) * t * t * t * t + 2) / 2;
    }


    public static double smoothStepIn(double t)
    {
        return 2 * smoothStepInOut(t / 2);
    }


    public static double smoothStepOut(double t)
    {
        return 2 * smoothStepInOut(t / 2 + 0.5) - 1;
    }


    public static double smoothStepInOut(double t)
    {
        return t * t * (t * -2 + 3);
    }


    public static double smootherStepIn(double t)
    {
        return 2 * smootherStepInOut(t / 2);
    }


    public static double smootherStepOut(double t)
    {
        return 2 * smootherStepInOut(t / 2 + 0.5) - 1;
    }


    public static double smootherStepInOut(double t)
    {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    public static double sineIn(double t)
    {
        return -Math.cos(PI2 * t) + 1;
    }

    public static double sineOut(double t)
    {
        return Math.sin(PI2 * t);
    }

    public static double sineInOut(double t)
    {
        return -Math.cos(Math.PI * t) / 2 + .5;
    }

    public static double bounceIn(double t)
    {
        t = 1 - t;
        if (t < B1)
            return 1 - 7.5625 * t * t;
        if (t < B2)
            return 1 - (7.5625 * (t - B3) * (t - B3) + .75);
        if (t < B4)
            return 1 - (7.5625 * (t - B5) * (t - B5) + .9375);
        return 1 - (7.5625 * (t - B6) * (t - B6) + .984375);
    }

    public static double bounceOut(double t)
    {
        if (t < B1)
            return 7.5625 * t * t;
        if (t < B2)
            return 7.5625 * (t - B3) * (t - B3) + .75;
        if (t < B4)
            return 7.5625 * (t - B5) * (t - B5) + .9375;
        return 7.5625 * (t - B6) * (t - B6) + .984375;
    }

    public static double bounceInOut(double t)
    {
        if (t < .5)
        {
            t = 1 - t * 2;
            if (t < B1)
                return (1 - 7.5625 * t * t) / 2;
            if (t < B2)
                return (1 - (7.5625 * (t - B3) * (t - B3) + .75)) / 2;
            if (t < B4)
                return (1 - (7.5625 * (t - B5) * (t - B5) + .9375)) / 2;
            return (1 - (7.5625 * (t - B6) * (t - B6) + .984375)) / 2;
        }
        t = t * 2 - 1;
        if (t < B1)
            return (7.5625 * t * t) / 2 + .5;
        if (t < B2)
            return (7.5625 * (t - B3) * (t - B3) + .75) / 2 + .5;
        if (t < B4)
            return (7.5625 * (t - B5) * (t - B5) + .9375) / 2 + .5;
        return (7.5625 * (t - B6) * (t - B6) + .984375) / 2 + .5;
    }

    public static double circIn(double t)
    {
        return -(Math.sqrt(1 - t * t) - 1);
    }

    public static double circOut(double t)
    {
        return Math.sqrt(1 - (t - 1) * (t - 1));
    }

    public static double circInOut(double t)
    {
        return t <= .5 ? (Math.sqrt(1 - t * t * 4) - 1) / -2 : (Math.sqrt(1 - (t * 2 - 2) * (t * 2 - 2)) + 1) / 2;
    }

    public static double expoIn(double t)
    {
        return Math.pow(2, 10 * (t - 1));
    }

    public static double expoOut(double t)
    {
        return -Math.pow(2, -10 * t) + 1;
    }

    public static double expoInOut(double t)
    {
        return t < .5 ? Math.pow(2, 10 * (t * 2 - 1)) / 2 : (-Math.pow(2, -10 * (t * 2 - 1)) + 2) / 2;
    }

    public static double backIn(double t)
    {
        return t * t * (2.70158 * t - 1.70158);
    }

    public static double backOut(double t)
    {
        return 1 - (--t) * (t) * (-2.70158 * t - 1.70158);
    }

    public static double backInOut(double t)
    {
        t *= 2;
        if (t < 1)
            return t * t * (2.70158 * t - 1.70158) / 2;
        t--;
        return (1 - (--t) * (t) * (-2.70158 * t - 1.70158)) / 2 + .5;
    }

    public static double elasticIn(double t)
    {
        return -(ELASTIC_AMPLITUDE * Math.pow(2,
                10 * (t -= 1)) * Math.sin((t - (ELASTIC_PERIOD / (2 * Math.PI) * Math.asin(1 / ELASTIC_AMPLITUDE))) * (2 * Math.PI) / ELASTIC_PERIOD));
    }

    public static double elasticOut(double t)
    {
        return (ELASTIC_AMPLITUDE * Math.pow(2,
                -10 * t) * Math.sin((t - (ELASTIC_PERIOD / (2 * Math.PI) * Math.asin(1 / ELASTIC_AMPLITUDE))) * (2 * Math.PI) / ELASTIC_PERIOD)
                + 1);
    }

    public static double elasticInOut(double t)
    {
        if (t < 0.5)
        {
            return -0.5 * (Math.pow(2, 10 * (t -= 0.5)) * Math.sin((t - (ELASTIC_PERIOD / 4)) * (2 * Math.PI) / ELASTIC_PERIOD));
        }
        return Math.pow(2, -10 * (t -= 0.5)) * Math.sin((t - (ELASTIC_PERIOD / 4)) * (2 * Math.PI) / ELASTIC_PERIOD) * 0.5 + 1;
    }

    public static double lerp(double a, double b, double ratio)
    {
        return a + ratio * (b - a);
    }
}
