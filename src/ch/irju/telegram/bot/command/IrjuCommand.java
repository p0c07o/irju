package ch.irju.telegram.bot.command;

import static ch.irju.telegram.bot.command.Emoji.BURGER;
import static ch.irju.telegram.bot.command.Emoji.FACE_SCREAMING_IN_FEAR;
import static ch.irju.telegram.bot.command.Emoji.FACE_WITH_TEARS_OF_JOY;
import static ch.irju.telegram.bot.command.Emoji.GRINNING_FACE_WITH_SMILING_EYES;
import static ch.irju.telegram.bot.command.Emoji.RAISED_HAND;
import static ch.irju.telegram.bot.command.Emoji.SMILING_FACE_WITH_HEART_SHAPED_EYES;
import static ch.irju.telegram.bot.command.Emoji.SMIRKING_FACE;
import io.github.nixtabyte.telegram.jtelebot.client.RequestHandler;
import io.github.nixtabyte.telegram.jtelebot.exception.JsonParsingException;
import io.github.nixtabyte.telegram.jtelebot.exception.TelegramServerException;
import io.github.nixtabyte.telegram.jtelebot.request.TelegramRequest;
import io.github.nixtabyte.telegram.jtelebot.request.factory.TelegramRequestFactory;
import io.github.nixtabyte.telegram.jtelebot.response.json.Message;
import io.github.nixtabyte.telegram.jtelebot.response.json.MessageEntity;
import io.github.nixtabyte.telegram.jtelebot.server.impl.AbstractCommand;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class IrjuCommand extends AbstractCommand {
	public IrjuCommand(Message theMessage, RequestHandler theRequestHandler) {
		super(theMessage, theRequestHandler);
	}
	
	@Override
	public void execute() {
		try {
			TelegramRequest aTelegramRequest = getTelegramRequest();
			
			if (aTelegramRequest != null) {
				requestHandler.sendRequest(aTelegramRequest);
			}
		} catch (JsonParsingException | TelegramServerException anException) {
			try {
				requestHandler.sendRequest(
					getSendMessageRequest("ERR: " + anException.getMessage() + " "
						+ FACE_SCREAMING_IN_FEAR + FACE_SCREAMING_IN_FEAR + FACE_SCREAMING_IN_FEAR, null));
			} catch (JsonParsingException | TelegramServerException anException2) {
				anException2.printStackTrace();
			}
		}
	}
	
	private TelegramRequest getTelegramRequest() throws JsonParsingException {
		if (message.getText() == null) {
			return null;
		}
		
		String aText = message.getText().toLowerCase();
/*
		for (String mention : getMentions()) {
			System.out.println(">>> " + mention);
			return getSendMessageRequest(mention, 
//				message.getContact().getUserId() + " vous a mentioné!", null); 
				"xxxxxxx vous a mentioné!", null); 
		}
*/		
		String aUsername = message.getFromUser().getUsername();
		
		switch (CommandType.getCommandType(aText)) {
			case GOODBYE:
				return getSendMessageRequest(Messages.getGoodByeMessage(aUsername), "Markdown"); 
			case HELLO:
				return getSendMessageRequest(Messages.getHelloMessage(aUsername), "Markdown");
			case URL:
				return getSendMessageRequest(Messages.getUrlMessage(), "Markdown");
			case IRJU:
				return getSendMessageRequest(Messages.getIrjuMessage(), "Markdown");
			case BURGER:
				return getSendMessageRequest(Messages.getBurgerMessage(), null);
			case ZELLO:
				return getSendMessageRequest(Messages.getZelloMessage(), "Markdown");
			case RESWUE:
				return getSendMessageRequest(Messages.getReswueMessage(), "Markdown");
			case HELP:
				return getSendMessageRequest(Messages.getHelpMessage(), "Markdown");
			case WHOCARES:
				return getSendMessageRequest(Messages.getWhoCaresMessages(), null);
			case NOSHIT:
				return getSendMessageRequest(Messages.getNoShitMessage(), null);
			case PROFILE:
				return getSendPhotoRequest();
			case TLDR:
				return null;
//			case SHUTDOWN:
//				System.exit(0);
//				return null;
			case OTHER:
			default:
				return null;
		}
	}

	private List<String> getMentions() {
		List<String> list = new ArrayList<>();
		
		for (MessageEntity messageEntity : message.getEntities()) {
			if (messageEntity.getType().equals("mention")) {
				list.add(message.getText().substring(
					messageEntity.getOffset(), messageEntity.getOffset() + messageEntity.getLength()));
			}
		}
		
		return list;
	}

	private TelegramRequest getSendPhotoRequest() throws JsonParsingException {
		return TelegramRequestFactory.createSendPhotoRequest(
			message.getChat().getId(), 
			new File(IrjuCommand.class.getResource("irju.png").getFile()), 
			"IRJU", 
			null, 
			null);
	}

	private TelegramRequest getSendMessageRequest(String theUsername, String theMessage, String theParseMode) throws JsonParsingException {
		return TelegramRequestFactory.createSendMessageRequest(
			theUsername, 
			theMessage, 
			true, 
			message.getId(), // avec le message d'origine 
			null,
			theParseMode);
	}

	private TelegramRequest getSendMessageRequest(String theMessage, String theParseMode) throws JsonParsingException {
		return TelegramRequestFactory.createSendMessageRequest(
			message.getChat().getId(), 
			theMessage, 
			true, 
			null, // message.getId(), // avec le message d'origine 
			null,
			theParseMode);
	}
}
