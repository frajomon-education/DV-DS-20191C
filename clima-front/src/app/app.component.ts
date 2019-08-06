import { Component } from '@angular/core';
import { StateService } from './service/state.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor (private state: StateService) {}

  ngOnInit(): void {
    let self = this;
    navigator.geolocation.getCurrentPosition(function(posicion){
      let coords = {
        latitud: posicion.coords.latitude,
        longitud: posicion.coords.longitude
      }
      self.state.updateCoordenadas(coords);
    })
  }
}
