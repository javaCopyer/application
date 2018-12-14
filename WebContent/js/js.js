function tables(ev, sl) {
	var index = $(ev).index();
	$(sl).eq(index).show().siblings().hide();
}

function active(ev) {
	$(ev).addClass('active').siblings().removeClass('active');
}

function shows(ev) {
	ev.show();
}

function hides(ev) {
	ev.hide();
}

function nums(x, y) {
	return parseInt(Math.random() * (y - x + 1) + x);
};

$('.nav_lists .ul li').click(function() {
	active(this);
})
$('.nav_lists .ul li').eq(0).click(function() {
	if($(this).hasClass('s')) {
		$(this).removeClass('s');
		$('.s').slideUp();
	} else {
		$(this).addClass('s');
		$('.s').slideDown();
	}
})
$('.nav_lists .ul li').eq(4).click(function() {
	if($(this).hasClass('s')) {
		$(this).removeClass('s');
		$('.s1').slideUp();
	} else {
		$(this).addClass('s');
		$('.s1').slideDown();
	}
})
$('.nav_lists .posa .bot_btn .fl').click(function() {
	$('.posa').slideUp();
	$('.nav_lists .ul li').removeClass('s');
})
$('.b_bgs .sess_dress a').click(function() {
	$('.b_bgs').hide();
})

$('.navbar_list ul li').click(function() {
	active(this);
	tables(this, $('.fadhide .fadins'));
})
$('.lable_fil ul li').click(function() {
	if($(this).hasClass('a')) {
		$(this).removeClass('a');
		$(this).children('.actsa').hide();
	} else {
		$(this).addClass('a');
		$(this).children('.actsa').show().parents('li').siblings().children('.actsa').hide();
		$(this).siblings().removeClass('a')
	}
})
$('.list_hr ul li').click(function() {
	if($(this).hasClass('active')) {
		$(this).removeClass('active');
	} else {
		$(this).addClass('active');
	}
})
$('.mz_list ul li').click(function() {
	active(this)
})
$('header img').click(function() {
	window.history.go(-1);
})
$('.wxmp .closes').click(function() {
	$('.wxmp').hide();
})
$('.sm').click(function() {
	$('.wxmp').show();
})


