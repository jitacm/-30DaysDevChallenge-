public enum Chairperson {
    NARENDRA_MODI("Narendra Modi"),
    RAHUL_GANDHI("Rahul Gandhi"),
    KEJRIWAL("Kejriwal");

    private String name;

    Chairperson(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
