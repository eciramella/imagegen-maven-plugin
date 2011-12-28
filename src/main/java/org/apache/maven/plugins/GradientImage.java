package org.apache.maven.plugins;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;

public class GradientImage extends AbstractImage {

    Map<String, String> colorStart;
    Map<String, String> colorEnd;
    int firstColorX;
    int firstColorY;
    int secondColorX;
    int secondColorY;

    public GradientImage(final Log log, final Integer height, final Integer width,
            final String type, final Map<String, String> colorStart, final int firstColorX,
            final int firstColorY, final Map<String, String> colorEnd, final int secondColorX,
            final int secondColorY, final Map<String, String> copyright,
            final Map<String, String>[] textFields, final Map<String, String>[] imageOverlays,
            final String filename, final File outputDirectory, final boolean insertVersion,
            final boolean trimSnapshot, final Map<String, String> versionDetails) {
        this.log = log;
        this.height = height;
        this.width = width;
        this.type = type;
        this.colorStart = colorStart;
        this.firstColorX = firstColorX;
        this.firstColorY = firstColorY;
        this.colorEnd = colorEnd;
        this.secondColorX = secondColorX;
        this.secondColorY = secondColorY;
        this.filename = filename;
        this.outputDirectory = outputDirectory;
        this.copyright = copyright;
        this.textFields = textFields;
        this.imageOverlays = imageOverlays;
        this.insertVersion = insertVersion;
        this.trimSnapshot = trimSnapshot;
        this.versionDetails = versionDetails;
    }

    // Returns a generated image.
    @Override
    public BufferedImage createImage() throws MojoExecutionException {

        // Create a buffered image in which to draw
        final BufferedImage bufferedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        // Create a graphics contents on the buffered image
        /*
         * Graphics2D g2d = bufferedImage.createGraphics();
         * 
         * // Draw graphics g2d.setColor(Color.white); g2d.fillRect(0, 0, width,
         * height); g2d.setColor(Color.black); g2d.fillOval(0, 0, width,
         * height); // Graphics context no longer needed so dispose it
         * g2d.dispose();
         */

        final Graphics2D g2d = bufferedImage.createGraphics();

        g2d.setPaint(Color.BLACK);

        // fill RoundRectangle2D.Double

        log.info("start r " + Integer.parseInt(colorStart.get("r")) + " start g "
                + Integer.parseInt(colorStart.get("g")) + " start b "
                + Integer.parseInt(colorStart.get("b")));
        log.info("end r " + Integer.parseInt(colorEnd.get("r")) + " end g "
                + Integer.parseInt(colorEnd.get("g")) + " end b "
                + Integer.parseInt(colorEnd.get("b")));
        final Color startcolor = new Color(Integer.parseInt(colorStart.get("r")),
                Integer.parseInt(colorStart.get("g")), Integer.parseInt(colorStart.get("b")));
        final Color endcolor = new Color(Integer.parseInt(colorEnd.get("r")),
                Integer.parseInt(colorEnd.get("g")), Integer.parseInt(colorEnd.get("b")));

        final GradientPaint redtoblue = new GradientPaint(firstColorX, firstColorY, startcolor,
                secondColorX, secondColorY, endcolor, false);
        g2d.setPaint(redtoblue);
        g2d.fillRect(0, 0, this.width, this.height);

        if (copyright != null) {
            addCopyText(g2d);
        }

        if (textFields != null) {
            addText(g2d);
        }

        if (imageOverlays != null) {
            addImages(g2d);
        }

        g2d.dispose();

        return bufferedImage;
    }
}
