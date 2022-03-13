package gravie.test.GiantBombWebPage.tomcat.transactions.objects;

public class Item {
	private Game game;
	private int quantity;
	
	public Item(Game game, int quantity) {
		this.game = game;
		this.quantity = quantity;
	}
	
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Item [game=" + game + ", quantity=" + quantity + "]";
	}
}
