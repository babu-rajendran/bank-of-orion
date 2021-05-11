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

	<div class="container-fluid" >
            <div class="row justify-content-center container-fluid bg-light border rounded-3 py-5">
                <div class="mt-5 mb-3 text-muted">
                    <a href="${pageContext.request.contextPath}"> Home Page (Admin Page) </a>
                </div>  
                <h2>Transaction Summary</h2>
                <ol class="list-group list-group-flushed">
                    <li class="list-group-item d-flex justify-content-between align-items-start">
                      <div class="ms-2 me-auto">
                        <div class="fw-bold">ID:</div>
                        123456789
                      </div>
                    </li>
                    <li class="list-group-item d-flex justify-content-between align-items-start">
                      <div class="ms-2 me-auto">
                        <div class="fw-bold">Description</div>
                        Transaction 100
                      </div>
                    </li>
                    <li class="list-group-item d-flex justify-content-between align-items-start">
                      <div class="ms-2 me-auto">
                        <div class="fw-bold">From</div>
                        John Doe
                      </div>
                    </li>
                    <li class="list-group-item d-flex justify-content-between align-items-start">
                        <div class="ms-2 me-auto">
                          <div class="fw-bold">To</div>
                          Jane Doe
                        </div>
                      </li>
                      <li class="list-group-item d-flex justify-content-between align-items-start">
                        <div class="ms-2 me-auto">
                          <div class="fw-bold">Date</div>
                          mm/dd/yy
                        </div>
                      </li>
                      <li class="list-group-item d-flex justify-content-between align-items-start">
                        <div class="ms-2 me-auto">
                          <div class="fw-bold">Amount</div>
                          1500
                        </div>
                      </li>
                      <li class="list-group-item d-flex justify-content-between align-items-start">
                        <div class="ms-2 me-auto">
                          <div class="fw-bold">Status</div>
                          Pending
                        </div>
                      </li>
                  </ol>
                  <div class="row">
                    <div class="col-md-12">
                        <div class="btn-group"></div>
                            <div class="form-group">
                                <br>
                                <button type="submit" class="btn btn-primary btn-sm" type="submit">Cancel Transaction</button>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <br>
                                <button type="submit" class="btn btn-primary btn-sm" type="submit">Dispute Transaction</button>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <br>
                                <button type="submit" class="btn btn-primary btn-sm" type="submit">Approve Transaction</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


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