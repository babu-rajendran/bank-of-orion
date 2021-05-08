<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="header.jsp" />

<!-- Import your custom css here -->

<%-- Example

<spring:url value="/resources/css/bootstrap/bootstrap.min.css" var="bootstrapCss" /> - This is where to link you file, search about "spring:url" to know more

<link href="${bootstrapCss}" rel="stylesheet"> - This is to assign your css style

End of Example --%>





<!-- End your custom css here -->
</head>
<body class="text-center">

	<!-- Build your body html here -->

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