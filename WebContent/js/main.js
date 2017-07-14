/**
 *
 */

$(function () {
	var body = $('div.contribution');
	//サイドメニューボタンが押されたら
	$('.commentView').on('click', function () {

		body.toggleClass('Cview');
		});
});