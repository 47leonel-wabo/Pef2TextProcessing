package root;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;

public class ApachePdfBox {

    private final static String DOCUMENT_TO_READ = "/home/ddok/Desktop/Pef2TextProcessing/samples/wordpress-pdf-invoice-plugin-sample.pdf";

    public static void main(String[] args) {
//        System.out.println("Hello Apache PDFBox!");
        showExtractedText(DOCUMENT_TO_READ);
    }

    private static void showExtractedText(final String path){
        try {
            // Loading an existing file
            File targetFile = new File(path);
            PDDocument document = PDDocument.load(targetFile);
            // Instantiate Stripper class
            PDFTextStripper textStripper = new PDFTextStripper();
            String extractedText = textStripper.getText(document);

            // show text
            System.out.println(extractedText);
        }catch (Exception e){
            System.err.println(e.getLocalizedMessage());
        }
    }
}
