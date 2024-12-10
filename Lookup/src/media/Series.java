package media;

public class Series extends Media {
    public Series(int id, String name, String type, boolean nsfw, int opinion) {
        super(id, name, type, nsfw, opinion);
    }

    @Override
    public void displayDetails() {
        System.out.println("Series ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("NSFW: " + nsfw);
        System.out.println("Opinion: " + opinion);
        System.out.println("-------------------");
    }
}
