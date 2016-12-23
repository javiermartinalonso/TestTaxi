package com.testtaxi.crypto;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.log4j.Logger;

//@SuppressWarnings("restriction")
public final class Encriptador 
{
	Cipher ecipher;
	Cipher dcipher;
	
	//SEGUIMIENTO LOG
	private static Logger log = Logger.getLogger(Encriptador.class);

	private Encriptador()
	{			
		String chiave = "KeyPassw0rd";

		java.security.Security.addProvider(new com.sun.crypto.provider.SunJCE());


			try {
				DESKeySpec desKeySpec = new DESKeySpec(chiave.getBytes());
				SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
				SecretKey key = keyFactory.generateSecret(desKeySpec);
				ecipher = Cipher.getInstance("DES");
				dcipher = Cipher.getInstance("DES");
				ecipher.init(Cipher.ENCRYPT_MODE, key);
				dcipher.init(Cipher.DECRYPT_MODE, key);
			} 
			catch (Exception e) 
			{
				// TODO: handle exception
			}

		
	}

	
	/**
	 * Encripta una cadena
	 * @param str cadena a encriptar
	 * @return cadena encriptada
	 * @throws UtilException 
	 */
	private String encrypt(String str)
	{

			try {
				// Encode the string into bytes using utf-8
				byte[] utf8 = str.getBytes("UTF8");
				// Encrypt
				byte[] enc = ecipher.doFinal(utf8);
				// Encode bytes to base64 to get a string
				return new sun.misc.BASE64Encoder().encode(enc);
			} catch (Exception e) {
				// TODO: handle exception
			}
			return str;

		
	}
	
	
	/**
	 * Desencripta una cadena
	 * @param cadena cadena a desencriptar
	 * @return cadena desencriptada
	 * @throws UtilException 
	 */
	private String decrypt(String cadena)
	{

			try {
				// Decode base64 to get bytes
				byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(cadena);

				// Decrypt
				byte[] utf8 = dcipher.doFinal(dec);

				// Decode using utf-8
				return new String(utf8, "UTF8");
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return cadena;
		
	}
	
	
	/**
	 * Metodo para encriptar cadenas
	 * @throws UtilException 
	 */
	public static String encriptar(String cadena)
	{
		String encrypted = "";


			java.security.Security.addProvider(new com.sun.crypto.provider.SunJCE());

			Encriptador encrypter = new Encriptador();
			encrypted = encrypter.encrypt(cadena);

		
		return encrypted;		
	}

	
	/**
	 * Metodo para desencriptar cadenas 
	 * @throws UtilException 
	 */
	public static String desencriptar(String cadena)
	{
		/*con el metodo anterior es identico al de cifrar, solo que en
		 * el momento de descifrar solicitamos el numero de espacios que la cadena habia sido dezplazada
		 * con anterioridad y volvemos negativo
		 */
		String decrypted="";


			java.security.Security.addProvider(new com.sun.crypto.provider.SunJCE());

			Encriptador encrypter = new Encriptador();
			decrypted = encrypter.decrypt(cadena);

		
		return decrypted;
	}
}