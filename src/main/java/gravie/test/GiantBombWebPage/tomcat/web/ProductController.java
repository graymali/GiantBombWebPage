package gravie.test.GiantBombWebPage.tomcat.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gravie.test.GiantBombWebPage.tomcat.transactions.GiantBombTransactions;
import gravie.test.GiantBombWebPage.tomcat.transactions.objects.GameList;

@Controller
@RequestMapping(value = "product")
public class ProductController {
	
	//TODO: add search function to Product page so users can find the game they want
	
	//TODO: be able to add multiple copies of games at once (rather than one at a time)
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		GiantBombTransactions gbt = new GiantBombTransactions();
		//Get the top 20 games in name order
		//TODO: implement pages and allow user to view all games rather than just the top 20
		GameList gameList = gbt.GetGames(20, "name:asc");
		modelMap.put("gameList", gameList);
		return "product/index";
	}
	
}