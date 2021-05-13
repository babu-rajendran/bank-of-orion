<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="header.jsp" />

<spring:url value="/resources/css/bootstrap/bootstrap.min.css"
	var="bootstrapCss" />
<spring:url value="/resources/css/signin.css" var="signinCss" />
<spring:url value="/resources/css/gradients.css" var="gradientsCss" />

<link href="${bootstrapCss}" rel="stylesheet">

<link href="${signinCss}" rel="stylesheet">
<link href="${gradientsCss}" rel="stylesheet">

</head>
<body class="">

	<!-- Build your body html here -->
	<div class="container-fluid">
		<header>
			<!-- Fixed navbar -->
			<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
				<div class="container-fluid">
					<a class="navbar-brand"> <img
						src="${pageContext.request.contextPath}/resources/images/orionbank_logo.png"
						class="img-thumbnail p-md-1" width="200" alt="Responsive image"></a>
					<ul class="navbar-nav me-auto">
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/">Home</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/signInPage">Sign In
						</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/signUpPage">Sign Up
						</a></li>
					</ul>
				</div>
			</nav>
		</header>
	</div>

	<div class="container-fluid">
		<div
			class="row justify-content-center container-fluid bg-light border rounded-3 py-5">
			<h2>Activate account</h2>
			<form:form method="POST"
				action="${pageContext.request.contextPath}/signUp"
				modelAttribute="signup">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<form:label path="name">Full Name</form:label>
							<form:input path="name" cssClass="form-control"
								required="required" />
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<form:label path="username">User Name</form:label>
							<form:input path="username" cssClass="form-control"
								required="required" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<form:label path="email">Email Address</form:label>
							<form:input path="email" cssClass="form-control"
								required="required" type="email" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<form:label path="password">Password</form:label>
							<form:password path="password" cssClass="form-control"
								required="required" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<form:label path="dob">Birthday</form:label>
							<form:input path="dob" cssClass="form-control"
								required="required" type="date" />
						</div>
					</div>

					<div class="col-md-6">
						<div class="form-group">
							<form:label path="ssn">Last 4 SSN</form:label>
							<form:input path="ssn" cssClass="form-control"
								required="required" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<br>
							<button type="submit" class="btn btn-primary submit">Activate
							</button>
						</div>
					</div>
				</div>
			</form:form>
			<div class="w-100">
				<p class="mt-4">
					I already have an account! <a
						href="${pageContext.request.contextPath}/signInPage">Sign In</a>
				</p>
			</div>
		</div>

	</div>
	<!-- End your body html here -->



	<!-- Import your custom js here -->

	<script type="text/javascript"
		src="<c:url value="/resources/js/jquery-3.6.0.min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/setBodyBackground.js" />"></script>
	<script type="text/javascript">
		$(function() {
			var msg = "${message}";
			if (msg != "") {
				alert(msg);
			}
			backgroundBody();
		});
	</script>

	<!-- End your custom js here -->


	<jsp:include page="footer.jsp" />