package ge.idevelopers.myapplication.models;

/**
 * Created by soso on 4/5/17.
 */

public class BlogsModel {
    private String text;
    private String image;

    public BlogsModel(String text, String image) {
        this.text = text;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
