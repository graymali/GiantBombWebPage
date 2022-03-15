package gravie.test.GiantBombWebPage.tomcat.web;

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

	// TODO: add search function to Product page so users can find the game they want

	// TODO: be able to add multiple copies of games at once (rather than one at a time)

	private static final int GAMES_PER_PAGE = 20;
	private static final String SORT = "name:asc";

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		GiantBombTransactions gbt = new GiantBombTransactions();
		// Get the top 20 games in name order
		// TODO: implement pages and allow user to view all games rather than just the top 20
		GameList gameList = gbt.GetGames(GAMES_PER_PAGE, SORT);
		modelMap.put("gameList", gameList);
		return "product/index";
	}

	@RequestMapping(value = "/loadPage", method = RequestMethod.POST, params = "nextPage")
	public String nextPage(@ModelAttribute("productPage") GameList gameList, BindingResult result, ModelMap modelMap) {
		// Make sure there is a next page
		int currentOffset = gameList.getOffset();
		if(gameList.getNumber_of_page_results() + currentOffset <= gameList.getNumber_of_total_results()) {
			// Get the next page
			GiantBombTransactions gbt = new GiantBombTransactions();
			int nextPageOffset = (int) (gameList.getNumber_of_page_results() + currentOffset);
			gameList = gbt.GetGames(nextPageOffset, GAMES_PER_PAGE, SORT);
		} else {
			// Get the current page again, no next page
			GiantBombTransactions gbt = new GiantBombTransactions();
			gameList = gbt.GetGames(currentOffset, GAMES_PER_PAGE, SORT);
		}
		
		modelMap.addAttribute("gameList", gameList);
		return "product/index";
	}
	
	@RequestMapping(value = "/loadPage", method = RequestMethod.POST, params = "previousPage")
	public String previousPage(@ModelAttribute("productPage") GameList gameList, BindingResult result, ModelMap modelMap) {
		// Make sure there is a next page
		int currentOffset = gameList.getOffset();
		if(currentOffset - gameList.getNumber_of_page_results() >= 0) {
			// Get the previous page
			GiantBombTransactions gbt = new GiantBombTransactions();
			int previousPageOffset = (int) (currentOffset - gameList.getNumber_of_page_results());
			gameList = gbt.GetGames(previousPageOffset, GAMES_PER_PAGE, SORT);
		} else {
			// Get the current page again, no next page
			GiantBombTransactions gbt = new GiantBombTransactions();
			gameList = gbt.GetGames(currentOffset, GAMES_PER_PAGE, SORT);
		}
		
		modelMap.addAttribute("gameList", gameList);
		return "product/index";
	}

}