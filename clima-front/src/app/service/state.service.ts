import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { AppConstants } from '../util/AppConstants';



@Injectable({
    providedIn: 'root'
  })
  export class StateService {
  
    constructor() { }

    private coordenadasSource = new BehaviorSubject<{}>(AppConstants.coordsDefault);
    coordenadas$ = this.coordenadasSource.asObservable();

    public updateCoordenadas (coords: any){
        this.coordenadasSource.next(coords);
    }

}