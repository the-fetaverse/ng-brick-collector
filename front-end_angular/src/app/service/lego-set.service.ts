import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LegoSet } from '../model/legoSet';

@Injectable({
  providedIn: 'root',
})
export class LegoSetService {
  baseURL: string;

  constructor(private http: HttpClient) {
    this.baseURL = 'http://localhost:8080/brick-collector-webapp/lego-set';
  }

  getAllSets(): Observable<LegoSet[]> {
    return this.http.get<LegoSet[]>(this.baseURL);
  }

  addSet(set: LegoSet): Observable<LegoSet> {
    return this.http.post<LegoSet>(this.baseURL, set);
  }

  updateSet(set: LegoSet): Observable<LegoSet> {
    return this.http.put<LegoSet>(`${this.baseURL}/${set.id}`, set);
  }

  deleteSet(set: LegoSet): Observable<LegoSet> {
    return this.http.delete<LegoSet>(`${this.baseURL}/${set.id}`);
  }
}
