package ch.irju.telegram.bot.command;

import static ch.irju.telegram.bot.command.Emoji.FACE_SCREAMING_IN_FEAR;
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

//		for (String mention : getMentions()) {
//			return getForwardMessageRequest(
//				mention,
//				message.getChat().getId(),
//				message.getId());
////			return getSendMessageRequest(mention, 
//////				message.getContact().getUserId() + " vous a mention�!", null); 
////				"xxxxxxx vous a mention�!", null); 
//		}
	
		String aUsername = message.getFromUser().getUsername();
		Long aChatId = message.getChat().getId();
		
		switch (CommandType.getCommandType(aText)) {
			case TLDR:
				String aMessage = Tldr.processTldrCommand(message.getText(), aChatId);
				return getSendMessageRequest(aMessage, "Markdown"); 
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
			case SMALLDICK:
				return getSendMessageRequest(Messages.getWhoGotASmallDickMessage(), null);
//			case DICTATOR:
//				return getSendMessageRequest(Messages.getDictatorMessage(), null);
			case DREDRE:
				return getSendMessageRequest(Messages.getDredreMessage(), null);
			case HQ:
				return getSendMessageRequest(Messages.getHeadquartersMessage(), null);
			case BOULET:
				return getSendMessageRequest(Messages.getBouletMessage(), null);
			case IRJUBOT:
				return getSendMessageRequest(Messages.getIrjuBotMessage(), null);
			case ROPLOPLO:
				return getSendMessageRequest(Messages.getRoploploMessage(), null);
			case DICTIONARY:
				return getSendMessageRequest(Messages.getCorrectedMessage(aText, aUsername), "Markdown");
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

	private TelegramRequest getForwardMessageRequest(String theUsername, long theFromChatId, long theMessageId) {
		String aUsername = theUsername.startsWith("@") ? theUsername : "@" + theUsername;
		System.out.println(aUsername);
		return TelegramRequestFactory.createForwardMessageRequest(
			aUsername, 
			theFromChatId, 
			theMessageId);
	}
	
//	private TelegramRequest getSendMessageRequest(String theUsername, String theMessage, String theParseMode) throws JsonParsingException {
//		return TelegramRequestFactory.createSendMessageRequest(
//			theUsername, 
//			theMessage, 
//			true, 
//			message.getId(), // avec le message d'origine 
//			null,
//			theParseMode);
//	}

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
