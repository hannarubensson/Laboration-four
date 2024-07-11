import java.util.Scanner;
import java.lang.String;

public class WordTranslation {

    private String[] swedishWords = { "Bil", "Hus", "Springa", "Blå", "Baka", "Hoppa", "Simma", "Måne", "Väg",
            "Snäll" };
    private String[] englishWords = { "Car", "House", "Run", "Blue", "Bake", "Jump", "Swim", "Moon", "Road", "Kind" };
    private int correctCounter = 0;
    private String swedishWord;
    private String englishWord;
    private String translationInput;

    public WordTranslation() {
    }

    public WordTranslation(String swedishWord, String englishWord, String translationInput) {
        this.swedishWord = swedishWord;
        this.englishWord = englishWord;
        this.translationInput = translationInput;
    }

    public void startProgram() {
        System.out.println("** GLOSÖVNING - ENGELSKA ** \n Skriv det engelska ordet. Avsluta med Q.");
        runProgram(swedishWords, englishWords);
    }

    public void runProgram(String[] swedishWords, String[] englishWords) {
        Scanner scan = new Scanner(System.in);
        int length = swedishWords.length;

        for (int i = 0; i < length; i++) {
            System.out.println(swedishWords[i] + " : ");
            String translationInput = scan.nextLine();

            if (translationInput.equalsIgnoreCase("Q")) {
                endMessage(i);
                System.out.println("Programmet avslutas...");
                break;
            }

            if (translationInput.equalsIgnoreCase(englishWords[i])) {
                correctCounter++;
                System.out.println("Korrekt! " + correctCounter + " rätt av " + length + " ord");
            } else {
                boolean almostCorrect = countLetters(englishWords, translationInput, i);

                if (almostCorrect) {
                    System.out.println("Nästan rätt. Korrekt svar är: " + englishWords[i]);
                } else {

                    System.out.println("Fel. Rätt svar är: " + englishWords[i]);
                }
            }

            // WordTranslation newTranslation = new WordTranslation(swedishWords[i],
            // englishWords[i], translationInput,
            // translationInput.equalsIgnoreCase(englishWords[i]));
        }

        scan.close();
    }

    // Ta med index så man vet i vilken position i arrayen som ordet finns och kan
    // jämföra
    public boolean countLetters(String[] englishWords, String translationInput, int index) {
        String correctWord = englishWords[index];
        int correctLetters = 0;
        int length = Math.min(translationInput.length(), correctWord.length());

        for (int i = 0; i < length; i++) {
            if (translationInput.charAt(i) == correctWord.charAt(i)) {
                correctLetters++;
            }
        }

        double percentCorrect = (double) correctLetters / correctWord.length();

        return percentCorrect >= 0.75;
    }

    public void endMessage(int index) {
        System.out.println(
                "Du svarade på totalt" + index + 1 + "glosor och hade " + correctCounter + " rätt! Välkommen åter!");
    }

    public static void main(String[] args) {

        WordTranslation program = new WordTranslation();
        program.startProgram();

    }

}
