package controllers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class SystemAdmin extends Controller {
    public Result importBooks() {
        try {
            String confDir = Paths.get("conf").toAbsolutePath().toString();
            File inputFile = new File(confDir, "books_data/books.html");
            Document doc = Jsoup.parse(inputFile, "UTF-8");

            StringBuilder sb = new StringBuilder();
            for (Element htmlTag : doc.select("html")) {
                Elements bookTitles = htmlTag.select("a.bookTitle");
                Elements bookElements = htmlTag.select("a.leftAlignedImage");
                Elements authorNames = htmlTag.select("a.authorName > span");
                Elements details = htmlTag.select("span.greyText.smallText");

                int count = 0;

                for (int i = 0; i < bookTitles.size(); i++) {
                    String bookTitle = bookTitles.get(i).text();
                    String authorName = authorNames.get(i).text();
                    String detailText = details.get(i).text();
                    String[] parts = detailText.split("—");
                    String avgRating = parts[0].replace("avg rating ", "").trim();
                    String ratingsCount = parts[1].replace("ratings", "").trim();
                    String publishDate = parts[2].replace("published", "").trim();
                    String goodReadsLink = bookElements.get(i).attr("href");

                    sb.append(++count + "\n");
                    sb.append(bookTitle + "\n");
                    sb.append(authorName  + "\n");
                    sb.append(avgRating  + "\n");
                    sb.append(ratingsCount  + "\n");
                    sb.append(publishDate  + "\n");
                    sb.append("<a href='https://www.goodreads.com/" + goodReadsLink  + "' target='_blank'>Goodreads</a>"  + "\n");
                    sb.append("========================"  + "\n\n");
                }
            }
            return ok(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return internalServerError("Failed to parse the HTML file.");
        }
    }
}
