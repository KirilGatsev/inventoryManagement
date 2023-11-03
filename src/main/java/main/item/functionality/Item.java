package main.item.functionality;

import main.util.Detail;

import java.util.Set;

public interface Item {
    public Set<Detail> getDetails();
    public double calculateValue();
    public String getDescription();
}
