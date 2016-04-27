package ch.irju.telegram.bot.command;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles TLDR commands.
 * 
 * @author poco
 */
final class Tldr {
	private static final String ADD = "add";
	private static final String REMOVE = "remove";
	private static final String DIR = "." + File.separator + "tldr";
	
	static String processTldrCommand(String theMessage, Long theChatId) {
		if ("/tldr".equals(theMessage.toLowerCase().trim())) {
			return listTldr(theChatId);
		}
		
		String[] aSplittedMessage = theMessage.split(" ");
		
		if (aSplittedMessage.length > 1 && ADD.equals(aSplittedMessage[1].toLowerCase().trim())) {
			return addTldr(theMessage.substring(theMessage.indexOf(ADD) + ADD.length() + 1), theChatId);
		} else if (aSplittedMessage.length > 1 && REMOVE.equals(aSplittedMessage[1].toLowerCase().trim())) {
			if (aSplittedMessage.length > 2 && isAnInteger(aSplittedMessage[2])) {
				int aLineNumber = Integer.parseInt(aSplittedMessage[2]);
				return removeTldr(theMessage, aLineNumber, theChatId);
			} else {
				return unknownTldr(theMessage); 
			}
		} else {
			return unknownTldr(theMessage);
		}
	}
	
	private static boolean isAnInteger(String theToken) {
		try {
			return theToken != null && theToken.length() > 0 && Integer.parseInt(theToken) > 0;
		} catch (NumberFormatException anException) {
			return false;
		}	
	}

	private static String listTldr(Long theChatId) {
		File aFile = getFile(theChatId);
		
		if (!aFile.exists()) {
			return "*TLDR* - ERR: Le TLDR est vide!";
		} else {
			StringBuilder aStringBuilder = new StringBuilder();
			aStringBuilder.append("*TLDR*\n\n");

			try {
				BufferedReader aReader = new BufferedReader(new FileReader(aFile));
				String aLine = null;
				int aCounter = 1;
				
				while ((aLine = aReader.readLine()) != null) {
					aStringBuilder.append(aCounter++ + ": " + aLine + "\n");
				}
				
				aReader.close();
			} catch (IOException anException) {
				return "*TLDR* - ERR: " + anException.getMessage();
			}
			
			return aStringBuilder.toString();
		}
	}

	private static String addTldr(String theMessage, Long theChatId) {
		File aFile = getFile(theChatId);
		
		try {
			BufferedWriter aWriter = new BufferedWriter(new FileWriter(aFile, true));
			aWriter.append(theMessage + "\n");
			aWriter.flush();
			aWriter.close();
		} catch (IOException anException) {
			return "*TLDR* - ERR: " + anException.getMessage();
		}
		
		return "*TLDR* - Message ajouté au TLDR!";
	}

	private static String removeTldr(String theMessage, int theLineToDelete, Long theChatId) {
		File aFile = getFile(theChatId);
		StringBuilder aContent = new StringBuilder();

		try {
			BufferedReader aReader = new BufferedReader(new FileReader(aFile));
			String aLine = null;
			int aCounter = 1;
			
			while ((aLine = aReader.readLine()) != null) {
				if (theLineToDelete != aCounter++) {
					aContent.append(aLine + "\n");
				}
			}
			
			aReader.close();
			
			FileWriter aWriter = new FileWriter(aFile);
			aWriter.write(aContent.toString());
			aWriter.flush();
			aWriter.close();
		} catch (IOException anException) {
			return "*TLDR* - ERR: " + anException.getMessage();
		}

		return "*TLDR* - Ligne " + theLineToDelete + " effacée du TLDR!";
	}

	private static String unknownTldr(String theMessage) {
		return "*TLDR* - ERR: commande inconnue";
	}
	
	private static File getFile(Long theChatId) {
		File aDir = new File(DIR);

		if (!aDir.exists()) {
			aDir.mkdir();
		}
	
		return new File(aDir, "messages." + theChatId + ".tldr");
	}
}
