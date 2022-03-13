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
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		GiantBombTransactions gbt = new GiantBombTransactions();
		GameList gameList = gbt.GetGames(20, "name:asc");
		modelMap.put("gameList", gameList);
		return "product/index";
	}
	
	

}