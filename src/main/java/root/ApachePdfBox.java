package root;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import root.domain.Enterprise;

import java.io.File;

public class ApachePdfBox {

    /*
     * IMPORTANT: THIS ONLY WORK FOR THIS SPECIFIC PDF CONTENT STRUCTURE
     * ANOTHER PDF FILE WITH DIFFERENT CONTENT MAY CAUSE THIS PROGRAM TO CRASH
     */

    private final static String DOCUMENT_TO_READ = "./samples/wordpress-pdf-invoice-plugin-sample.pdf";
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

    private static void extractInformationFromText(final String inputText) {

        getOriginatedEnterprise(inputText);
        System.out.println();
        getDestinationEnterprise(inputText);

    }

    private static void getOriginatedEnterprise(final String inputText) {
        // Extract substring starting at 'From:' up to 'To:'
        String substring = inputText.substring(inputText.indexOf("From:"), inputText.indexOf("To:"));
        // Transform that substring into an array, following line by line
        String[] split = substring.split("\\r?\\n");
        enterprise = new Enterprise();
        //We start at position [1] because we want to escape position [0] which hold 'From:'
        enterprise.setName(split[1]);
        enterprise.setBuilding(split[2]);
        enterprise.setStreet(split[3]);
        enterprise.setCityAndZipCode(split[4]);
        enterprise.setEmailAddress(split[5]);

        System.out.println("*************** SOURCE ENTERPRISE DETAILS ****************");
        System.out.println(enterprise);
    }

    private static void getDestinationEnterprise(final String inputText) {
        /*
         * Because we know that after the 'From:' keyword, we'll have 'To:' representing
         * our recipient enterprise, we can get its index and start reading information
         * straight after it.
         */
        String substringStartingAtTo = inputText.substring(inputText.indexOf("To:"));
        // transform this substring into an array of lines
        String[] strings = substringStartingAtTo.split("\\r?\\n");
        // to escape 'To:' line, we start reading at index "1" (four lines)
        enterprise.setName(strings[1]);
        enterprise.setStreet(strings[2]);
        enterprise.setCityAndZipCode(strings[3]);
        enterprise.setEmailAddress(strings[4]);
        // no building here!
        enterprise.setBuilding(null);

        System.out.println("*************** DESTINATION ENTERPRISE DETAILS ****************");
        System.out.println(enterprise);
    }
}
