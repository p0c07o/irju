package ch.irju.telegram.bot.command;

import static ch.irju.telegram.bot.command.Emoji.BURGER;
import static ch.irju.telegram.bot.command.Emoji.FACE_WITH_TEARS_OF_JOY;
import static ch.irju.telegram.bot.command.Emoji.GRINNING_FACE_WITH_SMILING_EYES;
import static ch.irju.telegram.bot.command.Emoji.RAISED_HAND;
import static ch.irju.telegram.bot.command.Emoji.SMILING_FACE_WITH_HEART_SHAPED_EYES;
import static ch.irju.telegram.bot.command.Emoji.SMIRKING_FACE;

final class Messages {
	private Messages() {
		// Hidden constructor.
	}
	
	static String getHelpMessage() {
		StringBuilder aText = new StringBuilder();
		aText.append("Bonjour, je suis le bot IRJU! Je suis l� pour vous aider et rendre votre vie plus bleue! " 
			+ GRINNING_FACE_WITH_SMILING_EYES + "\n\n");
		aText.append("Voici la liste des commandes:\n\n");
		aText.append("[/help](tg://bot_command?command=help&bot=irju) - Liste des commandes\n");
		aText.append("[/zello](tg://bot_command?command=zello&bot=irju) - Instructions Zello\n");
		aText.append("[/reswue](tg://bot_command?command=reswue&bot=irju) - Instructions Reswue\n");
		aText.append("[/irju](tg://bot_command?command=irju&bot=irju) - Infos IRJU\n");
		aText.append("[/url](tg://bot_command?command=url&bot=irju) - Adresses importantes\n");
		aText.append("[/tldr](tg://bot_command?command=tldr&bot=irju) - Liste le contenu du TLDR\n");
		aText.append("/tldr add Xxxxx - Ajoute le message Xxxxx � la fin du TLDR\n");
		aText.append("/tldr remove n - Efface le message num�ro n du TLDR\n\n");
		aText.append("Je contiens plein d'autres fonctionnalit�s cach�es... "
			+ "� vous de les d�couvrir! " + SMIRKING_FACE);
		return aText.toString();
	}

	static String getZelloMessage() {
		StringBuilder aText = new StringBuilder();
		aText.append("*Setup Zello*\n\n");
		aText.append("1) T�l�charger l'application ");
		aText.append("pour [Android](https://play.google.com/store/apps/details?id=com.loudtalks&hl=fr) ");
		aText.append("ou pour [iPhone](https://itunes.apple.com/us/app/zello-walkie-talkie/id508231856?mt=8)\n");
		aText.append("2) Cr�er un compte\n\n");
		aText.append("C'est tout! " + GRINNING_FACE_WITH_SMILING_EYES + "\n\n");
		aText.append("Les utilisateurs Android peuvent �galement t�l�charger [Zello Overlay]"
				+ "(https://play.google.com/store/apps/details?id=com.zachbarrett.zellooverlay&hl=fr) "
				+ "afin de pouvoir actionner Zello depuis n'importe quelle application!");
		return aText.toString();
	}
	
	static String getReswueMessage() {
		StringBuilder aText = new StringBuilder();
		aText.append("*Setup Reswue*\n\n");
		aText.append("Reswue est l'outil utilis� par la R�sistance et seulement par la R�sistance pour planifier "
				+ "et notifier les agents lors d'op�rations. Ce tool est confidentiel, n'en parlez donc jamais "
				+ "aux joueurs verts!\n\n");
		aText.append("Il vous faut obtenir une autorisation pour utiliser Reswue. Commencez par vous connecter "
				+ "� [Reswue Europe](https://ops.irde.net/iitc/reswue/my.php) et connectez-vous gr�ce � votre "
				+ "compte Google+. Puis envoyez un message � [p0c07o](https://web.telegram.org/#/im?p=@p0c07o) "
				+ "afin qu'il vous valide!\n\n");
		aText.append("Il existe 3 moyens d'utiliser Reswue:\n\n");
		aText.append("1) Via l'[interface Web](https://ops.irde.net/iitc/reswue/my.php)\n");
		aText.append("2) Via le client mobile pour [Android]"
				+ "(https://play.google.com/store/apps/details?id=net.irde.ops.agentclient) "
				+ "ou pour [iPhone](https://itunes.apple.com/us/app/reswue-client/id971201245?l=de&ls=1&mt=8)\n");
		aText.append("3) Via le [plugin IITC](https://ops.irde.net/iitc/reswue.user.js)");
		return aText.toString();
	}

	static String getIrjuMessage() {
		StringBuilder aText = new StringBuilder();
		aText.append("*IRJU*\n\n");
		aText.append("Voici les informations et adresses importantes � conna�tre concernant notre team:\n\n");
		aText.append("1) Notre [communaut� Google+](https://plus.google.com/communities/113582481918232084310)\n");
		aText.append("2) Notre [site web](http://www.irju.ch)\n");
		aText.append("3) Notre adresse email: info@irju.ch\n\n");
		aText.append("Les membres de la team sont scind�s en deux cat�gories:\n");
		aText.append("- membres passifs, qui n'ont pas acc�s aux informations confidentielles (OP, etc.)\n");
		aText.append("- membres actifs, qui ont acc�s � toutes les informations");
		return aText.toString();
	}

	static String getUrlMessage() {
		StringBuilder aText = new StringBuilder();
		aText.append("*Quelques adresses importantes*\n\n");
		aText.append("- [Ingress](https://www.ingress.com/)\n");
		aText.append("- [Ingress Events](https://www.ingress.com/events)\n");
		aText.append("- [Ingress Investigate](http://investigate.ingress.com/)\n");
		aText.append("- [Ingress Intel](https://www.ingress.com/intel)\n\n");
		aText.append("- [Histoire de la RES suisse](http://swiss-ingress.ch/)\n");
		aText.append("- [RES Suisse (IRS)](http://www.ingressistance.ch/fr/)\n");
		aText.append("- [RES Suisse Romande (IRSR)](http://www.ingressistance.ch/fr/)\n");
		aText.append("- [RES Jura (IRJU)](http://www.irju.ch/)");
		return aText.toString();
	}

	static String getBurgerMessage() {
		return "Burger? Quelqu'un a dit burger? " 
			+ BURGER + BURGER + BURGER 
			+ " " + SMILING_FACE_WITH_HEART_SHAPED_EYES;
	}
	
	static String getWhoCaresMessages() {
		return "On s'en bat les steaks! " +
				FACE_WITH_TEARS_OF_JOY + 
				FACE_WITH_TEARS_OF_JOY +
				FACE_WITH_TEARS_OF_JOY;
	}
	
	static String getNoShitMessage() {
		return
			RAISED_HAND + " " + 
			RAISED_HAND + " " + 
			RAISED_HAND + " " + 
			RAISED_HAND;
	}
	
	static String getHelloMessage(String theUsername) {
		return "Bonjour *" + theUsername + "*!";
	}

	static String getGoodByeMessage(String theUsername) {
		return "Au revoir *" + theUsername + "*!";
	}
}
