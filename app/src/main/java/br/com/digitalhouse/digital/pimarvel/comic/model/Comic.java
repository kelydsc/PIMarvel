package br.com.digitalhouse.digital.pimarvel.comic.model;

public class Comic {

    private int image;
    private String comicName;

    public Comic() {
    }

    public Comic(int image, String comicName) {
        this.image = image;
        this.comicName = comicName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getHeroName() {
        return comicName;
    }

    public void setHeroName(String heroName) {
            this.comicName = heroName;
    }
}