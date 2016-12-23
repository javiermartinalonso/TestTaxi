package com.testtaxi.crypto;



public class Crypto_test 
{
	public static void main(String[] args) throws Exception   
	{		
		//new Log4J();
		
		//String pass = "Passw0rd0123456789";
	
		
		System.out.println("\n*****************************************");		
		System.out.println("Password.generarPassword(int numChars)");
		String passAleatoria = Password.generarPassword(10);
		System.out.println("cadena generada aleatoriamente: " + passAleatoria);
		System.out.println("Cadena a encriptar:\n" + passAleatoria);
		
		
		System.out.println("*****************************************");		
		System.out.println("Encriptador.encriptar(pass)");
		String encriptado = Encriptador.encriptar("Passw0rd2014");
		System.out.println("cadena encriptada: " + encriptado);
		
		System.out.println("\n*****************************************");		
		System.out.println("Encriptador.desencriptar(pass)");
		String desencriptado = Encriptador.desencriptar("c0nwDItlrcL0ki9G5b2b5XBz4LXixjox0PXaJM33Je8=");		
		System.out.println("cadena desencriptada: " + desencriptado);


		
	}
}