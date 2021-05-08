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

	<div class="container-fluid">
		<div
			class="row justify-content-center container-fluid bg-light border rounded-3 py-5">
			<h2>Activate account</h2>
			<form action="#">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="label" for="name">Full Name</label> <input
								type="text" class="form-control">
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="label" for="name">Username</label> <input
								type="text" class="form-control">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="label" for="email">Email Address</label> <input
								type="email" class="form-control">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="label" for="password">Password</label> <input
								type="password" class="form-control">
						</div>
					</div>
				</div>


				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="label" for="name">Birthday</label> <input
								type="text" class="form-control">
						</div>

					</div>

					<div class="col-md-6">
						<div class="form-group">
							<label class="label" for="name">SSN</label> <input type="text"
								class="form-control">
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

			</form>
			<div class="w-100">
				<p class="mt-4">
					I already have an account! <a href="#signin">Sign In</a>
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
			backgroundBody();
		});
	</script>

	<!-- End your custom js here -->


	<jsp:include page="footer.jsp" />