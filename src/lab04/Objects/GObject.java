package lab04.Objects;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

abstract public class GObject {
    public AffineTransform t;

    abstract public void drawMe(Graphics g);
    abstract public void moveDown();
    public void moveUp() {}
    public void setDlugoscl1(int i){}
    public void setDlugoscl2(int i) {}
    public void setWysokosch(int i) {}
    public void setDlugoscd(int i) {}
    public void setWychyls(int i) {
    }
    public int getDlugoscl1(){

        return 1;
    }
    public int getDlugoscl2(){

        return 1;
    }
    public int getWychyls() {

        return 1;
    }
    public int getDlugoscd() {

        return 1;
    }
    public int getWysokosch() {

        return 1;
    }

}
