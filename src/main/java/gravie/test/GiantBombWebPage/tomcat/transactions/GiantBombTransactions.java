package gravie.test.GiantBombWebPage.tomcat.transactions;

import java.util.Arrays;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import gravie.test.GiantBombWebPage.tomcat.constants.Constants;
import gravie.test.GiantBombWebPage.tomcat.transactions.objects.Game;
import gravie.test.GiantBombWebPage.tomcat.transactions.objects.GameList;
import gravie.test.GiantBombWebPage.tomcat.transactions.objects.SingleGameList;

public class GiantBombTransactions {
	private static final Logger log = LoggerFactory.getLogger(GiantBombTransactions.class);

	public GiantBombTransactions() {

	}
	
	//TODO: update request to include "field_list" to reduce the returned object size
	
	private RestTemplate buildRestTemplate() {
		//Accept all certificates for an HTTPS connection
		//TODO: load the giantbomb certificate and only accept that one
		CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		return new RestTemplate(requestFactory);
	}
	
	private HttpEntity<String> buildHeaderEntity() {
		//Use "user-agent" to get past 403 error
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        return new HttpEntity<String>("parameters", headers);
	}
	
	public GameList GetGames(int limit, String sort) {
		return GetGames(0, limit, sort);
	}

	public GameList GetGames(int offset, int limit, String sort) {
		log.info(">>>>GetGames: " + offset + "," + limit + "," + sort);
		
		//Build the URL
		//TODO: make sure the limit and sort are valid inputs
		String url = Constants.URL + "/games/?api_key=" + Constants.API_KEY + "&format=json"
				+ "&offset=" + offset + "&limit=" + limit + "&sort=" + sort ;
		log.info("url: " + url);

		GameList gamesArray = new GameList();

		try {
			//Send REST request
			RestTemplate restTemplate = buildRestTemplate();
            HttpEntity<String> entity = buildHeaderEntity();
			ResponseEntity<GameList> response = restTemplate.exchange(url, HttpMethod.GET, entity, GameList.class);
			gamesArray = response.getBody();
			log.info(gamesArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}

		log.info("<<<<GetGames");
		return gamesArray;
	}
	
	public Game GetGame(String guid) {
		log.info(">>>>GetGame: " + guid);
		
		//Build the URL
		//TODO: make sure the guid is a valid input
		String url = Constants.URL + "/game/" +  guid + "/?api_key=" + Constants.API_KEY + "&format=json";
		log.info("url: " + url);

		Game game = null;
		SingleGameList gameList = new SingleGameList();

		try {
			//Send REST request
			RestTemplate restTemplate = buildRestTemplate();
            HttpEntity<String> entity = buildHeaderEntity();
			ResponseEntity<SingleGameList> response = restTemplate.exchange(url, HttpMethod.GET, entity, SingleGameList.class);
			gameList = response.getBody();
			log.info(gameList.toString());
			if(gameList != null && gameList.getResults() != null) {
				game = gameList.getResults();
				log.info(gameList.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}

		log.info("<<<<GetGame");
		return game;
	}
	
}
