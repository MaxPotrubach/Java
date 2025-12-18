public class TestFinder {
    public enum States{
        S, S1, S2, S3, F
    }
    public States Process(String input){
        if (input == null) return States.S;
        States currentState = States.S;

        for (char c : input.toCharArray()) {
            currentState = nextState(currentState, c);
            if (currentState == States.F) break;
        }
        return currentState;
    }

    private States nextState(States currentState, char c){
        return switch (currentState){
            case S -> {
                if (c == 'T') yield States.S1;
                yield States.S;
            }

            case S1 -> {
                if (c == 'E') yield States.S2;
                if (c == 'T') yield States.S1;
                yield States.S;
            }
            case S2 -> {
                if (c == 'S')  yield States.S3;
                if (c == 'T')  yield States.S1;
                yield States.S;
            }
            case S3 -> {
                if (c == 'T')   yield States.F;
                yield States.S;
            }

            case F -> States.F;
        };
    }

}