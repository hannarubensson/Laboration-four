import java.util.Scanner;
import java.lang.String;

/**
 * GLOSÖVNING
 * Ett program där man övar på glosor och får feedback
 * Vid rätt översättning får man 1 poäng, vid över 75% rätt
 * får man information om vad det rätta svaret var.
 * Om man har fel får man 0 poäng.
 * 
 * För att avsluta programmet skrivs 'Q', annars avslutas programmet
 * automatiskt efter 10 glosor.
 */

public class WordTranslation {

    private String[] swedishWords = { "Bil", "Hus", "Springa", "Blå", "Baka", "Hoppa", "Simma", "Måne", "Väg",
            "Snäll" };
    private String[] englishWords = { "Car", "House", "Run", "Blue", "Bake", "Jump", "Swim", "Moon", "Road", "Kind" };
    private int correctCounter = 0;
    private String swedishWord;
    private String englishWord;
    private String translationInput;

    /** Tom metod för att starta programmet */

    public WordTranslation() {
    }

    /** Konstruktors */

    public WordTranslation(String swedishWord, String englishWord, String translationInput) {
        this.swedishWord = swedishWord;
        this.englishWord = englishWord;
        this.translationInput = translationInput;
    }

    /**
     * Void-metod
     * Startar programmet i main
     * Kör sedan metoden runProgram med båda arrayer som finns i klassen
     * WordTranslation
     */

    public void startProgram() {
        System.out.println("** GLOSÖVNING - ENGELSKA ** \n Skriv det engelska ordet. Avsluta med Q.");
        runProgram(swedishWords, englishWords);
    }

    /**
     * Metod som utgör största delen av programmet, jämför svenska ord
     * med engelska motsvarigheten
     * 
     * Avslutar programmet om man kört 10 ord, eller om man skriver 'q'
     * 
     * @param swedishWords
     * @param englishWords
     */

    public void runProgram(String[] swedishWords, String[] englishWords) {
        Scanner scan = new Scanner(System.in);
        int length = swedishWords.length;

        for (int i = 0; i < length; i++) {
            System.out.println(swedishWords[i] + " : ");
            String translationInput = scan.nextLine();

            if (translationInput.equalsIgnoreCase("Q") || i == 10) {
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
        }

        scan.close();
    }

    /**
     * Räknar antalet korrekta bokstäver i englishWords och jämför med
     * translationInput
     * Tar med index på vilket ord som man ska räkna detta för så man vet dess
     * position i
     * respektive array
     * 
     * @param englishWords
     * @param translationInput
     * @param index
     * @return antalet procent rätt. Över 75% rätt skrivs ett meddelande ut från
     *         metoden runProgram.
     */

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

    /**
     * Void-metod som skriver ut avslutningsmeddelandet till runProgram
     * Tar med index som parameter för att räkna hur många glosor man svarat på
     * innan programmet avslutas
     * CorrectCounter är global och återanvänds här för att ge återkoppling på antal
     * rätt
     * 
     * 
     * @param index
     */

    public void endMessage(int index) {
        System.out.println(
                "Du svarade på totalt" + index + 1 + "glosor och hade " + correctCounter + " rätt! Välkommen åter!");
    }

    /**
     * Main-metoden som bara startar upp programmet
     * 
     * @param args
     */

    public static void main(String[] args) {

        WordTranslation program = new WordTranslation();
        program.startProgram();

    }

}
