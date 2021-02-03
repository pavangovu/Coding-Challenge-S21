# ACM Research Coding Challenge (Spring 2021)

## No Collaboration Policy

**You may not collaborate with anyone on this challenge.** You _are_ allowed to use Internet documentation. If you _do_ use existing code (either from Github, Stack Overflow, or other sources), **please cite your sources in the README**.

## Submission Procedure

Please follow the below instructions on how to submit your answers.

1. Create a **public** fork of this repo and name it `ACM-Research-Coding-Challenge-S21`. To fork this repo, click the button on the top right and click the "Fork" button.
2. Clone the fork of the repo to your computer using `git clone [the URL of your clone]`. You may need to install Git for this (Google it).
3. Complete the Challenge based on the instructions below.
4. Submit your solution by filling out this [form](https://acmutd.typeform.com/to/uqAJNXUe).

## Question One

Genome analysis is the identification of genomic features such as gene expression or DNA sequences in an individual's genetic makeup. A genbank file (.gb) format contains information about an individual's DNA sequence. The following dataset in `Genome.gb` contains a complete genome sequence of Tomato Curly Stunt Virus. 

**With this file, create a circular genome map and output it as a JPG/PNG/JPEG format.** We're not looking for any complex maps, just be sure to highlight the features and their labels.

**You may use any programming language you feel most comfortable. We recommend Python because it is the easiest to implement. You're allowed to use any library you want to implement this**, just document which ones you used in this README file. Try to complete this as soon as possible.

Regardless if you can or cannot answer the question, provide a short explanation of how you got your solution or how you think it can be solved in your README.md file. However, we highly recommend giving the challenge a try, you just might learn something new!

**WORKS CITED**
All external libraries, documentation, and API were referenced from the following websites/articless:

https://paulstothard.github.io/cgview/

Paul Stothard, David S. Wishart, Circular genome visualization and exploration using CGView, Bioinformatics, Volume 21, Issue 4, 15 February 2005, Pages 537–539, https://doi.org/10.1093/bioinformatics/bti054

Paul Stothard, Jason R Grant, Gary Van Domselaar, Visualizing and comparing circular genomes using the CGView family of tools, Briefings in Bioinformatics, Volume 20, Issue 4, July 2019, Pages 1576–1582, https://doi.org/10.1093/bib/bbx081

https://www.ncbi.nlm.nih.gov/pmc/articles/PMC6728899/

Google Images of Circular Genome Maps: https://www.google.com/search?q=circular+genomes&rlz=1C1CHBF_enUS839US839&sxsrf=ALeKk03AeqFV_btWqlizTAOioQ_BdA7sag:1612330386099&source=lnms&tbm=isch&sa=X&ved=2ahUKEwjZ1LHf_szuAhXKGs0KHaAXCSQQ_AUoAXoECBYQAw&biw=1366&bih=625


**Solution Explanation**
At first, I had to read the problem a couple times over in order to get a grasp of the programming objective. Then, I looked through all the files in the repository. I paid particular to attention the contents of the genbank file, as I knew that my implementation would be centered around the formatting of this file. After that, I proceeded to do a thorough Google Image search on "circular genome maps". Prior to this coding challenge, I had no idea what a genome map was, but after spending some time looking through those images, I understood that it was essentially a way to visually represent the DNA sequencing of an organism. I was slowly able to make a connection between the images and the information that was provided in the genbank file. It was then that I looked online for libraries that were designed for the creation of circular genome maps, given the necessary information in a .gb file. I came across several such libraries/tools, including CGView, BLAST Ring Image Generator, Genome Diagram, Circos, DNAPlotter, and SOFIA. Some of these libraries were designed for Python, but knowing that my strength is in Java, I limited myself to only the Java libraries. Eventually, after reading through their respective documentations, I settled on CGView, which provided dedicated Java libraries and a clear API. Using this documentation, I slowly started planning and developing. First, I implemented the file parsing, which allowed me to go through the .gb file line by line and extract the necessary information. I did this with a simple Scanner and some Java STL functions. Then, I referred to the CGView API to configure the resolution of the Cgview PNG that was to be produced. Then, I began the construction of my Cgview objects, features, and labels, of course referring to the Cgview API and provided examples along the way. At this point, I stuck close to the API suggestions rather than putting in my own customizations, so as to prevent possible cascading errors. Once I had somewhat working prototype, I produced my first PNG. At first glance, the PNG looked fine, but I noticed that several of the labels were missing. Even when I ran it again, I saw that the output read "labels were removed". This proved to be the hardest obstacle to overcome, as I had no idea why this was happening. I wanted to ensure that all the labels were included, to ensure maximum accuracy. After hours of scrolling through API, commenting out blocks of code, running the program with dummy values in debugger mode, and analyzing the results, I found the source of the issue: because there were so many labels, Cgview would be forced to delete labels that densely overlapped on the same tick mark. Thus, after making some changes with the radius, resolution, units, and scaling, I was able to fix the problem. After that, I changed the colors to alternate for easier readability.