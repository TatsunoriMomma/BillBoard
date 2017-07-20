/**
 *
 */

/*
$(function () {
	var body = $('body');
	//サイドメニューボタンが押されたら
	$('.commentView').on('click', function () {

		body.toggleClass('Cview');
		});
});
*/

function CountDownLength (id,str,max) {
	document.getElementById("max" +id).innerHTML = "あと" + (max - str.length) + "文字";
}

/*function nl2br(str) {
    str = str.replace(/\r\n/g, "<br />");
    str = str.replace(/(\n|\r)/g, "<br />");
    return str;
}*/

function commentView(id) {
	var target = document.getElementById("list" + id);

	var getStatus =  window.getComputedStyle(target, null).getPropertyValue('display');
	if(getStatus == 'block') {
		target.style.display = "none";
	} else {
		target.style.display = "block";
	}
}