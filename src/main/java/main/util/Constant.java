package main.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class Constant {

    private Constant(){} //place to keep constants so i don't want anyone instantiating

    public static final HashSet<String> categories = new HashSet<>(Arrays.asList("Fragile", "Grocery", "Electronics"));

}
