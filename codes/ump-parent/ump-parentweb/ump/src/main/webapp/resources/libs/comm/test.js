console.log('aaaaa');
// require('jquery');

require([ 'jquery' ], function($) {
	alert($().jquery);
	$('loginBtn').click(function() {
		alert('aaaa');
	});
});