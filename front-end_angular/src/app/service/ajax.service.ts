import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LegoSet } from '../model/legoSet';

@Injectable({
  providedIn: 'root',
})
export class AjaxService {
  headers = new HttpHeaders()
    .set('Accept', 'application/json')
    .set('Authorization', 'key 12e18d8e7af435397122373eed699382');
  baseURL: string;

  constructor(private ajax: HttpClient) {
    this.baseURL = 'https://rebrickable.com/api/v3/lego/sets/?search=';
  }

  searchAPI(set: LegoSet): Observable<any> {
    let words: string[] = [];
    words = set.name.split(' ');
    let URL: string = '';
    if (words.length === 1) {
      URL = `${this.baseURL}${words[0]}`;
    } else if (words.length === 2) {
      URL = `${this.baseURL}${words[0]}%20${words[1]}`;
    } else {
      URL = `${this.baseURL}${words[0]}%20${words[1]}%20${words[2]}`;
    }
    return this.ajax.get<any>(URL, {
      headers: this.headers,
    });
  }
}
