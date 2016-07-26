package model;

/**
 *
 */
public class Book {
    private String name;
    private String owner;
    private String pathToFile;

    public Book(String name, String owner, String pathToFile) {
        this.name = name;
        this.owner = owner;
        this.pathToFile = pathToFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }
}
