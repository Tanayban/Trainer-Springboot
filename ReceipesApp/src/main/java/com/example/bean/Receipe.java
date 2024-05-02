package com.example.bean;

public class Receipe {
	int id;
	String foodname;
	String description;
	String ingredient;
	String preparation;
	String history;
	String category;
	String type;
	String imagename;
	
	public Receipe() {
		// TODO Auto-generated constructor stub
	}

	public Receipe(int id, String foodname, String description, String ingredient, String preparation, String history,
			String category, String type, String imagename) {
		this.id = id;
		this.foodname = foodname;
		this.description = description;
		this.ingredient = ingredient;
		this.preparation = preparation;
		this.history = history;
		this.category = category;
		this.type = type;
		this.imagename = imagename;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFoodname() {
		return foodname;
	}

	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public String getPreparation() {
		return preparation;
	}

	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	@Override
	public String toString() {
		return "Receipe [id=" + id + ", foodname=" + foodname + ", description=" + description + ", ingredient="
				+ ingredient + ", preparation=" + preparation + ", history=" + history + ", category=" + category
				+ ", type=" + type + ", imagename=" + imagename + "]";
	}

}
