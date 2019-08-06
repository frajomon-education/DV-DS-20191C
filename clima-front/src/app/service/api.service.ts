import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams, HttpErrorResponse} from '@angular/common/http';
import { Clima } from '../model/clima.model';
import { environment } from '../../environments/environment';


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  }),
  params: new HttpParams()
};


@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  urlBase: string = environment.apiUrl;

  public getClimaActual(coords: any){
    let url: string = this.urlBase + '/clima';
 
    let request = JSON.stringify(coords);
    return this.http.post<Clima>(url, request, {headers: httpOptions.headers});
  }

  public getPronostico(coords: any){
    let url: string =  this.urlBase + '/pronostico';
 
    let request = JSON.stringify(coords);
    return this.http.post<Clima[]>(url, request, {headers: httpOptions.headers});
  }

  public getEstadisticas(){
    let url: string =  this.urlBase + '/estadisticas';

    return this.http.get<Clima[]>(url, {headers: httpOptions.headers});
  }
}
