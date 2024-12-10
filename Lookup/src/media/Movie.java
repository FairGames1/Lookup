package media;

public class Movie extends Media {
    public Movie(int id, String name, String type, boolean nsfw, int opinion) {
        super(id, name, type, nsfw, opinion);
    }

    @Override
    public void displayDetails() {
        System.out.println("Movie ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("NSFW: " + nsfw);
        System.out.println("Opinion: " + opinion);
        System.out.println("-------------------");
    }
}
