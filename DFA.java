import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DFA {
    String input;
    DFAGraph graph;

    public void getInput(String filename) {
        // System.out.println("Opening file for reading: " + filename);
        File fileObj = new File(filename);
        try {
            Scanner reader = new Scanner(fileObj);
            this.input = reader.nextLine();
            // System.out.println("Got input: \n" + input);
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.. Exiting.");
        }
    }

    public void parseInput() {
        String states, alphabet, transitions, initialState, finalStates;

        states = input.substring(input.indexOf('{') + 1, input.indexOf('}'));
        input = input.substring(input.indexOf('}') + 1);
        //System.out.println("States = " + states);

        alphabet = input.substring(input.indexOf('{') + 1, input.indexOf('}'));
        input = input.substring(input.indexOf('}') + 1);
        //System.out.println("Alphabet = " + alphabet);

        transitions = input.substring(input.indexOf('(') + 1, input.substring(0, input.length() - 1).lastIndexOf(')'));
        input = input.substring(transitions.length() + 4);
        //System.out.println("Transitions = " + transitions);

        initialState = input.substring(0, input.indexOf(','));
        input = input.substring(input.indexOf(',') + 1);
        //System.out.println("initialState = " + initialState);

        finalStates = input.substring(input.indexOf('{') + 1, input.indexOf('}'));
        //System.out.println("finalStates = " + finalStates);

        buildGraph(states, alphabet, transitions, initialState, finalStates);
    }

    public void buildGraph(String st, String af, String tr, String is, String fs) {
        graph = new DFAGraph(st, af, tr, is, fs);
    }

    public void solveAll() {
        System.out.println("-v:");
        graph.solve_V();
        System.out.println("-e:");
        graph.solve_E();
        System.out.println("-a:");
        graph.solve_A();
    }

}
