package lab04.Chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import java.awt.*;


public class Chart extends JPanel {


    public XYSeries series;
    public Chart() {
        super(); //dziedziczenie
        series = new XYSeries("Velocity Chart "); //tworzenie serii
        XYSeriesCollection collection = new XYSeriesCollection(); //tworzenie kolekcji
        collection.addSeries(series); //dodawanie serii do kolekcji
        this.setSize(500,400); //rozmiar
        this.setLayout(null); //wyglad okienka
        this.setBackground(Color.WHITE);

        JFreeChart chart = ChartFactory.createXYLineChart("Charts", "Time", "Velocity", collection, PlotOrientation.VERTICAL, true, true, false); //tworzenie
        ChartPanel chartPanel = new ChartPanel(chart); //tworzenie panelu z wykresu
        this.add(chartPanel); //dodawanie panelu
        chartPanel.setSize(400,300); //ustalanie rozmiaru
    }
}
