package edu.cis.main;
import java.util.ArrayList;

public class Mrna {
    ArrayList<String> mrnaList;
    public Mrna() {
        mrnaList = new ArrayList<>();
    }
    public void createCopy(ArrayList<String> dnaSequence){

        String copy;
        String change;
        for (String s : dnaSequence) { // copy is created by turning any "t" into "u" because of the difference between how DNA and mRNA are coded
            copy = s;
            change = "";
            if (copy.contains("t")) {
                for (int j = 0; j < copy.length(); j++) {
                    if (copy.charAt(j) == 't') {
                        change = change + 'u';
                    } else
                        change = change + copy.charAt(j);
                }
                mrnaList.add(change);

            } else {
                mrnaList.add(copy);
            }
        }
    }

    public ArrayList<String> getMessengerDna(){
        return mrnaList;
    }

}
