import { Injectable } from '@angular/core';
import { isNull } from '@angular/compiler/src/output/output_ast';


/*
 * The Util Service provides some basic parsing functionality
 * and a select suite of tools to assist in development
 * and presentation.
 */


@Injectable({
  providedIn: 'root'
})
export class UtilService {

  constructor() { }

  //Takes a Json Object and displays it within a given HTMLElement.
  DisplayJson(JsonObj:any, ContainerElement:HTMLElement, depth:number)
  {
    //console.log(typeof JsonObj)
    if(typeof JsonObj == "object" && JsonObj != null && depth != 0)
    {
      let ul1 = document.createElement('ul');
        //For each element in the object
      for(let element in JsonObj)
      {
        let li1= document.createElement('li');
        li1.append(element+" =");
        // console.log(element + "  -  " + JsonObj[element]);
        this.DisplayJson(JsonObj[element], li1, depth - 1);
        ul1.appendChild(li1);
      }
      ContainerElement.appendChild(ul1);	
    }
    else
    {
      ContainerElement.append(JsonObj);
    }
  }
}
