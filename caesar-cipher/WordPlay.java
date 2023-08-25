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
     * @return  bool    represents wheither ch is a vowel or not
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
     * This method returns true if ch is a vowel (one of 'a', 'e', 'i', 'o', or
     * 'u' or the uppercase versions) and false otherwise.
     *
     * @param   ch      char representing the character to test
     * @return  bool    represents wheither ch is a vowel or not
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
        
    }
    
    public static void main(String[] args){
        System.out.println("\n\n==== main ====");
        WordPlay wp = new WordPlay();
        
        wp.testIsVowel();
    }
    
}
