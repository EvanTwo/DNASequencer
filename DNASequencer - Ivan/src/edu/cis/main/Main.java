package edu.cis.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        /*
         * PART 1a and 1b
         */
//     UNCOMMENT THE FOLLOWING CODE WHEN YOU'RE READY TO TEST

        DNAStrand myDna = new DNAStrand();
        Mrna myMRNA = new Mrna();
        Ribosome myRib = new Ribosome();
        myDna.readDNA("dnaSequence");
        myDna.createCompliment();
        myMRNA.createCopy(myDna.getDnaSequence());

        ArrayList<String> protein = myRib.createProtein(myMRNA.getMessengerDna());


        for (String section : protein)
        {
            System.out.println(section);
        }



        /*
         * PART 2
         */

        // READ the file and turn it into one long DNA string
        GeneFinder myFinder = new GeneFinder();



        try {
            String X73525 = "";
            Scanner sc = new Scanner(new File("X73525.fna"));
            while(sc.hasNext())
            {
                X73525 += sc.next();
            }

            int test = myFinder.longestORFNonCoding(X73525,1500);
            ArrayList<ArrayList<Object>> geneList = myFinder.geneFinder(X73525, test);
            myFinder.printGenes(geneList);
        }
        catch (Exception err)
        {

        }
    }
}

