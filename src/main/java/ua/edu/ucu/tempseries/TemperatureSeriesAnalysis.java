package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;



public class TemperatureSeriesAnalysis {
    private double[] temperatures;
    private final double LOWEST_TEMP= -273.0;

    public TemperatureSeriesAnalysis() {
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        temperatures = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
        for (double el : temperatures) {
            if (el < LOWEST_TEMP) {
                throw new InputMismatchException();
            }
        }
    }

    public double average() {
        double aver = 0.0;
        if (temperatures.length == 0) {
            throw new IllegalArgumentException();
        }
        for (double el : temperatures) {
            aver = aver + el;
        }
        aver = aver / temperatures.length;
        return aver;
    }

    public double deviation() {
        if (temperatures.length == 0) {
            throw new IllegalArgumentException();
        }
        else {
            double sumSquare = 0.0;
            for (double el: temperatures) {
                double localSquare = (el - this.average())*(el - this.average());
                sumSquare = sumSquare + localSquare;
            }
            double finalDeviation = Math.sqrt(sumSquare/temperatures.length);
            return finalDeviation;
        }
    }

    public double min() {
        if (temperatures.length == 0) {
            throw new IllegalArgumentException();
        }
        double minim = Integer.MAX_VALUE;
        for (double el : temperatures) {
            if (el < minim) {
                minim = el;
            }
        }
        return minim;
    }

    public double max() {
        if (temperatures.length == 0) {
            throw new IllegalArgumentException();
        }
        double maxim = 0.0;
        for (double el : temperatures) {
            if (el > maxim) {
                maxim = el;
            }
        }
        return maxim;
    }

    public double findTempClosestToZero() {
        double small = 0.0000001;
        if (temperatures.length == 0) {
            throw new IllegalArgumentException();
        }
        double closest = Integer.MAX_VALUE;
        for (double el : temperatures) {
            if (Math.abs(el) < Math.abs(closest)) {
                closest = el;
            } else if (Math.abs(el - closest) < small && el > closest) {
                    closest = el;
            }
        }
        return closest;
    }

    public double findTempClosestToValue(double tempValue) {
        double small = 0.0000001;
        if (temperatures.length == 0) {
            throw new IllegalArgumentException();
        }
        double closestDist = Integer.MAX_VALUE;
        double closestNum = Integer.MAX_VALUE;
        for (double el : temperatures) {
            double difference = Math.abs(el - tempValue);
            if (difference < closestDist) {
                closestNum = el;
                closestDist = difference;
            } else if (Math.abs(difference - closestDist) < small
                    && el > closestNum) {
                    closestNum = el;
            }
        }
        return closestNum;
    }

    public double[] findTempsLessThen(double tempValue) {
        double[] newTemp = new double[0];
        int i = 0;
        for (double el : temperatures) {
            if (el < tempValue) {
                newTemp = Arrays.copyOf(newTemp, newTemp.length + 1);
                newTemp[i] = el;
                i++;
            }
        }
        return newTemp;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        double[] newTemp = new double[0];
        int i = 0;
        for (double el : temperatures) {
            if (el > tempValue) {
                newTemp = Arrays.copyOf(newTemp, newTemp.length + 1);
                newTemp[i] = el;
                i++;
            }
        }
        return newTemp;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (temperatures.length == 0) {
            throw new IllegalArgumentException();
        }
        else {
            TempSummaryStatistics newStat = new TempSummaryStatistics(this.average(),
                    this.deviation(), this.max(), this.min());
            return newStat;
        }
    }

    public double sum() {
        double sum = 0.0;
        for (double el : temperatures) {
            sum = sum + el;
        }
        return sum;
    }

    public int addTemps(double... temps) {
        int lengthTemp = temps.length;
        int i = 0;
        while (i != temps.length) {
            if (temperatures[temperatures.length - lengthTemp] == 0.0) {
                int it = 0;
                for (double el: temperatures) {
                    if (el == 0.0) {
                        break;
                    }
                    it++;
                }
                temperatures[it] = temps[i];
                i++;
                lengthTemp--;
            }
            else {
                temperatures = Arrays.copyOf(temperatures, temperatures.length*2);
            }
        }
        return (int) this.sum();
      }
    }
