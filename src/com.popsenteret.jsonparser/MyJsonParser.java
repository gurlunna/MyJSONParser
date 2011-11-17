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
        BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(infileName), "UTF-8"));
        StringBuffer stringBuffer = new StringBuffer();
        String dataFromFile;

        while ((dataFromFile = fr.readLine()) != null) {
            stringBuffer.append(dataFromFile);
        }
        fr.close();
        return stringBuffer.toString();
    }

    public static String formatFileName(String s) {
        String formatted = s.toLowerCase();
        formatted = formatted.replace(" ", "_");
        formatted = formatted.replace("&", "and");
        formatted = formatted.replace(".", "");
        formatted = formatted.replace("'", "");
        formatted = formatted.replace("(", "_");
        formatted = formatted.replace(")", "_");
        formatted = formatted.replace("/", "_");
        formatted = formatted.replace("\\", "_");
        formatted = formatted.replace("æ", "_");
        formatted = formatted.replace("ø", "_");
        formatted = formatted.replace("å", "_");
        formatted = formatted.replace("ð", "_");
        formatted = formatted.replace("`", "");
        formatted = formatted.replace("´", "");
        formatted = formatted.replace("ö", "o");
        formatted = formatted.replace("ü", "u");
        formatted = formatted.replace("$", "");
        formatted = formatted.replace("@", "");
        return formatted;
    }

    // Writes the tracks to the outputfile
    public static void writeBatFile(String outfileName, List<Track> tracks) throws IOException {
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outfileName), "UTF8"));
        String script1 = "convert -background black -gravity Center -density 100 -fill white  -font \"C:\\Windows\\Fonts\\alternategothic2_bt.ttf\" -size 600x60 label:\"";
        String script2 = "\" label.gif";
        String script3 = "composite -gravity South label.gif";
        printWriter.println("chcp 65001 &");
        for (Track track : tracks) {
            String artist = formatFileName(track.getArtistName());
            String title = formatFileName(track.getTitle());
            System.out.println(artist + " " + title);
            String[] temp = track.getImageName(track.getURL()).split("[.]");
            String imageName = temp[0] + ".jpg";
            printWriter.println(script1 + track.getArtistName().toUpperCase() + " - " + track.getTitle() + script2);
            printWriter.println(script3 + " " + "FraNRK" + File.separator + artist + "_" + track.getImageName(track.getImageXLURL()) + " " + "TilPopit" + File.separator + artist + "-" + title + "_" + imageName);
        }
        printWriter.println("@DEL /S /Q /F \"FraNRK\\*.*\"");
        printWriter.println("& chcp 850");
        printWriter.close();
    }
}
