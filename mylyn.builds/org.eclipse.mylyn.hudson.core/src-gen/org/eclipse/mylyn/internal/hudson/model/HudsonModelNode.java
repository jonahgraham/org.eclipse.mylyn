/*******************************************************************************
 * Copyright (c) 2010 Tasktop Technologies and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 *     Tasktop Technologies - initial API and implementation
 *******************************************************************************/

// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 

package org.eclipse.mylyn.internal.hudson.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for hudson.model.Node complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="hudson.model.Node">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="assignedLabel" type="{}hudson.model.Label" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mode" type="{}hudson.model.Node-Mode" minOccurs="0"/>
 *         &lt;element name="nodeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nodeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numExecutors" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hudson.model.Node", propOrder = { "assignedLabel", "mode", "nodeDescription", "nodeName",
		"numExecutors" })
@XmlSeeAlso({ HudsonModelHudson.class })
@SuppressWarnings("all")
public class HudsonModelNode {

	protected List<HudsonModelLabel> assignedLabel;

	protected HudsonModelNodeMode mode;

	protected String nodeDescription;

	protected String nodeName;

	protected int numExecutors;

	/**
	 * Gets the value of the assignedLabel property.
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
	 * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
	 * the assignedLabel property.
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getAssignedLabel().add(newItem);
	 * </pre>
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link HudsonModelLabel }
	 */
	public List<HudsonModelLabel> getAssignedLabel() {
		if (assignedLabel == null) {
			assignedLabel = new ArrayList<HudsonModelLabel>();
		}
		return this.assignedLabel;
	}

	/**
	 * Gets the value of the mode property.
	 * 
	 * @return possible object is {@link HudsonModelNodeMode }
	 */
	public HudsonModelNodeMode getMode() {
		return mode;
	}

	/**
	 * Sets the value of the mode property.
	 * 
	 * @param value
	 *            allowed object is {@link HudsonModelNodeMode }
	 */
	public void setMode(HudsonModelNodeMode value) {
		this.mode = value;
	}

	/**
	 * Gets the value of the nodeDescription property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getNodeDescription() {
		return nodeDescription;
	}

	/**
	 * Sets the value of the nodeDescription property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setNodeDescription(String value) {
		this.nodeDescription = value;
	}

	/**
	 * Gets the value of the nodeName property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getNodeName() {
		return nodeName;
	}

	/**
	 * Sets the value of the nodeName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setNodeName(String value) {
		this.nodeName = value;
	}

	/**
	 * Gets the value of the numExecutors property.
	 */
	public int getNumExecutors() {
		return numExecutors;
	}

	/**
	 * Sets the value of the numExecutors property.
	 */
	public void setNumExecutors(int value) {
		this.numExecutors = value;
	}

}