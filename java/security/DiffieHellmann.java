/**
 * Created by Hans Bjerkevoll
 * https://github.com/hansbjerkevoll
 */

import java.security.*;
import java.security.spec.*;
import javax.crypto.*;

public class DiffieHellman {
	
	/**
	 * Create DH key pair with 2048-bit key size
	 * 
	 * @return a 512 bit public/private key pair
	 * @throws NoSuchAlgorithmException
	 *         If the KeyPairGenerator method fails to generate a key pair
	 */
	public KeyPair generateKeyPair() throws NoSuchAlgorithmException {
		KeyPairGenerator keypairgen = KeyPairGenerator.getInstance("DH"); 
		keypairgen.initialize(512);
		KeyPair keypair = keypairgen.generateKeyPair();
		return keypair; 		
	}
	
	/**
	 * Create and initialize DH KeyAgreement object
	 * 
	 * @param the KeyPair object
	 * @return an initialized key agreement
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException
	 */
	public KeyAgreement generateKeyAgreement(KeyPair keypair) throws NoSuchAlgorithmException, InvalidKeyException {
		KeyAgreement keyagree = null;
		keyagree = KeyAgreement.getInstance("DH");
		keyagree.init(keypair.getPrivate());	
		return keyagree;			
	}
	
	/**
	 * Instantiate a DH public key from the encoded key material
	 * 
	 * @param an encoded key as byte array
	 * @return a DH public key
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException
	 */
	public PublicKey getPublicKey(byte[] encoded_key) throws NoSuchAlgorithmException, InvalidKeyException {	
		KeyFactory keyfac = KeyFactory.getInstance("DH");
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(encoded_key);
		PublicKey publickey = keyfac.generatePublic(x509KeySpec); 
		return publickey;		
	}
}