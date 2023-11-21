package it.unibo.mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private String nextString;
    private final List<String> history = new ArrayList<>();

    @Override
    public void setNextString(String string) {
        nextString = Objects.requireNonNull(string, "the string has to be non null");
    }

    @Override
    public String getNextString() {
        return new String(nextString);
    }

    @Override
    public List<String> getHistory() {
        return Collections.unmodifiableList(history);
    }

    @Override
    public void printNextString() {
        if(nextString == null){
            throw new IllegalStateException("cannot print a null string");
        }
        System.out.println(nextString);
        history.add(nextString);
    }

}
