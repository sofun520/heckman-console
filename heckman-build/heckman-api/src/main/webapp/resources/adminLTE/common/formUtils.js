/*序列化form数据成json对象字符串，t表示form对象*/
function serializeObject(formObj) {
	var o = {};
	var a = formObj.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};

function zipImg(path) {
	var img = new Image();
	img.src = path; // 传过来的图片路径在这里用
	img.onload = function() {
		var that = this;
		// 生成比例
		var w = that.width, h = that.height, scale = w / h;
		w = 480;
		h = w / scale;
		// 生成canvas
		var canvas = document.createElement("canvas");
		var ctx = canvas.getContext('2d');
		canvas.width = w;
		canvas.height = h;
		ctx.drawImage(that, 0, 0, w, h);
		base64 = canvas.toDataURL('image/jpeg', 0.4); // 1最清晰，越低越模糊
		console.log(base64);
	}
}
