public class Mod {
    public static int mod(int a, int b) {
    if (a < b) {
        return (b - a) % b;
    } else {
        int c = a % b;
        return (b - c) % b;
    }
}
}
