var ump = {}
$J = ump;
ump.enableResAuth = true;
var AUTH_RES_LIST = '_AUTH_RES_LIST';
var GLOBAL_CONTEXT = '_GLBAL_CONTEXT';
var globalContent = new Array();
ump.loadAuthResList = function() {
	var path = 'ump';
	// /auth/loadAuth.do
	// $.ajax({
	// type : "GET", // 请求类型
	// url : path + "/auth/loadAuth.do", // URL
	// data : {
	// uid : ''
	// }, // 传递的参数
	// dataType : "json", // 返回的数据类型
	// success : function(data) { // data就是返回的json类型的数据
	// var result = obj.data;
	// globalContent[AUTH_RES_LIST] = result;
	// },
	// error : function(data) {
	// alert("删除失败");
	// }
	// });
	var globalContent = this.getGlobalContext();
	globalContent[AUTH_RES_LIST] = [ 'test.do','aaaaa.do' ];
	// alert('globalContent=' + globalContent[AUTH_RES_LIST]);
}

ump.getGlobalContext = function() {
	return this.findPropertyValue(window, GLOBAL_CONTEXT);
}
ump.findPropertyValue = function(win, propName) {
	if (win[propName]) {
		return win[propName];
	}
	if (this.isTop(win)) {
		return null;
	}
	return this.findPropertyValue(win.parent, propName);
}
ump.isTop = function(win) {
	return win.parent === win;
}

ump.applyResAuth = function(authResList) {
	var appid = $("#myDiv").data("appid"); // 123
	var appId = $("#myDiv").data("app-id"); // 456
	// alert(appid + ' ' + appId);

	$('*[data-resid]').each(function() {
		var $this = $(this);
		var resIdAttr = $this.attr('data-resid');
		var flag = false;
		for (var i = 0; i < authResList.length; i++) {
			if (resIdAttr == authResList[i]) {
				flag = true;
			}
		}
		alert('flag='+flag);
		if (!flag) {
			// $this.remove();
			$this.attr('disabled', true);
		}
	});
}

ump.getAuthResList = function() {
	var authResList = this.getGlobalContext();
	alert(authResList[AUTH_RES_LIST]);
	return authResList ? authResList[AUTH_RES_LIST] : null;
}
ump.parse = function() {
	if ($J.enableResAuth) {
		var authResList = this.getAuthResList();
		alert('authResList=' + authResList);
		this.applyResAuth(authResList);
	}

}