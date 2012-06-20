This is a simple maven plugin for automatically generating images for use in your java projects.

There is a ton of documentation within the poms.

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>imagegen-maven-plugin</artifactId>
    <version>${project.version}</version>
    <executions>
         <execution>
             <id>generateImage</id>
             <phase>generate-sources</phase>
             <goals>
                 <goal>generate</goal>
             </goals>
             <configuration>
                <gradient>false</gradient>
                <type>png</type>
                <height>1000</height>
                <width>1000</width>
                <colorSolid>
                    <r>0</r>
                    <g>189</g>
                    <b>255</b>
                </colorSolid>
                
                <!-- OPTIONAL
                <copyright>
                    <x>10</x>
                    <y>20</y>
                    <text>2011 Foo, LLC.</text>
                    <font>Monospaced.bolditalic</font>
                    <fontsize>20</fontsize> -->
		    <!-- OPTIONAL set to true if you'd like a drop shadow behind your image. -->
                    <!-- <shadow>false</shadow> --> 
                    <!-- OPTIONAL text will default to black if this is omitted. -->
                    <!-- <color>0,0,0</color> --> 
                <!--
                </copyright>
                -->
                
                <!-- OPTIONAL
                <textFields>
                    <textField>
                        <x>100</x>
                        <y>250</y>
                        <text>Hello Image!</text>
                        <font>Arial Narrow</font>
                        <fontsize>20</fontsize> -->
                        <!-- OPTIONAL set to true if you'd like a drop shadow behind your image. -->
                        <!-- <shadow>false</shadow> -->
                        <!-- OPTIONAL text will default to black if this is omitted. -->
                        <!-- <color>0,0,0</color> --> 
                     <!--   
                    </textField>
                </textFields>
                -->
                <!-- OPTIONAL
                <imageOverlays>
                    <imageOverlay>
                        <x>100</x>
                        <y>800</y>
                        <imagePath>/path/to/the/image/you/need.jpg</imagePath>
                        -->
                        <!-- OPTIONAL set to true if you'd like a drop shadow behind your image. -->
                        <!--- <shadow>false</shadow> --> 
                        <!-- OPTIONAL what you'd like to divide your image height and width by to reduce it. -->
                        <!-- <scaling>2</scaling> --> 
                        <!--
                    </imageOverlay>
                </imageOverlays>
                -->
             </configuration>
         </execution>
    </executions>
</plugin>
```
