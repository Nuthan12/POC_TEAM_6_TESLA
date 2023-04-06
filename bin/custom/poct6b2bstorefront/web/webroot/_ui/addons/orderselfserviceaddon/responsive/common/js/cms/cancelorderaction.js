ACC.cancelorderaction = {
		
    _autoload: [
	    ["bindToCancelCompleteOrderButton", $(".js-cancel-complete-order-link").length != 0],
	    "bindToCancelEntryQuantityInput",
        "bindToCancelEntryQuantityFocusOut"
    ],

    bindToCancelCompleteOrderButton : function() {
        $(document).on('click', '.js-cancel-complete-order-link', function(event) {
            event.preventDefault();
            $.each( $('[id^="item_quantity_"]'), function() {
                var entryNumber = $(this).attr('id').split("item_quantity_")[1];
                $('[name^="cancelEntryQuantityMap[' + entryNumber + ']"]').val($('#item_quantity_' + entryNumber).val());
            });
            ACC.cancelorderaction.disableEnableCancelSubmit();
        });
    },

    bindToCancelEntryQuantityInput : function() {
        $('input[id^="cancelEntryQuantityMap"]').keypress(function(e) {
            if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                return false;
            }
        });		
    },

    bindToCancelEntryQuantityFocusOut : function() {
        $('[name^="cancelEntryQuantityMap"]').focusout(function(field) {
            var quantity = this.value.match("[0-9]+");
            if (quantity == null) {
                this.value = 0;
            } else {
                this.value = parseInt(quantity);
            }
            var index = this.id.replace("cancelEntryQuantityMap", "");
            if (parseInt($('#item_quantity_' + index).val()) < parseInt(this.value)) {
                this.value = $('#item_quantity_' + index).val();
            }
            $('[name^="cancelEntryQuantityMap[' + index + ']"]').val(this.value)
            
            ACC.cancelorderaction.disableEnableCancelSubmit();
        });
    },

    //Enable submit button in case some value is more than zero.
    disableEnableCancelSubmit: function() {
        var submitDisabled = true;
        $.each( $('[id^="item_quantity_"]'), function() {
            var entryNumber = $(this).attr('id').split("item_quantity_")[1];

            if (parseInt($('[name^="cancelEntryQuantityMap[' + entryNumber + ']"]').val()) > 0 ) {
                submitDisabled = false;
            }
        });
        $("#cancelOrderButtonConfirmation").prop("disabled", submitDisabled);
    }

}
