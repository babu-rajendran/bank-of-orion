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
							href="${pageContext.request.contextPath}/">Sign out </a></li>
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
			<h5 class="border-bottom pb-2 mb-0">Account Summary</h5>
			<div class="d-flex pt-3">
				<p class="pb-3 mb-0 small lh-sm">
					<strong class="d-block text-gray-dark">Account Type</strong>
					${account.accountType}
				</p>
			</div>
			<div class="d-flex pt-3">
				<p class="pb-3 mb-0 small lh-sm">
					<strong class="d-block text-gray-dark">Account Number</strong>
					${account.accountNumber}
				</p>
			</div>
			<div class="d-flex pt-3">
				<p class="pb-3 mb-0 small lh-sm">
					<strong class="d-block text-gray-dark">Balance (USD)</strong>
					${account.balance}
				</p>
			</div>
			<br> <br>
		</div>

		<div class="my-3 p-3 bg-body rounded shadow-sm">
			<h5 class="border-bottom pb-2 mb-0">Schedule Payment/Billing</h5>
			<br>
			<form:form method="POST"
				action="${pageContext.request.contextPath}/submitPayment"
				modelAttribute="schedule"
				cssClass="container-fluid border rounded-3 py-5">
				<table class="styled-table">
					<tr>
						<td><form:label path="receivingAccountNumber">Receiver's Account Number</form:label></td>
						<td><form:input path="receivingAccountNumber"
								cssClass="form-control" required="required" type="number" /></td>
						<td>&nbsp; &nbsp;</td>
						<td><form:label path="receivingRounting">Routing Number</form:label></td>
						<td><form:input path="receivingRounting"
								cssClass="form-control" required="required" type="number" /></td>
					</tr>
					<tr>
						<td><form:label path="amount">Amount</form:label></td>
						<td><form:input path="amount" cssClass="form-control"
								required="required" type="number" /></td>
						<td>&nbsp; &nbsp;</td>
						<td><form:label path="description">Description</form:label></td>
						<td><form:input path="description" /></td>
					</tr>
					<tr>
						<td><form:label path="transactionDate">Pay on</form:label></td>
						<td><form:input path="transactionDate"
								cssClass="form-control" required="required" type="date" /></td>
					</tr>
					<tr>
						<td><form:label path="isRecur">Recur?</form:label></td>
						<td><form:radiobutton path="isRecur" name="isRecur"
								value="Yes" /> Yes</td>
						<td><form:radiobutton path="isRecur" name="isRecur"
								value="No" checked="checked" /> No</td>
						<td>&nbsp; &nbsp;</td>
						<td>&nbsp; &nbsp;</td>
					</tr>

					<tr style="display: none;" id="otherAnswer">
						<td><form:label path="interval">Interval [days]</form:label></td>
						<td><form:input path="interval" cssClass="form-control"
								required="required" value="0" type="number" /></td>
						<td>&nbsp; &nbsp;</td>
						<td><form:label path="times">How many time(s)?</form:label></td>
						<td><form:input path="times" cssClass="form-control"
								required="required" value="0" type="number" /></td>
					</tr>
					<tr>
						<form:hidden path="sendingAccountNumber"
							value="${account.accountNumber}" />
					</tr>
					<tr>
						<td><input type="submit" class="btn btn-primary submit"
							value="Submit" /></td>
					</tr>
				</table>
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
			$("input[type=radio][name=isRecur]").change(function() {

				if ($(this).val() == "Yes") {
					$("#otherAnswer").show();
				} else {
					$("#otherAnswer").hide();
				}

			});

			var msg = "${message}";
			if (msg != "") {
				alert(msg);
			}
		});
	</script>



	<!-- End your custom js here -->


	<jsp:include page="footer.jsp" />