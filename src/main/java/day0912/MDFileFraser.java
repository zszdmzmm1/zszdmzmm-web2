package day0912;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.*;

public class MDFileFraser {
    public static void main(String[] args) {
        File file = new File("src/main/java/day0911/md/store.md");
        InputStream inputStream;
        byte[] mdFile;
        try {
            inputStream = new FileInputStream(file);
            mdFile = inputStream.readAllBytes();
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String sMdFile = new String(mdFile);
        Parser parser = Parser.builder().build();
        Node document = parser.parse(sMdFile);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String html = renderer.render(document);
        System.out.println(html);
    }
}
