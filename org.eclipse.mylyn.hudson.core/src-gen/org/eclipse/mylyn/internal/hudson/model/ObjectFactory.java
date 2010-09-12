/*******************************************************************************
 * Copyright (c) 2010 Tasktop Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tasktop Technologies - initial API and implementation
 *******************************************************************************/

// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 

package org.eclipse.mylyn.internal.hudson.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.eclipse.mylyn.internal.hudson.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
@SuppressWarnings("all")
public class ObjectFactory {

    private final static QName _HudsonMavenReportersSurefireAggregatedReport_QNAME = new QName("", "hudson.maven.reporters.SurefireAggregatedReport");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.eclipse.mylyn.internal.hudson.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HudsonTasksTestAggregatedTestResultActionChildReport }
     * 
     */
    public HudsonTasksTestAggregatedTestResultActionChildReport createHudsonTasksTestAggregatedTestResultActionChildReport() {
        return new HudsonTasksTestAggregatedTestResultActionChildReport();
    }

    /**
     * Create an instance of {@link HudsonModelUser }
     * 
     */
    public HudsonModelUser createHudsonModelUser() {
        return new HudsonModelUser();
    }

    /**
     * Create an instance of {@link HudsonTasksTestAbstractTestResultAction }
     * 
     */
    public HudsonTasksTestAbstractTestResultAction createHudsonTasksTestAbstractTestResultAction() {
        return new HudsonTasksTestAbstractTestResultAction();
    }

    /**
     * Create an instance of {@link HudsonMavenReportersSurefireAggregatedReport }
     * 
     */
    public HudsonMavenReportersSurefireAggregatedReport createHudsonMavenReportersSurefireAggregatedReport() {
        return new HudsonMavenReportersSurefireAggregatedReport();
    }

    /**
     * Create an instance of {@link HudsonScmChangeLogSet }
     * 
     */
    public HudsonScmChangeLogSet createHudsonScmChangeLogSet() {
        return new HudsonScmChangeLogSet();
    }

    /**
     * Create an instance of {@link HudsonModelAbstractBuild }
     * 
     */
    public HudsonModelAbstractBuild createHudsonModelAbstractBuild() {
        return new HudsonModelAbstractBuild();
    }

    /**
     * Create an instance of {@link HudsonModelRunArtifact }
     * 
     */
    public HudsonModelRunArtifact createHudsonModelRunArtifact() {
        return new HudsonModelRunArtifact();
    }

    /**
     * Create an instance of {@link HudsonTasksTestAggregatedTestResultAction }
     * 
     */
    public HudsonTasksTestAggregatedTestResultAction createHudsonTasksTestAggregatedTestResultAction() {
        return new HudsonTasksTestAggregatedTestResultAction();
    }

    /**
     * Create an instance of {@link HudsonModelRun }
     * 
     */
    public HudsonModelRun createHudsonModelRun() {
        return new HudsonModelRun();
    }

    /**
     * Create an instance of {@link HudsonModelUserProperty }
     * 
     */
    public HudsonModelUserProperty createHudsonModelUserProperty() {
        return new HudsonModelUserProperty();
    }

    /**
     * Create an instance of {@link HudsonModelActionable }
     * 
     */
    public HudsonModelActionable createHudsonModelActionable() {
        return new HudsonModelActionable();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HudsonMavenReportersSurefireAggregatedReport }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "hudson.maven.reporters.SurefireAggregatedReport")
    public JAXBElement<HudsonMavenReportersSurefireAggregatedReport> createHudsonMavenReportersSurefireAggregatedReport(HudsonMavenReportersSurefireAggregatedReport value) {
        return new JAXBElement<HudsonMavenReportersSurefireAggregatedReport>(_HudsonMavenReportersSurefireAggregatedReport_QNAME, HudsonMavenReportersSurefireAggregatedReport.class, null, value);
    }

}
