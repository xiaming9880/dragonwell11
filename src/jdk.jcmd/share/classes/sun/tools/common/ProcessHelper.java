/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package sun.tools.common;

import java.lang.reflect.Method;

/**
 * A helper class to retrieve the main class name for a running
 * Java process.
 */

public interface ProcessHelper {

    /**
     * Returns an instance of the ProcessHelper class.
     *
     * @return ProcessHelper object or null if not supported on this platform.
     */
    public static ProcessHelper platformProcessHelper() {
        try {
            Class<?> c = Class.forName("sun.tools.ProcessHelper");
            @SuppressWarnings("unchecked")
            Method m = c.getMethod("getInstance");
            return (ProcessHelper) m.invoke(null);
        } catch (ClassNotFoundException e) {
            return null;
        } catch (ReflectiveOperationException e) {
            throw new InternalError(e);
        }
    }


    /**
     * Returns the main class name for the given Java process
     *
     * @param pid - process ID (pid)
     * @return main class name or null if the main class could not be retrieved
     */

    String getMainClass(String pid);
}
