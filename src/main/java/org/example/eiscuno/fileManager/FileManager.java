package org.example.eiscuno.fileManager;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.*;
import java.io.*;
import java.util.*;

/**
 * Manages file operations
 * This class is used for loading and saving game data, such as character and biome information
 */
public class FileManager {

    private static final String FILE_PATH = "src/main/resources/org/example/eiscuno/saves/saves.txt"; // Ruta del archivo


    /**
     * Updates a specific line in the file with new content.
     * @param lineNumber the zero based index of the line to update
     * @param newContent the new content to write to the specified line
     */
    public static void updateLine(int lineNumber, String newContent) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            // Si la línea existe, la reemplazamos con el nuevo contenido
            if (lineNumber >= 0 && lineNumber < lines.size()) {
                lines.set(lineNumber, newContent);
            } else {
                // Si no existe esa línea (por ejemplo, si el archivo está vacío o tiene menos líneas), la agregamos
                for (int i = lines.size(); i <= lineNumber; i++) {
                    lines.add(""); // Agrega líneas vacías hasta llegar a la línea deseada
                }
                lines.set(lineNumber, newContent);
            }

            // Escribimos todas las líneas de nuevo en el archivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (String updatedLine : lines) {
                    writer.write(updatedLine);
                    writer.newLine(); // Asegura que haya un salto de línea
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the character information from the first line of the file
     * @return the character data as an integer, or -1 if an error occurs or no data is found
     */
    public static int loadCharacter() {
        int character = -1;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String characterLine = reader.readLine();
            if (characterLine != null) {
                character = Integer.parseInt(characterLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return character;
    }

    /**
     * Loads the biome information from the second line of the file
     * The first line is skipped as it contains the character data
     * @return the biome data as an integer, or -1 if an error occurs or no data is found
     */
    public static int loadBiome() {
        int biome = -1;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            reader.readLine();
            String biomeLine = reader.readLine();
            if (biomeLine != null) {
                biome = Integer.parseInt(biomeLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return biome;
    }
}
