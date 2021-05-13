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
							href="${pageContext.request.contextPath}/userPage">${userSession.legalName}</a></li>
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
			<h5 class="border-bottom pb-2 mb-0">Transaction Summary</h5>

			<div class="row">
				<div class="col-md-6">
					<div class="d-flex pt-2 pb-2">
						<table>
							<tr>
								<td colspan="2">
									<p class="pb-1 mb-0 small lh-sm">
										<strong class="d-block text-gray-dark">Account Number</strong>
									</p>
								</td>
							</tr>
							<tr>
								<td>${acctNum}</td>
								<td><form:form method="POST"
										action="${pageContext.request.contextPath}/viewAcctPage"
										modelAttribute="userForm" cssClass="container-fluid">
										<form:hidden path="acctNum" value="${acctNum}" />
										<input type="submit" class="btn btn-primary submit btn-sm"
											style="width: 50px; height: 30px; text-align: center;"
											value="View" />
									</form:form></td>
							</tr>
						</table>
					</div>
					<div class="d-flex pt-2">
						<p class="pb-2 mb-0 small lh-sm">
							<strong class="d-block text-gray-dark">ID</strong>
							${transaction.transactionID}
						</p>
					</div>
					<div class="d-flex pt-2">
						<p class="pb-2 mb-0 small lh-sm">
							<strong class="d-block text-gray-dark">Description</strong>
							${transaction.description}
						</p>
					</div>
					<div class="d-flex pt-2">
						<p class="pb-2 mb-0 small lh-sm">
							<strong class="d-block text-gray-dark">From</strong>
							${transaction.sendingAccountNumber}
						</p>
					</div>
					<div class="d-flex pt-2">
						<p class="pb-2 mb-0 small lh-sm">
							<strong class="d-block text-gray-dark">To</strong>
							${transaction.receivingRounting}&nbsp;${transaction.receivingAccountNumber}
						</p>
					</div>
					<div class="d-flex pt-2">
						<p class="pb-2 mb-0 small lh-sm">
							<strong class="d-block text-gray-dark">Date</strong>
							${transaction.transactionDate}
						</p>
					</div>
					<div class="d-flex pt-2">
						<p class="pb-2 mb-0 small lh-sm">
							<strong class="d-block text-gray-dark">Amount (USD)</strong>
							${transaction.amount}
						</p>
					</div>
					<div class="d-flex pt-2">
						<p class="pb-2 mb-0 small lh-sm">
							<strong class="d-block text-gray-dark">Status</strong>
							${transaction.transactionStatus}
						</p>
					</div>
				</div>
				<div class="col-md-6">
					<table class="styled-table">
						<tr>
							<td><strong class="d-block text-gray-dark">Cancel
									Transaction</strong></td>
							<td><form:form method="POST"
									action="${pageContext.request.contextPath}/cancelTransaction"
									modelAttribute="userForm" cssClass="container-fluid">
									&nbsp;
									<form:hidden path="transactionId"
										value="${transaction.transactionID}" />
									<form:hidden path="acctNum" value="${acctNum}" />
									<c:if test="${transaction.transactionStatus != 'Pending'}">
										<input type="submit"
											class="btn btn-primary submit btn-sm disabled"
											style="width: 75px;" value="Cancel" />
									</c:if>
									<c:if test="${transaction.transactionStatus == 'Pending'}">
										<input type="submit" class="btn btn-primary submit btn-sm"
											style="width: 75px;" value="Cancel" />
									</c:if>
								</form:form></td>
						</tr>

					</table>
				</div>
			</div>


			<br> <br>
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
			var msg = "${error}";
			if (msg != "") {
				alert(msg);
			}
		});
	</script>


	<!-- End your custom js here -->


	<jsp:include page="footer.jsp" />