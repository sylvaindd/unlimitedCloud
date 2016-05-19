/// <reference path="../typings/jquery/jquery.d.ts" />

declare var jQuery: JQueryStatic;

export class Validation {
    static validateEmail(email) {
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    }
    static errorInput(selector){
        jQuery("#"+selector).parent(".form-group").addClass("has-error");
        jQuery("#"+selector).parent(".form-group").find("span").show();
    }
    static noErrorInput(selector){
        jQuery("#"+selector).parent(".form-group").removeClass("has-error");
        jQuery("#"+selector).parent(".form-group").find("span").hide();
    }
}