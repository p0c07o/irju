package ch.irju.telegram.bot.command;

public enum CommandType {
	HELLO("salut", "hello", "hallo", "bonjour", "guten tag"),
	GOODBYE("bye", "au revoir", "tschüss", "ciao", "bonne nuit"),
	
	// Get some help!
	HELP("/help"),
	
	// Some important URL
	URL("/url"),
	
	// Some important URL
	IRJU("/irju"),
	
	// Zello setup instructions
	ZELLO("/zello", "installe zello", "installer zello"),
	
	// Reswue setup instructions
	RESWUE("/reswue", "installe reswue", "installer reswue"),
	
	// Shuts the bot down
	SHUTDOWN("/shutdown"),
	
	WHOCARES("lawson"),
	BURGER("burger"),
	NOSHIT("qui s'en fout"),
	TLDR("tldr"),
	PROFILE("profile"),
	OTHER();
	
	private final String[] tokens;
	
	private CommandType(String... theTokens) {
		tokens = theTokens;
	}
	
	public static CommandType getCommandType(String theCommand) {
		for (CommandType aCommandType : CommandType.values()) {
			for (String aToken : aCommandType.tokens) {
//				if (theCommand.toLowerCase().startsWith(aToken)) {
				if (theCommand.toLowerCase().indexOf(aToken) > -1) {
					return aCommandType;
				}
			}
		}
		
		return OTHER;
	}
}
