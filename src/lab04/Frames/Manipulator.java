package lab04.Frames;

import lab04.Objects.GObject;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Manipulator extends GObject {

    int dx0;
    int dy0;
    int dy1 = 0;
    int dlugoscl1 = 100;
    int dlugoscl2 = 200;
    int wysokosch = 50;
    int dlugoscd = 50;
    int wychyls = 50;
    public double gamma;
    public double lastGamma;
    public static double kat;


    @Override
    public int getDlugoscl1() {
        return dlugoscl1;
    }
    public void setDlugoscl1(int dlugoscl1) {
        this.dlugoscl1 = dlugoscl1;
    }
    @Override
    public int getDlugoscl2() {
        return dlugoscl2;
    }
    public void setDlugoscl2(int dlugoscl2) {
        this.dlugoscl2 = dlugoscl2;
    }
    @Override
    public int getDlugoscd() {
        return dlugoscd;
    }
    public void setDlugoscd(int dlugoscd) {
        this.dlugoscd = dlugoscd;
    }
    @Override
    public int getWysokosch() {
        return wysokosch;
    }
    public void setWysokosch(int wysokosch) {
        this.wysokosch = wysokosch;
    }
    @Override
    public int getWychyls() {
        return wychyls;
    }
    public void setWychyls(int wychyls) {
        this.wychyls = wychyls;
    }
    @Override
    public void drawMe(Graphics g) {
        System.out.println(gamma);

        Graphics2D g2d = (Graphics2D) g;

        dx0 = g2d.getClipBounds().width / 2;
        dy0 = g2d.getClipBounds().height / 2;


        AffineTransform saveAT = g2d.getTransform();
        t = new AffineTransform();
        t.translate(dx0, dy0);
        t.scale(1, -1);
        g2d.setTransform(t);
        //printing Y axis
        g2d.drawLine(0, -dlugoscl1 - 20, 0, 300);
        g2d.drawLine(-5, 295, 0, 300);
        g2d.drawLine(5, 295, 0, 300);
        //drawing X axis
        g2d.drawLine(-50, 0, 300, 0);
        g2d.drawLine(295, -5, 300, 0);
        g2d.drawLine(295, 5, 300, 0);

        //drawing first things
        g2d.drawOval(dlugoscd - 5, wysokosch - 5, 10, 10);
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(0, dy1, 0, dy1 + dlugoscl1);
        g2d.fillOval(-5,  dy1 -5, 10, 10);
        g2d.fillOval(-5, dy1 + dlugoscl1 -5, 10, 10);
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.fillPolygon(new int[] {dlugoscd - 8, dlugoscd, dlugoscd + 8}, new int[] {wysokosch - 15, wysokosch, wysokosch - 15}, 3);

        //ustawienie transformacji
        g2d.setTransform(t);



        //katy potrzebne do wykresow
        gamma = Math.atan2(wysokosch - dlugoscl1 - dy1, dlugoscd);
        kat = gamma;
        lastGamma = gamma;

        //druga translacja i obrot
        t.translate(0, dy1 + dlugoscl1);
        t.rotate(gamma);
        g2d.setTransform(t);

        //narysowanie linii
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(0, 0, dlugoscl2, 0);
        g2d.fillOval(dlugoscl2 -5, -5, 10, 10);
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));


        //zapis
        g2d.setTransform(saveAT);

    }
    @Override
    public void moveDown() {
        dy1--;
    }

    public void moveUp() {
        dy1++;
    }

}
