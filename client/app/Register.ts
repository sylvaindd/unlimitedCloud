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
    phone:String;
    password:String;
    repassword:String;

    constructor(private apiService:APIService, private router:Router) {
        this.mail = "";
        this.username = "";
        this.password = "";
        this.repassword = "";
        this.phone = "";
    }

    onSubmit(){
        let valid = true;
        if (Validation.validateEmail(this.mail)) {
            Validation.noErrorInput("mail")
        } else {
            valid = false;
            Validation.errorInput("mail");
        }
        if (Validation.validatePhone(this.phone)) {
            Validation.noErrorInput("phone")
        } else {
            valid = false;
            Validation.errorInput("phone");
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

        let callback = function(data){
            console.log(data.succes);
        };
        
        this.apiService.register(this.username, this.mail, this.phone, this.password, callback);
    }
}
