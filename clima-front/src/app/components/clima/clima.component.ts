import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/service/api.service';
import { Clima } from 'src/app/model/clima.model';
import { StateService } from 'src/app/service/state.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { interval } from 'rxjs';
import { skip } from 'rxjs/operators';

@Component({
  selector: 'clima',
  templateUrl: './clima.component.html',
  styleUrls: ['./clima.component.css']
})
export class ClimaComponent implements OnInit {
  
  clima: Clima;

  constructor(private api: ApiService, private state: StateService, private spinner: NgxSpinnerService) {}

  ngOnInit(): void { 
    let self = this;
    this.spinner.show();
    this.state.coordenadas$.subscribe(function(coords){
      self.api.getClimaActual(coords).subscribe(function(clima: Clima){
        self.clima = clima;
        self.spinner.hide();
      })
    
    self.observarClima();
    }) 
  }

  observarClima(){
    let self = this;
    const intervalo = interval(10000);

    this.state.coordenadas$.pipe(skip(1)).subscribe(function(coords){
      intervalo.subscribe(function(val){
        self.api.getClimaActual(coords).subscribe(function(clima: Clima){
          if ((clima.temperatura != self.clima.temperatura) || (clima.estado != self.clima.estado)){
              self.clima = clima;
          }
        })
      })
    })
  }

}
