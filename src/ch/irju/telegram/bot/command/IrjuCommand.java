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

		for (String mention : getMentions()) {
			System.out.println(">>> " + mention);
			return getSendMessageRequest(mention, 
//				message.getContact().getUserId() + " vous a mentioné!", null); 
				"xxxxxxx vous a mentioné!", null); 
		}
		
		switch (CommandType.getCommandType(aText)) {
			case GOODBYE:
				return getSendMessageRequest("Au revoir *" + message.getFromUser().getUsername() + "*!", "Markdown"); 
			case HELLO:
				return getSendMessageRequest("Bonjour *" + message.getFromUser().getUsername() + "*!", "Markdown");
			case URL:
				return getSendMessageRequest("http://www.irju.ch/", null);
			case BURGER:
				return getSendMessageRequest("Burger? Quelqu'un a dit burger? " 
					+ BURGER + BURGER + BURGER 
					+ " " + SMILING_FACE_WITH_HEART_SHAPED_EYES, null);
			case ZELLO:
				return getSendMessageRequest(getZelloSetupInstructions(), "Markdown");
			case RESWUE:
				return getSendMessageRequest(getReswueSetupInstructions(), "Markdown");
			case HELP:
				return getSendMessageRequest(getHelp(), "Markdown");
			case WHOCARES:
				return getSendMessageRequest("On s'en bat les steaks! " +
					FACE_WITH_TEARS_OF_JOY + 
					FACE_WITH_TEARS_OF_JOY +
					FACE_WITH_TEARS_OF_JOY, null);
			case NOSHIT:
				return getSendMessageRequest(
					RAISED_HAND + " " + 
					RAISED_HAND + " " + 
					RAISED_HAND + " " + 
					RAISED_HAND,
					null);
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

	private String getHelp() {
		StringBuilder aText = new StringBuilder();
		aText.append("Bonjour, je suis le bot IRJU! Je suis là pour vous aider et rendre votre vie plus bleue! " 
			+ GRINNING_FACE_WITH_SMILING_EYES + "\n\n");
		aText.append("Voici la liste des commandes:\n\n");
		aText.append("[/help](tg://bot_command?command=help&bot=irju) - Liste des commandes\n");
		aText.append("[/zello](tg://bot_command?command=zello&bot=irju) - Instructions Zello\n");
		aText.append("[/reswue](tg://bot_command?command=reswue&bot=irju) - Instructions Reswue\n\n");
		aText.append("Je contiens plein d'autres fonctionnalités cachées... "
			+ "à vous de les découvrir! " + SMIRKING_FACE);
		return aText.toString();
	}

	private String getZelloSetupInstructions() {
		StringBuilder aText = new StringBuilder();
		aText.append("*Setup Zello*\n\n");
		aText.append("1) Télécharger l'application ");
		aText.append("pour [Android](https://play.google.com/store/apps/details?id=com.loudtalks&hl=fr) ");
		aText.append("ou pour [iPhone](https://itunes.apple.com/us/app/zello-walkie-talkie/id508231856?mt=8)\n");
		aText.append("2) Créer un compte\n\n");
		aText.append("C'est tout! " + GRINNING_FACE_WITH_SMILING_EYES + "\n\n");
		aText.append("Les utilisateurs Android peuvent également télécharger [Zello Overlay]"
				+ "(https://play.google.com/store/apps/details?id=com.zachbarrett.zellooverlay&hl=fr) "
				+ "afin de pouvoir actionner Zello depuis n'importe quelle application!");
		return aText.toString();
	}
	
	private String getReswueSetupInstructions() {
		StringBuilder aText = new StringBuilder();
		aText.append("*Setup Reswue*\n\n");
		aText.append("Reswue est l'outil utilisé par la Résistance et seulement par la Résistance pour planifier "
				+ "et notifier les agents lors d'opérations. Ce tool est confidentiel, n'en parlez donc jamais "
				+ "aux joueurs verts!\n\n");
		aText.append("Il vous faut obtenir une autorisation pour utiliser Reswue. Commencez par vous connecter "
				+ "à [Reswue Europe](https://ops.irde.net/iitc/reswue/my.php) et connectez-vous grâce à votre "
				+ "compte Google+. Puis envoyez un message à [p0c07o](https://web.telegram.org/#/im?p=@p0c07o) "
				+ "afin qu'il vous valide!\n\n");
		aText.append("Il existe 3 moyens d'utiliser Reswue:\n\n");
		aText.append("1) Via l'[interface Web](https://ops.irde.net/iitc/reswue/my.php)\n");
		aText.append("2) Via le client mobile pour [Android]"
				+ "(https://play.google.com/store/apps/details?id=net.irde.ops.agentclient) "
				+ "ou pour [iPhone](https://itunes.apple.com/us/app/reswue-client/id971201245?l=de&ls=1&mt=8)\n");
		aText.append("3) Via le [plugin IITC](https://ops.irde.net/iitc/reswue.user.js﻿)");
		return aText.toString();
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
