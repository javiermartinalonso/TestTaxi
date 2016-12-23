package com.testtaxi.crypto;

import com.Ostermiller.util.RandPass;

public class Password 
{
	/**
	 * Genera una cadena aleatoria de 8 caracteres que puede ser util para generar password u otros menesteres 
	 * @return
	 */
	public static String generarPassword(int numChars)
	{
		return new RandPass().getPass(numChars);
	}
	
	//TODO sin probar
    public int getIntAleatorio(int base)
    {
        return (int)(Math.random()*base*1.0);    
    }
}