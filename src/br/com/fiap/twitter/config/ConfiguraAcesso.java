package br.com.fiap.twitter.config;

import br.com.fiap.twitter.exception.ConexaoTwitterException;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class ConfiguraAcesso {

	private static final String consumerKey = "bFABArLLYy0dLJx5BsvfmOVa3";
	private static final String consumerSecret = "qWHz3rd70g0RknFbtH32gMx6TjaTmvnMeNv2biwEObf516oWHp";
	private static final String token = "790979536368332802-qKpjnOSeuZGt8tVHDvfxVttZ9aEPE0y";
	private static final String tokenSecret = "P4dxf7kp0ApwkVAWxqf3lKfYqFHaNyvB4oGEDdp6X7cI1";

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
