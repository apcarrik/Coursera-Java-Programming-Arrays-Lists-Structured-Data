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
     * Cipher algorithm. Assume that all the alphabetic characters are 
     * uppercase letters.
     *
     * @param   input   String representing phrase to encrypt
     * @param   key     int representing offset for Caesar Cipher
     * @return          String representing encrypted phrase
     */
    public String encrypt(String input, int key)
    {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shifted = alphabet.substring(key) + alphabet.substring(0);
        for (int i = 0; i < encrypted.length(); i++) {
            char c = encrypted.charAt(i);
            int idx = alphabet.indexOf(c);
            if (idx != -1) {
                encrypted.setCharAt(i, shifted.charAt(idx));
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
    
    public static void main(String[] args){
        System.out.println("\n\n==== main ====");
        CaesarCipher cc = new CaesarCipher();
        
        cc.testEncrypt();
        cc.testCaesar();
    }
}
