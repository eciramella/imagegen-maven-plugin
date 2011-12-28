package org.apache.maven.plugins;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;

public class SolidImage extends AbstractImage {

    Map<String, String> colorSolid;

    public SolidImage(final Log log, final Integer height, final Integer width, final String type,
            final Map<String, String> colorSolid, final Map<String, String> copyright,
            final Map<String, String>[] textFields, final Map<String, String>[] imageOverlays,
            final String filename, final File outputDirectory, final boolean insertVersion,
            final boolean trimSnapshot, final Map<String, String> versionDetails) {
        this.log = log;
        this.height = height;
        this.width = width;
        this.type = type;
        this.filename = filename;
        this.outputDirectory = outputDirectory;
        this.colorSolid = colorSolid;
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

        final Graphics2D g2d = bufferedImage.createGraphics();

        g2d.setPaint(Color.BLACK);

        if (colorSolid == null) {
            log.info("Color solid is null");
        }
        log.info("start r " + Integer.parseInt(colorSolid.get("r")) + " start g "
                + Integer.parseInt(colorSolid.get("g")) + " start b "
                + Integer.parseInt(colorSolid.get("b")));
        final Color color = new Color(Integer.parseInt(colorSolid.get("r")),
                Integer.parseInt(colorSolid.get("g")), Integer.parseInt(colorSolid.get("b")));

        g2d.setPaint(color);
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
