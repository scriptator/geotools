/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2002-2008, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotools.sld.bindings;

import java.util.List;
import javax.xml.namespace.QName;
import org.geotools.api.style.NamedLayer;
import org.geotools.api.style.StyleFactory;
import org.geotools.api.style.StyledLayer;
import org.geotools.api.style.StyledLayerDescriptor;
import org.geotools.api.style.UserLayer;
import org.geotools.api.util.InternationalString;
import org.geotools.xsd.AbstractComplexBinding;
import org.geotools.xsd.ElementInstance;
import org.geotools.xsd.Node;
import org.picocontainer.MutablePicoContainer;

/**
 * Binding object for the element http://www.opengis.net/sld:StyledLayerDescriptor.
 *
 * <p>
 *
 * <pre>
 *         <code>
 *  &lt;xsd:element name="StyledLayerDescriptor"&gt;
 *      &lt;xsd:annotation&gt;
 *          &lt;xsd:documentation&gt;         A StyledLayerDescriptor is a
 *              sequence of styled layers, represented         at the first
 *              level by NamedLayer and UserLayer elements.       &lt;/xsd:documentation&gt;
 *      &lt;/xsd:annotation&gt;
 *      &lt;xsd:complexType&gt;
 *          &lt;xsd:sequence&gt;
 *              &lt;xsd:element ref="sld:Name" minOccurs="0"/&gt;
 *              &lt;xsd:element ref="sld:Title" minOccurs="0"/&gt;
 *              &lt;xsd:element ref="sld:Abstract" minOccurs="0"/&gt;
 *              &lt;xsd:choice minOccurs="0" maxOccurs="unbounded"&gt;
 *                  &lt;xsd:element ref="sld:NamedLayer"/&gt;
 *                  &lt;xsd:element ref="sld:UserLayer"/&gt;
 *              &lt;/xsd:choice&gt;
 *          &lt;/xsd:sequence&gt;
 *          &lt;xsd:attribute name="version" type="xsd:string" use="required" fixed="1.0.0"/&gt;
 *      &lt;/xsd:complexType&gt;
 *  &lt;/xsd:element&gt;
 *
 *          </code>
 *         </pre>
 *
 * @generated
 */
public class SLDStyledLayerDescriptorBinding extends AbstractComplexBinding {
    StyleFactory styleFactory;

    public SLDStyledLayerDescriptorBinding(StyleFactory styleFactory) {
        this.styleFactory = styleFactory;
    }

    /** @generated */
    @Override
    public QName getTarget() {
        return SLD.STYLEDLAYERDESCRIPTOR;
    }

    /**
     *
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated modifiable
     */
    @Override
    public int getExecutionMode() {
        return AFTER;
    }

    /**
     *
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated modifiable
     */
    @Override
    public Class getType() {
        return StyledLayerDescriptor.class;
    }

    /**
     *
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated modifiable
     */
    @Override
    public void initialize(ElementInstance instance, Node node, MutablePicoContainer context) {}

    /**
     *
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated modifiable
     */
    @Override
    public Object parse(ElementInstance instance, Node node, Object value) throws Exception {
        StyledLayerDescriptor sld = styleFactory.createStyledLayerDescriptor();

        // &lt;xsd:element ref="sld:Name" minOccurs="0"/&gt;
        if (node.hasChild("Name")) {
            sld.setName((String) node.getChildValue("Name"));
        }

        // &lt;xsd:element ref="sld:Title" minOccurs="0"/&gt;
        if (node.hasChild("Title")) {
            InternationalString intString = (InternationalString) node.getChildValue("Title");
            sld.setTitle(intString.toString());
        }

        // &lt;xsd:element ref="sld:Abstract" minOccurs="0"/&gt;
        if (node.hasChild("Abstract")) {
            InternationalString intString = (InternationalString) node.getChildValue("Abstract");
            sld.setAbstract(intString.toString());
        }

        // &lt;xsd:choice minOccurs="0" maxOccurs="unbounded"&gt;
        //     &lt;xsd:element ref="sld:NamedLayer"/&gt;
        //     &lt;xsd:element ref="sld:UserLayer"/&gt;
        // &lt;/xsd:choice&gt;
        StyledLayer[] layers = null;

        if (node.hasChild(NamedLayer.class)) {
            List<NamedLayer> namedLayers = node.getChildValues(NamedLayer.class);
            layers = namedLayers.toArray(new StyledLayer[namedLayers.size()]);
        } else if (node.hasChild(UserLayer.class)) {
            List<UserLayer> userLayers = node.getChildValues(UserLayer.class);
            layers = userLayers.toArray(new StyledLayer[userLayers.size()]);
        }

        sld.setStyledLayers(layers);

        // &lt;xsd:attribute name="version" type="xsd:string" use="required" fixed="1.0.0"/&gt;
        // TODO: no version?
        return sld;
    }
}
