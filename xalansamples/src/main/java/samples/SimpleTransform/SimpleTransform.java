/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the  "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * $Id$
 */
package samples.SimpleTransform;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *  Use the TraX interface to perform a transformation in the simplest manner possible
 *  (3 statements).
 */
public class SimpleTransform
{
  public static void main(String[] args)
    throws TransformerException, TransformerConfigurationException,
       FileNotFoundException, IOException
  {
    // Use the static TransformerFactory.newInstance() method to instantiate
    // a TransformerFactory. The javax.xml.transform.TransformerFactory
    // system property setting determines the actual class to instantiate --
    // for Xalan, org.apache.xalan.transformer.TransformerImpl.
    TransformerFactory tFactory = TransformerFactory.newInstance();

    // Grab the Name of the Stylesheet from the command line
    String stylesheet="birds.xml";
    if (args.length == 0)
    {
       System.out.println("You must provide the path and name to a stylesheet to process birds.xml into birds.out");
       System.out.println("Defaulting to birds.xsl");
    }
    else
       stylesheet = args[0];

    System.out.println("Transforming birds.xml with stylesheet "+ stylesheet);

    //  processes the stylesheet into a compiled Templates object.
    Transformer transformer = tFactory.newTransformer(new StreamSource("birds.xsl"));

    // Use the Transformer to apply the associated Templates object to
    // an XML document and write the output to a file
    transformer.transform(new StreamSource("birds.xml"),
              new StreamResult(new FileOutputStream("birds.out")));

    System.out.println("************* The result is in birds.out *************");
  }
}
