package com.spring.basics.spring_basics.XMLContext;

public class XmlDAO { 
    XmlJdbcConnection xmlJdbcConnection; // Dependency

    // Do not use constructor in this case. Or else you will get the "Default constructor not found error."
    // INstead use getters and setters for each of the dependencies.   

    public XmlJdbcConnection getXmlJdbcConnection() {
        return xmlJdbcConnection;
    }

    public void setXmlJdbcConnection(XmlJdbcConnection xmlJdbcConnection) {
        this.xmlJdbcConnection = xmlJdbcConnection;
    }
}