package guru.qa;

public enum Gender {
    MALE("Мужчина"),
    FEMALE("Девушка");

    public final String desc;

    Gender(String desc) {
        this.desc = desc;
    }
}
