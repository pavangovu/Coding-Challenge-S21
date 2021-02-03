import ca.ualberta.stothard.cgview.*;
import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.awt.*;

class GenomeMapBackup implements CgviewConstants
{ 
   public static void main( String args[] ) throws FileNotFoundException
   {
      File genomeFile = new File("Genome.gb");
   
      Scanner input = new Scanner(genomeFile);        
      boolean sequenceFound=false;
      java.util.List<String> sequences = new ArrayList<String>();
         
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
         
         if(chopped[0].equals("ORIGIN"))
            sequenceFound=true;
      }
      
      //System.out.println(sequences.get(sequences.size()-1));   
      
      int length = 2765;
      Cgview cgview = new Cgview(length);
      
      //some optional settings
      cgview.setWidth(2000);
      cgview.setHeight(2000);
      cgview.setBackboneRadius(350.0f);
      cgview.setTitle("Tomato Curly Stunt Virus");
      cgview.setTitleFont(new Font("SansSerif", Font.BOLD, 22));
      cgview.setLabelPlacementQuality(20);
      cgview.setShowWarning(true);
      cgview.setLabelLineLength(8.0d);
      cgview.setLabelLineThickness(0.5f);
   
      //create a FeatureSlot to hold sequence features
      FeatureSlot featureSlot = new FeatureSlot(cgview, DIRECT_STRAND);
   
      //create random sequence features
      for (int i = 0; i <sequences.size(); i++) {
         int j = Math.round((float)((float)(length - 2) * Math.random())) + 1;
         
         //a Feature to add to our FeatureSlot
         Feature feature = new Feature(featureSlot, sequences.get(i)+""+i);
      
         //a single FeatureRange to add the Feature
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
         FeatureRange featureRange = new FeatureRange (feature,start, stop);
         
         featureRange.setDecoration(DECORATION_CLOCKWISE_ARROW);
          
      }
      
      try {
         //create a PNG file
         CgviewIO.writeToPNGFile(cgview, ".\\CgviewTest1.png");
         System.exit(0);
      }
      catch (IOException e) {
         e.printStackTrace(System.err);
         System.exit(1);
      }
   }
}