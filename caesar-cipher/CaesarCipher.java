import edu.duke.FileResource;
/**
 * Contains my solution to the CaesarCipher assignment.
 *
 * @author apcarrik
 * @version 8-26-2023
 */
public class CaesarCipher
{
    /**
     * This method returns a String that has been encrypted using the Caesar 
     * Cipher algorithm. Handles both uppercase and lowercase characters.
     *
     * @param   input   String representing phrase to encrypt
     * @param   key     int representing offset for Caesar Cipher
     * @return          String representing encrypted phrase
     */
    public String encrypt(String input, int key)
    {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shifted = alphabet.substring(key) + alphabet.substring(0);
        String SHIFTED = ALPHABET.substring(key) + ALPHABET.substring(0);
        for (int i = 0; i < encrypted.length(); i++) {
            char c = encrypted.charAt(i);
            if (Character.isUpperCase(c)) {
                int IDX = ALPHABET.indexOf(c);
                if (IDX != -1) {
                    encrypted.setCharAt(i, SHIFTED.charAt(IDX));
                }
            } else {                
                int idx = alphabet.indexOf(c);
                if (idx != -1) {
                    encrypted.setCharAt(i, shifted.charAt(idx));
                }
            }
        }
        return encrypted.toString();
    }
    
    /**
     * This method returns a String that has been encrypted using the Caesar 
     * Cipher algorithm. Handles both uppercase and lowercase characters.
     *
     * @param   input   String representing phrase to encrypt
     * @param   key     int representing first offset for Caesar Cipher
     * @param   key2    int representing second offset for Caesar Cipher
     * @return          String representing encrypted phrase
     */
    public String encryptTwoKeys(String input, int key1, int key2)
    {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shifted1 = alphabet.substring(key1) + alphabet.substring(0);
        String shifted2 = alphabet.substring(key2) + alphabet.substring(0);
        String SHIFTED1 = ALPHABET.substring(key1) + ALPHABET.substring(0);
        String SHIFTED2 = ALPHABET.substring(key2) + ALPHABET.substring(0);
        for (int i = 0; i < encrypted.length(); i++) {
            char c = encrypted.charAt(i);
            if (Character.isUpperCase(c)) {
                int IDX = ALPHABET.indexOf(c);
                if (IDX != -1) {
                    if (i%2 == 0) {
                        encrypted.setCharAt(i, SHIFTED1.charAt(IDX));                        
                    } else {
                        encrypted.setCharAt(i, SHIFTED2.charAt(IDX));
                    }
                }
            } else {                
                int idx = alphabet.indexOf(c);
                if (idx != -1) {
                    if (i%2 == 0) {
                        encrypted.setCharAt(i, shifted1.charAt(idx));
                    } else {
                        encrypted.setCharAt(i, shifted2.charAt(idx));                        
                    }
                }
            }
        }
        return encrypted.toString();
    }
    
    /**
     * Tests method encrypt().
     */
    public void testEncrypt() {
        System.out.println("\nTesting encrypt()");
        int testkey = 23;
        String teststr = "FIRST LEGION ATTACK EAST FLANK!";
        String result = encrypt(teststr, testkey);
        String expected = "CFOPQ IBDFLK XQQXZH BXPQ CIXKH!";       
        System.out.println("Test passed = " + (result.equals(expected)));
        
    }
    
    /**
     * Tests method encrypt() with content from files.
     */
    public void testCaesar() {
        System.out.println("\nTesting encrypt() with file content");
        FileResource fr = new FileResource("message1.txt");
        int key = 17;
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        String decrypted = encrypt(encrypted, 26-key);
        // System.out.println("key: " + key + "\nmessage: " + message
        // + "encrypted: " + encrypted + "decrypted: " + decrypted);   
        System.out.println("Test passed = " + (message.equals(decrypted)));
        
    }
    
    /**
     * Tests method encryptTwoKeys().
     */
    public void testEncryptTwoKeys() {
        System.out.println("\nTesting encryptTwoKeys()");
        int testkey1 = 23;
        int testkey2 = 17;
        String teststr = "First Legion";
        String result = encryptTwoKeys(teststr, testkey1, testkey2);
        String expected = "Czojq Ivdzle";       
        System.out.println("Test passed = " + (result.equals(expected)));
        
    }
    
    /**
     * Tests method encryptTwoKeys() with content from files.
     */
    public void testCaesarTwoKeys() {
        System.out.println("\nTesting encryptTwoKeys() with file content");
        FileResource fr = new FileResource("message1.txt");
        int key1 = 12;
        int key2 = 21;
        String message = fr.asString();
        String encrypted = encryptTwoKeys(message, key1, key2);
        String decrypted = encryptTwoKeys(encrypted, 26-key1, 26-key2);
        // System.out.println("key: " + key + "\nmessage: " + message
        // + "encrypted: " + encrypted + "decrypted: " + decrypted);   
        System.out.println("Test passed = " + (message.equals(decrypted)));
        
    }
    
    public void quiz() {
        System.out.println("\nAnswers to quiz questions");
        System.out.println("5)");
        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
        System.out.println("6)");
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));        
    }
    
    
    public static void main(String[] args){
        System.out.println("\n\n==== main ====");
        CaesarCipher cc = new CaesarCipher();
        
        // cc.testEncrypt();
        // cc.testCaesar();
        // cc.testEncryptTwoKeys();
        // cc.testCaesarTwoKeys();
        cc.quiz();
    }
}
