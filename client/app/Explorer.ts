import { Component } from '@angular/core';
import {Drawer} from "./Drawer";

@Component({
    selector: "explorer",
    templateUrl: "app/explorer.html",
    directives:[Drawer]
})
export class Explorer {

    constructor(){


    }
}