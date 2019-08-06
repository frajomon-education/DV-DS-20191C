export class ClimaUtil {

    public static getDiaSemana(fechaString: string) {
        let fecha = new Date(fechaString)
        let dia = fecha.getDay() + 1;
        switch (dia) {
            case 0: 
                return 'Domingo';
            case 1: 
                return 'Lunes';
            case 2: 
                return 'Martes';
            case 3: 
                return 'Miercoles';
            case 4: 
                return 'Jueves';
            case 5:
                return 'Viernes';
            case 6:
                return 'Sabado';
            default:
                return '';
        }
    }
    
}