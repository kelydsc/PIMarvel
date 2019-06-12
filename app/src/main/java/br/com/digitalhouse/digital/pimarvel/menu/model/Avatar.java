package br.com.digitalhouse.digital.pimarvel.menu.model;

public class Avatar {

    private int image;
    private String heroName;

    public Avatar() {
    }

    public Avatar(int image, String heroName) {
        this.image = image;
        this.heroName = heroName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }
}
