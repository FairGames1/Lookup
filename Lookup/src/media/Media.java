package media;

public abstract class Media {
    protected int id;
    protected String name;
    protected String type;
    protected boolean nsfw;
    protected int opinion;

    // Constructor
    public Media(int id, String name, String type, boolean nsfw, int opinion) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.nsfw = nsfw;
        this.opinion = opinion;
    }


    public abstract void displayDetails();

    // funny
    public boolean isNSFW() {
        return nsfw;
    }
}
