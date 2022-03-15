package gravie.test.GiantBombWebPage.tomcat.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gravie.test.GiantBombWebPage.tomcat.transactions.GiantBombTransactions;
import gravie.test.GiantBombWebPage.tomcat.transactions.objects.GameList;

@Controller
@RequestMapping(value = "product")
public class ProductController {
	private static final Logger log = LoggerFactory.getLogger(ProductController.class);

	// TODO: fix url so that you can refresh the page without losing your location/search
	// This will probably take re-working how the pages are mapped and just passing in parameters instead of them being in the gameList object
	
	// TODO: allow pages for searches. Currently it'll only display the top 100 items, and no pages

	// TODO: be able to add multiple copies of games at once (rather than one at a time)

	private static final int GAMES_PER_PAGE = 20;
	private static final String SORT = "name:asc";

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		log.info(">>>>product");
		GiantBombTransactions gbt = new GiantBombTransactions();
		// Default get the top 20 games in name order
		GameList gameList = gbt.GetGames(GAMES_PER_PAGE, SORT);
		modelMap.put("gameList", gameList);
		log.info("<<<<product");
		return "product/index";
	}

	@RequestMapping(value = "/loadPage", method = RequestMethod.POST, params = "nextPage")
	public String nextPage(@ModelAttribute("gameList") GameList gameList, BindingResult result, ModelMap modelMap) {
		log.info(">>>>nextPage");
		// Make sure there is a next page
		int currentOffset = gameList.getOffset();
		if(gameList.getNumber_of_page_results() + currentOffset <= gameList.getNumber_of_total_results()) {
			// Get the next page
			log.debug("get next page");
			GiantBombTransactions gbt = new GiantBombTransactions();
			int nextPageOffset = (int) (gameList.getNumber_of_page_results() + currentOffset);
			gameList = gbt.GetGames(nextPageOffset, GAMES_PER_PAGE, SORT);
		} else {
			// Get the current page again, no next page
			//TODO: probably don't need to actually get the next page if we just pass back the full current gameList
			log.debug("get current page");
			GiantBombTransactions gbt = new GiantBombTransactions();
			gameList = gbt.GetGames(currentOffset, GAMES_PER_PAGE, SORT);
		}
		
		modelMap.addAttribute("gameList", gameList);
		log.info("<<<<nextPage");
		return "product/index";
	}
	
	@RequestMapping(value = "/loadPage", method = RequestMethod.POST, params = "previousPage")
	public String previousPage(@ModelAttribute("gameList") GameList gameList, BindingResult result, ModelMap modelMap) {
		log.info(">>>>previousPage");
		// Make sure there is a next page
		int currentOffset = gameList.getOffset();
		if(currentOffset - gameList.getNumber_of_page_results() >= 0) {
			// Get the previous page
			log.debug("get next page");
			GiantBombTransactions gbt = new GiantBombTransactions();
			int previousPageOffset = (int) (currentOffset - gameList.getNumber_of_page_results());
			gameList = gbt.GetGames(previousPageOffset, GAMES_PER_PAGE, SORT);
		} else {
			// Get the current page again, no next page
			//TODO: probably don't need to actually get the next page if we just pass back the full current gameList
			log.debug("get current page");
			GiantBombTransactions gbt = new GiantBombTransactions();
			gameList = gbt.GetGames(currentOffset, GAMES_PER_PAGE, SORT);
		}
		
		modelMap.addAttribute("gameList", gameList);
		log.info("<<<<previousPage");
		return "product/index";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(@ModelAttribute("search") GameList gameList, BindingResult result, ModelMap modelMap) {
		log.info(">>>>search: " + gameList.getSearchName());
		// Get the current page again, no next page
		GiantBombTransactions gbt = new GiantBombTransactions();
		String searchName = gameList.getSearchName();
		gameList = gbt.GetGames(searchName, SORT);
		gameList.setSearchName(searchName);
		
		modelMap.addAttribute("gameList", gameList);
		log.info("<<<<search");
		return "product/index";
	}

}