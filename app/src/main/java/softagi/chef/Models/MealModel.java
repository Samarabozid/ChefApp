package softagi.chef.Models;

import java.util.List;

public class MealModel
{
    private String title,image,by,duration,enough;
    private float rate;
    private List<String> recipes;

    public MealModel(String title, String image, String by, String duration, String enough, float rate, List<String> recipes) {
        this.title = title;
        this.image = image;
        this.by = by;
        this.duration = duration;
        this.enough = enough;
        this.rate = rate;
        this.recipes = recipes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEnough() {
        return enough;
    }

    public void setEnough(String enough) {
        this.enough = enough;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public List<String> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<String> recipes) {
        this.recipes = recipes;
    }
}
