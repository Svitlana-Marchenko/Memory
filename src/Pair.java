public class Pair {
    String value;
    String pairValue;

    public Pair(String value, String pairValue) {
        this.value = value;
        this.pairValue = pairValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPairValue() {
        return pairValue;
    }

    public void setPairValue(String pairValue) {
        this.pairValue = pairValue;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "value='" + value + '\'' +
                ", pairValue='" + pairValue + '\'' +
                '}';
    }
}
