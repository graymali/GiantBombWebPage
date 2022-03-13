package gravie.test.GiantBombWebPage.tomcat.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gravie.test.GiantBombWebPage.tomcat.transactions.GiantBombTransactions;
import gravie.test.GiantBombWebPage.tomcat.transactions.objects.Game;
import gravie.test.GiantBombWebPage.tomcat.transactions.objects.Item;

@Controller
@RequestMapping(value = "cart")
public class CartController {

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "cart/index";
	}

	@RequestMapping(value = "buy/{guid}", method = RequestMethod.GET)
	public String buy(@PathVariable("guid") String guid, HttpSession session) {
		GiantBombTransactions gbt = new GiantBombTransactions();
		if (session.getAttribute("cart") == null) {
			List<Item> cart = new ArrayList<Item>();
			Game game = gbt.GetGame(guid);
			if(game != null) {
				cart.add(new Item(game, 1));
			}
			session.setAttribute("cart", cart);
		} else {
			List<Item> cart = (List<Item>) session.getAttribute("cart");
			int index = this.exists(guid, cart);
			if (index == -1) {
				Game game = gbt.GetGame(guid);
				if(game != null) {
					cart.add(new Item(game, 1));
				}
			} else {
				int quantity = cart.get(index).getQuantity() + 1;
				cart.get(index).setQuantity(quantity);
			}
			session.setAttribute("cart", cart);
		}
		return "redirect:/cart/index";
	}

	@RequestMapping(value = "remove/{guid}", method = RequestMethod.GET)
	public String remove(@PathVariable("guid") String guid, HttpSession session) {
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		int index = this.exists(guid, cart);
		cart.remove(index);
		session.setAttribute("cart", cart);
		return "redirect:/cart/index";
	}

	private int exists(String guid, List<Item> cart) {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getGame().getGuid().equalsIgnoreCase(guid)) {
				return i;
			}
		}
		return -1;
	}

}