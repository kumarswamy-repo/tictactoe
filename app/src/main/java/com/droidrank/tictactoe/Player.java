package com.droidrank.tictactoe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

/**
 * Created by kumar on 10/7/2017.
 */

public class Player implements Serializable{

    private Set<Integer> selected = new HashSet<>();

    public void addSelectedBtn(int num){
        selected.add(num);
    }
    public void clearSelected(){
        selected.clear();
    }
    public boolean contains(int num){
        return selected.contains(num);
    }

    public boolean validate() {
        return ((selected.contains(1) && selected.contains(2) && selected.contains(3)) ||
                (selected.contains(4) && selected.contains(5) && selected.contains(6)) ||
                (selected.contains(7) && selected.contains(8) && selected.contains(9)) ||
                (selected.contains(1) && selected.contains(4) && selected.contains(7)) ||
                (selected.contains(2) && selected.contains(5) && selected.contains(8)) ||
                (selected.contains(3) && selected.contains(6) && selected.contains(9)) ||
                (selected.contains(3) && selected.contains(5) && selected.contains(7)) ||
                (selected.contains(1) && selected.contains(5) && selected.contains(9)));
    }
}
