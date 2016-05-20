import {Component} from '@angular/core';
import {Router, ROUTER_DIRECTIVES} from '@angular/router';

@Component({
    selector: "toolbarExplorer",
    templateUrl: "app/html/toolbarExplorer.html",
    directives: [ROUTER_DIRECTIVES]
})

export class ToolbarExplorer {

    constructor(private router:Router){
        
    }
}