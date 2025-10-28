package gh2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

public class HISTHEME {
    public static void main(String[] args) {
        GuitarPlayer player = new GuitarPlayer(new java.io.File("gh2/his_theme.mid"));
        player.play();
        player.play();

    }
}
