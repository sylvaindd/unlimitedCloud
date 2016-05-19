import {Component} from '@angular/core';
import { Router, ROUTER_DIRECTIVES, Routes } from '@angular/router';
import {App} from "./App";
import {FirstPage} from "./FirstPage";

@Component({
    selector: "mainPage",
    template: `
<router-outlet></router-outlet>`,
    directives: [ROUTER_DIRECTIVES]
})

@Routes([
    {path: '/', component: FirstPage},
    {path: '/App', component: App}
])

export class MainPage {

    constructor(private router: Router) {
    }

    onSubmit() {
        this.router.navigate(['/App']);
    }
}