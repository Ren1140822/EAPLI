/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.util.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
    public static boolean updateProperty(String property, String value, String path) throws IOException {
        File propertiesFile = null;
        BufferedReader buffer = null;
        try {
            propertiesFile = new File(path);
            buffer = new BufferedReader(new FileReader(propertiesFile));
        } catch (IOException ex) {
            throw new IOException("The file is unreachable!");
        }

        List<String> lines = new ArrayList<String>();
        String readLine = "";
        while ((readLine = buffer.readLine()) != null) {
            lines.add(readLine);
        }

        PrintWriter writer = new PrintWriter(propertiesFile);
        writer.print("");
        for (String line : lines) {
            if (line.contains(property)) {
                String[] splittedLine = line.split("=");
                String newLine = splittedLine[0].trim() + "=" + value;
                line = newLine;
            }
            writer.println(line);
        }
        writer.close();
        buffer.close();
        return true;
    }
}
