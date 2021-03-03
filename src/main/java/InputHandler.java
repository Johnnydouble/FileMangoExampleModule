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
        boolean cont = true;
        while (cont) {
            String input = inputScanner.nextLine();
            try {
                m = g.fromJson(input, Message.class);
            } catch (JsonParseException jpe) {
                System.err.println(jpe.getMessage());
                continue;
            }

            //handled types
            if (m.Input.MsgType == 0)
            handleAnalysisRequest(m);
            cont = false;//                                                Exit After Complete
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
            System.err.println(f);
            return;
        }

        Color result = new ImageAnalyzer().analyze(img);
        String colorName = new ColorNameRequester().getColorName(result);
        var outMsg = generateOutputMessage(m, colorName);
        System.out.println(g.toJson(outMsg)); //                                     Print output message
    }

    Message generateOutputMessage(Message in, String colorName){
        return in.appendToMsg(in, in.Input.Data, colorName);
    }
}
