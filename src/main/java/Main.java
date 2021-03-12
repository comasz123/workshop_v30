public class Main {
    public static void main(String[] args) {
        boolean menuChoice = true;
        MainMenu option;
        MainMenuControl mmc = new MainMenuControl();

        UserDao.createDBIfNotExist();

        while (menuChoice) {
            printMenu();
            int i = KeyboardReader.readInt(MainMenu.EXIT.getValue());
            option = MainMenu.createFromInt(i);

            switch (option) {

                case ADD_USER:
                    mmc.createUser();
                    break;

                case DEL_USER:
                    mmc.deleteUser();
                    break;

                case LIST_USERS:
                    mmc.listUsers();
                    break;

                case EDIT_USER:
                    mmc.editUser();
                    break;

                case LOG_USER:
                    mmc.logUser ();
                    break;

                case NEW_IDS:
                    mmc.reindexUsers();
                    break;

                case EXIT:
                    System.out.println("Koniec na dzisiaj");
                    menuChoice = false;
                    KeyboardReader.close();
            }
        }
    }
    public static void printMenu() {
        for (MainMenu options : MainMenu.values()) {
            System.out.println(options);
        }

    }
}
