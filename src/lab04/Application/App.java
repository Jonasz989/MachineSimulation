/**
 * Whole client class
 *
 * @author Damian Jabłoński
 *
 * Compiling:
 * javac *.java
 * Creating JAR file
 * jar cfm jarFileName MANIFEST.MF *.class
 * Starting program
 * java -jar jarFileName.jar
 */

package lab04.Application;

import lab04.Frames.MainFrame;
import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class App {
    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
            SwingUtilities.invokeAndWait(() -> {
                MainFrame thisClass = new MainFrame();
                thisClass.getInput().startButton.addActionListener(e -> thisClass.animate());
            });
    }
}
