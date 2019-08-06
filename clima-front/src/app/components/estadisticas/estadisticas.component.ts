import { Component, OnInit } from '@angular/core';
import { StateService } from 'src/app/service/state.service';
import { ApiService } from 'src/app/service/api.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { Clima } from 'src/app/model/clima.model';
import { ClimaUtil } from 'src/app/util/ClimaUtil';

@Component({
  selector: 'estadisticas',
  templateUrl: './estadisticas.component.html',
  styleUrls: ['./estadisticas.component.css']
})
export class EstadisticasComponent implements OnInit {

  constructor(private api: ApiService, private state: StateService, private spinner: NgxSpinnerService) { }

  chartLabels:  Array<any>;
  chartDatasets: Array<any>;

  ngOnInit() {
    let self = this;
      self.api.getEstadisticas().subscribe(function(estadisticas: Clima[]){
        self.formatearEstadisticas(estadisticas);
    })
  }

  formatearEstadisticas(estadisticas: Clima[]){

    let tempProm: Array<any> = [];
    let tempMin: Array<any> = [];
    let tempMax: Array<any> = [];
    let chartLabels: Array<any> = [];

    estadisticas.forEach(function(item){
      tempProm.push(item.temperatura);
      tempMin.push(item.temperaturaMinima);
      tempMax.push(item.temperaturaMaxima);
      chartLabels.push(ClimaUtil.getDiaSemana(item.fecha));
    })

    let chartDatasets: Array<any> = [
      { data: tempMax, label: 'Temperatura maxima' },
      { data: tempProm, label: 'Temperatura promedio' },
      { data: tempMin, label: 'Temperatura minima' }
    ];

    this.chartLabels = chartLabels;
    this.chartDatasets = chartDatasets;

  }

}
