/* -*- mode: java; c-basic-offset: 4; tab-width: 4; indent-tabs-mode: nil -*- */
/*
 * Copyright 2013 Real Logic Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.real_logic.sbe.xml;

import org.w3c.dom.Node;

import static uk.co.real_logic.sbe.xml.XmlSchemaParser.*;

/**
 * An SBE type. One of encodedDataType, compositeType, enumType, or setType per the SBE spec.
 */
public class Type
{
    /** Default presence attribute for Types */
    public static final String DEFAULT_PRESENCE = "required";

    private final String name;
    private final Presence presence;
    private final String description;
    private final String fixUsage;

    /**
     * Construct a new Type from XML Schema. Called by subclasses to mostly set common fields
     *
     * @param node from the XML Schema Parsing
     */
    public Type(final Node node)
    {
        name = getAttributeValue(node, "name");
        // The schema should set default, so "presence" should always be available, but let's set a default anyway
        presence = Presence.lookup(getAttributeValue(node, "presence", "required"));
        description = getAttributeValueOrNull(node, "description");
        fixUsage = getAttributeValueOrNull(node, "fixUsage");
    }

    /**
     * Construct a new Type from direct values.
     *
     * @param name of the type
     * @param presence of the type
     * @param description of the type or null
     * @param fixUsage of the type or null
     */
    public Type(final String name,
                final Presence presence,
                final String description,
                final String fixUsage)
    {
        this.name = name;
        this.presence = presence;
        this.description = description;
        this.fixUsage = fixUsage;
    }

    /**
     * Return the name of the type
     *
     * @return name of the Type
     */
    public String getName()
    {
        return name;
    }

    /**
     * Return the presence of the type
     *
     * @return presence of the Type
     */
    public Presence getPresence()
    {
        return presence;
    }

    /**
     * The size (in octets) of the Type.
     * <p>
     * Overridden by subtypes. This returns 0 by default.
     *
     * @return size of the type in octets
     */
    public int size()
    {
        return 0;
    }

    /**
     * The description of the Type (if set) or null
     *
     * @return description set by the type or null
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * The fixUsage of the Type
     *
     * @return fixUsage of the Type if set or null if not set
     */
    public String getFixUsage()
    {
        return fixUsage;
    }
}
