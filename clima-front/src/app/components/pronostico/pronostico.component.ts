import { Component, OnInit } from '@angular/core';
import { Clima } from 'src/app/model/clima.model';
import { ApiService } from 'src/app/service/api.service';
import { StateService } from 'src/app/service/state.service';
import { ClimaUtil } from 'src/app/util/ClimaUtil';
import { NgxSpinnerService } from 'ngx-spinner';
import { interval } from 'rxjs';
import { skip } from 'rxjs/operators';

@Component({
  selector: 'pronostico',
  templateUrl: './pronostico.component.html',
  styleUrls: ['./pronostico.component.css']
})
export class PronosticoComponent implements OnInit {

  pronostico: Clima[];
  getDiaSemana = ClimaUtil.getDiaSemana;

  constructor(private api: ApiService, private state: StateService, private spinner: NgxSpinnerService) { }

  ngOnInit(): void { 
    let self = this;
    this.spinner.show('pronostico');
    this.state.coordenadas$.subscribe(function(coords){
      self.api.getPronostico(coords).subscribe(function(pronostico: Clima[]){
        self.pronostico = pronostico;
        self.spinner.hide('pronostico');
      })

      self.observarClima();
    })  
  }


  observarClima(){
    let self = this;
    const intervalo = interval(10000);

    self.state.coordenadas$.pipe(skip(1)).subscribe(function(coords){
      intervalo.subscribe(function(val){
        self.api.getPronostico(coords).subscribe(function(pronostico: Clima[]){
          console.log(val)
          if (pronostico != self.pronostico){
            self.pronostico = pronostico;
          }
        })
      })
    })
  }

}
