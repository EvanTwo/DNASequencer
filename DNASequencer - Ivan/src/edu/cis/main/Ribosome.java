package edu.cis.main;

import java.util.ArrayList;

public class Ribosome {

    public ArrayList<String> createProtein(ArrayList<String> copyList){
        ArrayList<String> proteins = new ArrayList<>();
        String protein;
        for (String s : copyList) { // for each codon in the arraylist, add the amino acid to a new arraylist
            protein = s;
            if (protein.equals("uuu") || protein.equals("uuc")) {
                proteins.add("Phenylalanine ");
            }
            if (protein.equals("uua") || protein.equals("uug") || protein.indexOf("cu") == 0) {
                proteins.add("Leucine ");
            }
            if (protein.indexOf("au") == 0 && !protein.equals("aug")) {
                proteins.add("Isoleucine ");
            }
            if (protein.equals("aug")) {
                proteins.add("Methionine ");
            }
            if (protein.indexOf("gu") == 0) {
                proteins.add("Valine ");
            }
            if (protein.indexOf("uc") == 0 || protein.equals("agu") || protein.equals("agc")) {
                proteins.add("Serine ");
            }
            if (protein.indexOf("cc") == 0) {
                proteins.add("Threonine ");
            }
            if (protein.indexOf("gc") == 0) {
                proteins.add("Alanine ");
            }
            if (protein.equals("uau") || protein.equals("uac")) {
                proteins.add("Tyrosine ");
            }
            if (protein.equals("uaa")) {
                proteins.add("Stop (Ochre) ");
            }
            if (protein.equals("uag")) {
                proteins.add("Stop (Amber) ");
            }
            if (protein.equals("cau") || protein.equals("cac")) {
                proteins.add("Histidine ");
            }
            if (protein.equals("caa") || protein.equals("cag")) {
                proteins.add("Glutamine ");
            }
            if (protein.equals("aau") || protein.equals("aac")) {
                proteins.add("Asparagine ");
            }
            if (protein.equals("aaa") || protein.equals("aag")) {
                proteins.add("Glutamine ");
            }
            if (protein.equals("gau") || protein.equals("gac")) {
                proteins.add("Aspartic Acid ");
            }
            if (protein.equals("gaa") || protein.equals("gag")) {
                proteins.add("Glutamic Acid ");
            }
            if (protein.equals("ugu") || protein.equals("ugc")) {
                proteins.add("Cysteine ");
            }
            if (protein.equals("uga")) {
                proteins.add("Stop (Opal) ");
            }
            if (protein.equals("ugg")) {
                proteins.add("Tryptophan ");
            }
            if (protein.indexOf("cg") == 0 || protein.equals("aga") || protein.equals("agg")) {
                proteins.add("Arginine ");
            }
            if (protein.indexOf("GG") == 0) {
                proteins.add("Glycine ");
            }
        }
        return proteins;
    }
}
