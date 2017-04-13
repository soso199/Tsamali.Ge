package ge.idevelopers.tsamali.models;

/**
 * Created by soso on 4/5/17.
 */

public class OffersModel {
    private String title;
    private String img;
    private String text;
    private int views;

    public OffersModel(String title, String img, String text, int views) {
        this.title = title;
        this.img = img;
        this.text = text;
        this.views = views;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
