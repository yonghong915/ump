'use strict';
(function(win) {
	var config = {
		urlArgs : 'bust=' + (new Date()).getTime(),
		waitSeconds : 20,
		baseUrl : './',
		paths : {
			'jquery' : 'jquery-3.3.1',
			'jquery-easyui' : 'jquery-easyui/jquery.easyui.min'
		},
		shim : {
			bootstrap : [ 'jquery' ]
		}
	};
	require.config(config);

	require([ 'jquery' ], function($) {
		alert($().jquery);
	});

	if (typeof define === "function" && define.amd && define.amd.jQuery) {
		define("jquery", [], function() {
			return jQuery;
		});
	}
})(window);
