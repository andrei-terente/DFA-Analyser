public class Transition {
    Transition() {}

    Transition(String crt, char chr) {
        this.crt = crt;
        this.chr = chr;
    }

    public String crt;
    public char chr;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Transition)) {
            return false;
        }

        Transition stObj = (Transition) obj;
        return (stObj.crt.equals(this.crt)) && (stObj.chr == this.chr);
    }
}
