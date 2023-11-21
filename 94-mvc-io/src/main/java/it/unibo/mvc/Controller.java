package it.unibo.mvc;

import java.util.List;
/**
 *
 */
public interface Controller {

    public void setNextString(final String string);

    public String getNextString();

    public List<String> getHistory();

    public void printNextString();
}
