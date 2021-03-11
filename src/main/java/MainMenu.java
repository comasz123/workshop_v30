public enum MainMenu {
    ADD_USER(1, "Dodaj Użytkownika"),
    DEL_USER(2, "Usuń użytkownika"),
    EDIT_USER(3, "Edytuj użytkownika"),
    LIST_USERS(4, "Lista użytkowników"),
    LOG_USER(5, "Zaloguj użytkownika"),
    NEW_IDS(6, "Uporządkuj indexy (ids)"),
    EXIT(7, "KONIEC");
  //  DO_NOTHING(11,"NIC");

    private int value;
    private String description;

    MainMenu(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return value + ". " + description;
    }

    static MainMenu createFromInt(int option) {
        return MainMenu.values()[option - 1];
    }
}
