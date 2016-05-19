import {bootstrap} from '@angular/platform-browser-dynamic'
import {HTTP_PROVIDERS} from '@angular/http';
import { ROUTER_PROVIDERS } from 'angular2/router';
// Dependencies for HTTP service
import 'rxjs/Rx'; // For using methods on observables
import {App} from "./App";
import {Connection} from "./Connection";

bootstrap(App, [ROUTER_PROVIDERS]);
bootstrap(App, [HTTP_PROVIDERS]);
bootstrap(Connection, [HTTP_PROVIDERS]);

