import {Component} from '@angular/core';
/// <reference path="typings/jquery/jquery.d.ts" />
import {Router} from '@angular/router';
import {Validation} from "./utils/Utils";
import {APIService} from "./services/APIService";

declare var jQuery:JQueryStatic;

@Component({
    selector: "connection",
    templateUrl: "app/html/connection.html",
    providers: [APIService],
})

export class Connection {

    password:String;
    mailOrUsername:String;

    constructor(private apiService:APIService, private router:Router) {
        this.password = "";
        this.mailOrUsername = "";
    }

    onSubmit() {
        let valid = true;

        console.log("Submit ! " + this.mailOrUsername + " - " + this.password);
        if (this.mailOrUsername.length < 6) {
            valid = false;
            Validation.errorInput("mailOrUsername");
        } else {
            Validation.noErrorInput("mailOrUsername")
        }

        if (this.password.length < 6) {
            valid = false;
            Validation.errorInput("password");
        } else {
            Validation.noErrorInput("password")
        }

        if (!valid)
            return;

        this.router.navigate(['/App']);

    }
}
