$('#loginScreenOpen').click(function(){
	$('#loginScreen').removeClass('row-fluid');
	$('#loginScreen').addClass('row');
	
	$('#loginScreenPopup').toggle(function() {
		$('#loginScreenPopup #j_username').trigger('focus');
	});
});