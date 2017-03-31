package eapli.ecafeteria.user.consoleapp;

import eapli.ecafeteria.bootstrapapp.ECafeteriaBootstrap;
import eapli.ecafeteria.user.consoleapp.presentation.FrontMenu;

/**
 * eCafeteria User App.
 */
public final class ECafeteriaUserApp {

    /**
     * Empty constructor is private to avoid instantiation of this class.
     */
    private ECafeteriaUserApp() {
    }

    public static void main(final String[] args) {

        // only needed because of the in memory persistence
        new ECafeteriaBootstrap().execute();

        new FrontMenu().show();
    }
}
