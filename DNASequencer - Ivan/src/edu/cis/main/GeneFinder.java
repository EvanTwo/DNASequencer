package edu.cis.main;

import java.util.ArrayList;
import java.util.Collections;


public class GeneFinder {
    int skip = 0; // used to skip nested reading frames

    public ArrayList<String> oneFrame(String fullDNA) {
        //look for "atg"
        fullDNA = fullDNA.toLowerCase();
        String partDNA;
        ArrayList<String> proteinSequence = new ArrayList<>();
        try {
            for (int i = 0; i < fullDNA.length(); i += 3) {
                skip = 0;
                //find ATG, then call restOfORF from ATG onwards
                if (fullDNA.startsWith("atg", i)) {
                    partDNA = fullDNA.substring(i);
                    String readingFrame = restOfORF(partDNA);
                    proteinSequence.add(readingFrame);
                    i = skip + i;
                }
            }
        } catch (StringIndexOutOfBoundsException ignored) {
        }

        return proteinSequence;
    }

    public String restOfORF(String partOfDNA) {
        //as "atg" has been found, locate end codons: "tag", "taa", tga"
        partOfDNA = partOfDNA.toLowerCase();
        String readingFrame = "";
        for (int i = 0; i < partOfDNA.length(); i += 3) {
            if ((i + 1) >= partOfDNA.length() || (i + 2) >= partOfDNA.length()) {  // if the next search exceeds the length of the sequence passed, return the entire string
                readingFrame = partOfDNA;
                break;
            }
            if ((partOfDNA.startsWith("tag", i)) ||
                    (partOfDNA.startsWith("taa", i)) ||
                    (partOfDNA.startsWith("tga", i))) {
                readingFrame = partOfDNA.substring(0, i);
                skip = i; // set skip to the length of the reading frame found, so that the next search starts afterwards
                break;
            }
        }
        if (readingFrame.equals("")) {
            readingFrame = partOfDNA;
        }

        return readingFrame;
    }

    public String longestORF(String fullDNA) {
        fullDNA = fullDNA.toLowerCase();

        // create three arraylists, searching for reading frames in all three shifts
        ArrayList<String> noShift = oneFrame(fullDNA);
        String shifted = fullDNA.substring(1);
        ArrayList<String> oneShift = oneFrame(shifted);
        shifted = fullDNA.substring(2);
        ArrayList<String> twoShift = oneFrame(shifted);
        ArrayList<String> allORF = new ArrayList<>();

        //combine all three arraylists, resulting in a master list of reading frames
        allORF.addAll(noShift);
        allORF.addAll(oneShift);
        allORF.addAll(twoShift);
        String longestORF = "";

        //if a reading frame longer than the current longest is found, set that to be the longest reading frame
        for (String s : allORF) {
            if (s.length() > longestORF.length()) {
                longestORF = s;
            }
        }
        return longestORF;
    }

    public String longestORFBothStrands(String fullDNA) {
        // call longestORF twice, once on normal, once on reverse compliment
        fullDNA = fullDNA.toLowerCase();
        String normal = longestORF(fullDNA);
        String rv = reverseCompliment(fullDNA);
        String longestRV = longestORF(rv);
        // compare the longest reading frames of the two
        if (normal.length() > longestRV.length()) {
            return normal;
        } else
            return longestRV;
    }

    public String reverseCompliment(String dna) {
        String compliment = "";
        String reverseCompliment = "";
        dna = dna.toLowerCase();
        //looping from the end of the dna string, adding characters to create the reverse
        for (int i = dna.length() - 1; i >= 0; i--) {
            compliment += dna.charAt(i);
        }
        // creating the compliment for the reverse sequence
        for (int i = 0; i < compliment.length(); i++) {
            if (compliment.charAt(i) == 'a') {
                reverseCompliment = reverseCompliment + 't';
            }
            if (compliment.charAt(i) == 't') {
                reverseCompliment = reverseCompliment + 'a';

            }
            if (compliment.charAt(i) == 'c') {
                reverseCompliment = reverseCompliment + 'g';

            }
            if (compliment.charAt(i) == 'g') {
                reverseCompliment = reverseCompliment + 'c';
            }
        }
        return reverseCompliment;
    }

    public int longestORFNonCoding(String dna, int numReps) {
        dna = dna.toLowerCase();
        char[] dnaArray = dna.toCharArray(); // turn dna string into array of nucleotides
        String shuffled;
        String longest = "";
        ArrayList<Character> nucleotide = new ArrayList<>();
        // turning array of nucleotides into arraylist
        for (char c : dnaArray) {
            nucleotide.add(c);
        }
        for (int i = 0; i < numReps; i++) {     // loops numReps times,
            Collections.shuffle(nucleotide);    // shuffles the arraylist
            shuffled = collapse(nucleotide);    // collapses back into a string
            if (longestORFBothStrands(shuffled).length() > longest.length()) { // finds the longestORF between all the shuffled sequences
                longest = longestORFBothStrands(shuffled);
            }
        }
        return longest.length();
    }

    public String collapse(ArrayList<Character> charList) {
        String backTogether = ""; // This is our initial output string

        for (Character ch : charList) // for each char in the list...
        {
            backTogether += ch; // ... construct a new output string
        }

        return backTogether; // and return the final output string
    }

    public ArrayList<String> findORFs(String fullDNA) {
        // create three arraylists, searching for reading frames in all three shifts
        fullDNA = fullDNA.toLowerCase();
        ArrayList<String> noShift = oneFrame(fullDNA);
        String shifted = fullDNA.substring(1);
        ArrayList<String> oneShift = oneFrame(shifted);
        shifted = fullDNA.substring(2);
        ArrayList<String> twoShift = oneFrame(shifted);
        ArrayList<String> allORF = new ArrayList<>();

        //combine all three arraylists, resulting in a master list of reading frames
        allORF.addAll(noShift);
        allORF.addAll(oneShift);
        allORF.addAll(twoShift);

        return allORF;
    }

    public ArrayList<String> findORFsBothStrands(String fullDNA) {
        // call findORF twice, once on normal, once on reverse compliment
        fullDNA = fullDNA.toLowerCase();
        ArrayList<String> normal = findORFs(fullDNA);
        String rv = reverseCompliment(fullDNA);
        ArrayList<String> reverse = findORFs(rv);
        ArrayList<String> both = new ArrayList<>();

        //combine the two arrayLists into a master list of ORFs
        both.addAll(normal);
        both.addAll(reverse);

        return both;
    }

    public Coordinate getCoordinates(String gene, String DNA)
    {
        gene = gene.toLowerCase();
        DNA = DNA.toLowerCase();
        String geneToUse = gene;
        int start = DNA.indexOf(geneToUse);

        if (start == -1)
        {
            geneToUse = reverseCompliment(gene);
            start = DNA.indexOf(geneToUse);
        }

        int end = start + gene.length();
        return new Coordinate(start, end);
    }

    public ArrayList<ArrayList<Object>> geneFinder(String dna, int minLen) {
        dna = dna.toLowerCase();
        ArrayList<String> allORFs = findORFsBothStrands(dna); // master list of all reading frames
        ArrayList<String> longORFs = new ArrayList<>();
        ArrayList<ArrayList<Object>> finalOutputList = new ArrayList<>();
        Coordinate coords;
        ArrayList<String> proteins = new ArrayList<>();

        for (String orf: allORFs) { // only keeps ORFs longer than minLen
            if(orf.length() > minLen){
                longORFs.add(orf);
            }
        }

        for(String longORF: longORFs){ // for each of the reading frames long enough, locate coordinates and create protein sequences
            ArrayList<Object> output = new ArrayList<>();
            coords = getCoordinates(longORF, dna); // use coordinate object to find start and end coordinate
            output.add(coords.getStart());  // add start coordinate to output array
            output.add(coords.getEnd());    // add end coordinate to output array
            for(int i = 0; i<longORF.length();i+=3){ // turn sequence of nucleotides into arraylist of codon
                proteins.add(longORF.substring(i,i+3));
            }
            ArrayList<String> aminoAcids;
            Ribosome ribosome = new Ribosome();
            aminoAcids = ribosome.createProtein(proteins); // create protein sequence using the previously generated arraylist
            String proteinSequence = "";
            for (String aminoAcid : aminoAcids) {    // turn protein sequence into a string
                proteinSequence += aminoAcid;
            }
            output.add(proteinSequence); // add protein sequence to output arraylist
            finalOutputList.add(output); // add arraylist to final arraylist
        }
        return finalOutputList;
    }

    public void printGenes(ArrayList<ArrayList<Object>> geneList) {
        for (ArrayList<Object> objects : geneList) {
            System.out.println("Starting Coordinate: " + objects.get(0) + " Ending Coordinate: " + objects.get(1) + " Protein Sequence: " + objects.get(2));

        }
    }
}


