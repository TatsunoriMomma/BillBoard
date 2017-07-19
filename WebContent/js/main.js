/**
 *
 */

$(function () {
	var body = $('body');
	//サイドメニューボタンが押されたら
	$('.commentView').on('click', function () {

		body.toggleClass('Cview');
		});
});

function CountDownLength (id,str,max) {
	document.getElementById("max" +id).innerHTML = "あと" + (max - str.length) + "文字";
}