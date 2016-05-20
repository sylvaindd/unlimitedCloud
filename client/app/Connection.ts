import {Component} from '@angular/core';
/// <reference path="./models/jquery.d.ts" />
import {Router} from '@angular/router';
import {Validation} from "./utils/Utils";
import {APIService} from "./services/APIService";
import {ContextContainer} from "./utils/ContextContainer";

declare var jQuery:JQueryStatic;

@Component({
    selector: "connection",
    templateUrl: "app/html/connection.html",
    providers: [APIService],
})

export class Connection {

    password:String;
    mailOrUsername:String;

    constructor(private apiService:APIService, private router:Router, private contextContainer: ContextContainer) {
        this.password = "";
        this.mailOrUsername = "";
    }

    onSubmit() {
        let valid = true;

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

        let callback = function(data){
            console.log(data.succes);
            if(data.succes){
                Validation.noErrorConnection();
                this.contextContainer.token = data.token;
                this.router.navigate(['/App']);
            }else{
                Validation.errorConnection(data.message);
            }
        }.bind(this);

        this.apiService.authent(this.mailOrUsername, this.password, callback);
        this.router.navigate(['/App']);
    }
}
