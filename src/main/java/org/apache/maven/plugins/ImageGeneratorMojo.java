package org.apache.maven.plugins;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * This mojo will attempt to generate either a solid or gradient image based
 * upon the user's selections and configuration options.
 * 
 * @goal generate
 * 
 * @phase generate-sources
 */
public class ImageGeneratorMojo extends AbstractMojo {

    /**
     * Whether or not to use a gradient for this image.
     * 
     * @parameter
     * @required
     */
    private boolean gradient;

    /**
     * Location of the file to be generated.
     * 
     * @parameter expression="${project.build.directory}"
     * @required
     */
    private File outputDirectory;

    /**
     * Height of the generated image you'd like in pixels.
     * 
     * @parameter
     * @required
     */
    private Integer height;

    /**
     * Width of the generated image you'd like in pixels.
     * 
     * @parameter
     * @required
     */
    private Integer width;

    /**
     * Type - acceptable values are png and jpg or both (for now).
     * 
     * Both obviously will generate both image types.
     * 
     * @parameter
     * @required
     */
    private String type;

    /**
     * copyright - Put in the copyright string.
     * 
     * This property should conditionally take the date in the text parameter.
     * 
     * If the date is supplied, then just put in what the user has typed. If the
     * date is NOT specified, then add it.
     * 
     * shadow - if you would like the text to cast a shadow.
     * 
     * @parameter
     */
    private Map<String, String> copyright;

    /**
     * text - Any other text you would like to display in the image and their
     * associated properties.
     * 
     * x - the x coordinate of the text
     * 
     * y - the y coordinate of the text
     * 
     * text - the text you'd like displayed
     * 
     * font - the font you'd like to use
     * 
     * fontsize - the fontsize you'd like to use
     * 
     * shadow - if you would like the text to cast a shadow.
     * 
     * @parameter
     */
    private Map<String, String>[] textFields;

    /**
     * imagePath - The path to the image you would like to overlay.
     * 
     * x - the x coordinate of where you would like to place the image.
     * 
     * y - the y coordinate of where you would like to place the image.
     * 
     * shadow - if you would like the image to cast a shadow.
     * 
     * scaling - what percentage of the original image size to use. Defaults to
     * 100%
     * 
     * @parameter
     */
    private Map<String, String>[] imageOverlays;

    /**
     * The details surrounding the images you'd like to overlay.
     * 
     * x - the X coordinate of the image to overlay.
     *
     * y - the Y coordinate of the image to overlay.
     * 
     * shadow - (boolean) - add a drop shadow.
     *
     * scaling - This integer is used to divide the actual settings. So 2 will be 1/2 of the original size.
     *
     * rotation - This integer determines what degree from 0 we should rotate the x axis to the y axis.
     *
     * imagePath - The LOCAL path to where the overlay image should be found.  URLS are not currently
     * supported.
     * 
     * @parameter
     */
    private Map<String, String> colorStart;

    /**
     * The RGB values of the ending color.
     * 
     * r - the red value
     * 
     * g - the green value
     * 
     * b - the blue value
     * 
     * @parameter
     */
    private Map<String, String> colorEnd;

    /**
     * The the location of where the gradient should start.
     * 
     * r - the red value
     * 
     * g - the green value
     * 
     * b - the blue value
     * 
     * @parameter
     */
    private int firstColorX;

    /**
     * The the location of where the gradient should start.
     * 
     * @parameter
     */
    private int firstColorY;

    /**
     * The the location of where the gradient should start.
     * 
     * @parameter
     */
    private int secondColorX;

    /**
     * The the location of where the gradient should start.
     * 
     * @parameter
     */
    private int secondColorY;

    /**
     * The RGB values of the solid color for a solid image.
     * 
     * @parameter
     */
    private Map<String, String> colorSolid;

    /**
     * File name
     * 
     * @parameter default-value="image"
     */
    private String filename;

    /**
     * Would you like the version of the product leveraging this plugin to be
     * reflected in the image?
     * 
     * @parameter default-value="false"
     */
    private boolean insertVersion;

    /**
     * If you plan on inserting the version string, should we trim off the
     * -SNAPSHOT extension?
     * 
     * @parameter default-value="true"
     */
    private boolean trimSnapshot;

    /**
     * What are the details about the version insertion?
     * 
     * x - the x coordinate of the text
     * 
     * y - the y coordinate of the text
     * 
     * font - the font you'd like to use
     * 
     * fontsize - the fontsize you'd like to use
     * 
     * @parameter
     */
    private Map<String, String> versionDetails;

    public void execute() throws MojoExecutionException {
        // Create an image to save
        final BufferedImage rendImage = null;
        if (type.equalsIgnoreCase("png") && type.equalsIgnoreCase("jpg")) {
            throw new MojoExecutionException(
                    "You've selected an invalid file type.  We currently only support png and jpg images");
        }

        if (gradient) {
            final GradientImage gi = new GradientImage(getLog(), height, width, type, colorStart,
                    firstColorX, firstColorY, colorEnd, secondColorX, secondColorY, copyright,
                    textFields, imageOverlays, filename, outputDirectory, insertVersion,
                    trimSnapshot, versionDetails);
            gi.execute(rendImage);
        } else {
            final SolidImage si = new SolidImage(getLog(), height, width, type, colorSolid,
                    copyright, textFields, imageOverlays, filename, outputDirectory, insertVersion,
                    trimSnapshot, versionDetails);
            si.execute(rendImage);
        }

    }

}
