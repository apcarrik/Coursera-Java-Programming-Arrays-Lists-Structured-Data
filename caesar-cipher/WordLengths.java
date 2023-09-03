
/**
 * Holds my code for the WordLengths assignment.
 *
 * @author apcarrik
 * @version 8-28-2023
 */

import edu.duke.FileResource;
public class WordLengths
{
    /**
     * This method should read in the words from resource and count the number
     * of words of each length for all the words in resource, storing these 
     * counts in the array counts.
     *
     * @param   resource    FileResource representing file to use
     * @param   counts      int[] representing an array of word counts
     */
    public void countWordLengths(String filetext, int[] counts) {
        int charCount = 0;
        boolean prevNonChar = false;
        for (int i = 0; i<filetext.length(); i++) {
            char c = filetext.charAt(i);
            if (Character.isWhitespace(c)) {
                if (charCount > 0) {
                    if (charCount < counts.length) {
                        counts[charCount]++;
                    } else {
                        counts[counts.length]++;
                    }
                }
                charCount = 0;
                prevNonChar = false;
            } else if (Character.isLetter(c) || prevNonChar == true) {
                charCount++;
                if (prevNonChar == true) {
                    charCount++;
                    prevNonChar = false;
                }
            } else if (charCount > 0) {
                prevNonChar = true;
            }   
        }
    }
    
    /**
     * This method returns the index position of the largest element in 
     * values.
     *
     * @param   counts  int[] representing an array of word counts
     * @return          int representing the index with the largest of values
     */
    public int indexOfMax(int[] counts) {
        int maxVal = -1;
        int maxValIdx = -1;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > maxVal) {
                maxVal = counts[i];
                maxValIdx = i;
            }
        }
        return maxValIdx;
    }
    
    
    /**
     * Tests method countWordLengths() with content from files.
     */
    public void testCountWordLengths() {
        System.out.println("\nTesting countWordLengths()");
        FileResource fr = new FileResource("smallHamlet.txt");
        int[] counts = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        String filetext = fr.asString();
        countWordLengths(filetext, counts);
        int[] expected = new int[]{0,0,2,3,2,1,1,1,2,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
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
        int[] counts = new int[]{0,0,2,3,2,1,1,1,2,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int actual = indexOfMax(counts);
        int expected = 3;
        System.out.println("Test passed: " + (actual == expected));
        
        counts = new int[]{0,0,0,0,0,0,0,0,0};
        actual = indexOfMax(counts);
        expected = 0;
        System.out.println("Test passed: " + (actual == expected));
        
    } 
    
    public static void main(String[] args){
        System.out.println("\n\n==== main ====");
        WordLengths wl = new WordLengths();
        wl.testCountWordLengths();
        wl.testIndexOfMax();
        
    }
}
