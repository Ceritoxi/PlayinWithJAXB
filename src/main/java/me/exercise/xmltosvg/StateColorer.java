package me.exercise.xmltosvg;

import java.util.HashMap;
import java.util.Map;

public class StateColorer {

    private static final int DEFAULT_COLOR_DIFFERENCE = 4000;
    private static final int STARTER_COLOR = 0xAABBCC;
    Integer currentColor;
    Map<String, String> statesWithColor;

    public StateColorer() {
        statesWithColor = new HashMap<>();
        currentColor = STARTER_COLOR;
    }

    public String getColorOfState(String state) {
        assignColorToNewState(state);
        return statesWithColor.get(state);
    }

    private void assignColorToNewState(String state) {
        if (!statesWithColor.containsKey(state)) {
            statesWithColor.put(state, "#" + Integer.toHexString(currentColor));
            currentColor += DEFAULT_COLOR_DIFFERENCE;
        }
    }

}
