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
<body class="bg-dark">

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


	<div class="container-fluid text-center">
		<div class="form-signin bg-light border rounded-3">
			<form:form method="POST"
				action="${pageContext.request.contextPath}/login"
				modelAttribute="login">
				<h2 class="h3 mb-3 fw-normal">Please sign in</h2>

				<div class="form-floating">
					<form:input path="username" cssClass="form-control"
						id="floatingInput" placeholder="Username" required="required" />
					<label for="floatingInput">User Name</label>
				</div>
				<div class="form-floating">
					<form:password path="password" cssClass="form-control"
						id="floatingPassword" placeholder="Password" required="required" />
					<label for="floatingPassword">Password</label>
				</div>

				<button class="w-100 btn btn-lg btn-primary" type="submit">Sign
					in</button>

				<div class="mt-5 mb-3 text-muted">
					<span> Don't have an account? </span> <a
						href="${pageContext.request.contextPath}/signUpPage"> Sign Up
					</a>
				</div>
			</form:form>
		</div>
	</div>


	<script type="text/javascript"
		src="<c:url value="/resources/js/jquery-3.6.0.min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/setBodyBackground.js" />"></script>
	<script type="text/javascript">
		$(function() {
			var msg = "${error}";
			if (msg != "") {
				alert(msg);
			}
			backgroundBody();
		});
	</script>

	<jsp:include page="footer.jsp" />