/**
 * Created by Hans Bjerkevoll
 * https://github.com/hansbjerkevoll
 */

import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	
	private byte[] key_1;
	private byte[] key_2;
	private byte[] key_3;
	
	/**
	 * Constructor for AES object
	 * 
	 * @param a byte array of the desired key
	 * 			must be excactly 48 bytes
	 */
	public AES(byte[] key) {
		if(key.length != 48) {
			throw new IllegalArgumentException("Key length must be 48 bytes for triple 16 bytes AES");
		}
		this.key_1 = Arrays.copyOfRange(key, 0, 16);
		this.key_2 = Arrays.copyOfRange(key, 16, 32);
		this.key_3 = Arrays.copyOfRange(key, 32, 48);
	}
	
	/**
	 * Encrypt the given plaintext using triple AES
	 * 
	 * @param chosen plaintext string
	 * @return the encrypted ciphertext
	 */
	public byte[] triple_AES_encrypt(byte[] plaintext) {
		byte[] ciphertext = encrypt(plaintext, key_1);
		ciphertext = encrypt(ciphertext, key_2);
		ciphertext = encrypt(ciphertext, key_3);
		return ciphertext;
	}
	
	/**
	 * Decrypt the given plaintext using triple AES
	 * 
	 * @param chosen ciphertext string
	 * @return the decrypted plaintext
	 */
	public byte[] triple_AES_decrypt(byte[] ciphertext) {
		byte[] plaintext = decrypt(ciphertext, key_3);
		plaintext = decrypt(plaintext, key_2);
		plaintext = decrypt(plaintext, key_1);
		return plaintext;
	}
	
	/**
	 * Encrypt the given plaintext using regular AES
	 * 
	 * @param chosen plaintext string
	 * @return the encrypted ciphertext
	 * @throws GeneralSecurityException
	 */
	private byte[] encrypt(byte[] plaintext, byte[] key) throws GeneralSecurityException {
		SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		return cipher.doFinal(plaintext);
	}
	
	/**
	 * Decrypt the given plaintext using regular AES
	 * 
	 * @param chosen ciphertext string
	 * @return the decrypted plaintext
	 * @throws GeneralSecurityException
	 */
	private byte[] decrypt(byte[] ciphertext, byte[] key) throws GeneralSecurityException {
		SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		return cipher.doFinal(ciphertext);
	}
}