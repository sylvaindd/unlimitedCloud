import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {ContextContainer} from "./utils/ContextContainer";
import {WebSocketService} from "./services/WebSocketService";

@Component({
    selector: "settings",
    templateUrl: "app/html/settings.html"
})

export class Settings {

    constructor(private contextContainer: ContextContainer, private router:Router, private webSocketService:WebSocketService) {

    }

    onAddGoogleAccount()
    {

        let url = "http://localhost:8080/lebonnuage/askDriveauthorise?token_ultimate_cloud="+this.contextContainer.token;
        let windowObjectReference;

        windowObjectReference = window.open(url,
            'DescriptiveWindowName',
            'width=420,height=230,resizable,scrollbars=yes,status=0,toolbar=0,menubar=0,location=0'
        );
    }

    onAddDropboxAccount()
    {
        let url = "http://localhost:8080/lebonnuage/askauthorise?token_ultimate_cloud="+this.contextContainer.token;
        let windowObjectReference;

        windowObjectReference = window.open(url,
            'DescriptiveWindowName',
            'width=420,height=230,resizable,scrollbars=yes,status=0,toolbar=0,menubar=0,location=0'
        );
    }
}