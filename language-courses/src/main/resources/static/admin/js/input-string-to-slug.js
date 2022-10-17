$(".edit-role").on("click", function() {
	let getRoleId = $(this).attr("data-roleId");
	$.ajax({
		url: "/quan-tri/vai-tro/api?role-id=" + getRoleId,
		type: "GET",
		contentType: "application/json",
		data: JSON.stringify(getRoleId),
		dataType: "json",
		success: function(result) {

		},
		error: function(error) {

		}
	});
});

$(".edit-language").on("click", function() {
	let getLanguageId = $(this).attr("data-languageId");
	alert(getLanguageId);
});