import {Component} from '@angular/core';
import {Router, ROUTER_DIRECTIVES} from '@angular/router';

@Component({
    selector: "upload",
    templateUrl: "app/html/upload.html",
    directives: [ROUTER_DIRECTIVES]
})

export class Upload {

    constructor(){

    }

    upload(){
        console.log("Upload in operation");
    }
}