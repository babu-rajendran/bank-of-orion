<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="header.jsp" />

<!-- Import your custom css here -->

<%-- Example

<spring:url value="/resources/css/bootstrap/bootstrap.min.css" var="bootstrapCss" /> - This is where to link you file, search about "spring:url" to know more

<link href="${bootstrapCss}" rel="stylesheet"> - This is to assign your css style

End of Example --%>

<spring:url value="/resources/css/bootstrap/bootstrap.min.css"
	var="bootstrapCss" />

<link href="${bootstrapCss}" rel="stylesheet">

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

html, body {
	overflow-x: hidden; /* Prevent scroll on narrow devices */
}

body {
	padding-top: 56px;
}

.styled-table {
	border-collapse: collapse;
	margin: 25px 0;
	font-size: 0.9em;
	font-family: sans-serif;
	min-width: 400px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}

.styled-table thead tr {
	background-color: #1e8bd4;
	color: #ffffff;
	text-align: left;
}

.styled-table th, .styled-table td {
	padding: 12px 15px;
}

.styled-table tbody tr:nth-of-type(even) {
	background-color: #f3f3f3;
}
</style>



<!-- End your custom css here -->
</head>
<body>

	<!-- Build your body html here -->
	<div class="container-fluid">
		<header>
			<!-- Fixed navbar -->
			<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
				<div class="container-fluid">
					<a class="navbar-brand"> <img
						src="${pageContext.request.contextPath}/resources/images/orionbank_logo.png"
						class="img-thumbnail p-md-1" width="200" alt="Responsive image"></a>
					<ul class="navbar-nav ms-auto">
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/adminPage">${userSession.legalName}</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/logout">Sign out </a></li>
					</ul>
				</div>
			</nav>
		</header>
	</div>

	<main class="container">
		<div class="my-3 p-3 bg-body rounded">
			<br> <br>
		</div>
		<div class="my-3 p-3 bg-body rounded shadow-sm">
			<h5 class="border-bottom pb-2 mb-0">Create New Account</h5>
			<br>
			<form:form method="POST"
				action="${pageContext.request.contextPath}/createAccount"
				modelAttribute="createUser"
				cssClass="container-fluid border rounded-3 py-5">
				<table class="table table-hover table-striped">
					<tr>
						<td><form:label path="name">Name</form:label></td>
						<td><form:input path="name" cssClass="form-control"
								required="required" /></td>
						<td>&nbsp; &nbsp;</td>
						<td><form:label path="dob">Birthday</form:label></td>
						<td><form:input path="dob" cssClass="form-control"
								required="required" type="date" /></td>
					</tr>
					<tr>
						<td><form:label path="acctNum">Account Number</form:label></td>
						<td><form:input path="acctNum" cssClass="form-control"
								required="required" type="number" /></td>
						<td>&nbsp; &nbsp;</td>
						<td><form:label path="acctType">Account Type</form:label></td>
						<td><form:input path="acctType" cssClass="form-control"
								required="required" /></td>
					</tr>
					<tr>
						<td><form:label path="amount">Amount</form:label></td>
						<td><form:input path="amount" cssClass="form-control"
								required="required" type="number" /></td>
						<td>&nbsp; &nbsp;</td>
						<td><form:label path="ssn">Last 4 SSN</form:label></td>
						<td><form:input path="ssn" cssClass="form-control"
								required="required" type="number" /></td>
					</tr>
					<tr>
						<td><form:label path="email">Email</form:label></td>
						<td><form:input path="email" cssClass="form-control"
								required="required" type="email" /></td>
						<td>&nbsp; &nbsp;</td>
						<td><form:label path="phone">Phone</form:label></td>
						<td><form:input path="phone" cssClass="form-control" /></td>
					</tr>
					<tr>
					</tr>
				</table>
				<input type="submit" class="btn btn-primary submit" value="Submit" />
			</form:form>

		</div>
		<div class="my-3 p-3 bg-body rounded">
			<br> <br>
		</div>
	</main>


	<!-- End your body html here -->



	<!-- Import your custom js here -->

	<%-- Example
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.6.0.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/setBodyBackground.js" />"></script>
	<script type="text/javascript">
		$(function() {
			backgroundBody();
		});
	</script> --%>

	<script type="text/javascript"
		src="<c:url value="/resources/js/jquery-3.6.0.min.js" />"></script>
	<script type="text/javascript">
		$(function() {
			var msg = "${message}";
			if (msg != "") {
				alert(msg);
			}
		});
	</script>


	<!-- End your custom js here -->


	<jsp:include page="footer.jsp" />