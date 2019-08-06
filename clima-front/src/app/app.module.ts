import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { NgxSpinnerModule } from "ngx-spinner";
import { AppComponent } from './app.component';
import { ClimaComponent } from './components/clima/clima.component';
import { PronosticoComponent } from './components/pronostico/pronostico.component';
import { EstadisticasComponent } from './components/estadisticas/estadisticas.component';
import { CapitalizeFirstPipe } from './util/CapitalizeFirst.pipe';
import { GraficoComponent } from './components/grafico/grafico.component';
import { ChartsModule, WavesModule } from 'angular-bootstrap-md'

@NgModule({
  declarations: [
    AppComponent,
    ClimaComponent,
    PronosticoComponent,
    EstadisticasComponent,
    CapitalizeFirstPipe,
    GraficoComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    NgxSpinnerModule,
    ChartsModule,
    WavesModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
