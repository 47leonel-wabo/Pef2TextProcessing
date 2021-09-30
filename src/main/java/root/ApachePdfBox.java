package root;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import root.domain.Enterprise;

import java.io.File;

public class ApachePdfBox {

    private final static String DOCUMENT_TO_READ = "/home/ddok/Desktop/Pef2TextProcessing/samples/wordpress-pdf-invoice-plugin-sample.pdf";
    //    private static final String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static Enterprise enterprise;

    public static void main(String[] args) {
        showExtractedText(DOCUMENT_TO_READ);
    }

    private static void showExtractedText(final String path) {
        try {
            // Loading an existing file
            File targetFile = new File(path);
            PDDocument document = PDDocument.load(targetFile);
            // Instantiate Stripper class
            PDFTextStripper textStripper = new PDFTextStripper();
            String extractedText = textStripper.getText(document);

            // Processing
            extractInformationFromText(extractedText);
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
