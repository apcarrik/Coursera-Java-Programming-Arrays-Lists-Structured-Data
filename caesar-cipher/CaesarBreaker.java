
/**
 * Contains my code for the CaesarBreaker assignment.
 *
 * @author apcarrik
 * @version 8-29-2023
 */

import edu.duke.FileResource;
import java.util.Arrays;

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
     * This method should return a new String that is every other character 
     * from message starting with the start position.
     *
     * @param   message String representing message to return half of
     * @param   start   int representing which character index to start at
     * @return          String representing the appropriate part of message
     */
    public String halfOfString(String message, int msgIdx) {
        StringBuilder half = new StringBuilder(Math.floorDiv(message.length()-msgIdx+1,2));
        for (int i = 0; msgIdx < message.length(); i++) {
            char c = message.charAt(msgIdx);
            half.append(c);
            msgIdx+=2;
        }
        return half.toString();
    }
    
    /**
     * This method attempts to determine the two keys used to encrypt the 
     * message, prints the two keys, and then returns the decrypted String 
     * with those two keys.
     *
     * @param   encrypted   String representing the encrypted message
     * @return              String representing the decrypted message
     */
    public String decryptTwoKeys(String encrypted) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        WordLengths wl = new WordLengths();
        
        String half1 = halfOfString(encrypted, 0);
        int[] counts1 = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        countLetters(half1, alphabet, counts1);
        int key1 = 0;
        int maxIdx1 = wl.indexOfMax(counts1);
        if (maxIdx1 > 4) {
            key1 = maxIdx1 - 4;
        } else {
            key1 = 4 - maxIdx1;
        }
        
        String half2 = halfOfString(encrypted, 1);
        int[] counts2 = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        countLetters(half1, alphabet, counts2);
        int key2 = 0;
        int maxIdx2 = wl.indexOfMax(counts2);
        if (maxIdx2 > 4) {
            key2 = maxIdx2 - 4;
        } else {
            key2 = 4 - maxIdx2;
        }
        System.out.println("Key1 = " + key1 + ", Key2 = " + key2);
        CaesarCipher cc = new CaesarCipher();
        return cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
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
    
    /**
     * Tests method halfOfString().
     */
    public void testHalfOfString() {
        System.out.println("\nTesting halfOfString()");
        String message = "abcd";
        int start = 0;
        String result = halfOfString(message, start);
        String expected = "ac";        
        System.out.println("Test passed = " + (result.equals(expected)));
        
        start = 1;
        result = halfOfString(message, start);
        expected = "bd";        
        System.out.println("Test passed = " + (result.equals(expected)));
        
        message = "abc";
        start = 0;
        result = halfOfString(message, start);
        expected = "ac";        
        System.out.println("Test passed = " + (result.equals(expected)));
        
        start = 1;
        result = halfOfString(message, start);
        expected = "b";        
        System.out.println("Test passed = " + (result.equals(expected)));
        
        message = "Qbkm Zgis";
        start = 0;
        result = halfOfString(message, start);
        expected = "Qk gs";        
        System.out.println("Test passed = " + (result.equals(expected)));
        
        start = 1;
        result = halfOfString(message, start);
        expected = "bmZi";        
        System.out.println("Test passed = " + (result.equals(expected)));
        
    }
    
    
    /**
     * Tests method decryptTwoKeys().
     */
    public void testDecryptTwoKeys() {
        System.out.println("\nTesting decryptTwoKeys()");
        CaesarCipher cc = new CaesarCipher();
        int testkey1 = 23;
        int testkey2 = 10;
        String message = "ABCDEFGHIJKLMNOPQRSTUVWXYZE";
        String encrypted = cc.encryptTwoKeys(message, testkey1, testkey2);              
        String decrypted = decryptTwoKeys(message);        
        System.out.println("Test passed = " + (message.equals(decrypted)));
        
        testkey1 = 23;
        testkey2 = 23;
        FileResource fr = new FileResource("smallHamlet.txt");
        message = fr.asString();
        encrypted = cc.encryptTwoKeys(message, testkey1, testkey2);              
        decrypted = decryptTwoKeys(message);
        
        System.out.println("Test passed = " + (message.equals(decrypted)));
        
    }
    
    /**
     * Computes answers for quiz().
     */
    public void quiz() {
        System.out.println("\n4)");        
        WordLengths wl = new WordLengths();
        FileResource fr = new FileResource("romeo.txt");
        String filetext = fr.asString();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        wl.countWordLengths(filetext, counts);
        System.out.println("counts = " + Arrays.toString(counts));
        int max = wl.indexOfMax(counts);
        System.out.println("Most common word length in romeo.txt: " + max);
        
        System.out.println("\n5)");        
        fr = new FileResource("lotsOfWords.txt");
        filetext = fr.asString();
        counts = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        wl.countWordLengths(filetext, counts);
        System.out.println("counts = " + Arrays.toString(counts));
        max = wl.indexOfMax(counts);
        System.out.println("Most common word length in lotsOfWords.txt: " + max);
        
        System.out.println("\n8)");      
        CaesarCipher cc = new CaesarCipher();
        String decrypted = cc.encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", 26 - 2, 26 - 20);
        System.out.println("Decrypted answer: " + decrypted);
        
        System.out.println("\n9)");   
        decrypted = decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko");   
        System.out.println("Decrypted answer: " + decrypted);
        
        System.out.println("\n10) & 11)");    
        fr = new FileResource("mysteryTwoKeysPractice.txt");
        String encrypted = fr.asString();
        decrypted = decryptTwoKeys(encrypted);   
        System.out.println("Decrypted answer: " + decrypted);
        
    }
    
    public static void main(String[] args){
        System.out.println("\n\n==== main ====");
        CaesarBreaker db = new CaesarBreaker();
        // db.testCountLetters();
        // db.testIndexOfMax();
        // db.testDecrypt();
        // db.testHalfOfString();
        // db.testDecryptTwoKeys();
        
        db.quiz();
    }
    
}
