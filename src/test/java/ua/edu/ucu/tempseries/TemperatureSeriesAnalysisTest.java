package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {
    private double[] arr1;
    private double[] arr2;
    private  double[] arr3;

    @Before
    public void setUp() {
        arr1 = new double[] {34.4, 322.0, 12, -400};
        arr2 = new double[] {5, 10, 15, 5, 20};
        arr3 = new double[] {3.0, -5.0, 1.0, 5.0};
    }

    @Test
    public void testTemperatureSeriesAnalysis() {
        TemperatureSeriesAnalysis temp2 = new TemperatureSeriesAnalysis();
    }

    @Test
    public void testTemperatureSeriesAnalysisWithGivenArray() {
        TemperatureSeriesAnalysis temp2 = new TemperatureSeriesAnalysis(arr2);
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;
        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);
        TemperatureSeriesAnalysis temp2 = new TemperatureSeriesAnalysis(arr2);
        assertEquals(temp2.average(), 11.0, 0.0001);
    }

    @Test
    public void testDeviation() {
        TemperatureSeriesAnalysis temp2 = new TemperatureSeriesAnalysis(arr2);
        assertEquals(temp2.deviation(), 5.83095, 0.0001);
        TemperatureSeriesAnalysis temp3 = new TemperatureSeriesAnalysis(arr3);
        assertEquals(temp3.deviation(), 3.74165, 0.0001);
    }

    @Test
    public void testMax() {
        TemperatureSeriesAnalysis temp2 = new TemperatureSeriesAnalysis(arr2);
        assertEquals(temp2.max(), 20.0,0.00001);
        TemperatureSeriesAnalysis temp3 = new TemperatureSeriesAnalysis(arr3);
        assertEquals(temp3.max(), 5.0, 0.0001);
    }

    @Test
    public void testMin() {
        TemperatureSeriesAnalysis temp2 = new TemperatureSeriesAnalysis(arr2);
        assertEquals(temp2.min(), 5.0,0.00001);
        TemperatureSeriesAnalysis temp3 = new TemperatureSeriesAnalysis(arr3);
        assertEquals(temp3.min(), -5.0, 0.0001);
    }

    @Test
    public void testFindTempClosestToZero() {
        TemperatureSeriesAnalysis temp2 = new TemperatureSeriesAnalysis(arr2);
        assertEquals(temp2.findTempClosestToZero(), 5.0,0.00001);
        TemperatureSeriesAnalysis temp3 = new TemperatureSeriesAnalysis(arr3);
        assertEquals(temp3.findTempClosestToZero(), 1.0, 0.0001);
    }

    @Test
    public void testFindTempClosestToValue() {
        TemperatureSeriesAnalysis temp2 = new TemperatureSeriesAnalysis(arr2);
        assertEquals(temp2.findTempClosestToValue(100), 20.0,0.00001);
        TemperatureSeriesAnalysis temp3 = new TemperatureSeriesAnalysis(arr3);
        assertEquals(temp3.findTempClosestToValue(1), 1.0, 0.0001);
    }

    @Test
    public void testFindTempsLessThen() {
        TemperatureSeriesAnalysis temp2 = new TemperatureSeriesAnalysis(arr2);
        double[] newArr = {5.0, 10.0, 5};
        assertArrayEquals(temp2.findTempsLessThen(11), newArr,0.00001);

        TemperatureSeriesAnalysis temp3 = new TemperatureSeriesAnalysis(arr3);
        double[] newArr2 = {-5.0};
        assertArrayEquals(temp3.findTempsLessThen(1), newArr2,0.00001);
    }

    @Test
    public void testFindTempsGreaterThen() {
        TemperatureSeriesAnalysis temp2 = new TemperatureSeriesAnalysis(arr2);
        double[] newArr = {15.0, 20.0};
        assertArrayEquals(temp2.findTempsGreaterThen(11), newArr,0.00001);

        TemperatureSeriesAnalysis temp3 = new TemperatureSeriesAnalysis(arr3);
        double[] newArr2 = {3.0, 5.0};
        assertArrayEquals(temp3.findTempsGreaterThen(1), newArr2,0.00001);
    }

    @Test
    public  void testSummaryStatistics() {
        TemperatureSeriesAnalysis temp2 = new TemperatureSeriesAnalysis(arr2);
        TempSummaryStatistics newStat = new TempSummaryStatistics(11.0, 5.830951894845301,20.0,5.0);
        assertEquals(temp2.summaryStatistics().getAvgTemp(), newStat.getAvgTemp(),0.00001);
        assertEquals(temp2.summaryStatistics().getDevTemp(), newStat.getDevTemp(),0.00001);
        assertEquals(temp2.summaryStatistics().getMaxTemp(), newStat.getMaxTemp(),0.00001);
        assertEquals(temp2.summaryStatistics().getMinTemp(), newStat.getMinTemp(),0.00001);
    }

    @Test
    public void testAddTemps() {
        TemperatureSeriesAnalysis temp2 = new TemperatureSeriesAnalysis(arr2);
        double[] newArr = {5.0, 10.0, 15.0, 5.0, 20.0, 12.0, 12.0, 5.0, 0.0, 0.0};
        double newSum = 0;
        for (double el: newArr) {
            newSum = newSum + el;
        }
        assertEquals(temp2.addTemps(12.0, 12.0, 5.0), (int) newSum);
    }

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        seriesAnalysis.average();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviationWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        seriesAnalysis.deviation();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        seriesAnalysis.max();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        seriesAnalysis.min();
    }

    @Test(expected = InputMismatchException.class)
    public void testTemperatureSeriesAnalysisWithException() {
        TemperatureSeriesAnalysis temp1 = new TemperatureSeriesAnalysis(arr1);
    }
}
