/**
 * Created by Isaac on 3/31/2016.
 */

import java.io.IOException;
import java.util.ArrayList;

public class SimplePDFReader{

    public static void main(String[] args) throws IOException {

        PDFManager pdfManager = new PDFManager();
        pdfManager.setFilePath("F:\\dailysales\\3.6\\1.pdf");
        String text;
        text = pdfManager.ToText();
        String[] strArray = textExtract(text); //split by lines
        ArrayList<Product> allProduct = new ArrayList<>();
        System.out.println(strArray[1]);

        String str = strArray[1];
        String[] subline = str.split(" ");
        for (int i = 0; i < subline.length; i++){
            System.out.println(subline[i]);
        }

        String productName = "";
        int SKUIndex = 0;

        for (int i = 0; i < subline.length; i++){
            String subtext = subline[i];
            if (!isNumeric(subtext)){
                productName += subtext;
            }
            else{
                SKUIndex = i;
                break;
            }
        }

        System.out.print(SKUIndex);

        int index = 5;
        String test = subline[index];
        int indexM = test.indexOf("$");
        System.out.println("indexM: "+indexM);
        String qu = test.substring(0, indexM);
        String pr = test.substring(indexM + 2);
        System.out.println("quantity: " + qu);
        System.out.println("Price: " + pr);





/*
        for (int i = 0; i < strArray.length; i++){
            String line = strArray[i];
            Product newProduct = lineSplit(line);
            allProduct.add(newProduct);
        }
        String name = allProduct.get(1).name;
        int SKU = allProduct.get(1).SKU;
        int quantity = allProduct.get(1).quantity;
        int price = allProduct.get(1).price;
        System.out.println(name);
        System.out.println(SKU);
        System.out.println(quantity);
        System.out.println(price);
*/
    }

    //split the string by lines
    private static String[] textExtract(String text){
        String start = "小计";
        int startIndex = text.indexOf(start);
        int endIndex = text.lastIndexOf(start);
        String subText = text.substring(startIndex + 2, endIndex);
        subText = subText.trim();
        String[] strArray = subText.split("\r\n");
        return strArray;
    }

    //split the single line into names, SKU No., quantity and price
    private static Product lineSplit(String text){
        String[] subArray = text.split(" ");
        String productName = "";
        String SKU = "";
        String quantity = "";
        String price = "";
        int SKUIndex = 0;
        for (int i = 0; i < subArray.length; i++){
            String subtext = subArray[i];
            if (!isNumeric(subtext)){
                productName += subtext;
            }
            else{
                SKUIndex = i;
                break;
            }
        }
        SKU = subArray[SKUIndex];
        quantity = subArray[SKUIndex + 1];
        quantity = quantity.replace('$',' ');
        price = subArray[SKUIndex + 2];

        Product newProduct = new Product(productName, Integer.parseInt(SKU), Integer.parseInt(quantity), Integer.parseInt(price));
        return newProduct;
    }

    //see if the string is SKU No., which is all digit
    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
