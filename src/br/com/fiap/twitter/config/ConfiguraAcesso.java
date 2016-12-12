package br.com.fiap.twitter.config;

import br.com.fiap.twitter.exception.ConexaoTwitterException;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class ConfiguraAcesso {

  //Configurar credencias
	private static final String consumerKey = "";
	private static final String consumerSecret = "";
	private static final String token = "";
	private static final String tokenSecret = "";

	/**
	 * Metodo estatico que cria as configuracoes de acesso a API do twitter
	 * @author Douglas
	 * @return Objeto twitter autenticado
	 * @throws ConexaoTwitterException 
	 */
	public static Twitter iniciaTwitter() throws ConexaoTwitterException {
		try {
			ConfigurationBuilder builder = new ConfigurationBuilder();

			builder.setOAuthConsumerKey(consumerKey);
			builder.setOAuthConsumerSecret(consumerSecret);

			Configuration configuration = builder.build();

			TwitterFactory factory = new TwitterFactory(configuration);
			Twitter twitter = factory.getInstance();

			AccessToken accessToken = new AccessToken(token, tokenSecret);

			twitter.setOAuthAccessToken(accessToken);

			return twitter;

		} catch (Exception e) {
			throw new ConexaoTwitterException("Falha ao autenticar no Twitter!!!");
		}

	}

}
