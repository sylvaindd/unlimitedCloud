import {bootstrap} from '@angular/platform-browser-dynamic'
import {HTTP_PROVIDERS} from '@angular/http'; // Dependencies for HTTP service
import 'rxjs/Rx'; // For using methods on observables
import {PokedexComponent} from "./PokedexComponent";

bootstrap(PokedexComponent, [HTTP_PROVIDERS]);

