import java.util.InputMismatchException;
import java.util.Scanner;

public class KeyboardReader {
     private Scanner sc = new Scanner(System.in);

    public void close() {sc.close();}

    public int readInt(int range) {

        boolean choice = true;
        int result = -1;
 //       Scanner sc = new Scanner(System.in);

        while (choice) {
            try {
                result = sc.nextInt();
                if (result>0 && result<=range) {
                    choice = false;
                } else {
                    System.out.println("Podaj liczbę całkowitą pomiędzy 1-" + range);
                    sc.next();}

            } catch (InputMismatchException ex) {
                System.out.println("Podaj liczbę całkowitą pomiędzy 1-"+range);
            }
        }
   //     sc.close();
        return result;
    }
    public String readString() {
    //    Scanner sc = new Scanner(System.in);
        String result= sc.nextLine();
    //    sc.close();

        return result;
    }

}
