/**
 * Created by Isaac on 3/31/2016.
 */

import java.io.IOException;

public class SimplePDFReader{

    public static void main(String[] args) throws IOException {

        PDFManager pdfManager = new PDFManager();
        pdfManager.setFilePath("F:\\dailysales\\3.6\\1.pdf");
        String text;
        text = pdfManager.ToText();
        System.out.println(text);
    }
}
