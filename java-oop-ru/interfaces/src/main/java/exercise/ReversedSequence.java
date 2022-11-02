package exercise;

// BEGIN
public final class ReversedSequence implements CharSequence {

    private final String reversed;

    public ReversedSequence(String string) {
        this.reversed = new StringBuilder(string).reverse().toString();
    }

    @Override
    public int length() {
        return reversed.length();
    }

    @Override
    public char charAt(int i) {
        return reversed.charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return reversed.subSequence(i, i1);
    }
}
// END
