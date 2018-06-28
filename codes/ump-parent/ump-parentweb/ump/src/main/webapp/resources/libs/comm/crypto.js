function encryptByDES() {
}

function decryptByDES() {
}

var ICrypto = function(name, methods) {
	if (arguments.length != 2) {
		throw new Error("");
	}
	this.name = name;
	this.methods = [];
	for (var i = 0; i < motheds.length; i++) {
		if (typeof motheds[i] !== 'string') {
			throw new Error('Interface constructor expects mothed names to be'
					+ 'passes in as a string');
		}
		this.methods.push(motheds[i]);
	}
}