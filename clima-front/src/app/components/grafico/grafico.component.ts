import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'grafico-linea',
  templateUrl: './grafico.component.html',
  styleUrls: ['./grafico.component.css']
})

export class GraficoComponent {
  @Input('chartLabels') chartLabels: Array<any>;
  @Input('chartDatasets') chartDatasets: Array<any>;

  constructor() { }

  public chartType: string = 'line';

  public chartColors: Array<any> = [
    {
      // backgroundColor: 'rgba(245, 176, 65, .2)',
      borderColor: 'rgba(245, 176, 65, .7)',
      borderWidth: 2,
    },
    {
      // backgroundColor: 'rgba(0, 137, 132, .2)',
      borderColor: 'rgba(0, 10, 130, .7)',
      borderWidth: 2,
    },
    {
      // backgroundColor: 'rgba(0, 137, 132, .2)',
      borderColor: 'rgba(192, 57, 43, .7)',
      borderWidth: 2,
    }
  ];

  public chartOptions: any = {
    responsive: true
  };
  public chartClicked(e: any): void { }
  public chartHovered(e: any): void { }
}