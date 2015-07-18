<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>cse5335nizi</title>
</head>
<body>
<f:view>
		<h:form>
		<h:panelGrid border="1" columns="2">
		 <h:panelGrid border="1" columns="2">
			<h:inputText value="#{dropbox1.filename}"/> 
			<h:commandButton value="Upload" action="#{dropbox1.dbUpload()}"/>
			<h:commandButton value="Generate List" action="#{dropbox1.dbListing()}"></h:commandButton>
			<h:selectOneListbox value="#{dropbox1.dfile}">
				<f:selectItems value="#{dropbox1.filelist}"/>
			</h:selectOneListbox> 
			<h:commandButton value="Download" action = "#{dropbox1.dbDownload()}"></h:commandButton>
			<h:commandButton value="Delete" action = "#{dropbox1.dbDelete()}"></h:commandButton>
			</h:panelGrid>
			<h:graphicImage  url="#{dropbox1.dispImage}"/>
			</h:panelGrid>
		</h:form>
	</f:view>
</body>
</html>