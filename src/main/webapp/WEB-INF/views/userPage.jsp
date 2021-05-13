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
						<li class="nav-item"><a class="nav-link" href="#">${userSession.legalName}</a></li>
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
			<h5 class="border-bottom pb-2 mb-0">Accounts</h5>
			<br>
			<table class="table table-hover table-bordered table-striped">
				<tr>
					<th>Account Type</th>
					<th>Account Number</th>
					<th>Balance (USD)</th>
					<th>Status</th>
					<th style="text-align: center">Action</th>
				</tr>
				<c:forEach var="account" items="${accounts}">
					<tr>
						<td>${account.accountType}</td>
						<td>${account.accountNumber}</td>
						<td>${account.balance}</td>
						<td>${account.accountStatus}</td>
						<td style="text-align: center"><form:form method="POST"
								action="${pageContext.request.contextPath}/viewAcctPage"
								modelAttribute="userForm" cssClass="container-fluid">
									&nbsp;
									<form:hidden path="acctNum" value="${account.accountNumber}" />
								<input type="submit" class="btn btn-primary submit btn-sm"
									value="View" />
							</form:form></td>
					</tr>
				</c:forEach>
			</table>
			<br> <br>
		</div>

		<div class="my-3 p-3 bg-body rounded shadow-sm">
			<h5 class="border-bottom pb-2 mb-0">Make Payment</h5>
			<div class="row container-fluid">
				<div class="col-md-8">
					<div class="d-flex pt-3">
						<form:form method="POST"
							action="${pageContext.request.contextPath}/viewPaymentPage"
							modelAttribute="userForm" cssClass="container-fluid ">
							<form:select path="acctNum" style="width: 500px;">
								<form:option value="-" label="--Please Select--" />
								<form:options items="${accounts}" itemValue="accountNumber"
									itemLabel="accountNumber" />
							</form:select>
							<input type="submit" class="btn btn-primary submit btn-sm"
								id="button-addon2" style="width: 75px;" value="Go" />
						</form:form>
					</div>
				</div>
			</div>
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



	<!-- End your custom js here -->


	<jsp:include page="footer.jsp" />