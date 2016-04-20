package ch.irju.telegram.bot.command;

public enum CommandType {
	// Get some help!
	HELP("/help"),
	
	// Too long didn't read!
	TLDR("/tldr"),
	
	// Some important URL
	URL("/url"),
	
	// Some important URL
	IRJU("/irju"),
	
	// Zello setup instructions
	ZELLO("/zello", "installe zello", "installer zello"),
	
	// Reswue setup instructions
	RESWUE("/reswue", "installe reswue", "installer reswue"),
	
	// Shuts the bot down
//	SHUTDOWN("/shutdown"),
	
	HELLO("salut", "hello", "hallo", "bonjour", "guten tag"),
	GOODBYE("bye", "au revoir", "tschüss", "ciao", "bonne nuit"),
	WHOCARES("lawson"),
	BURGER("burger"),
	NOSHIT("qui s'en fout"),
	PROFILE("profile"),
	OTHER();
	
	private final String[] tokens;
	
	private CommandType(String... theTokens) {
		tokens = theTokens;
	}
	
	public static CommandType getCommandType(String theMessage) {
		for (CommandType aCommandType : CommandType.values()) {
			for (String aToken : aCommandType.tokens) {
				if (theMessage.trim().startsWith("/") && theMessage.toLowerCase().startsWith(aToken)) {
					return aCommandType;
				} else if (theMessage.toLowerCase().indexOf(aToken) > -1) {
					return aCommandType;
				}
			}
		}
		
		return OTHER;
	}
}
