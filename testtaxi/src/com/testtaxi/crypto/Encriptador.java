package com.testtaxi.crypto;

import java.io.UnsupportedEncodingException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.log4j.Logger;

import com.sun.xml.ws.util.UtilException;

//@SuppressWarnings("restriction")
public final class Encriptador 
{
	Cipher ecipher;
	Cipher dcipher;
	
	//SEGUIMIENTO LOG
	private static Logger log = Logger.getLogger(Encriptador.class);

	private Encriptador() throws UtilException 
	{			
		String chiave = "KeyPassw0rd";

		java.security.Security.addProvider(new com.sun.crypto.provider.SunJCE());

		try 
		{
			DESKeySpec desKeySpec = new DESKeySpec(chiave.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(desKeySpec);
			ecipher = Cipher.getInstance("DES");
			dcipher = Cipher.getInstance("DES");
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			dcipher.init(Cipher.DECRYPT_MODE, key);
		} 
		catch (javax.crypto.NoSuchPaddingException e) 
		{
			log.error("ERROR : Se ha producido un error",e);			
			throw new UtilException("ERROR : Se ha producido un error",Encriptador.class,"Encriptador",e);			
		} 
		catch (java.security.NoSuchAlgorithmException e) 
		{
			log.error("ERROR : Se ha producido un error",e);			
			throw new UtilException("ERROR : Se ha producido un error",Encriptador.class,"Encriptador",e);			
		} 
		catch (java.security.InvalidKeyException e) 
		{
			log.error("ERROR : Se ha producido un error",e);			
			throw new UtilException("ERROR : Se ha producido un error",Encriptador.class,"Encriptador",e);			
		} 
		catch (InvalidKeySpecException e) 
		{
			log.error("ERROR : Se ha producido un error",e);			
			throw new UtilException("ERROR : Se ha producido un error",Encriptador.class,"Encriptador",e);
		}
	}

	
	/**
	 * Encripta una cadena
	 * @param str cadena a encriptar
	 * @return cadena encriptada
	 * @throws UtilException 
	 */
	private String encrypt(String str) throws UtilException 
	{
		try 
		{
			// Encode the string into bytes using utf-8
			byte[] utf8 = str.getBytes("UTF8");

			// Encrypt
			byte[] enc = ecipher.doFinal(utf8);

			// Encode bytes to base64 to get a string
			return new sun.misc.BASE64Encoder().encode(enc);
		} 
		catch (javax.crypto.BadPaddingException e) 
		{
			log.error("ERROR : Se ha producido un error",e);			
			throw new UtilException("ERROR : Se ha producido un error",Encriptador.class,"encrypt(String str)",e);
		} 
		catch (IllegalBlockSizeException e) 
		{
			log.error("ERROR : Se ha producido un error",e);			
			throw new UtilException("ERROR : Se ha producido un error",Encriptador.class,"encrypt(String str)",e);			
		} 
		catch (UnsupportedEncodingException e) 
		{
			log.error("ERROR : Se ha producido un error",e);			
			throw new UtilException("ERROR : Se ha producido un error",Encriptador.class,"encrypt(String str)",e);			
		}
	}
	
	
	/**
	 * Desencripta una cadena
	 * @param cadena cadena a desencriptar
	 * @return cadena desencriptada
	 * @throws UtilException 
	 */
	private String decrypt(String cadena) throws UtilException 
	{
		try 
		{
			// Decode base64 to get bytes
			byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(cadena);

			// Decrypt
			byte[] utf8 = dcipher.doFinal(dec);

			// Decode using utf-8
			return new String(utf8, "UTF8");
		}
		catch (javax.crypto.BadPaddingException e) 
		{
			log.error("ERROR : Se ha producido un error desencriptando la cadena: '"+cadena+ "'.\nASEGURATE QUE LA CADENA EST� ENCRIPTADA.",e);			
			throw new UtilException("ERROR : Se ha producido un error desencriptando la cadena: '"+cadena+ "'.\nASEGURATE QUE LA CADENA EST� ENCRIPTADA.",Encriptador.class,"decrypt(String cadena)",e);			
		}
		catch (IllegalBlockSizeException e) 
		{
			log.error("ERROR : Se ha producido un error desencriptando la cadena: '"+cadena+ "'.\nASEGURATE QUE LA CADENA EST� ENCRIPTADA.",e);			
			throw new UtilException("ERROR : Se ha producido un error desencriptando la cadena: '"+cadena+ "'.\nASEGURATE QUE LA CADENA EST� ENCRIPTADA.",Encriptador.class,"decrypt(String cadena)",e);			
		} 
		catch (UnsupportedEncodingException e) 
		{
			log.error("ERROR : Se ha producido un error desencriptando la cadena: '"+cadena+ "'.\nASEGURATE QUE LA CADENA EST� ENCRIPTADA.",e);			
			throw new UtilException("ERROR : Se ha producido un error desencriptando la cadena: '"+cadena+ "'.\nASEGURATE QUE LA CADENA EST� ENCRIPTADA.",Encriptador.class,"decrypt(String cadena)",e);						
		} 
		catch (java.io.IOException e) 
		{
			log.error("ERROR : Se ha producido un error desencriptando la cadena: '"+cadena+ "'.\nASEGURATE QUE LA CADENA EST� ENCRIPTADA.",e);			
			throw new UtilException("ERROR : Se ha producido un error desencriptando la cadena: '"+cadena+ "'.\nASEGURATE QUE LA CADENA EST� ENCRIPTADA.",Encriptador.class,"decrypt(String cadena)",e);						
		}
	}
	
	
	/**
	 * Metodo para encriptar cadenas
	 * @throws UtilException 
	 */
	public static String encriptar(String cadena) throws UtilException 
	{
		String encrypted = "";

		try 
		{
			java.security.Security.addProvider(new com.sun.crypto.provider.SunJCE());

			Encriptador encrypter = new Encriptador();
			encrypted = encrypter.encrypt(cadena);
		} 
		catch (Exception e) 
		{
			log.error("ERROR : Se ha producido un error encriptando la cadena: '"+cadena+ "'",e);			
			throw new UtilException("ERROR : Se ha producido un error encriptando la cadena: '"+cadena+ "'",Encriptador.class,"encriptar(String cadena)",e);			
		}
		
		return encrypted;		
	}

	
	/**
	 * Metodo para desencriptar cadenas 
	 * @throws UtilException 
	 */
	public static String desencriptar(String cadena) throws UtilException
	{
		/*con el metodo anterior es identico al de cifrar, solo que en
		 * el momento de descifrar solicitamos el numero de espacios que la cadena habia sido dezplazada
		 * con anterioridad y volvemos negativo
		 */
		String decrypted="";

		try 
		{
			java.security.Security.addProvider(new com.sun.crypto.provider.SunJCE());

			Encriptador encrypter = new Encriptador();
			decrypted = encrypter.decrypt(cadena);
		}
		catch (Exception e) 
		{
			log.error("ERROR : Se ha producido un error desencriptando la cadena: '"+cadena+ "'.\nASEGURATE QUE LA CADENA EST� ENCRIPTADA.",e);			
			throw new UtilException("ERROR : Se ha producido un error desencriptando la cadena: '"+cadena+ "'.\nASEGURATE QUE LA CADENA EST� ENCRIPTADA.",Encriptador.class,"desencriptar(String cadena)",e);			
		}
		
		return decrypted;
	}
}
