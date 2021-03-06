//$HeadURL$
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2010 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
 ----------------------------------------------------------------------------*/
package org.deegree.commons.utils.io;

import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.apache.commons.io.IOUtils.toByteArray;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 * @author <a href="mailto:schmitz@lat-lon.de">Andreas Schmitz</a>
 * @author last edited by: $Author$
 * 
 * @version $Revision$, $Date$
 */
public class Utils {

    /**
     * Output stream that loses all data.
     */
    public static final OutputStream DEV_NULL = new OutputStream() {
        @Override
        public void write( int b )
                                throws IOException {
            // lost
        }
    };

    /**
     * Makes byte by byte comparison and determines the percentage of equal bytes. This is a very crude method, but
     * works well enough for images. Take into account that the encoding of images (such as png) might be different
     * across Java implementations and even versions.
     * 
     * @param in1
     * @param in2
     * @return the percentage (0..1)
     * @throws IOException
     */
    public static double determineSimilarity( InputStream in1, InputStream in2 )
                            throws IOException {
        try {
            byte[] buf1 = toByteArray( in1 );
            byte[] buf2 = toByteArray( in2 );
            long equal = 0;
            for ( int i = 0; i < buf1.length; ++i ) {
                if ( i < buf2.length && buf1[i] == buf2[i] ) {
                    ++equal;
                }
            }
            return (double) equal / (double) buf1.length;
        } finally {
            closeQuietly( in1 );
            closeQuietly( in2 );
        }
    }

}
