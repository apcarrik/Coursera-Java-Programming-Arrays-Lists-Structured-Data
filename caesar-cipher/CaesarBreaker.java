
/**
 * Contains my code for the CaesarBreaker assignment.
 *
 * @author apcarrik
 * @version 8-29-2023
 */

import edu.duke.FileResource;

public class CaesarBreaker
{
    
    /**
     * This method finds the frequency of each letter of the english 
     * alphabet as found in the input file resource, and returns the result 
     * in the given array counts.
     *
     * @param   alphabet    String representing alphabet
     * @param   resource    FileResource representing file to use
     * @param   counts      int[] representing an array of word counts
     */
    public void countLetters(String filetext, String alphabet, int[] counts) {
        int charCount = 0;
        for (int i = 0; i<filetext.length(); i++) {
            char c = Character.toLowerCase(filetext.charAt(i));
            int idx = alphabet.indexOf(c);
            if (idx > -1) {
                counts[idx]++;
            }
        }
    }
    
    
    /**
     * This method returns a String that has been decrypted using the Caesar 
     * Cipher algorithm with one index. Handles both uppercase and lowercase
     * characters.
     *
     * @param   input   String representing phrase to decrypt
     * @return          String representing encrypted phrase
     */
    public String decrypt(String message)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        WordLengths wl = new WordLengths();
        int[] counts = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        countLetters(message, alphabet, counts);
        int key = 0;
        int maxIdx = wl.indexOfMax(counts);
        if (maxIdx > 4) {
            key = maxIdx - 4;
        } else {
            key = 4 - maxIdx;
        }
        CaesarCipher cc = new CaesarCipher();
        return cc.encrypt(message, 26 - key);
    }
    
    
    /**
     * Tests method countLetters() with content from files.
     */
    public void testCountLetters() {
        System.out.println("\nTesting countLetters()");
        FileResource fr = new FileResource("smallHamlet.txt");
        String filetext = fr.asString();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        countLetters(filetext, alphabet, counts);
        int[] expected = new int[]{7,2,1,3,13,2,1,1,5,0,1,3,2,4,0,0,0,6,7,3,0,1,2,0,1,0};
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != expected[i]) {                
                System.out.println("Test failed. counts[" + i + "] = " + 
                counts[i] + ", expected[" + i + "] = " + expected[i]);
                return;
            }
        }
        System.out.println("Test passed.");
        
    }
    
    
    /**
     * Tests method indexOfMax().
     */
    public void testIndexOfMax() {
        System.out.println("\nTesting indexOfMax()");
        
        WordLengths wl = new WordLengths();
        FileResource fr = new FileResource("smallHamlet.txt");
        String filetext = fr.asString();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        countLetters(filetext, alphabet, counts);
        int actual = wl.indexOfMax(counts);
        int expected = 4;
        System.out.println("Test passed: " + (actual == expected));
        
        counts = new int[]{0,0,0,0,0,0,0,0,0};
        actual = wl.indexOfMax(counts);
        expected = 0;
        System.out.println("Test passed: " + (actual == expected));
        
    } 
    
    /**
     * Tests method decrypt().
     */
    public void testDecrypt() {
        System.out.println("\nTesting decrypt()");
        CaesarCipher cc = new CaesarCipher();
        int testkey = 23;
        String message = "ABCDEFGHIJKLMNOPQRSTUVWXYZE";
        String encrypted = cc.encrypt(message, testkey);              
        String decrypted = decrypt(message);        
        System.out.println("Test passed = " + (message.equals(decrypted)));
        
        testkey = 23;
        FileResource fr = new FileResource("smallHamlet.txt");
        message = fr.asString();
        encrypted = cc.encrypt(message, testkey);              
        decrypted = decrypt(message);
        
        System.out.println("Test passed = " + (message.equals(decrypted)));
        
    }
    
    public static void main(String[] args){
        System.out.println("\n\n==== main ====");
        CaesarBreaker db = new CaesarBreaker();
        db.testCountLetters();
        db.testIndexOfMax();
        db.testDecrypt();
    }
    
}
