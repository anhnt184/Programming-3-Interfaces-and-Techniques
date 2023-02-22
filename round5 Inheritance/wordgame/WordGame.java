import java.io.*;
import java.util.ArrayList;

public class WordGame {
    private String[] words;
    private String word;
    private int mistakeCount;
    private int mistakeLimit;
    private int missingChars;
    private boolean isActive;
    private String guessedString;

    public WordGame(String wordFilename) throws IOException {
        ArrayList<String> wordsList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(wordFilename));
        String line;
        while ((line = br.readLine()) != null) {
            wordsList.add(line);
        }
        words = wordsList.toArray(new String[0]);
    }

    public void initGame(int wordIndex, int mistakeLimit) {
        word = words[wordIndex % words.length];
        this.mistakeLimit = mistakeLimit;
        mistakeCount = 0;
        missingChars = word.length();
        isActive = true;
        guessedString = "_".repeat(word.length());

    }

    public boolean isGameActive() {
        if (mistakeCount >= mistakeLimit + 1 || missingChars == 0) {
            isActive = false;
            
        }
        return isActive;
    }

    public WordGameState getGameState() throws GameStateException {
        if (!isActive) {
            throw new GameStateException("There is currently no active word game!");
        }
        return new WordGameState(word, mistakeCount, mistakeLimit, missingChars);
    }

    public WordGameState guess(char c) throws GameStateException {
        
        if (!isActive || missingChars == 0) {
            throw new GameStateException("There is currently no active word game!");
        }
        c = Character.toLowerCase(c);
        int index = word.indexOf(c);
        int guessedChar = guessedString.indexOf(c);
            if (index != -1 && guessedChar == -1) {
            while (index != -1 ) {
                missingChars--;
                index = word.indexOf(c, index + 1);
            }
        } else {
            mistakeCount++;
        }
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == c) {
                guessedString = replaceCharAt(guessedString, i, c);
            }
        }
        
        if (mistakeCount >= mistakeLimit + 1 || missingChars == 0) {
            isActive = false;
            guessedString = word;
        }
        return new WordGameState(guessedString, mistakeCount, mistakeLimit, missingChars);
    }
    private static String replaceCharAt(String s, int pos, char c) {
        return s.substring(0, pos) + c + s.substring(pos + 1);
    }
    public WordGameState guess(String guess) throws GameStateException {
      if (!isActive || missingChars == 0) {
        throw new GameStateException("There is currently no active word game!");
      }
      if (guess.equals(word)) {
        missingChars = 0;
        return new WordGameState(word, mistakeCount, mistakeLimit, missingChars);
      }
      mistakeCount++;
      if (mistakeCount >= mistakeLimit+1) {
        isActive = false;
        return new WordGameState(word, mistakeCount, mistakeLimit, missingChars);
      }
      return new WordGameState(guessedString, mistakeCount, mistakeLimit, missingChars);
    }

    public static class WordGameState {
        private final String word;
        private final int mistakeCount;
        private final int mistakeLimit;
        private final int missingChars;
        
       

        private WordGameState(String word, int mistakeCount, int mistakeLimit, int missingChars) {
            this.word = word;
            this.mistakeCount = mistakeCount;
            this.mistakeLimit = mistakeLimit;
            this.missingChars = missingChars;
            
           
        }

        public String getWord() {
            return word;
        }

        public int getMistakes() {
            return mistakeCount;
        }

        public int getMistakeLimit() {
            return mistakeLimit;
        }

        public int getMissingChars() {
            return missingChars;
        }
        
    }
}