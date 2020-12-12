package models.personnages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public enum Type {
    GUERRIER,
    BARBARE,
    LANCIER,
    CHASSEUR,
    MAGE;

    private ArrayList<Type> faiblesse;

    static {
        GUERRIER.faiblesse = new ArrayList<>(Collections.singletonList(LANCIER));
        LANCIER.faiblesse = new ArrayList<>(Collections.singletonList(BARBARE));
        BARBARE.faiblesse = new ArrayList<>(Collections.singletonList(LANCIER));
        MAGE.faiblesse = new ArrayList<>(Arrays.asList(GUERRIER, LANCIER, BARBARE));
        CHASSEUR.faiblesse = new ArrayList<>(Arrays.asList(GUERRIER, LANCIER, BARBARE));
    }

    public ArrayList<Type> getFaiblesse() {
        return faiblesse;
    }

    @Override
    public String toString() {
        String string = super.toString();
        return string.substring(0, 1) + string.substring(1, string.length()).toLowerCase();
    }
}
