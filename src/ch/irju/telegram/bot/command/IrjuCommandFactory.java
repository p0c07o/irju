package ch.irju.telegram.bot.command;

import java.util.logging.Logger;

import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;
import io.github.nixtabyte.telegram.jtelebot.server.Command;
import io.github.nixtabyte.telegram.jtelebot.server.CommandFactory;

public class IrjuCommandFactory implements CommandFactory {
	private static final Logger LOG = Logger.getLogger(IrjuCommandFactory.class.getName());
	
	@Override
	public Command createCommand(Message theMessage, RequestHandler theRequestHandler) {
		LOG.info(theMessage.toString());
		return new IrjuCommand(theMessage, theRequestHandler);
	}
}
