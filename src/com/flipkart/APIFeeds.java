/***
 * The main class to execute.
 * Please refer to the instructions.txt
 *
 * @author vijay.v@flipkart.com
 * @version 1.0
 * Copyright (c) Flipkart India Pvt. Ltd.
 */

import java.lang.*;
import java.util.*;
import java.io.*;

public class APIFeeds {

    private DataParser parser;

    APIFeeds(String affiliateId, String affiliateToken, String downloadType) {
        if(downloadType.equalsIgnoreCase("XML")) {
//            parser = new XMLDataParser(affiliateId, affiliateToken);
            System.out.println("Usage: APIFeeds <Affiliate ID> <Affiliate Token> <JSON>");

        }
        else {
            parser = new JSONDataParser(affiliateId, affiliateToken);
        }
    }

    public DataParser getParser() {
        return parser;
    }

    public static void main(String args[]) {

        /**
         * Usage: APIFeeds <AffiliateID> <AffiliateToken> <XML/JSON>
         */
        if(args.length < 3) {
            System.out.println(); System.out.println();
            System.out.println("Usage: APIFeeds <Affiliate ID> <Affiliate Token> <JSON>");
            System.out.println(); System.out.println();
            return;
        }

        try {
            if (args[2].equalsIgnoreCase("XML")) {
                //            parser = new XMLDataParser(affiliateId, affiliateToken);
                System.out.println("Usage: APIFeeds <Affiliate ID> <Affiliate Token> <JSON>");
                return;
            }
            APIFeeds feeds = new APIFeeds(args[0], args[1], args[2]);

            // Query the API service to get the list of categories and the corresponding URLs and store it
            // locally in productDirectory Map.
            if(feeds.getParser().initializeProductDirectory()) {

                System.out.println("Choose one of the categories:");
                // Get the list of categories from the locally stored productDirectory Map.
                Iterator<String> category_iterator = feeds.getParser().getProductDirectory().keySet().iterator();
                while(category_iterator.hasNext()) { System.out.println(category_iterator.next()); }
                System.out.print("Enter a category (or type 'q' to quit): ");
                Scanner s = new Scanner(System.in);
                String category;

                do {
                    category = s.nextLine();
                    if(category.equalsIgnoreCase("q")) { return; }

                    if(!feeds.getParser().getProductDirectory().keySet().contains(category)) {
                        System.out.print("Enter a valid category (or type 'q' to quit): ");
                    }
                    else {
                        break;
                    }
                } while(Boolean.TRUE);

                int count = 0;
                // Get a list of products for the given category.
                Iterator<ProductInfo>  products_iterator = feeds.getParser().getProductList(category).listIterator();
                while(products_iterator.hasNext()) {
                    ProductInfo product = products_iterator.next();
                    if(product.isInStock()) {
                        // Some of the fields are printed.
                        System.out.println("Title: " + product.getTitle());
                        System.out.println("URL: " + product.getProductUrl());
                        System.out.println("Price: " + product.getMrp() + "\n\n");
                        count++;
                    }
                }

                System.out.println("Found " + count + " products in " + category + " category.\n\n");
            }
            else {
                System.out.println("Unable to contact the Flipkart Affiliate API service.");
            }
        }
        catch(AffiliateAPIException e) {
            System.out.println("API Exception raised: " + e.getMessage());
        }
    }
}

