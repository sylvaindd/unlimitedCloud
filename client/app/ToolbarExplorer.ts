import {Component} from '@angular/core';
import {Router, ROUTER_DIRECTIVES} from '@angular/router';
import {WebSocketService} from "./services/WebSocketService";

@Component({
    selector: "toolbarExplorer",
    templateUrl: "app/html/toolbarExplorer.html",
    directives: [ROUTER_DIRECTIVES]
})

export class ToolbarExplorer {
    nameFolder:String;
    constructor(private router:Router, private webSocketService:WebSocketService){
        
    }

    createFolder()
    {
        this.webSocketService.mkdir(this.nameFolder);
        console.log(this.nameFolder);
        this.nameFolder = "";
        this.router.navigate(['/App']);
    }
}