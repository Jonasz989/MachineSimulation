package lab04.Frames;

import lab04.Chart.Chart;
import lab04.Input.Input;

import javax.swing.*;
import java.io.Serial;

public class MainFrame extends JFrame {

    private final MyPanel mp;
    static int time = 0;
    private final Input input;
    private final Chart chartX;
    private final Chart chartY;

    @Serial
    private static final long serialVersionUID = 1L;
    static Boolean isStart = false;


//main do testowania
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrameClass = new MainFrame(); //tworzenie glownej ramki
            mainFrameClass.setTitle("Lab04_pop"); //tytul
            mainFrameClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //zamknij program przy wyjsciu
            mainFrameClass.setVisible(true); //ustaw widocznosc
            mainFrameClass.animate(); //rozpocznij animacje
        });
    }

    public void animate() {
        Thread t = new Thread(() -> {

            //uzupelnienie pól
            mp.g.setDlugoscl1(Integer.parseInt(input.tflenght1.getText()));
            mp.g.setDlugoscl2(Integer.parseInt(input.tflength2.getText()));
            mp.g.setWysokosch(Integer.parseInt(input.tfheight.getText()));
            mp.g.setDlugoscd(Integer.parseInt(input.tfdlugosc.getText()));
            mp.g.setWychyls(Integer.parseInt(input.tfskok.getText()));
            mp.repaint(); //rysowanie
            checkParameters(); //sprawdzenie czy parametry sie ze soba zgadzaja


            if (isStart) {
                int i;
                i = mp.g.getWychyls(); //zmienna jako maksymalny wychyl
                while (i > 0) {

                    chartX.series.add(time, Manipulator.kat * mp.g.getDlugoscl2()); //uzupelnianie wykresu X
                    chartY.series.add(time++, Manipulator.kat * mp.g.getWychyls()); //uzupelnianie wykresu Y
                    mp.g.moveDown(); //poruszenie w dol
                    mp.repaint(); //namalowanie

                    i--; //dekrementacja

                    try { //sleep
                        Thread.sleep(10);
                    } catch (Exception ignored) {
                    }
                }

                while (i < mp.g.getWychyls()) {
                    chartX.series.add(time, Manipulator.kat * mp.g.getDlugoscl2()); //uzupelnianie wykresu X
                    chartY.series.add(time++,Math.abs(Manipulator.kat * mp.g.getWychyls())); //uzupelnianie wykresu Y
                    mp.g.moveUp(); //poruszanie w gore
                    mp.repaint(); //malowanie
                    i++; //inkrementacja
                    try { //sleep
                        Thread.sleep(10);
                    } catch (Exception ignored) {
                    }
                }
            }
            isStart = false; //zmiana zmiennej na false asekuracyjnie
        });
        t.start();

    }

    public MainFrame() {
        //ustawienia glownej ramki
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Lab04_pop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //tworzenie paneli
        //JPanel
        mp = new MyPanel();
        mp.setLocation(400, 200);
        input = new Input();
        chartX = new Chart();
        chartX.setLocation(1400, 150);
        chartY = new Chart();
        chartY.setLocation(1400, 500);


        //Title
        JLabel label = new JLabel("Przebieg symulacji");
        label.setSize(200, 50);
        label.setLayout(null);
        label.setLocation(750, 150);

        //for charts
        //x
        JLabel labelChartX = new JLabel("Wykres prędkości dla współrzędnej X");
        labelChartX.setSize(300, 50);
        labelChartX.setLayout(null);
        labelChartX.setLocation(1400, 0);
        //y
        JLabel labelChartY = new JLabel("Wykres prędkości dla współrzędnej Y");
        labelChartY.setSize(300, 50);
        labelChartY.setLayout(null);
        labelChartY.setLocation(1100, 350);


        //dodawanie do wykresu
        this.add(mp);
        this.add(input);
        this.add(chartX);
        this.add(chartY);
        this.add(label);
        this.add(labelChartX);
        this.add(labelChartY);
    }

    public void checkParameters() {
        int d = mp.g.getDlugoscd();
        int l2 = mp.g.getDlugoscl2();
        int h = mp.g.getWysokosch();

        isStart = Math.sqrt(l2 * l2) > Math.sqrt(h * h + d * d);
    }

    public Input getInput() {
        return input;
    }
}
