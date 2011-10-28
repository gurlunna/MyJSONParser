package com.popsenteret.jsonparser;

import com.google.gson.Gson;
import java.io.*;
import java.util.List;

public class MyJsonParser {

    public static void main(String[] args) throws IOException {
        String[] infiles = getInfileNames();
        for (int i = 0; i < infiles.length; i++) {
            String json = readJSONFromFile(infiles[i]);
            Data data = new Gson().fromJson(json, Data.class);

            List<Track> tracks = data.getResult().getTracks();
            String outfileName = "test" + i + ".bat";
            writeBatFile(outfileName, tracks);
        }
    }

    //  Gets all the inputfiles from JSONfiles.txt
    public static String[] getInfileNames() throws IOException {
        BufferedReader fr = new BufferedReader(new FileReader("JSONfiles.txt"));
        StringBuffer stringBuffer = new StringBuffer();
        String dataFromFile;
        while ((dataFromFile = fr.readLine()) != null) {
            stringBuffer.append(dataFromFile + ",");
        }
        fr.close();
        String tmp = stringBuffer.toString();
        return tmp.split(",");
    }

    // Reads input from file to String
    public static String readJSONFromFile(String infileName) throws IOException {
        BufferedReader fr = new BufferedReader(new FileReader(infileName));
        StringBuffer stringBuffer = new StringBuffer();
        String dataFromFile;

        while ((dataFromFile = fr.readLine()) != null) {
            stringBuffer.append(dataFromFile);
        }
        fr.close();
        return stringBuffer.toString();
    }

    // Writes the tracks to the outputfile
    public static void writeBatFile(String outfileName, List<Track> tracks) throws IOException {
        PrintWriter printWriter = new PrintWriter(new File(outfileName));
        String script1 = "convert -background black -density 100 -fill white  -font \"C:\\Windows\\Fonts\\alternategothic2_bt.ttf\" -size 600x60 -pointsize 32 label:\"";
        String script2 = "\" label.gif";
        String script3 = "composite -gravity South label.gif";
        int i = 1;
        for (Track t : tracks) {
            printWriter.println(script1 + t.getArtistName().toUpperCase() + " - " + t.getTitle() + script2);
            printWriter.println(script3 + " " + t.getImageXLURL() + " " + t.getImageName(t.getImageXLURL()) + " " + t.getImageNameWithText(t.getImageXLURL()));
            i++;
        }
        printWriter.close();
    }
}
