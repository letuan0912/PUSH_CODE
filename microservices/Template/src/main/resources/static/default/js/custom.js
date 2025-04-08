(function() {
	'use strict';

	var tinyslider = function() {
		var el = document.querySelectorAll('.testimonial-slider');

		if (el.length > 0) {
			var slider = tns({
				container: '.testimonial-slider',
				items: 1,
				axis: "horizontal",
				controlsContainer: "#testimonial-nav",
				swipeAngle: false,
				speed: 700,
				nav: true,
				controls: true,
				autoplay: true,
				autoplayHoverPause: true,
				autoplayTimeout: 3500,
				autoplayButtonOutput: false
			});
		}
	};
	tinyslider();

	


	var sitePlusMinus = function() {

		var value,
    		quantity = document.getElementsByClassName('quantity-container');

		function createBindings(quantityContainer) {
	      var quantityAmount = quantityContainer.getElementsByClassName('quantity-amount')[0];
	      var increase = quantityContainer.getElementsByClassName('increase')[0];
	      var decrease = quantityContainer.getElementsByClassName('decrease')[0];
	      increase.addEventListener('click', function (e) { increaseValue(e, quantityAmount); });
	      decrease.addEventListener('click', function (e) { decreaseValue(e, quantityAmount); });
	    }

	    function init() {
	        for (var i = 0; i < quantity.length; i++ ) {
						createBindings(quantity[i]);
	        }
	    };

	    function increaseValue(event, quantityAmount) {
	        value = parseInt(quantityAmount.value, 10);

	        console.log(quantityAmount, quantityAmount.value);

	        value = isNaN(value) ? 0 : value;
	        value++;
	        quantityAmount.value = value;
	    }

	    function decreaseValue(event, quantityAmount) {
	        value = parseInt(quantityAmount.value, 10);

	        value = isNaN(value) ? 0 : value;
	        if (value > 0) value--;

	        quantityAmount.value = value;
	    }
	    init();
	};
	sitePlusMinus();

	var promoScroll = function () {
		var promoBtn = document.querySelector(".promo-btn");
		var specialOffer = document.querySelector(".special-offer");

		if (promoBtn && specialOffer) {
			promoBtn.addEventListener("click", function () {
				specialOffer.scrollIntoView({ behavior: "smooth" });
			});
		}
	};
	promoScroll();

	var sitePlusMinus = function() {
		var value,
			quantity = document.getElementsByClassName('quantity-container');

		function createBindings(quantityContainer) {
			var quantityAmount = quantityContainer.getElementsByClassName('quantity-amount')[0];
			var increase = quantityContainer.getElementsByClassName('increase')[0];
			var decrease = quantityContainer.getElementsByClassName('decrease')[0];
			var maxStock = parseInt(quantityAmount.getAttribute('data-max'), 10);

			increase.addEventListener('click', function (e) {
				increaseValue(e, quantityAmount, maxStock);
			});
			decrease.addEventListener('click', function (e) {
				decreaseValue(e, quantityAmount);
			});

			// Thêm validation khi người dùng nhập trực tiếp
			quantityAmount.addEventListener('change', function() {
				validateQuantity(this, maxStock);
			});
		}

		function validateQuantity(input, maxStock) {
			var value = parseInt(input.value, 10);

			if (isNaN(value) || value < 1) {
				value = 1;
			} else if (value > maxStock) {
				value = maxStock;
				alert('Số lượng không thể vượt quá số lượng tồn kho!');
			}

			input.value = value;
		}

		function increaseValue(event, quantityAmount, maxStock) {
			value = parseInt(quantityAmount.value, 10);
			value = isNaN(value) ? 0 : value;

			if (value < maxStock) {
				value++;
			} else {
				alert('Số lượng không thể vượt quá số lượng tồn kho!');
			}

			quantityAmount.value = value;
		}

		function decreaseValue(event, quantityAmount) {
			value = parseInt(quantityAmount.value, 10);
			value = isNaN(value) ? 0 : value;
			if (value > 1) value--;
			quantityAmount.value = value;
		}

		function init() {
			for (var i = 0; i < quantity.length; i++ ) {
				createBindings(quantity[i]);
			}
		}

		init();
	};
	sitePlusMinus();

})()