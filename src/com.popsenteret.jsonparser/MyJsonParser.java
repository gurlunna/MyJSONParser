package com.popsenteret.jsonparser;

import com.google.gson.Gson;

import java.io.*;
import java.util.List;

public class MyJsonParser {

    public static void main(String[] args) throws IOException {
        String json = readJSONFromFile("urortJsonFile.txt");
        Data data = new Gson().fromJson(json, Data.class);

        List<Track> tracks = data.getResult().getTracks();
        String outfileName = "makeLabels.bat";
        writeBatFile(outfileName, tracks);
    }

    /* //  Gets all the inputfiles from JSONfiles.txt  - use if you want multiple infiles
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
    }       */

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
        String script1 = "convert -background black -gravity Center -density 100 -fill white  -font \"C:\\Windows\\Fonts\\alternategothic2_bt.ttf\" -size 600x60 label:\"";
        String script2 = "\" label.gif";
        String script3 = "composite -gravity South label.gif";
        int i = 1;
        printWriter.println("chcp 65001 &");
        for (Track t : tracks) {
            String artist = t.getArtistName().replace(" ", "_");
            artist = artist.replace("&", "and");
            artist = artist.replace(".", "");
            String title = t.getTitle().replace(" ", "_");
            title = title.replace("&", "and");
            title = title.replace("/", "_");
            title = title.replace("\\", "_");
            printWriter.println(script1 + t.getArtistName().toUpperCase() + " - " + t.getTitle() + script2);
            printWriter.println(script3 + " " + ("FraNRK/" + artist + "_" + t.getImageName(t.getImageXLURL())) + " " + "TilPopit/" + artist + "-" + title + "_" + t.getImageNameWithText(t.getImageXLURL()));
            i++;
        }
        printWriter.println("@DEL /S /Q /F \"FraNRK\\*.*\"");
        printWriter.println("& chcp 850");
        printWriter.close();
    }
}
