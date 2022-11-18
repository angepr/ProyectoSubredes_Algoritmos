/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author jaime
 */
public class Ip_Ms {
    //Variables
    private int Ip[];
    private final int[] tipoIp = {126,191,223};
    private final String[] tipoIpSArray = {"tipo A","tipo B","tipo C"};
    //cambio ip privada/publica
    private final int[] ipPrivadaArray = {10,172,192};
    
    //Arrays de la Máscara de subred
    private int nuevoNumN;
    private int[] nuevaMs = new int[4];        
    private final int[] nuevaMsA = {255,0,0,0};
    private final int[] nuevaMsB = {255,255,0,0};
    private final int[] nuevaMsC = {255,255,255,0};
    
    private String tipoIpS;
    private int numSaltos;
    //cambio ip privada/publica
    private String ipPrivada;
    
    //constructor
    public Ip_Ms(int[] _Ip, int _nuevoNumN) {
        this.Ip=_Ip;
        this.nuevoNumN=_nuevoNumN;
        this.tipoIpS=tipoIpS();
        this.numSaltos = ultByteMs();
    }
    //Detectar el tipo de ip
    private String tipoIpS(){
        String tipoIpStr = "";
        for(int i=0; i<tipoIp.length;i++){
            if(Ip[0]<=tipoIp[i]){
                tipoIpStr=tipoIpSArray[i];
                //cambio ip privada/publica
                if(Ip[0]==ipPrivadaArray[0]||Ip[0]==ipPrivadaArray[1]||Ip[0]==ipPrivadaArray[2]){
                        this.ipPrivada="Privada";
                    }else{
                        this.ipPrivada="Pública";
                    }
                break;
            } 
        }
        return tipoIpStr;
    }    

    //Asignar nueva Mascara de subred segun el tipo de ip
    //Calculo del byte cambiado de la nueva Máscara de subred
    private int cambioByteMs (){
        int restaN = 8-nuevoNumN;
        int ultByteMs = 0;
        
        for (int i=7; i>=restaN;i--){
            ultByteMs += Math.pow(2, i);
        }
        return ultByteMs;
    }
    
    //Calculo del ultimo byte de la nueva Máscara de subred dependiendo del tipo de Ip
    //y el nro de saltos para subredes
    private int ultByteMs(){
        int nSaltos = 0;
        switch(tipoIpS){
        case "tipo A":
            this.nuevaMs = nuevaMsA;
            nuevaMs[1]=cambioByteMs();
            nSaltos = 256 - nuevaMs[1];
        break;
        case "tipo B":
            this.nuevaMs = nuevaMsB;
            nuevaMs[2]=cambioByteMs();
            nSaltos = 256 - nuevaMs[2];
        break;
        case "tipo C":
            this.nuevaMs = nuevaMsC;
            nuevaMs[3]=cambioByteMs();
            nSaltos = 256 - nuevaMs[3];
        break;
        }
        return nSaltos;
    }    
           
    
    //Metodos
    /*private void main(String[] args) {
        ultByteMs();
    }*/
    
    public String getTipoIpS() {
        return tipoIpS;
    }

    public int[] getNuevaMs() {
        return nuevaMs;
    }

    public int getNumSaltos() {
        return numSaltos;
    }

    public String getIpPrivada() {
        return ipPrivada;
    }
                
}
