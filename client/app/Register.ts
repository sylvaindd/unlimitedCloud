import {Component} from '@angular/core';
/// <reference path="typings/jquery/jquery.d.ts" />
import {Validation} from "./utils/Utils";


declare var jQuery: JQueryStatic;

@Component({
    selector: "register",
    templateUrl: "app/html/register.html"
})
export class Register {

    mail:String;
    username:String;
    password:String;
    repassword:String;

    constructor() {
        this.mail = "";
        this.username = "";
        this.password = "";
        this.repassword = "";
    }

    onSubmit(){
        console.log("Submit ! " + this.username + " - " + this.mail + this.password + " - " + this.repassword);
        let valid = true;
        if (Validation.validateEmail(this.mail)) {
            Validation.noErrorInput("mail")
        } else {
            valid = false;
            Validation.errorInput("mail");
        }
        if (this.username.length > 6) {
            Validation.noErrorInput("username")
        } else {
            valid = false;
            Validation.errorInput("username");
        }

        console.log("Mon if : " +(this.password.length > 6 && this.password == this.repassword));
        if (this.password.length > 6 && this.password == this.repassword) {
            Validation.noErrorInput("ppassword")
        } else {
            valid = false;
            Validation.errorInput("ppassword");
        }

        if (this.repassword.length > 6 && this.password == this.repassword) {
            Validation.noErrorInput("repassword")
        } else {
            valid = false;
            Validation.errorInput("repassword");
        }

        if(!valid)
            return;
    }
}
