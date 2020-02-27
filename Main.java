public class Main {
    public static void main(String[] argz) {
        if(argz.length != 1) {
            System.err.println("Argument error");
            System.exit(1);
        }
        int mode = -1;
        if(argz[0].equals("-e")) {
            mode = 1;
        } else if(argz[0].equals("-a")) {
            mode=2;
        } else if(argz[0].equals("-u")) {
            mode=3;
        } else if(argz[0].equals("-v")) {
            mode=4;
        } else if(argz[0].equals("-f")) {
            mode=5;
        } else {
            System.err.println("Argument error");
            System.exit(1);
        }

        DFA dfa = new DFA();
        dfa.getInput("dfa");
        dfa.parseInput();

        switch(mode) {
            case 1:
                //TODO: Has e
                dfa.graph.solve_E();
                break;
            case 2:
                //TODO: Accessible states
                dfa.graph.solve_A();
                break;
            case 3:
                //TODO: Useful states
                dfa.graph.solve_U();
                break;
            case 4:
                //TODO: Empty language
                dfa.graph.solve_V();
                break;
            case 5:
                //TODO: Finite language
                dfa.graph.solve_F();
                break;
            default:
                System.err.println("Argument error");
                System.exit(1);
        }
    }
}
