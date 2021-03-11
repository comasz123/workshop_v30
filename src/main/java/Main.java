public class Main {
    public static void main(String[] args) {
        boolean menu_choice = true;
        MainMenu option;
        MainMenuControl mmc = new MainMenuControl();

        UserDao.createDBIfNotExist();

        // tu zaszla zmiania

        do {
            printMenu();
            KeyboardReader kr = new KeyboardReader();
            option = MainMenu.createFromInt(kr.readInt(MainMenu.EXIT.getValue()));

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
                    menu_choice = false;
            }


        } while (menu_choice);
    }
    public static void printMenu() {
        for (MainMenu options : MainMenu.values()) {
            System.out.println(options);
        }

    }
}
