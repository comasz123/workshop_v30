public class Main {
    public static void main(String[] args) {
        boolean menu_choice = true;
        MainMenu option;
        MainMenuControl mmc = new MainMenuControl();

        UserDao.createDBIfNotExist();

        do {
            printMenu();
            int i = KeyboardReader.kr.readInt(MainMenu.EXIT.getValue());
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
                    menu_choice = false;
                    KeyboardReader.kr.close();

            }


        } while (menu_choice);
    }
    public static void printMenu() {
        for (MainMenu options : MainMenu.values()) {
            System.out.println(options);
        }

    }
}
