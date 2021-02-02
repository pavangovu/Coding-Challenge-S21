import ca.ualberta.stothard.cgview.*;
import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.awt.*;

class GenomeMap implements CgviewConstants
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
      
      int length = 12078;
      Cgview cgview = new Cgview(length);
        
        //some optional settings
      cgview.setWidth(600);
      cgview.setHeight(600);
      cgview.setBackboneRadius(140.0f);
      cgview.setTitle("Tomato curly stunt virus");
      cgview.setLabelPlacementQuality(5);
      cgview.setShowWarning(false);
      cgview.setLabelLineLength(15.0d);
      cgview.setLabelLineThickness(1.0f);
      cgview.setUseInnerLabels(INNER_LABELS_SHOW);
      cgview.setMoveInnerLabelsToOuter(true);
      cgview.setMinimumFeatureLength(1.0d);
   
      Legend legend = new Legend(cgview);
      legend.setPosition(LEGEND_UPPER_CENTER);
      LegendItem legendItem = new LegendItem(legend);
      legendItem.setLabel("ACM Coding Challenge");
      legendItem.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 22));
   
        //create FeatureSlots to hold sequence features
      FeatureSlot directSlot0 = new FeatureSlot(cgview, DIRECT_STRAND);
      FeatureSlot directSlot1 = new FeatureSlot(cgview, DIRECT_STRAND);
      FeatureSlot reverseSlot0 = new FeatureSlot(cgview, REVERSE_STRAND);
   
        //Features to add to the FeatureSlots
      Feature feature0 = new Feature(directSlot0, "A");
      
   
      Feature feature1 = new Feature(directSlot1, "B");
      feature1.setColor(Color.red);
        
      Feature feature2 = new Feature(reverseSlot0, "C");
      feature2.setColor(Color.green);
      
      //create random sequence features
      for (int i = 0; i <= (sequences.size()-2); i = i + 3) {
      
         int j = Math.round((float)((float)(length - 2) * Math.random())) + 1;
         int k = Math.round((float)((float)(length - 2) * Math.random())) + 1;
         int l = Math.round((float)((float)(length - 2) * Math.random())) + 1;
      
            //a single FeatureRange to add the Feature
         feature0=new Feature(directSlot0, sequences.get(i));
         feature0.setColor(Color.blue);
         FeatureRange featureRange0 = new FeatureRange (feature0, j, j + 1);
         
         feature1=new Feature(directSlot1, sequences.get(i+1));
         feature1.setColor(Color.red);
         FeatureRange featureRange1 = new FeatureRange (feature1, k, k + 1);
         
         feature2=new Feature(reverseSlot0, sequences.get(i+2));
         feature2.setColor(Color.green);
         FeatureRange featureRange2 = new FeatureRange (feature2, l, l + 1);    
      }
                  
      try {
         //create a PNG file
         CgviewIO.writeToPNGFile(cgview, ".\\CgviewTest0.png");
      }
      catch (IOException e) {
         e.printStackTrace(System.err);
         System.exit(1);
      }
   }
}