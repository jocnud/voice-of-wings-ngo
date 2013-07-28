/*
 * Common functions used through out the application
 */
function formSubmit(url, data) {
	$j
			.ajax({
				type : "POST",
				beforeSend : function(req) {
					$j("#main-area")
							.html(
									'<div class="loading"><img src="assets/img/ajax-loader.gif" alt="Loading..." /></div>');
				},
				data : data,
				url : url,
				success : function(response) {
					$j("#main-area").html(response);
				},
				error : function(e) {
					alert('Error invoking ' + url + ' : ' + e);
				}
			});
}
function formNavigate(text) {
	$j
			.ajax({
				type : "GET",
				cache: false,
				beforeSend : function(req) {
					$j("#main-area")
							.html(
									'<div class="loading"><img src="assets/img/ajax-loader.gif" alt="Loading..." /></div>');
				},
				url : text,
				success : function(response) {
					$j("#main-area").html(response);
				},
				error : function(e) {
					alert('Error invoking ' + text + ' : ' + e);
				}
			});
}
