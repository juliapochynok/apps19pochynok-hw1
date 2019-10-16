package ua.edu.ucu.tempseries;

public class TempSummaryStatistics {
    private final double avgTemp;
    private final double devTemp;
    private final double minTemp;
    private final double maxTemp;


    public TempSummaryStatistics(double avg, double dev,
                                 double maxim, double minim) {
        this.avgTemp = avg;
        this.devTemp = dev;
        this.maxTemp = maxim;
        this.minTemp = minim;
    }

    public double getAvgTemp() {
        return avgTemp;
    }

    public double getDevTemp() {
        return devTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

}
