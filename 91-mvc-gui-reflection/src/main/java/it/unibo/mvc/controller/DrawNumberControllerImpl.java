package it.unibo.mvc.controller;

import it.unibo.mvc.api.DrawNumber;
import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
//import it.unibo.mvc.view.DrawNumberSwingView;

import java.util.Objects;


/**
 * This class implements the game controller. It orchestrates the game, exposes methods to its observers
 * (the boundaries), and sends results to them.
 */
public final class DrawNumberControllerImpl implements DrawNumberController {

    private final DrawNumber model;
    private DrawNumberView viewSwing;
    private DrawNumberView viewStdout;
    //private List<DrawNumberView> views = new ArrayList<>();

    /**
     * Builds a new game controller provided a game model.
     *
     * @param model the implementation of the game model
     */
    public DrawNumberControllerImpl(final DrawNumber model) {
        this.model = model;
    }

    @Override
    public void addView(final DrawNumberView view) {
        if(view.getClass().toString().equals("class it.unibo.mvc.view.DrawNumberSwingView")) {
            Objects.requireNonNull(view, "Cannot set a null view");
            if (this.viewSwing != null) {
                throw new IllegalStateException("The view is already set! Multiple views are not supported");
            }
            this.viewSwing = view;
        } else {
            this.viewStdout = view;
        }
        view.setController(this);
        view.start();
    }

    @Override
    public void newAttempt(final int n) {
        newAttemptView(n, viewSwing);
        newAttemptView(n, viewStdout);
    }

    private void newAttemptView(final int n, final DrawNumberView view){
        Objects.requireNonNull(view, "There is no view attached!").result(model.attempt(n));
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        /*
         * A bit harsh. A good application should configure the graphics to exit by
         * natural termination when closing is hit. To do things more cleanly, attention
         * should be paid to alive threads, as the application would continue to persist
         * until the last thread terminates.
         */
        System.exit(0);
    }

}
