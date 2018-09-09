////////String///////
String.prototype.getByteLength = function() {
	var ff = this.match(/[^\x00-\xff]/ig);
	return this.length + (ff == null ? 0 : ff.length);
}

function getByte(str) {
	if (null != str && str.length > 0) {
		return str.replace(/[^\x00-\xff]/g, "**").length;
	} else {
		return 0;
	}
}

String.prototype.escapeHTML = function() {

}

String.prototype.unescapeHTML = function() {

}

String.prototype.include = function(substring) {
	if (!substring) {
		return false;
	}
	return this.indexOf(substring) > -1;
}