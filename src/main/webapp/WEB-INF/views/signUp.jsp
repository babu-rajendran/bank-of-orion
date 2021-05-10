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

	<div class="container-fluid" >
	    <div class="row justify-content-center container-fluid bg-light border rounded-3 py-5">
	        <div class="mt-5 mb-3 text-muted">
	            <a href="${pageContext.request.contextPath}"> Home Page (Admin Page) </a>
	        </div>
	        <h2>Create New Account</h2>
	        <form action="#">
	            <div class="row">
	                <div class="col-md-6">
	                    <div class="form-group">
	                        <label class="label" for="name">*Name: </label> <input
	                            type="text" class="form-control"  placeholder="John Doe">
	                    </div>
	                </div>
	                <div class="col-md-6">
	                    <div class="form-group">
	                        <label class="label" for="name">*Date of Birth: </label> <input
	                            type="text" class="form-control"  placeholder="mm/dd/yy">
	                    </div>
	                </div>
	                <div class="col-md-6">
	                    <div class="form-group">
	                        <label class="label" for="name">*Account Number: </label> <input
	                            type="text" class="form-control"  placeholder="123456789">
	                    </div>
	                </div>
	                <div class="col-md-6">
	                    <div class="form-group">
	                        <label class="label" for="name">*Account Type: </label> <input
	                            type="text" class="form-control"  placeholder="Checking">
	                    </div>
	                </div>
	                <div class="col-md-6">
	                    <div class="form-group">
	                        <label class="label" for="name">*Amount: </label> <input
	                            type="text" class="form-control"  placeholder="1500">
	                    </div>
	                </div>
	                <div class="col-md-6">
	                    <div class="form-group">
	                        <label class="label" for="name">*Last 4 SSN: </label> <input
	                            type="text" class="form-control"  placeholder="1234">
	                    </div>
	                </div>
	                <div class="col-md-6">
	                    <div class="form-group">
	                        <label class="label" for="name">*Email: </label> <input
	                            type="text" class="form-control"  placeholder="johndoe@sjsu.edu">
	                    </div>
	                </div>
	                <div class="col-md-6">
	                    <div class="form-group">
	                        <label class="label" for="name">*Phone: </label> <input
	                            type="text" class="form-control"  placeholder="(408)123-4567">
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="col-md-12">
	                    <div class="form-group">
	                        <br>
	                        <button type="submit" class="btn btn-primary submit">Submit</button>
	                    </div>
	                </div>
	            </div>
	        </form>
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