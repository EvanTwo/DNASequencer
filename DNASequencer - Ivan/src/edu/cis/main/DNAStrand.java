package edu.cis.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DNAStrand {
    ArrayList<String> dnaSequenceList;
    ArrayList<String> complimentList;

    public DNAStrand(){
        dnaSequenceList = new ArrayList<>();
        complimentList = new ArrayList<>();
    }

    public void readDNA (String filename){ // read in dna file, and store into dnaSequenceList
        try
        {
            File file = new File(filename);
            Scanner scan = new Scanner(file);

            while ((scan.hasNextLine())){
                String line = scan.nextLine();
                dnaSequenceList.add(line);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createCompliment(){
        String nucleotide;
        String compliment;
        for (String cod : dnaSequenceList) {
            nucleotide = cod;
            compliment = "";
            for(int i = 0; i < nucleotide.length();i++){ // for each nucleotide, the compliment is found and added to a new string
                if (nucleotide.charAt(i) == 'a'){
                    compliment = compliment + 't';
                }
                if (nucleotide.charAt(i) == 't'){
                    compliment = compliment + 'a';

                }
                if (nucleotide.charAt(i) == 'c'){
                    compliment = compliment + 'g';

                }
                if(nucleotide.charAt(i) == 'g'){
                    compliment = compliment + 'c';
                }
            }
            complimentList.add(compliment);
        }
    }


    public ArrayList<String> getDnaSequence(){
        return dnaSequenceList;
    }
    public ArrayList<String> getCompliment() {
        return complimentList;
    }
}
