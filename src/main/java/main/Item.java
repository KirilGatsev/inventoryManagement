package main;

import main.testPackage.Detail;

import java.util.Set;

public interface Item {
    public Set<Detail> getDetails();
    public double calculateValue();
    public String getDescription();
}
