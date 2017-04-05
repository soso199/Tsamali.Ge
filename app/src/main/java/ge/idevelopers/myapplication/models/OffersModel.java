package ge.idevelopers.myapplication.models;

/**
 * Created by soso on 4/5/17.
 */

public class OffersModel {
    private String image;
    private String text;

    public OffersModel(String text, String image) {
        this.image = image;
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
