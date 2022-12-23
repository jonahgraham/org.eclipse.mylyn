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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for hudson.model.LoadStatistics complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="hudson.model.LoadStatistics">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="busyExecutors" type="{}hudson.model.MultiStageTimeSeries" minOccurs="0"/>
 *         &lt;element name="queueLength" type="{}hudson.model.MultiStageTimeSeries" minOccurs="0"/>
 *         &lt;element name="totalExecutors" type="{}hudson.model.MultiStageTimeSeries" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hudson.model.LoadStatistics", propOrder = { "busyExecutors", "queueLength", "totalExecutors" })
@XmlSeeAlso({ HudsonModelOverallLoadStatistics.class })
@SuppressWarnings("all")
public class HudsonModelLoadStatistics {

	protected HudsonModelMultiStageTimeSeries busyExecutors;

	protected HudsonModelMultiStageTimeSeries queueLength;

	protected HudsonModelMultiStageTimeSeries totalExecutors;

	/**
	 * Gets the value of the busyExecutors property.
	 * 
	 * @return possible object is {@link HudsonModelMultiStageTimeSeries }
	 */
	public HudsonModelMultiStageTimeSeries getBusyExecutors() {
		return busyExecutors;
	}

	/**
	 * Sets the value of the busyExecutors property.
	 * 
	 * @param value
	 *            allowed object is {@link HudsonModelMultiStageTimeSeries }
	 */
	public void setBusyExecutors(HudsonModelMultiStageTimeSeries value) {
		this.busyExecutors = value;
	}

	/**
	 * Gets the value of the queueLength property.
	 * 
	 * @return possible object is {@link HudsonModelMultiStageTimeSeries }
	 */
	public HudsonModelMultiStageTimeSeries getQueueLength() {
		return queueLength;
	}

	/**
	 * Sets the value of the queueLength property.
	 * 
	 * @param value
	 *            allowed object is {@link HudsonModelMultiStageTimeSeries }
	 */
	public void setQueueLength(HudsonModelMultiStageTimeSeries value) {
		this.queueLength = value;
	}

	/**
	 * Gets the value of the totalExecutors property.
	 * 
	 * @return possible object is {@link HudsonModelMultiStageTimeSeries }
	 */
	public HudsonModelMultiStageTimeSeries getTotalExecutors() {
		return totalExecutors;
	}

	/**
	 * Sets the value of the totalExecutors property.
	 * 
	 * @param value
	 *            allowed object is {@link HudsonModelMultiStageTimeSeries }
	 */
	public void setTotalExecutors(HudsonModelMultiStageTimeSeries value) {
		this.totalExecutors = value;
	}

}
