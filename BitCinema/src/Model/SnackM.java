package Model;

public class SnackM {
	String Food = "";
	String Juice = "";
	String Goods = "";

	public SnackM() {

	}

	public SnackM(String Food, String Juice, String Goods) {
		this.Food = Food;
		this.Juice = Juice;
		this.Goods = Goods;

	}

	public void SetAll(String Food, String Juice, String Goods) {
		this.Food = Food;
		this.Juice = Juice;
		this.Goods = Goods;

	}

	public void FoodSave(String sFood) {
		this.Food = sFood;
	}

	public void JuiceSave(String sJuice) {
		this.Juice = sJuice;
	}

	public void GoodsSave(String sGoods) {
		this.Goods = sGoods;
	}

	public String FoodOut() {
		return Food;
	}

	public String JuiceOut() {
		return Juice;
	}

	public String GoodsOut() {
		return Goods;
	}
}