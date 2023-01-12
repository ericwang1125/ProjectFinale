
 function showResult() {

	if (result != "") {

		var y;
		var i = document.createElement('div');
		i.setAttribute("class", "col rounded overflow-auto list-group overflow-y-scroll");
		i.setAttribute("style", "width: 1298px; height: 748px; ");
		i.setAttribute("id", "result");
		var addressContainer = document.getElementById("rectangle_5");
		addressContainer.insertAdjacentElement("beforeend", i);

		for (var i in result) {
			var a = document.createElement('a');
			a.setAttribute("class", "list-group-item list-group-item-action");
			a.setAttribute("style", "height: 74.8px; font-size: 30px; text-indent: 4px;");
			a.setAttribute("href", result[i].URL);
			a.setAttribute("id", "resultList");
			var aName = document.createTextNode(result[i].webName);
			a.appendChild(aName);
			var div = document.getElementById("result");
			div.insertAdjacentElement("beforeend", a);
			console.log(result[i].webName);
			console.log(result[i].URL);
		}
	}else {
		console.log("result is empty");
	}
	
}

window.onload = showResult;

