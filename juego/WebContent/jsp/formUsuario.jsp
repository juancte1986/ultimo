<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/juego.css" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css" />
		<title><fmt:message key="label.title"/></title>
	</head>
	<body>
		<div class="middlePage">
			<div class="page-header">
  				<h1 class="logo"><fmt:message key="label.title"/></h1>
			</div>
			<div class="panel panel-info">
  				<div class="panel-heading">
    				<h3 class="panel-title"><fmt:message key="label.register"/></h3>
 				 </div>
 				 <div class="panel-body">
 				 	  <div class="row">
 				 	 	<div class="col-md-5" >
 				 	 		<img alt="" width="200px" height="200px" src='<c:url value="/images/logo.jpg"/>' />
 				 	 	</div>
 				 	  	 <div style="border-left:1px solid #ccc;height:160px">
							<form:form commandName="formJugador" action='${pageContext.request.contextPath}/procesarFormUsuario.htm' method="POST" class="form-horizontal">
								<fieldset>
									<div class="col-xs-8">
										<form:label path="nombre">
											<fmt:message key="label.name" />
										</form:label>
										<form:input path="nombre" class="form-control input-md"/>
										<form:errors path="nombre" cssStyle="color: red" />
										<br/>
										<form:select path="idioma" class="form-control">
											<form:option value="es_ar"><fmt:message key="label.es" /></form:option>
											<form:option value="en_us"><fmt:message key="label.en"/></form:option>
										</form:select>
										<br/>
										<form:button id="singlebutton" name="singlebutton" class="btn btn-info btn-sm pull-right"><fmt:message key="label.start"/></form:button>
									</div>
								</fieldset>
							</form:form>
						</div>
					 </div>
				</div>
			</div>	
		</div>
	</body>
</html>