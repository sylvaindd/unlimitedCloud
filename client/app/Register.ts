import {Component} from '@angular/core';
/// <reference path="typings/jquery/jquery.d.ts" />
import {Validation} from "./utils/Utils";
import {APIService} from "./services/APIService";
import {Router} from '@angular/router';
declare var jQuery: JQueryStatic;

@Component({
    selector: "register",
    templateUrl: "app/html/register.html",
    providers: [APIService],
})
export class Register {

    mail:String;
    username:String;
    password:String;
    repassword:String;

    constructor(private apiService:APIService, private router:Router) {
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
        if (this.username.length >= 6) {
            Validation.noErrorInput("username")
        } else {
            valid = false;
            Validation.errorInput("username");
        }

        if (this.password.length >= 6 && this.password == this.repassword) {
            Validation.noErrorInput("ppassword")
        } else {
            valid = false;
            Validation.errorInput("ppassword");
        }

        if (this.repassword.length >= 6 && this.password == this.repassword) {
            Validation.noErrorInput("repassword")
        } else {
            valid = false;
            Validation.errorInput("repassword");
        }

        if(!valid)
            return;

        this.apiService.register(this.username, this.mail, this.password);
    }
}
