import java.sql.SQLOutput;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DFAGraph {
    int V;
    String initialState;
    HashSet<String> finalStates;
    HashSet<String> allStates;
    HashSet<String> accesibleStates;
    Map<String, HashSet<String>> adjacencyMap;
    Map<Transition, String> nextStates;

    DFAGraph(String states, String alphabet, String transitions,
             String initialState, String finalStates)
    {
        adjacencyMap = new HashMap<>();
        nextStates = new HashMap<>();
        this.initialState = initialState;

        String[] stArr = states.split(",");
        allStates = new HashSet<>();
        allStates.addAll(Arrays.asList(stArr));

        String[] afArr = alphabet.split(",");
        String[] trArr;
        String[] fsArr = finalStates.split(",");
        this.finalStates = new HashSet<>();
        this.finalStates.addAll(Arrays.asList(fsArr));

        LinkedList<String> trList = new LinkedList<>();
        Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(transitions);
        while (m.find()) trList.add(m.group(1));

        trArr = new String[trList.size()];
        trArr = trList.toArray(trArr);

        //System.out.println("stArr: " + Arrays.toString(stArr));
        //System.out.println("afArr: " + Arrays.toString(afArr));
        //System.out.println("trArr: " + Arrays.toString(trArr));
        //System.out.println("fsArr: " + this.finalStates);

        V = stArr.length;
        for (String tr : trArr) {
           String[] trElems = tr.split(",");

           // Adding entry to nextStates/transitions map
           Transition trz = new Transition(trElems[0], trElems[1].charAt(0));
           nextStates.put(trz, trElems[2]);

           // Adding entry to adjacency map
           HashSet<String> neighbours = adjacencyMap.get(trElems[0]);
           if (neighbours == null) neighbours = new HashSet<>();
           neighbours.add(trElems[2]);
           adjacencyMap.put(trElems[0], neighbours);
        }
    }

    // Check if accepted language is null
    void solve_V() {
        getAccesibleStates();
        String ans = "Yes";
        for (String s : finalStates) {
            if (accesibleStates.contains(s)) {
                ans = "No";
                break;
            }
        }
        System.out.println(ans);
    }

    // Check if accepted language contains 'e'
    void solve_E() {
        String ans = ((finalStates.contains(initialState)) ? "Yes" : "No");
        System.out.println(ans);
    }

    void solve_A() {
        HashSet<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<String>();
        stack.push(initialState);

        while (!stack.empty()) {
            String crt = stack.pop();
            if (visited.contains(crt))
                continue;

            visited.add(crt);
            HashSet<String> neighbours = adjacencyMap.get(crt);
            if (neighbours == null) continue;
            for (String s : neighbours) {
                if (!visited.contains(s))
                    stack.push(s);
            }
        }

        for (String s : visited) {
            System.out.println(s);
        }
    }

    void getAccesibleStates() {
        HashSet<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<String>();
        stack.push(initialState);

        while (!stack.empty()) {
            String crt = stack.pop();
            if (visited.contains(crt))
                continue;

            visited.add(crt);
            HashSet<String> neighbours = adjacencyMap.get(crt);
            if (neighbours == null) continue;
            for (String s : neighbours) {
                if (!visited.contains(s))
                    stack.push(s);
            }
        }

        accesibleStates = visited;
    }

    boolean productiveState(String root) {
        HashSet<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {
            String crt = stack.pop();
            if (finalStates.contains(crt))
                return true;

            if (visited.contains(crt))
                continue;

            visited.add(crt);
            HashSet<String> neighbours = adjacencyMap.get(crt);
            if (neighbours == null) continue;
            for (String s : neighbours) {
                if (!visited.contains(s))
                    stack.push(s);
            }
        }

        return false;
    }

    void solve_U() {
        getAccesibleStates();
        for (String state : accesibleStates) {
            if (productiveState(state)) {
                System.out.println(state);
            }
        }
        // System.out.println("States: " + allStates.size() + " Useful: " + count);
    }

    boolean inCycle(String root) {
        HashSet<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();
        visited.add(root);
        for (String s : adjacencyMap.get(root))
            stack.push(s);

        while (!stack.empty()) {
            String crt = stack.pop();

            if (crt.equals(root))
                return true;

            visited.add(crt);
            HashSet<String> neighbours = adjacencyMap.get(crt);
            if (neighbours == null) continue;
            for (String s : neighbours) {
                if (!visited.contains(s))
                    stack.push(s);
                else if (s.equals(root))
                    return true;
            }
        }

        return false;
    }


    void solve_F() {
        getAccesibleStates();
        //HashSet<String> visited = new HashSet<>();
        //HashSet<String> recArr = new HashSet<>();
        for (String s : accesibleStates) {
            if (productiveState(s)) {
                if (inCycle(s)) {
                    System.out.println("No");
                    return;
                }
            }
        }
        System.out.println("Yes");
    }
}
