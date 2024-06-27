package utils;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

public class ExperimentMain {

    public static void main(String[] args) {
        String url = "view-source:https://www.goodreads.com/shelf/show/psychology"; // specify the URL you want to open

        if (Desktop.isDesktopSupported()) {
            try {
                for(int i=1; i<=2; i++) {
                    if(i == 1) {
                        Desktop.getDesktop().browse(new URI(url));
                    } else {
                        Desktop.getDesktop().browse(new URI(url + "?page=" + i));
                    }
                    Random random = new Random();
                    int delay = 1000 + random.nextInt(2000);
                    Thread.sleep(delay);
                }
            } catch (IOException | URISyntaxException | InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Desktop is not supported. Cannot open browser.");
        }
    }
}
