import {bootstrap} from '@angular/platform-browser-dynamic'
import {HTTP_PROVIDERS} from '@angular/http'; // Dependencies for HTTP service
import 'rxjs/Rx'; // For using methods on observables
import {App} from "./App";
import {Connection} from "./Connection";

bootstrap(App, [HTTP_PROVIDERS]);
bootstrap(Connection, [HTTP_PROVIDERS]);

