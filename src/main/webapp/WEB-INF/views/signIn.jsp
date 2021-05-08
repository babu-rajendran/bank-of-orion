<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
							href="${pageContext.request.contextPath}">Home</a></li>
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
			<form>
				<h2 class="h3 mb-3 fw-normal">Please sign in</h2>

				<div class="form-floating">
					<input type="text" class="form-control" id="floatingInput"
						placeholder="username"> <label for="floatingInput">User
						Name</label>
				</div>
				<div class="form-floating">
					<input type="password" class="form-control" id="floatingPassword"
						placeholder="Password"> <label for="floatingPassword">Password</label>
				</div>

				<button class="w-100 btn btn-lg btn-primary" type="submit">Sign
					in</button>

				<div class="mt-5 mb-3 text-muted">
					<span> Don't have an account? </span> <a
						href="${pageContext.request.contextPath}/signUpPage"> Sign Up
					</a>
				</div>
			</form>
		</div>
	</div>


	<script type="text/javascript"
		src="<c:url value="/resources/js/jquery-3.6.0.min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/setBodyBackground.js" />"></script>
	<script type="text/javascript">
		$(function() {
			backgroundBody();
		});
	</script>

	<jsp:include page="footer.jsp" />