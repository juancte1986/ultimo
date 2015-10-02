<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
				<h1 class="logo">
					<h1 class="logo"><fmt:message key="label.title"/></h1>
				</h1>	
			</div>
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title"><fmt:message key="label.play"/></h3>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-5">
							<img alt="" width="200px" height="200px" src='<c:url value="/images/logo.jpg"/>' />
						</div>
						<div class="col-md-7" style="border-left: 1px solid #ccc; min-height: 300px">
							<form:form commandName="formJuego" action='${pageContext.request.contextPath}/login/procesarFormJugada.htm' method="POST">
								<fieldset>
									<div class="col-xs-8">
										<form:label path="numero">
											<fmt:message key="label.number" />
										</form:label>
										<form:input path="numero" class="form-control input-md" />
										<form:errors path="numero" cssStyle="color: red" />
										<br/>
									</div>
									<div class="col-xs-8">
										<form:button class="btn btn-info form-control">
											<fmt:message key="label.play" />
										</form:button>
										<br/>
										<br/>
										<p><a class="btn btn-info form-control" href='<c:url value="/login/reiniciarJuego.htm"/>'>
											<fmt:message key="label.restart"/></a>
										</p>
									</div>
								</fieldset>
							</form:form>
							<div class="col-xs-8">
								<c:if test="${resultDTO.ganador}">
									<h2>
										<span class="label label-success"><fmt:message key="label.ganador" /></span>
										<br/><br/>
									</h2>
								</c:if>
		
								<c:if test="${resultDTO.perdedor}">
									<h2>
										<span class="label label-warning"><fmt:message key="label.perdedor" /></span>
										<br/><br/>
									</h2>
								</c:if>
		
								<c:if test="${fn:length(sessionScope.jugador.numIncorrectos) > 0}">
									<c:forEach items="${sessionScope.jugador.numIncorrectos}" var="num">
										<c:if test="${num > sessionScope.jugador.numeroAleatorio }">
											<h5>
												<span class="label label-danger"> 
													<fmt:message key="label.mayor">
														<fmt:param value="${num}" />
													</fmt:message>
													<br/>
												</span>
											</h5>
										</c:if>
										<c:if test="${num < sessionScope.jugador.numeroAleatorio }">
											<h5>
												<span class="label label-danger">
													<fmt:message key="label.menor">
														<fmt:param value="${num}"/>
													</fmt:message>
													<br/>
												</span>
											</h5>
										</c:if>
									</c:forEach>
								</c:if>
								<h5>
									<span class="label label-primary">
										<fmt:message key="label.racha.session" />:
											${sessionScope.jugador.racha.mejorRacha == 0 ? 'N/A' : sessionScope.jugador.racha.mejorRacha}</span>
										<br/>
								</h5>
								<h5>
									<span class="label label-primary">
										<fmt:message key="label.racha.mejor.jugador.maquina" />: 
											${cookieJugador == "0" ? 'N/A' : cookieJugador}</span>
										<br/>
								</h5>
								<h5>
									<span class="label label-primary">
										<fmt:message key="label.racha.mejor.maquina" />: 
											${cookieMaquina == "N/C 0" ? 'N/A' : cookieMaquina}</span>
										<br/>
								</h5>
								<h5>
									<span class="label label-primary">
										<fmt:message key="label.racha.mejor.sistema" />: 
											${applicationScope.racha == 0 ? 'N/A' : applicationScope.jugador}
											${applicationScope.racha == 0 ? '' : applicationScope.racha}</span>
										<br/>
								</h5>
							</div>
						</div>
					</div>
				</div>
			</div>
			<p>
				<a href='<c:url value="/login/salir.htm"/>'><fmt:message key="label.salir"/></a>
			</p>
		</div>
	</body>
</html>