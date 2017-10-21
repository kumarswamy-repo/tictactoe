package com.droidrank.tictactoe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

/**
 * Created by kumar on 10/7/2017.
 */

class Player implements Serializable{

    private Set<Integer> selected = new HashSet<>();

    void addSelectedBtn(int num){
        selected.add(num);
    }

    boolean contains(int num){
        return selected.contains(num);
    }

    boolean validate() {
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
