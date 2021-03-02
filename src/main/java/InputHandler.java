import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class InputHandler extends Thread {
    Gson g = new Gson();

    public void run() {
        Scanner inputScanner = new Scanner(System.in);
        Message m;
        while (true) {
            String input = inputScanner.nextLine();
            try {
                m = g.fromJson(input, Message.class);
            } catch (JsonParseException jpe) {
                System.err.println(jpe.getMessage());
                continue;
            }

            //handled types
            if (m.Input.MsgTyp.equals(new messageType(0)))
            handleAnalysisRequest(m);
        }
    }

    void handleAnalysisRequest(Message m) {
        //get file
        String path = m.Input.Data;
        File f = new File(path);

        //get image
        BufferedImage img;
        try {
            img = ImageIO.read(f);
        } catch (IOException e) {
            //do nothing
            return;
        }

        Color result = new ImageAnalyzer().analyze(img);
    }
}
