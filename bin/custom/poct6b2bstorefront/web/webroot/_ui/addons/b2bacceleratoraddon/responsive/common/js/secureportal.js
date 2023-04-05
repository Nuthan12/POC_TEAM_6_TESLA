ACC.secureportal = {

	_autoload : [ 	"bindRegister",
					"registerNavigationItem"],

	bindRegister : function() {
		const registerButton = $(".js-secureportal-register-button");

		if (!$(".js-secureportal-confirm-register-email").val() && $(".js-secureportal-orignal-register-email").val()) {
			registerButton.attr("disabled", "disabled");
		}
		$(document).on("change", ".js-secureportal-confirm-register-email, .js-secureportal-orignal-register-email", function() {

					var orginalEmail = $(".js-secureportal-orignal-register-email").val();
					var confirmationEmail = $(".js-secureportal-confirm-register-email").val();

					if (orginalEmail === confirmationEmail) {
						$(".js-secureportal-email-not-match-message").hide();
						registerButton.removeAttr("disabled");
					} else {
						$(".js-secureportal-email-not-match-message").show();
						registerButton.attr("disabled", "disabled");
					}
				});
	},

	registerNavigationItem: function(){
		var registerLink = $(".js-register-navigation-item").clone();
		$('.js-userAccount-Links').append(registerLink);
		registerLink.wrap( "<li class='auto'></li>" );
	}
};
