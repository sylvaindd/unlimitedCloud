import {Component} from '@angular/core';
import {Tabs} from './Tabs';

@Component({
    selector: 'tab',
    inputs: [
        'title:tab-title'
    ],
    templateUrl: "app/html/tab.html"
})
export class Tab {
    title: string;
    active: boolean;

    constructor(tabs:Tabs) {
        tabs.addTab(this);
    }
}