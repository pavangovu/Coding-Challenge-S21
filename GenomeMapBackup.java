//Java Standard Template Library
import java.io.*;
import java.util.*;
import java.nio.file.*;

//Cgview Library
import ca.ualberta.stothard.cgview.*;
import java.awt.*;

class GenomeMapBackup implements CgviewConstants
{ 
   public static void main( String args[] ) throws FileNotFoundException
   {
      //genbank input file 
      File genomeFile = new File("Genome.gb");
   
      //necessary local variables
      Scanner input = new Scanner(genomeFile);
      boolean sequenceFound=false;                                   
      java.util.List<String> sequences = new ArrayList<String>();
         
      //parse through .gb file
      while(input.hasNext())
      {
         String currentLine=input.nextLine();
         String[] chopped = currentLine.trim().split(" +");
         
         if(sequenceFound&&Character.isDigit(chopped[0].charAt(0)))
         {
            for(int i=1; i<chopped.length; i++)
            {
               sequences.add(chopped[i]);
            }
         }
         
         //beginning of DNA sequence
         if(chopped[0].equals("ORIGIN"))
            sequenceFound=true;
      }
      
      //for testing purposes only
      //System.out.println(sequences.get(sequences.size()-1));   
      
      int length = 2765;
      Cgview cgview = new Cgview(length);
      
      //Genome Map Configurations
      cgview.setWidth(1350);  //optimal width of png
      cgview.setHeight(1080); //omptimal height of png
      cgview.setBackboneRadius(350.0f);            //radius of circular genome map
      cgview.setTitle("Tomato Curly Stunt Virus"); //title
      cgview.setTitleFont(new Font("SansSerif", Font.BOLD, 22)); //title font
      cgview.setLabelPlacementQuality(20);   
      cgview.setShowWarning(false);
      
      //label specifications
      cgview.setLabelLineLength(8.0d);
      cgview.setLabelLineThickness(0.5f);
   
      //FeatureSlot to hold sequence features
      FeatureSlot featureSlot = new FeatureSlot(cgview, DIRECT_STRAND);
   
      //load sequences
      int alternate=1;
      for (int i = 0; i <sequences.size(); i++) {
         
         //new feature
         Feature feature = new Feature(featureSlot, sequences.get(i));
         
         //alternate label colors for increased readability
         if(alternate==1)
         {
            feature.setColor(Color.blue);//blue
            alternate++;
         }
         else if(alternate==2)
         {
            feature.setColor(Color.red);//red
            alternate++;
         } 
         else if(alternate==3)
         {
            feature.setColor(Color.green);//green
            alternate=1;//reset color
         }
         
         //length of feature sector
         int start=0, stop=0;
         
         if(i==276)
         {
            start=i*10+1;
            stop=i*10+5;
         }
         
         else
         {
            start=i*10+1;
            stop=(i+1)*10;
         }
         
         //draw feature onto genome map
         FeatureRange featureRange = new FeatureRange (feature,start, stop);
         featureRange.setDecoration(DECORATION_CLOCKWISE_ARROW);//each feature demarcated by arrow
      }
      
      try 
      {
         //PNG File
         CgviewIO.writeToPNGFile(cgview, ".\\CgviewTest1.png");
         
         //testing purposes only
         //System.exit(0);
      }
      catch (IOException e)//handle possible PNG exception 
      {
         e.printStackTrace(System.err);//see what the error was
         System.exit(1);
      }
   }
}