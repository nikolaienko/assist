package logic;

/**
 * Created by vlad on 12/22/14.
 */
public final class DistanceCalculatorFactory {

    private DistanceCalculatorFactory() {
    }

    public static final DistanceCalculatorFactory getInstance(){
        return InstanceHolder.INSTANCE;
    }

    public final DistanceCalculator getDistanceCalculator(String impl){
        switch (impl){
            case "Google": return new GoogleDistanceCalculator();

            case "Native": return new NativeDistanceCalculator();

            default: return new NativeDistanceCalculator();

        }
    }

    private static class InstanceHolder{
        private static final DistanceCalculatorFactory INSTANCE = new DistanceCalculatorFactory();
    }
}
