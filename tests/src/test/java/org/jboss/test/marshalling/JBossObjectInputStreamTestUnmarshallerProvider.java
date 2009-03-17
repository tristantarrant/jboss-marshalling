/*
 * JBoss, Home of Professional Open Source
 * Copyright 2009, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.test.marshalling;

import java.io.IOException;

import org.jboss.marshalling.ByteInput;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.marshalling.Unmarshaller;
import org.jboss.serial.io.JBossObjectInputStreamSharedTree;
import org.testng.SkipException;

/**
 *
 */
public final class JBossObjectInputStreamTestUnmarshallerProvider implements TestUnmarshallerProvider {

   public Unmarshaller create(final MarshallingConfiguration config, final ByteInput source) throws IOException {
      if (config.getClassExternalizerFactory() != null ||
            config.getClassResolver() != null ||
            config.getClassTable() != null ||
            config.getObjectResolver() != null ||
            config.getObjectTable() != null ||
            config.getStreamHeader() != null) {
         throw new SkipException("Don't need extra features for JBossSerialization compatibility tests");           
      }
      final JBossObjectInputStreamSharedTree ois = new JBossObjectInputStreamSharedTree(Marshalling.createInputStream(source));
      return new JBossObjectInputStreamUnmarshaller(ois);
   }
}