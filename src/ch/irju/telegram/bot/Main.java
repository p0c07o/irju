package ch.irju.telegram.bot;

import ch.irju.telegram.bot.command.IrjuCommandFactory;
import io.github.nixtabyte.telegram.jtelebot.server.impl.DefaultCommandDispatcher;
import io.github.nixtabyte.telegram.jtelebot.server.impl.DefaultCommandQueue;
import io.github.nixtabyte.telegram.jtelebot.server.impl.DefaultCommandWatcher;

public class Main {
	public static void main(String[] args) {
		DefaultCommandDispatcher aCommandDispatcher = new DefaultCommandDispatcher(
			10, 100, 10, new DefaultCommandQueue());
		aCommandDispatcher.startUp();
		
		DefaultCommandWatcher aCommandWatcher = new DefaultCommandWatcher(
			10, 
			100, 
			"183024294:AAHuN89b2c6cgLk9nURseazXeuiQD5QX9ec", 
			aCommandDispatcher, 
			new IrjuCommandFactory());
		aCommandWatcher.startUp();
	}
}
