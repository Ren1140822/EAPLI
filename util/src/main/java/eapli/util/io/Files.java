/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.util.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * utility class for file manipulation.
 *
 * @author Paulo Gandra Sousa
 */
public class Files {

    private Files() {
        // to make sure this is an utility class
    }

    public static String currentDirectory() {
        return new java.io.File(".").getAbsolutePath();
    }

    public static String ensureExtension(final String filename, final String extension) {
        if (!filename.endsWith(extension)) {
            return filename + extension;
        } else {
            return filename;
        }
    }

    /**
     * Replaces a value within the properties by by another.
     *
     * @param property
     * @param value
     * @return
     */
    public static boolean updateProperty(String property, String value, String path) {
        try {
            File propertiesFile = new File(path);
            Scanner sc = new Scanner(propertiesFile);

            List<String> lines = new ArrayList<String>();
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }

            sc.close();
            PrintWriter writer = new PrintWriter(propertiesFile);
            writer.print("");
            for (String line : lines) {
                System.out.println("Linha: " + line);
                if (line.contains(property)) {
                    String[] splittedLine = line.split("=");
                    String newLine = splittedLine[0].trim() + "=" + value;
                    line = newLine;
                }
                writer.println(line.toString());

            }
            writer.close();
        } catch (FileNotFoundException e) {
            //FIX ME
            return false;
        }
        return true;
    }
}
