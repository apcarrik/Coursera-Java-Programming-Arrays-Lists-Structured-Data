import java.util.Arrays;

/**
 * Holds my code for week 1 wordplay class.
 *
 * @author apcarrik
 * @version 8-25-2023
 */
public class WordPlay
{
    /**
     * This method returns true if ch is a vowel (one of 'a', 'e', 'i', 'o', or
     * 'u' or the uppercase versions) and false otherwise.
     *
     * @param   ch      char representing the character to test
     * @return          bool representing wheither ch is a vowel or not
     */
    public Boolean isVowel(char ch)
    {
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        for (char c: vowels) {
            if (c == Character.toLowerCase(ch)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * This method should return a String that is the string phrase with all 
     * the vowels (uppercase or lowercase) replaced by ch.
     *
     * @param   ch      char representing the character to test
     * @param   phrase  String representing the phrase to modify
     * @return          String representing modified phrase
     */
    public String replaceVowels(char ch, String phrase)
    {
        StringBuilder out = new StringBuilder(phrase);
        for (int i = 0; i < out.length(); i++) {
            char c = out.charAt(i);
            if (isVowel(c)) {
                out.setCharAt(i, ch);
            }
        }
        return out.toString();
    }
    
    /**
     * This method should return a String that is the string phrase but with 
     * the Char ch (upper- or lowercase) replaced by:
     * 
     *   1. ‘*’ if it is in an odd number location in the string (e.g. the 
     *   first * character has an odd number location but an even index, it 
     *   is at index * 0), or 
     *   2. ‘+’ if it is in an even number location in the string (e.g. the 
     *   second character has an even number location but an odd index, it is 
     *   at index 1).
     *
     * @param   match      char representing the character to test
     * @param   phrase  String representing the phrase to modify
     * @return          String representing modified phrase
     */
    public String emphasize(char match, String phrase)
    {
        StringBuilder out = new StringBuilder(phrase);
        for (int i = 0; i < out.length(); i++) {
            char c = out.charAt(i);
            if (c == Character.toLowerCase(match)) {
                if (i%2 == 0) {
                    out.setCharAt(i, '*');
                } else {
                    out.setCharAt(i, '+');                    
                }
            }
        }
        return out.toString();
    }
    

    /**
     * Tests method isVowel().
     */
    public void testIsVowel() {
        System.out.println("\nTesting isVowel()");
        char test = 'a';
        Boolean result = isVowel(test);
        Boolean expected = true;       
        System.out.println("Test passed = " + (result == expected));
        
        test = 'z';
        result = isVowel(test);
        expected = false;      
        System.out.println("Test passed = " + (result == expected));
        
        test = 'F';
        result = isVowel(test);
        expected = false;      
        System.out.println("Test passed = " + (result == expected));
        
        test = 'a';
        result = isVowel(test);
        expected = true;      
        System.out.println("Test passed = " + (result == expected));
        
    }
    
    /**
     * Tests method replaceVowels().
     */
    public void testReplaceVowels() {
        System.out.println("\nTesting replaceVowels()");
        char testch = '*';
        String teststr = "Hello world";
        String result = replaceVowels(testch, teststr);
        String expected = "H*ll* w*rld";       
        System.out.println("Test passed = " + (result.equals(expected)));
        
        testch = '*';
        teststr = "b";
        result = replaceVowels(testch, teststr);
        expected = "b";       
        System.out.println("Test passed = " + (result.equals(expected)));
        
    }
    
    /**
     * Tests method emphasize().
     */
    public void testEmphasize() {
        System.out.println("\nTesting emphasize()");
        char testch = 'a';
        String teststr = "dna ctgaaactga";
        String result = emphasize(testch, teststr);
        String expected = "dn* ctg+*+ctg+";       
        System.out.println("Test passed = " + (result.equals(expected)));
        
        testch = 'a';
        teststr = "Mary Bella Abracadabra";
        result = replaceVowels(testch, teststr);
        expected = "M+ry Bell+ +br*c*d*br+";       
        System.out.println("Test passed = " + (result.equals(expected)));
        
    }
    
    public static void main(String[] args){
        System.out.println("\n\n==== main ====");
        WordPlay wp = new WordPlay();
        
        wp.testIsVowel();
        wp.testReplaceVowels();
        wp.testEmphasize();
    }
    
}
