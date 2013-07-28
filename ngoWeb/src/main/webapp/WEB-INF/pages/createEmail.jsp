<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title></title>

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">


<link href="assets/css/gumby.css" rel="stylesheet">

<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link href="../assets/css/bootstrap-responsive.min.css" rel="stylesheet">


<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600"
	rel="stylesheet">

<link href="../assets/css/font-awesome.css" rel="stylesheet"
	type="text/css" />






<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="../assets/js/jquery-1.7.2.min.js"></script>
<script src="../assets/js/excanvas.min.js"></script>
<script src="../assets/js/jquery.flot.js"></script>
<script src="../assets/js/jquery.flot.pie.js"></script>
<script src="../assets/js/jquery.flot.orderBars.js"></script>
<script src="../assets/js/jquery.flot.resize.js"></script>


<script src="../assets/js/bootstrap.js"></script>
<script src="assets/js/base.js"></script>

<script src="../assets/js/charts/area.js"></script>
<script src="../assets/js/charts/donut.js"></script>

<script src="../assets/js/JMX/jquery.min.js"></script>

</head>


<body>


	<c:url var="save" value="/email/saveEmail?action=save" />



	<div class="container">
		<div class="row">
			<div class="span6">

				<form:form id="email" modelAttribute="emailFormBean" name="email"
					action="" class="form-horizontal">

					<div class="control-group">
						<label class="control-label" for="inputEmail"> Email</label>
						<div class="controls">
							<form:input class="span4" path="toAddr" type="
							text"
								placeholder="Enter email adress" />
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="inputEmail"> Subject</label>
						<div class="controls">
							<form:input class="span4" path="subject" type="text"
								placeholder="Write a Subject Line . . . " />
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="inputEmail">Compose</label>
						<div class="controls">
							<form:textarea class="span4" path="body" type="text"
								placeholder="Type your messages . . ." />
						</div>
					</div>






					<div class="form-actions">
						<button class="btn btn-primary" type="submit" id="save"
							onclick="return setValue('save');">
							<i class="icon-plus-sign"></i> &nbsp;Send Email
						</button>

						<button class="btn" type="reset" id="reset">
							<i class="icon-remove"></i> &nbsp;Cancel
						</button>
					</div>




				</form:form>
			</div>
		</div>




	</div>


	<!--  FOR CONTROLLER  -->

	<script type="text/javascript">
		var action = "";
		var url = "";
		$(document).ready(function() {

			$('#email').on('submit', function(e) {
				if (action == 'save') {
					url = '${save}';
				} else {
					url = '${stop}';
				}
				e.preventDefault();
				$('#email').attr('action', url); //give it the action url ); 
				$('#email').unbind('submit').submit(); //submit it...
			});

		});

		function setColor(category) {
			alert('setColor');
			alert(category);
		}

		function setValue(newValue) {
			action = newValue;
		}
	</script>





</body>
</html>
