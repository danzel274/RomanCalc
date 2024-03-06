import java.util.Arrays;

public enum RomanTens {
    X, XX, XXX, XL, L, LX, LXX, LXXX, XC;

    public static boolean exist(String name) {
        for (RomanTens value : RomanTens.values()) {
            if (value.name().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public int toInt() {
        return (this.ordinal() + 1) * 10;
    }
}
