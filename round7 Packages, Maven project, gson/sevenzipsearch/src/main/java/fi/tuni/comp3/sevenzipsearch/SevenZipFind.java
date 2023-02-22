
package fi.tuni.comp3.sevenzipsearch;

/**
 *
 * @author Tuan Anh Nguyen <anh.5.nguyen@tuni.fi>
 */
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

public class SevenZipFind {
  
  public static void main(String[] args) throws IOException {
    if (args.length != 2) {
      System.err.println("Usage: SevenZipSearch <7z-file> <search-word>");
      System.exit(1);
    }
    
    String filename = args[0];
    String searchWord = args[1];
    
    Path path = Paths.get(filename);
    try (SevenZFile sevenZFile = new SevenZFile(path.toFile())) {
      SevenZArchiveEntry entry;
      while ((entry = sevenZFile.getNextEntry()) != null) {
        if (entry.isDirectory() || !entry.getName().endsWith(".txt")) {
          continue; // Skip directories and non-text files
        }
        
        System.out.println(entry.getName());
        
        int lineNumber = 0;
        try (InputStream is = sevenZFile.getInputStream(entry)) {
          for (String line : new String(is.readAllBytes(), "UTF-8").split("\\r?\\n")) {
            lineNumber++;
            if (line.toLowerCase().contains(searchWord.toLowerCase())) {
              System.out.println(lineNumber + ": " + highlightWord(line, searchWord));
            }
          }
        }
        
        System.out.println();
      }
    }
  }
  
  private static String highlightWord(String line, String searchWord) {
    return line.replaceAll("(?i)" + searchWord, searchWord.toUpperCase());
  }
  
}
