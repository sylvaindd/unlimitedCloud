/// <reference path="../typings/jquery/jquery.d.ts" />

declare var jQuery: JQueryStatic;

export class Validation {
    static validateEmail(email) {
        let re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    }

    static validatePhone(phone){
        let re = /^\d{10}$/;
        return re.test(phone);
    }

    static errorInput(selector){
        jQuery("#"+selector).parent(".form-group").addClass("has-error");
        jQuery("#"+selector).parent(".form-group").find("span").show();
    }
    static noErrorInput(selector){
        jQuery("#"+selector).parent(".form-group").removeClass("has-error");
        jQuery("#"+selector).parent(".form-group").find("span").hide();
    }

    static errorConnection(message){
        jQuery("#connectionError").text(message);
        jQuery("#connectionError").css('display', 'block').slideDown(300);
    }

    static noErrorConnection(){
        jQuery("#connectionError").slideUp(300, function(){
            jQuery(this).text("");
            jQuery(this).hide();
        });
    }

    static errorRegister(message){
        jQuery("#registerError").text(message);
        jQuery("#registerError").css('display', 'block').slideDown(300);
    }

    static noErrorRegister(){
        jQuery("#registerError").slideUp(300, function(){
            jQuery(this).text("");
            jQuery(this).hide();
        });
    }
}