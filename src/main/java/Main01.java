public class Main01 {
    public static void main(String[] args) {
        KeyboardReader kr = new KeyboardReader();
        int result = kr.readInt(7);
        System.out.println(result);
        KeyboardReader kr1 = new KeyboardReader();
        String result1 = kr1.readString();
        System.out.println(result1);
    }
}
